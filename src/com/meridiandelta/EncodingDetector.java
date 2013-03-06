/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.meridiandelta;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mozilla.universalchardet.UniversalDetector;

//http://userguide.icu-project.org/conversion/detection
/**
 *
 * @author Ben
 */
public class EncodingDetector {

    String fileName = null;
    Path filePath = null;

    public EncodingDetector(String fileName) {
        this.fileName = fileName;
        this.filePath = Paths.get(fileName);
    }

    public EncodingDetector(Path filePath) {
        this.filePath = filePath;
    }

    public String detect() throws FileNotFoundException {
        byte[] buf = new byte[4096];
        java.io.FileInputStream fis = null;

        fis = new java.io.FileInputStream(this.filePath.toFile());

        // (1)
        UniversalDetector detector = new UniversalDetector(null);

        // (2)
        int nread;
        if (fis != null) {
            try {
                while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                    detector.handleData(buf, 0, nread);
                }
            } catch (IOException ex) {
                Logger.getLogger(EncodingDetector.class.getName()).log(Level.SEVERE, null, ex);
            }
            // (3)
            detector.dataEnd();
        }
        // (4)
        String encoding = detector.getDetectedCharset();
//        if (encoding != null) {
//            System.out.println("Detected encoding = " + encoding);
//        } else {
//            System.out.println("No encoding detected.");
//        }

        // (5)
        detector.reset();
        return encoding;
    }

    public static void detectOLD() {
        byte[] buf = new byte[4096];
        String fileName = "C:\\Documents and Settings\\Ben\\My Documents\\NetBeansProjects\\MDEncodingDetector\\src\\TestDocuments\\UTF8.htm";
        java.io.FileInputStream fis = null;
        try {
            fis = new java.io.FileInputStream(fileName);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EncodingDetector.class.getName()).log(Level.SEVERE, null, ex);
        }

        // (1)
        UniversalDetector detector = new UniversalDetector(null);

        // (2)
        int nread;
        if (fis != null) {
            try {
                while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
                    detector.handleData(buf, 0, nread);
                }
            } catch (IOException ex) {
                Logger.getLogger(EncodingDetector.class.getName()).log(Level.SEVERE, null, ex);
            }
            // (3)
            detector.dataEnd();
        }
        // (4)
        String encoding = detector.getDetectedCharset();
        if (encoding != null) {
            System.out.println("Detected encoding = " + encoding);
        } else {
            System.out.println("No encoding detected.");
        }

        // (5)
        detector.reset();
    }
}
