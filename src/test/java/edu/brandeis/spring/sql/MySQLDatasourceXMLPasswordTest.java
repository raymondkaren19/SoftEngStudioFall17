package edu.brandeis.spring.sql;

import junit.framework.TestCase;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// The Production and Staging server are expecting sql username and password 
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
