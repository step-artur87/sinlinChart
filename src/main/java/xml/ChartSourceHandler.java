package xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tags.*;
import tags.func.Fn;
import tags.func.Num;
import tags.renderable.Sheet;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:17 PM
 */
public class ChartSourceHandler extends DefaultHandler {
    private TagDeque tagDeque = TagDeque.getInstance();

    @Override
    public void startDocument(){
        tagDeque.clear();
    }

    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes)
            throws SAXException {
        Tag tag = TagBuilder.create(qName, attributes);

        //addChild
        if (!tagDeque.isEmpty()) {
            try {
                ((Tag) tagDeque.peek()).addChildTag(tag);
                //todo test addChildTag
            } catch (TagException e) {
                e.printStackTrace();
            }
        }

        //push
        tagDeque.push(tag);
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName)
            throws SAXException {
        Tag tag = TagBuilder.create(qName, null);
        if (tagDeque.size() == 1) {//todo if root again
            tagDeque.setRootTag((Tag) tagDeque.peek());

            //when end root tag collect all RI
            ((Sheet) tagDeque.getRootTag()).appendRI(
                    new ArrayDeque<>());
        }

        //poll
        Tag pollTag = (Tag) tagDeque.poll();
        if (!pollTag.getClass().equals(
                tag.getClass())) {
            try {
                throw new TagException("Ends "
                        + tag.getClass()
                        + ", but polls"
                        + pollTag.getClass());
            } catch (TagException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void characters(char[] ch,
                           int start,
                           int length)
            throws SAXException {
        if (tagDeque.peek().getClass().equals(Num.class)) {
            ((Num) tagDeque.peek()).setText(new String(ch, start, length));
        }
    }
}
