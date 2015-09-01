import org.jfree.chart.plot.Plot;
import org.jfree.ui.RefineryUtilities;
import tags.TagDeque;
import tags.render.Sheet;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/25/15
 * Time: 12:45 AM
 */
public class Main {
    public static void main(String[] args) {
        SaxParsing.parse("1.xml");

        MyDemo myDemo = new MyDemo();
        TagDeque tagDeque = TagDeque.getInstance();
        Sheet sheet = (Sheet) tagDeque.getRootTag();
        Plot plot = sheet.getPlots().get(0);

         /*show chart*/
        myDemo.pack();
        RefineryUtilities.centerFrameOnScreen(myDemo);
        myDemo.setVisible(true);


        plot.draw((Graphics2D) myDemo.getGraphics(),
                new Rectangle2D.Double(100, 100, 600, 600),
                null, null, null);

    }
}