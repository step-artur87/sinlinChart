import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tags.*;
import tags.func.Fn;
import tags.func.Num;
import tags.render.Sheet;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:17 PM
 */
public class ChartSourceHandler extends DefaultHandler {
    private TagDeque tagDeque = TagDeque.getInstance();//todo <>
    private FnDeque fnDeque = FnDeque.getInstance();//todo <>

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

        //Fn.setParent
        if (tag.getClass().equals(Fn.class)) {
            ((Fn) tag).setParentTag((Tag) tagDeque.peek());
            ((Fn) tag).setParentFn((Fn) fnDeque.peek());

            clearFnDeque();


            fnDeque.push(tag);
        }

        //push
        tagDeque.push(tag);//todo more
    }

    private void clearFnDeque() {//fixme
        while (!fnDeque.isEmpty()
                && !tagDeque.contains(
                ((Fn) fnDeque.peek()).getParentTag())) {
            fnDeque.removeFirst();
        }
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName)
            throws SAXException {
        Tag tag = TagBuilder.create(qName, null);//todo class
        if (tagDeque.size() == 1) {//todo if root again
            tagDeque.setRootTag((Tag) tagDeque.peek());

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
        if (tagDeque.peek().getClass().equals(Num.class)) {      //fixme
            ((Num) tagDeque.peek()).setText(new String(ch, start, length));
        }
    }
}