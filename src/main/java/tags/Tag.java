package tags;

import org.xml.sax.Attributes;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 6:55 PM
 */
public interface Tag {
    public static final String ARGS = "args";
    public static final String CHART = "chart";
    public static final String CONST = "const";
    public static final String ELEMENT = "element";
    public static final String FN = "fn";
    public static final String NUM = "num";
    public static final String PROP = "prop";
    public static final String RES = "res";
    public static final String SHEET = "sheet";
    public static String TYPE = "type";

    public void addChildTag(Tag tag) throws TagException;

    public Attributes getAttributes();

    public void setAttributes(Attributes attributes);

}
