package deadtv;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class DeadTvPanel extends JPanel implements Runnable
{
    private Thread animator;
    private final int DELAY = DeadTVConfig.delay;
    private final int width  = DeadTVConfig.width;
    private final int height = DeadTVConfig.height;

    public DeadTvPanel()
    {
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }

    public void addNotify()
    {
        super.addNotify();
        animator = new Thread(this);
        animator.start();
    }

    Font font = new Font("Sans", Font.PLAIN, 48);
    private long time;
    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        java.util.List<Color> colors = new ArrayList<>();
        for (int i = 0; i < 16; i++)
            colors.add(new Color(i*16,i*16,i*16));

        Random r = new Random();
        int pixelSize = 8;

        for (int y = 0; y < height; y+=pixelSize)
        {
            for (int x = 0; x < width; x+=pixelSize)
            {
                int value = r.nextInt(16);
                g2d.setColor(colors.get(value));

                g2d.fillRect(x, y, pixelSize, pixelSize);
            }
        }

        g2d.setColor(Color.GREEN);
        g2d.setFont(font);
        g2d.drawString("CH 04", 50, 50);

        long elapsedTime = (System.currentTimeMillis() - time);
        String fps = String.valueOf(1000 / elapsedTime);
        g2d.drawString(fps, 50, 200);
        time = System.currentTimeMillis();

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void run()
    {
        // run animation loop
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (true)
        {
            repaint();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0)
                sleep = 2;
            try
            {
                Thread.sleep(sleep);
            }
            catch (InterruptedException e)
            {
                System.out.println("interrupted");
            }

            beforeTime = System.currentTimeMillis();
        }
    }
}