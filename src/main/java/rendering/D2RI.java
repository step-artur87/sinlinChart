package rendering;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/30/15
 * Time: 9:31 PM
 */
public class D2RI implements RenderInstruction {
    private String name;
    private double[][] data;

    public D2RI(String name, double[][] data) {
        this.data = data;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }
}
