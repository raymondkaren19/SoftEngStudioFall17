
package edu.brandeis.spring.sql;

// Source for original file:
// Test connection parameters and availability of MySQL.
// @author Doug Saylor
// https://gist.github.com/drognisep/2795aaaaa4dca52ba37bed97bde14bc6

import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static junit.framework.Assert.fail;

// Test the local conection of the mysql instance
public class MySQLConnectionTest extends TestCase  {

	protected String host = "localhost";
	protected String port = "3306";
	protected String database = "InventoryDB";
	protected String username = "root";
	protected String password = "mysql";
	protected String connectionParameters = "?useSSL=false";
	protected Connection con;
	protected String printColWidth = "30";
	
        public void test() {
            // Only run on local machine
            if(System.getenv("CI") == null)
            {
             initAndConnect();   
            }
	}

	protected void initAndConnect() {
		String connectionString = String.format("jdbc:mysql://%s:%s/%s%s", host, port, database, connectionParameters);
		
		try {
			System.out.printf("%-"+printColWidth+"s", "Initializing driver class... ");
			// Init DriverClass
                        Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success");
			System.out.printf("%-"+printColWidth+"s", "Establishing connection... ");
			// Connect to MySQL
                        con = DriverManager.getConnection(connectionString, username, password);
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
}