package rendering;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/30/15
 * Time: 9:31 PM
 * Two-dimensioned RenderInstruction
 */
public class D2RI implements RenderInstruction {
    private String name;
    private String legend;
    private ArrayList<ArrayList<Double>> data;

    public D2RI(String name,
                String legend,
                ArrayList<ArrayList<Double>> data) {
        this.data = data;
        this.name = name;
        this.legend = legend;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<Double>> getData() {
        return data;
    }

    public String getLegend() {
        return legend;
    }
}
