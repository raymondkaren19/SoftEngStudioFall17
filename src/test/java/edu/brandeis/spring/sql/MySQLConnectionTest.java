
package edu.brandeis.spring.sql;

// Source for original file:
// https://gist.github.com/drognisep/2795aaaaa4dca52ba37bed97bde14bc6

import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static junit.framework.Assert.fail;

/*
 * TEST ENVIRONMENT:
 *   Tested with MySQL 5.7 on localhost:3306 on Windows 10
 * 
 * REQUIRED DEPENDENCIES:
 *    MySQL Connector/J
 *     - mysql-connector-java-5.1.40-bin.jar
 */

/**
 * Test connection parameters and availability of MySQL.
 * @author Doug Saylor
 *
 */
public class MySQLConnectionTest extends TestCase  {
	/**
	 * Connector/J driver class [com.mysql.jdbc.Driver]. Per MySQL docs, may also be "org.gjt.mm.mysql.Driver" for backward compatibility with MM.MySQL, the predecessor of Connector/J.
	 * @see <a href="https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-reference-configuration-properties.html">MySQL docs reference</a>
	 */
	protected String driverClass = "com.mysql.jdbc.Driver";
	/**
	 * The host argument to connectionString [localhost].
	 */
	protected String host = "localhost";
	/**
	 * The port argument to connectionString [3306].
	 */
	protected String port = "3306";
	/**
	 * The database argument to connectionString [test].
	 */
	protected String database = "test";
	/**
	 * Second (username) argument to {@link DriverManager#getConnection(String, String, String)} [root].
	 */
	protected String username = "root";
	/**
	 * Third (password) argument to {@link DriverManager#getConnection(String, String, String)} [root].
	 */
	protected String password = "root";
	/**
	 * Parameters that will be passed to MySQL during connection
	 * [?useSSL=false].
	 * 
	 * useSSL=false : This is set because SSL is used by default, the following
	 * warning will print to console otherwise:
	 * 
	 * WARN: Establishing SSL connection without server's identity verification
	 * is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+
	 * requirements SSL connection must be established by default if explicit
	 * option isn't set. For compliance with existing applications not using SSL
	 * the verifyServerCertificate property is set to 'false'. You need either
	 * to explicitly disable SSL by setting useSSL=false, or set useSSL=true and
	 * provide truststore for server certificate verification.
	 */
	protected String connectionParameters = "?useSSL=false";
	/**
	 * String created to pass as the first argument to
	 * {@link DriverManager#getConnection(String, String, String)} [].
	 * 
	 * String will be initialized within
	 * {@link MySQLConnectionTest#initAndConnect()} to
	 * String.format("jdbc:mysql://%s:%s/%s%s", host, port, database,
	 * connectionParameters).
	 */
	protected String connectionString;
	protected Connection con;
	/**
	 * Print width of operation output column. Status column is not constrained.
	 */
	protected String printColWidth = "30";

	/**
	 * The only test decorated method in this class.
	 */
	public void test() {
		initAndConnect();
	}

	/**
	 * Perform connection attempt.
	 */
	protected void initAndConnect() {
		connectionString = String.format("jdbc:mysql://%s:%s/%s%s", host, port, database, connectionParameters);
		
		try {
			System.out.printf("%-"+printColWidth+"s", "Initializing driver class... ");
			initDriverClass();
			System.out.println("Success");
			System.out.printf("%-"+printColWidth+"s", "Establishing connection... ");
			connectToMySql();
			System.out.println("Success");
			System.out.printf("%-"+printColWidth+"s", "Closing connection... ");
			con.close();
			System.out.println("Success");
		} catch (ClassNotFoundException cnfx) {
			System.out.println("Error");
			System.out.flush();
			cnfx.printStackTrace();
			fail("Unable to locate driver class");
		} catch (SQLException sqlx) {
			System.out.println("Error");
			System.out.flush();
			sqlx.printStackTrace();
			fail("Error connecting to MySQL");
		}
	}

	/**
	 * Performs the method call to connect using all supplied arguments.
	 * @throws SQLException
	 */
	protected void connectToMySql() throws SQLException {
		con = DriverManager.getConnection(connectionString, username, password);
	}

	/**
	 * Initialize driver class.
	 * @throws ClassNotFoundException
	 */
	protected void initDriverClass() throws ClassNotFoundException {
		Class.forName(driverClass);
	}

}