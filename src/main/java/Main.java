import org.jfree.chart.plot.Plot;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import tags.TagDeque;
import tags.renderable.Sheet;
import xml.ChartSourceHandler;
import xml.DataSourceHandler;
import xml.Output;
import xml.SaxParsing;

import javax.swing.*;
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
        SaxParsing.parse(new DataSourceHandler(), "data.xml");
        SaxParsing.parse(new ChartSourceHandler(), "1.xml");

        /*create frame*/
        ApplicationFrame applicationFrame = new ApplicationFrame("tittle");
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1200, 800));
        applicationFrame.setContentPane(jPanel);

        TagDeque tagDeque = TagDeque.getInstance();
        Sheet sheet = (Sheet) tagDeque.getRootTag();
        Plot plot = sheet.getPlots().get(0);

        Output.printXML(sheet);

         /*show chart*/
        applicationFrame.pack();
        RefineryUtilities.centerFrameOnScreen(applicationFrame);
        applicationFrame.setVisible(true);


        plot.draw((Graphics2D) applicationFrame.getGraphics(),
                new Rectangle2D.Double(100, 100, 600, 600),
                null, null, null);

    }
}