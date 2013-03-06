/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meridiandelta;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ben
 */
public class EncodingDetectorTest {
    
    public EncodingDetectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void test_encodingutf8_filePassedAsStringName_correctyDetected() throws FileNotFoundException {
        // TODO review the generated test code and remove the default call to fail.
       // Path p1 = Paths.get(".");
        //System.out.println(p1.toAbsolutePath().toString());
        //System.out.println("Hello World!");
       // System.out.println("Working Directory = " +  System.getProperty("user.dir"));
        String fileName = "C:\\Documents and Settings\\Ben\\My Documents\\NetBeansProjects\\MDEncodingDetector\\src\\TestDocuments\\UTF8.htm";
        EncodingDetector myEnc = new EncodingDetector(fileName);
        Assert.assertEquals("Incorrect Encoding detected", "UTF-8", myEnc.detect());
       // Assert.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void test_encodingutf8_filePassedAsPathObject_correctyDetected() throws FileNotFoundException {
        String fileName = "C:\\Documents and Settings\\Ben\\My Documents\\NetBeansProjects\\MDEncodingDetector\\src\\TestDocuments\\UTF8.htm";
        Path filePath = Paths.get(fileName);
        EncodingDetector myEnc = new EncodingDetector(filePath);
        Assert.assertEquals("Incorrect Encoding detected", "UTF-8", myEnc.detect());
    }
        
}