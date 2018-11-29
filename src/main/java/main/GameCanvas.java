package main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.math.BigDecimal;

public final class GameCanvas extends Canvas
{
    public static long frameTime;
    public static long accumulator;

    public GameCanvas()
    {
        this.setSize(1024, 768);

        this.setBounds(0, 0, 1024, 768);
        this.setIgnoreRepaint(true);
    }

    public static void runGameLoop(BufferStrategy bufferStrategy)
    {
        double t = 0;
//        final int dt = 33333332; // (33 ms 30fps)
        final int dt = 16666666; // (16 ms 60fps)
//        final int dt = 8333333; // (8 ms 120fps)
//        final int dt = 4166666; // (4 ms 240fps)
        final BigDecimal dtInSeconds = new BigDecimal(String.valueOf(dt)).divide(new BigDecimal(String.valueOf(1_000_000_000)), 5, BigDecimal.ROUND_HALF_UP);

        long currentTime = System.nanoTime();
        accumulator = 0;

        // run game loop
        while (true)
        {
            long newTime = System.nanoTime();
            frameTime = newTime - currentTime;
            if (frameTime < dt)
            {
                long waitTime = (dt - frameTime) / 1_000_000;
                try
                {
                    Thread.sleep(waitTime);
                }
                catch (InterruptedException e)
                {
                    Log.info(e.getMessage());
                }
            }
            newTime = System.nanoTime();
            frameTime = newTime - currentTime;
            currentTime = newTime;
            Metrics.timeDiff = frameTime;
            accumulator += frameTime;

            while(accumulator >= dt)
            {
                PhysicsDemo.doTimeStep(dtInSeconds.doubleValue());
                accumulator -= dt;
                t += dt;
            }

            // Grab the current non visible frame (Memory on the graphics card)
            // getDrawGraphics actually creates a new off screen buffer; it doesn't get something that already exists.
            Graphics2D frameBuffer = (Graphics2D) bufferStrategy.getDrawGraphics();

            InterfaceLogic.paintWorld(frameBuffer);

            // Release the off screen buffer
            frameBuffer.dispose();

            // Flip the off screen buffer back in.
            bufferStrategy.show();
        }
    }
}
