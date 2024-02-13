package com.zzaug.flyway.datasource.support;

import com.zzaug.flyway.datasource.SecurityDataSource;
import java.util.List;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.stereotype.Component;

@Component
public class DataSourceFilter {

	public <T extends DataSource> DataSource filter(Class<T> clazz, List<DataSource> dataSources) {
		if (Objects.isNull(dataSources) || dataSources.isEmpty()) {
			return null;
		}
		for (DataSource dataSource : dataSources) {
			if (dataSource.getClass().isAssignableFrom(clazz)) {
				return dataSource;
			}
		}
		// 기존의 DataSource 만으로 저장한 경우에도 사용할 수 있도록 추가
		for (DataSource dataSource : dataSources) {
			if (!dataSource.getClass().isAssignableFrom(SecurityDataSource.class)) {
				return dataSource;
			}
		}
		return null;
	}
}
