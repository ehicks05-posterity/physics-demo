package main;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.*;
import java.util.List;

public class PhysicsDemo
{
    private static Random random = new Random();

    public static void main(String[] args)
    {
        DisplayInfo.setDisplayProperties();

        MyFrame frame = new MyFrame();
        MyGamePanel gamePanel = new MyGamePanel();

        GameCanvas gameCanvas = new GameCanvas();
        gamePanel.add(gameCanvas);

        JPanel cards = new JPanel(new CardLayout());
        cards.add(gamePanel);
        frame.add(cards);

        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.addLayoutComponent(gamePanel, "gamePanel");
        cardLayout.show(cards, gamePanel.getName());

        frame.pack();

        // Let our Canvas know we want to do Double Buffering
        gameCanvas.createBufferStrategy(3);
        BufferStrategy bufferStrategy = gameCanvas.getBufferStrategy();

        generateBalls();

        Utils.addGround(200, 1);
        Utils.addWall(10, 0, 1, 150); //Left wall
        Utils.addWall(120, 0, 1, 150); //Right wall

        GameCanvas.runGameLoop(bufferStrategy);
    }

    public static void generateBalls()
    {
        List<Color> ballColors = Arrays.asList(Color.red, Color.green, Color.yellow, Color.blue, Color.MAGENTA, Color.CYAN, Color.ORANGE, Color.PINK);
        Random r = new Random();
        for (int i = 0; i < 8; i++)
        {
            float posX = random.nextInt(80) + 10;
            float posY = random.nextInt(80);
            Ball entity = new Ball(posX, posY);
            entity.id = i;
            entity.radius = 4;
            entity.body.applyLinearImpulse(new Vec2(r.nextInt(200) - 100, r.nextInt(200) - 100), new Vec2(r.nextInt(2000) - 1000, r.nextInt(2000) - 1000));
            entity.color = ballColors.get(i % ballColors.size());
            GameEntities.addEntity(entity);
        }
    }

    public static void doTimeStep(double dt)
    {
        //Create time step. Set Iteration count 8 for velocity and 3 for positions
        Utils.world.step((float)dt, 8, 3);

        //Move balls to the new position computed by JBox2D
        for (Ball ball : GameEntities.gameEntities)
        {
            Body body = ball.body;
//            float xpos = Utils.toPixelPosX(body.getPosition().x);
//            float ypos = Utils.toPixelPosY(body.getPosition().y);
            float xpos = body.getPosition().x;
            float ypos = body.getPosition().y;
            ball.posX = (xpos);
            ball.posY = (ypos);
        }
    }
}
