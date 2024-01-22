package com.zzaug.web.filter;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.jboss.logging.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/** API 요청시 가장 먼저 거치며 MDC를 활용하여 로깅을 수행하는 필터 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MDCFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Object traceId = request.getAttribute("X-ZZAUG-ID");
		if (Objects.isNull(traceId)) {
			traceId = UUID.randomUUID().toString();
		} else {
			traceId = traceId.toString();
		}
		MDC.put("traceId", traceId);
		MDC.put("referer", ((RequestFacade) request).getHeader("referer"));
		MDC.put("userAgent", ((RequestFacade) request).getHeader("user-agent"));
		MDC.put("startTime", System.currentTimeMillis());
		log.info(
				"{} -> [{}] uri : {}",
				request.getRemoteAddr(),
				((RequestFacade) request).getMethod(),
				((RequestFacade) request).getRequestURI());
		chain.doFilter(request, response);
		MDC.put("endTime", System.currentTimeMillis());
		MDC.put(
				"elapsedTime",
				System.currentTimeMillis() - Long.parseLong(MDC.get("startTime").toString()));
		log.info("requestLogging: {}", MDC.getMap());
		MDC.clear();
	}
}
