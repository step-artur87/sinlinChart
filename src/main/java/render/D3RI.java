package render;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/30/15
 * Time: 9:31 PM
 */
public class D3RI<T> implements RenderInstruction {
    private String name;
    private T[][][] data;

    public D3RI(String name, T[][][] data) {
        this.data = data;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T[][][] getData() {
        return data;
    }

    public void setData(T[][][] data) {
        this.data = data;
    }
}
