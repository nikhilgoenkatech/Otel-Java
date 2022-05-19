package com.dynatrace.sample.databases;

import java.net.URISyntaxException;
import java.sql.SQLException;

public abstract class Database {
	private String dbName;
	private String url;
	private String userName;
	private String password;
	private String driver;
	private int port;
	private boolean createCalled;

	Database() {
		this.dbName = "";
		this.url = "";
		this.userName = "";
		this.password = "";
		this.port = 0;
		this.driver = "";
		this.createCalled = false;
	}
	public String getDriver() {
		if(driver.isEmpty()) {
			return "not found";
		} else {
			return driver;
		}
	}

	public void setCreateCalledTrue() {
		createCalled = true;
	}

	public boolean isCreateCalled() {
		return createCalled;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDbName() {
		return dbName;
	}

	public String getUrl() {
		return url;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public int getPort() {
		return port;
	}

	public abstract String makeCRUDCalls() throws SQLException, URISyntaxException;
}
