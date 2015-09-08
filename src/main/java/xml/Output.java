package xml;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 9/8/15
 * Time: 3:08 PM
 */
public class Output {
    public static void main(String[] args) throws Exception {
        XMLStreamWriter xsw = XMLOutputFactory.newInstance()
                .createXMLStreamWriter(System.out);
        xsw.writeStartDocument();
        xsw.writeStartElement("Root");
        xsw.writeAttribute("Name", "Value");
        xsw.writeEmptyElement("Child");
        xsw.writeEndElement();
        xsw.writeEndDocument();
        xsw.close();
    }
}


