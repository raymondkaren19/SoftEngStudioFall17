package edu.brandeis.spring.sql;

// Source for original file:
// Test connection parameters and availability of MySQL.
// @author Doug Saylor
// https://gist.github.com/drognisep/2795aaaaa4dca52ba37bed97bde14bc6
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.junit.Ignore;


// Test the local conection of the mysql instance
@Ignore
public class MySQLConnectionLocalhostTest extends TestCase {

    protected String port = "3306";
    protected String database = "InventoryDB";
    protected String connectionParameters = "?useSSL=false";
    protected Connection con;
    protected String printColWidth = "30";

    public void test() {
        if (System.getenv("CI") == null) {
            // These should match the password in the data source
            // While not ideal these are root:mysql
            // If you want to override these, you can use the following global env variables.
            // https://stackoverflow.com/questions/13046624/how-to-permanently-export-a-variable-in-linux
            //https://stackoverflow.com/questions/22502759/mac-os-x-10-9-setting-permanent-environment-variables
            // Alternatibly you can set some varibles in your IDE so you can use the IDE's terminal
            // https://stackoverflow.com/questions/11823233/how-to-set-environment-variable-in-netbeans

            String username = System.getenv("MYSQLUSER") == null ? "root" : System.getenv("MYSQLUSER");
            String password = System.getenv("MYSQLPASS") == null ? "mysql" : System.getenv("MYSQLPASS");
            initAndConnect(username, password, "localhost");
        }
    }

    protected void initAndConnect(String username, String password, String host) {

        String connectionString = String.format("jdbc:mysql://%s:%s/%s%s", host, port, database, connectionParameters);

        try {
            System.out.printf("%-" + printColWidth + "s", "Initializing driver class... ");
            // Init DriverClass
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Success");
            System.out.printf("%-" + printColWidth + "s", "Establishing connection... ");
            // Connect to MySQL
            con = DriverManager.getConnection(connectionString, username, password);
            System.out.println("Success");
            System.out.printf("%-" + printColWidth + "s", "Closing connection... ");
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
