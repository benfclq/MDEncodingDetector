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
/*
# GREAT BROWSER UNICODE PAGE
# http://www.cs.tut.fi/~jkorpela/html/characters.html

#DOS UNICODE
#http://www.howtogeek.com/howto/windows-vista/stupid-geek-tricks-enable-more-fonts-for-the-windows-command-prompt/ need to change standard font to display non ascii
# set font to lucida to see non ascii characters
# http://www.microsoft.com/typography/fonts/font.aspx?FMID=160 what lucida can display
#http://www.sqlsnippets.com/en/topic-13410.html Lucida will display box character if it does not support code point
#http://www.ehow.com/how_5919196_change-dos-font-windows.html how to change default font in dos
#http://stackoverflow.com/questions/1035388/unicode-output-on-windows-command-line change encoding used in dos eg chcp 1252 to change to 1252
#http://msdn.microsoft.com/en-gb/library/windows/desktop/dd317756%28v=vs.85%29.aspx list of windows code page identifiers
# type 'type filename' do dump a file to the screen


# convert all special characters to html entities 
# http://www.emailonacid.com/blog/details/C13/the_importance_of_content-type_character_encoding_in_html_emails

# determine whether perl considers a string binary or character
#perlgeek.de/en/article/encodings-and-unicode
#use Devel::Peek; Dump $fc; if UTF8 not present in FLAFS of dump then is binary

#download perl modules not in activestate
#http://www.thegeekstuff.com/2008/09/how-to-install-perl-modules-manually-and-using-cpan-command/
# install 'Encode::Detect' at cpan

#unicode links
#http://perlgeek.de/en/article/encodings-and-unicode is string decoded? 
#
#http://www.perlmonks.org/?node=String%20Literals%20in%20Perl meaning of embedded code literals in string literal eg '"\332\246vis-\303\240-vis Beyonc'
# \007	octal ascii value this time 007 or the bell, \x07	hex ascii value this time 007 or the bell, \cD	any control character.. here it is control-D
#
# http://www.motobit.com/util/charset-codepage-conversion.asp online charset conversion
# http://software.hixie.ch/utilities/cgi/unicode-decoder/utf8-decoder give utf8 bytess eg \332\246 and see character it represents

# Web Pages in different encodings
#http://vancouver-webpages.com/multilingual/
# and https://github.com/seomoz/asis/tree/master/test/encoding
# http://www.validome.org/lang/en/errors/HTML-CHARSET excellent one has utf16 amongst others
#www.i18nguy.com/unicode/unicode-example-intro.html
* */