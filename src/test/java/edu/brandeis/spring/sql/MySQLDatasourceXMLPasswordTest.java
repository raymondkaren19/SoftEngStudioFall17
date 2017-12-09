package edu.brandeis.spring.sql;
// to be a the same so a script can swap their values. 
// This makes sure that this does not change with out updating the other scripts.
public class MySQLDatasourceXMLPasswordTest extends MySQLDatasourceXMLUsernameTest {

    @Override
    public void test() {
        String path = "./src/main/resources/META-INF/spring/datasource-tx-jpa.xml";

        if (findValue(path, "password", "mysql")) {
            // continue
        } else {
            fail("Passwords do not match on Datasource");
        }
    }

}
