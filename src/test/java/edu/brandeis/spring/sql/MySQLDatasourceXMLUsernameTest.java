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
public class MySQLDatasourceXMLUsernameTest extends TestCase {

    public void test() {
        String path = "./src/main/resources/META-INF/spring/datasource-tx-jpa.xml";

        if (findValue(path, "password", "mysql")) {
            // continue
        } else {
            fail("Passwords do not match on Datasource");
        }
    }

    public Boolean findValue(String path, String propetyName, String compare) {
        try {
            File file = new File(path);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(file);
            NodeList list = document.getElementsByTagName("bean");

            for (int index = 0; index < list.getLength(); index += 1) {
                Node each = list.item(index);

                if (each.getNodeType() == Node.ELEMENT_NODE) {
                    String attibute = ((Element) each).getAttribute("id");

                    if (attibute.equals("dataSource")) {
                        NodeList innerList = ((Element) each).getElementsByTagName("property");
                        for (int innerIndex = 0; innerIndex < innerList.getLength(); innerIndex += 1) {
                            Node innerEach = innerList.item(innerIndex);
                            if (innerEach.getNodeType() == Node.ELEMENT_NODE) {
                                String innerAttibute = ((Element) innerEach).getAttribute("name");
                                if (innerAttibute.equals(propetyName)) {
                                    if (((Element) innerEach).getAttribute("value").equalsIgnoreCase(compare)) {
                                        return true;
                                    }

                                }
                            }

                        }
                    }
                }

            }

        } catch (Exception e) {
            fail("An exception was thrown while checking " + propetyName + "." + e.toString());

        }

        return false;

    }

}
