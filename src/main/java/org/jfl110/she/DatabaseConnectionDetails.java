package org.jfl110.she;

/**
 * Details for connecting to a database.
 * 
 * @author JFL110
 */
public class DatabaseConnectionDetails {

	private final String hostName;
	private final String databaseName;
	private final String userName;
	private final String password;
	private final int port;

	public DatabaseConnectionDetails(String hostName, String databaseName, int port, String userName, String password) {
		this.hostName = hostName;
		this.databaseName = databaseName;
		this.port = port;
		this.userName = userName;
		this.password = password;
	}

	public String getHostName() {
		return hostName;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public int getPort() {
		return port;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}