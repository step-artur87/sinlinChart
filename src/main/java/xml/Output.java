package xml;

import tags.Tag;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 9/8/15
 * Time: 3:08 PM
 */
public class Output {
    public static void printXML(Tag tag) {
        XMLStreamWriter xmlStreamWriter = null;
        try {
            xmlStreamWriter = XMLOutputFactory.newInstance()
                    .createXMLStreamWriter(System.out);
            xmlStreamWriter.writeStartDocument();
            tag.writeXML(xmlStreamWriter);
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();
        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}


