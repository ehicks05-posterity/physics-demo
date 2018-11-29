package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame
{
    public JMenuBar menuBar;

    public MyFrame()
    {
        super("Eric's Physics");
        getContentPane().setPreferredSize(new Dimension(DisplayInfo.getWindowWidth(), DisplayInfo.getWindowHeight()));

        setWindowsLookAndFeel();

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocation((DisplayInfo.getDisplayWidth() - DisplayInfo.getWindowWidth()) / 2, (DisplayInfo.getDisplayHeight() - DisplayInfo.getWindowHeight()) / 2);
        setResizable(false);
        setVisible(true);

        menuBar = createMenuBar();
        setJMenuBar(menuBar);
        menuBar.setVisible(false);

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowEvent)
            {
                if (JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit?", "Exit?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                {
                    Log.info("Game was manually terminated...");
                    System.exit(0);
                }
            }
        });
    }

    private void setWindowsLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }
        catch (Exception e)
        {
            Log.info("Error setting UIManager Look and Feel.");
        }
    }

    private JMenuBar createMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.setMnemonic(KeyEvent.VK_A);
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null, "Copyright Eric Hicks, 2014.", "About", JOptionPane.INFORMATION_MESSAGE));

        menuBar.add(fileMenu);
        menuBar.add(gameMenu);

        return menuBar;
    }
}
