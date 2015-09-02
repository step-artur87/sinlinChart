package tags.func;

import tags.AbstractTag;
import tags.Data;
import tags.Tag;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/26/15
 * Time: 8:05 PM
 */
public class Num extends AbstractTag implements Tag {//todo fields
    private String text;
    private ArrayList<Double> values;

    @Override
    public void addChildTag(Tag tag) {
        //todo exception
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
}
