/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meridiandelta;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    public void test_encodingutf8_filePassedAsStringName_correctyDetected() throws FileNotFoundException, IOException {
        String fileName = "UTF8.htm";
        EncodingDetector myEnc = new EncodingDetector(getTestDocFullStringPath(fileName));
        Assert.assertEquals("Incorrect Encoding detected", "UTF-8", myEnc.detect());
        // Assert.
        //fail("The test case is a prototype.");
    }

    @Test
    public void test_encodingutf8_filePassedAsPathObject_correctyDetected() throws FileNotFoundException, IOException {
        String fileName = "UTF8.htm";
        Path filePath = Paths.get(getTestDocFullStringPath(fileName));
        EncodingDetector myEnc = new EncodingDetector(filePath);
        Assert.assertEquals("Incorrect Encoding detected", "UTF-8", myEnc.detect());
    }

    private String getTestDocFullStringPath(String fileName) throws IOException {
        Path p1 = Paths.get(".");
        Path p2 = p1.resolve("src\\TestDocuments\\" + fileName);
        return p2.toRealPath().toString();
        
    }
}