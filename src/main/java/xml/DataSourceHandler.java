package xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tags.Data;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:17 PM
 */
public class DataSourceHandler extends DefaultHandler {
    public static final String DATA = "data";
    public static final String COLUMN = "column";
    public static final String ROW = "row";
    public static final String ID = "id";

    private ArrayList<Double> currentColumn;
    private String string;

    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes)
            throws SAXException {
        switch (qName) {
            case DATA:
                break;
            case COLUMN:
                Map<String, ArrayList<Double>> dataMap = Data.getInstance().getDataMap();
                dataMap.put(attributes.getValue(ID),
                        new ArrayList<>());
                currentColumn = dataMap.get(attributes.getValue(ID));
                break;
            case ROW:
                break;
        }
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName)
            throws SAXException {
        switch (qName) {
            case DATA:
                break;
            case COLUMN:
                break;
            case ROW:
                currentColumn.add(Double.parseDouble(string));
                break;
        }
    }

    @Override
    public void characters(char[] ch,
                           int start,
                           int length)
            throws SAXException {
        string = new String(ch, start, length);
    }
}
