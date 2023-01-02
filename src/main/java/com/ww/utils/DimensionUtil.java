package com.ww.utils;

import javax.swing.*;
import java.awt.*;

public class DimensionUtil {
    public static Rectangle getBounds(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets screenInsets = Toolkit.getDefaultToolkit().getScreenInsets(new JFrame().getGraphicsConfiguration());
        Rectangle rectangle = new Rectangle(screenInsets.left, screenInsets.top, screenSize.width - screenInsets.left - screenInsets.right, screenSize.height - screenInsets.top - screenInsets.bottom);
        return rectangle;
    }
}
