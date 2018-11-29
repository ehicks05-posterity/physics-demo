package main;

import java.awt.*;

public class InterfaceLogic
{
    public static void paintWorld(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, 1024, 768);

        drawTileGrid(g2d);

        g2d.setColor(Color.red);
        g2d.drawLine( 0, 600, 1024, 600); // horizontal line y = 600
        g2d.drawLine( 600, 0, 600, 768);  // vertical line x = 600
        g2d.drawLine( 50, 0, 50, 768);    // vertical line x = 50

        for (Ball entity : GameEntities.gameEntities)
        {
            g2d.setColor(entity.color);
            float x = entity.posX;
            float y = entity.posY;
            drawCenteredCircle(g2d, (int) (x * 5), (int) (y * 5), entity.radius * 5);

            g2d.drawString(entity.toString(), 650, 30 + 20 * entity.id);
        }

        g2d.drawString(Metrics.calculateFPS().toString() + "fps", 60, 30);

        Toolkit.getDefaultToolkit().sync();
    }

    public static void drawCenteredCircle(Graphics2D g, int x, int y, int r)
    {
        g.fillOval(x - r, y - r, r * 2, r * 2);
    }

    private static void drawTileGrid(Graphics2D g2d)
    {
        g2d.setColor(Color.GRAY);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 3 * .1f));
        for (int width = 0; width < 1024; width += 32)
            g2d.drawLine( width, 0, width, 768);
        for (int height = 0; height < 768; height += 32)
            g2d.drawLine( 0, height, 1024, height);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}
