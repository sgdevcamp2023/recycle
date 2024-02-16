package com.zzaug.member.web.controller.v1.member;

import com.zzaug.member.domain.dto.member.CheckDuplicationUseCaseRequest;
import com.zzaug.member.domain.dto.member.CheckDuplicationUseCaseResponse;
import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.CheckEmailAuthUseCaseResponse;
import com.zzaug.member.domain.dto.member.EmailAuthUseCaseRequest;
import com.zzaug.member.domain.dto.member.EmailAuthUseCaseResponse;
import com.zzaug.member.domain.dto.member.SuccessCheckEmailAuthUseCaseResponse;
import com.zzaug.member.domain.usecase.member.CheckDuplicationUseCase;
import com.zzaug.member.domain.usecase.member.CheckEmailAuthUseCase;
import com.zzaug.member.domain.usecase.member.EmailAuthUseCase;
import com.zzaug.member.web.dto.member.CheckEmailAuthRequest;
import com.zzaug.member.web.dto.validator.Certification;
import com.zzaug.security.authentication.token.TokenUserDetails;
import com.zzaug.security.filter.token.AccessTokenResolver;
import com.zzaug.web.support.ApiResponse;
import com.zzaug.web.support.ApiResponseGenerator;
import com.zzaug.web.support.CookieGenerator;
import com.zzaug.web.support.CookieSameSite;
import com.zzaug.web.support.MessageCode;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/v1/members/check")
@RequiredArgsConstructor
public class MemberCheckController {

	private static final String SESSION_ID_KEY = "sessionId";
	private static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";

	private final CookieGenerator cookieGenerator;

	private final CheckDuplicationUseCase checkDuplicationUseCase;
	private final EmailAuthUseCase emailAuthUseCase;
	private final CheckEmailAuthUseCase checkEmailAuthUseCase;

	@GetMapping()
	public ApiResponse<ApiResponse.SuccessBody<CheckDuplicationUseCaseResponse>> check(
			@Certification @RequestParam(value = "certification", required = true) String certification) {
		CheckDuplicationUseCaseRequest useCaseRequest =
				CheckDuplicationUseCaseRequest.builder().certification(certification).build();
		CheckDuplicationUseCaseResponse response = checkDuplicationUseCase.execute(useCaseRequest);
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@GetMapping("/email")
	public ApiResponse<ApiResponse.SuccessBody<EmailAuthUseCaseResponse>> emailAuth(
			HttpServletRequest servletRequest,
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@Email @RequestParam(value = "email", required = true) String email,
			@NotNull @NotEmpty @RequestParam(value = "nonce", required = true) String nonce) {
		Long memberId = Long.valueOf(userDetails.getId());
		HttpSession session = servletRequest.getSession();
		session.setAttribute(SESSION_ID_KEY, session.getId());
		EmailAuthUseCaseRequest useCaseRequest =
				EmailAuthUseCaseRequest.builder()
						.memberId(memberId)
						.sessionId(session.getId())
						.email(email)
						.nonce(nonce)
						.build();
		EmailAuthUseCaseResponse response = emailAuthUseCase.execute(useCaseRequest);
		return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
	}

	@PostMapping("/email")
	public ApiResponse<ApiResponse.SuccessBody<CheckEmailAuthUseCaseResponse>> checkEmailAuth(
			@CookieValue(REFRESH_TOKEN_COOKIE_NAME) String refreshTokenValue,
			@AuthenticationPrincipal TokenUserDetails userDetails,
			@Valid @RequestBody CheckEmailAuthRequest request,
			HttpServletResponse httpServletResponse,
			HttpServletRequest httpServletRequest) {
		Long memberId = Long.valueOf(userDetails.getId());
		String authorization = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
		String accessTokenValue = AccessTokenResolver.resolve(authorization);
		HttpSession session = httpServletRequest.getSession();
		String sessionId = (String) session.getAttribute(SESSION_ID_KEY);
		CheckEmailAuthUseCaseRequest useCaseRequest =
				CheckEmailAuthUseCaseRequest.builder()
						.memberId(memberId)
						.code(request.getCode())
						.sessionId(sessionId)
						.email(request.getEmail())
						.nonce(request.getNonce())
						.accessToken(accessTokenValue)
						.refreshToken(refreshTokenValue)
						.build();
		CheckEmailAuthUseCaseResponse response = checkEmailAuthUseCase.execute(useCaseRequest);
		if (!(response instanceof SuccessCheckEmailAuthUseCaseResponse)) {
			return ApiResponseGenerator.success(response, HttpStatus.OK, MessageCode.SUCCESS);
		}
		SuccessCheckEmailAuthUseCaseResponse successResponse =
				(SuccessCheckEmailAuthUseCaseResponse) response;
		ResponseCookie refreshToken =
				cookieGenerator.createCookie(
						CookieSameSite.LAX, REFRESH_TOKEN_COOKIE_NAME, successResponse.getRefreshToken());
		httpServletResponse.addHeader(HttpHeaders.SET_COOKIE, refreshToken.toString());
		session.removeAttribute(SESSION_ID_KEY);
		return ApiResponseGenerator.success(successResponse, HttpStatus.OK, MessageCode.SUCCESS);
	}
}
