package xml;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 5:51 PM
 */
public class SaxParsing {
    public static void parse(DefaultHandler defaultHandler,
                             String filename) {
        try {
            SAXParserFactory.newInstance().
                    newSAXParser().parse(filename, defaultHandler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}
