package com.thrive.common.testdata.utils;

import com.thrive.ui.core.Config;

public class DBConnection {
	
	private String dbServer;
	private int dbPort;
	private String dbName;
	private String dbUsername;
	private String dbPassword;
	private String dbDriver;
	
	public DBConnection(String dbDriver, String dbServer, int dbPort, String dbName, String dbUsername, String dbPassword) {
		super();
		this.dbServer = dbServer;
		this.dbPort = dbPort;
		this.dbName = dbName;
		this.dbUsername = dbUsername;
		this.dbPassword = dbPassword;
		this.dbDriver = dbDriver;
	}
	
	public DBConnection() {
		this.dbServer = Config.getDBHost();
		this.dbPort = Config.getDBPort();
		this.dbName = Config.getDBName();
		this.dbUsername = Config.getDBUsername();
		this.dbPassword = Config.getDBPassword();
		this.dbDriver = Config.getDBDriver();
	}
	
	public DBConnection getDbConnectionDetails() {
		
		this.dbServer = Config.getDBHost();
		this.dbPort = Config.getDBPort();
		this.dbName = Config.getDBName();
		this.dbUsername = Config.getDBUsername();
		this.dbPassword = Config.getDBPassword();
		
		return this;
	}
	
	public String getDbServer() {
		return dbServer;
	}
	public void setDbServer(String dbServer) {
		this.dbServer = dbServer;
	}
	public int getDbPort() {
		return dbPort;
	}
	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getDbUsername() {
		return dbUsername;
	}
	public void setDbUsername(String dbUsername) {
		this.dbUsername = dbUsername;
	}
	public String getDbPassword() {
		return dbPassword;
	}
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
	public String getDbDriver() {
		return dbDriver;
	}
	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}
	
}
	
	
	
	
