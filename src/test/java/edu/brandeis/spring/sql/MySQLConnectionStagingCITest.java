package edu.brandeis.spring.sql;

// Source for original file:
// Test connection parameters and availability of MySQL.
// @author Doug Saylor
// https://gist.github.com/drognisep/2795aaaaa4dca52ba37bed97bde14bc6
// Test the Staging conection of the mysql instance
public class MySQLConnectionStagingCITest extends MySQLConnectionLocalhostTest {

    @Override
    public void test() {
        // Only run on CI machine
        if (System.getenv("CI") != null) {
            initAndConnect(
                    System.getenv("MYSQL_USER_STAGING"),
                    System.getenv("MYSQL_PASS_STAGING"),
                    System.getenv("STAGING_HOST")
            );
        }
    }
}
