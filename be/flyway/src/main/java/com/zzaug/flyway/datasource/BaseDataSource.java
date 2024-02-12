package com.zzaug.flyway.datasource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;

public abstract class BaseDataSource implements DataSource {

	private final DataSource source;

	public BaseDataSource(DataSource source) {
		this.source = source;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return source.getConnection();
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		return source.getConnection(username, password);
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		return source.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		source.setLogWriter(out);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		source.setLoginTimeout(seconds);
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return source.getLoginTimeout();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return source.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return source.isWrapperFor(iface);
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return source.getParentLogger();
	}
}
