package main;

import javax.swing.*;
import java.awt.*;

public class MyGamePanel extends JPanel
{
    public MyGamePanel()
    {
        super();
        this.setName("gamePanel");
        this.setPreferredSize(new Dimension(1024, 768));

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 0, 0);
        flowLayout.setAlignment(FlowLayout.LEFT);
        this.setLayout(flowLayout);
    }
}
