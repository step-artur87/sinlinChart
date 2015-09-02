package tags;

import org.xml.sax.Attributes;
import tags.func.*;
import tags.renderable.Chart;
import tags.renderable.Element;
import tags.renderable.Prop;
import tags.renderable.Sheet;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 9:31 PM
 */
public class TagBuilder {

    public static Tag create(String qName, Attributes attributes) {
        Tag tag = null;
        switch (qName) {
            case Tag.ARGS:
                tag = new Args();
                break;
            case Tag.CHART:
                tag = new Chart();
                break;
            case Tag.CONST:
                tag = new Const();
                break;
            case Tag.ELEMENT:
                tag = new Element();
                break;
            case Tag.FN:
                tag = new Fn();
                break;
            case Tag.NUM:
                tag = new Num();
                break;
            case Tag.PROP:
                tag = new Prop();
                break;
            case Tag.RES:
                tag = new Res();
                break;
            case Tag.SHEET:
                tag = new Sheet();
                break;
            default:
                try {
                    throw new TagException("Tag "
                            + qName
                            + "do not exists.");
                } catch (TagException e) {
                    e.printStackTrace();
                }
        }
        if (tag != null) {
            tag.setAttributes(attributes);
        }
        return tag;
    }
}
