
package edu.brandeis.spring.sql;

import junit.framework.TestCase;
import static junit.framework.Assert.fail;


// The Production and Staging server are expecting sql username and password 
// to be a the same so a script can swap their values. 
// This makes sure that this does not change with out updating the other scripts.
public class MySQLDatasourceXMLTest  extends TestCase  {

    public void test() {
         String path = "./src/main/resources/META-INF/spring/datasource-tx-jpa.xml";
         String parentNode = "dataSource";
         String extractedUsername = ExtractValue(path, parentNode, "username");
         String extractedPassword = ExtractValue(path, parentNode, "password");

         if(extractedUsername == "root" && extractedPassword == "mysql")
         {
             return;
             
         } else
         {
             fail("Username or Password do not match on Datasource");
         }
    }
    public String ExtractValue(String path, String parentNode, String childNode)
    {
        
        return "";
    }

}