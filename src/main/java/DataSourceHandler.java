import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
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

    private Map<String, ArrayList<Double>> data = new HashMap<>();
    private ArrayList<Double> currentColumn;
    String string;

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
                data.put(attributes.getValue(ID),//todo if to "id"
                        new ArrayList<>());
                currentColumn = data.get(attributes.getValue(ID));
                break;
            case ROW:
                break;
        }
    }

    public void endElement(String uri,
                           String localName,
                           String qName,
                           Attributes attributes)
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

    public Map<String, ArrayList<Double>> getData() {
        return data;
    }
}
