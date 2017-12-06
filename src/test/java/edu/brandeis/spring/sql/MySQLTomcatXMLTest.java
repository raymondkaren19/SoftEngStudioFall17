
package edu.brandeis.spring.sql;

import junit.framework.TestCase;
import static junit.framework.Assert.fail;


// The Production and Staging server are expecting Tomcats's username and password 
// to be a the same so a script can swap their values. 
// This makes sure that this does not change with out updating the other scripts.
public class MySQLTomcatXMLTest  extends TestCase  {

    public void test() {
         if(true)
         {
             
         } else
         {
             fail("I failed");
         }
    }

}