import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: art
 * Date: 8/24/15
 * Time: 4:56 PM
 */
public class MyDemo extends ApplicationFrame {

    public MyDemo() {
        super("tittle");
        JPanel jPanel = new JPanel();
        jPanel.setPreferredSize(new Dimension(1200, 800));
        setContentPane(jPanel);
    }
}


