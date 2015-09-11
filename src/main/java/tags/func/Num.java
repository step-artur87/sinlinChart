package tags.func;

import tags.AbstractTag;
import tags.Data;
import tags.Tag;
import tags.TagException;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:05 PM
 */
public class Num extends AbstractTag implements Tag {
    private String text;
    private ArrayList<Double> values;

    @Override
    public void addChildTag(Tag tag) throws TagException {
        throw new TagException("Num can not have children.");
    }

    public void setText(String text) {
        this.text = text;
        setValues(Data.getInstance().getDataMap().get(text));
    }

    public String getText() {
        return text;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }

    public void writeXML(XMLStreamWriter xmlStreamWriter) {
        try {
            xmlStreamWriter.writeStartElement(
                    this.getClass().getSimpleName());
            xmlStreamWriter.writeCharacters(
                    getText().toCharArray(),
                    0,
                    getText().length());
            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
