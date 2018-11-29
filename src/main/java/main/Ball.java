package main;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import java.awt.*;

/**
 * @author dilip
 */
public class Ball
{

    public int id;
    //JavaFX UI for ball
    public Body body;
    Color color;

    //X and Y position of the ball in JBox2D world
    public float posX;
    public float posY;

    //Ball radius in pixels
    public int radius;

    /**
     * There are three types bodies in JBox2D – Static, Kinematic and dynamic
     * In this application static bodies (BodyType.STATIC – non movable bodies)
     * are used for drawing hurdles and dynamic bodies (BodyType.DYNAMIC–movable bodies)
     * are used for falling balls
     */
    private BodyType bodyType;

    public Ball(float posX, float posY)
    {
        this(posX, posY, Utils.BALL_SIZE, BodyType.DYNAMIC, Color.RED);
        this.posX = posX;
        this.posY = posY;
    }

    public Ball(float posX, float posY, int radius, BodyType bodyType, Color color)
    {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.bodyType = bodyType;
        this.color = color;
        body = create();
    }

    @Override
    public String toString()
    {
        return String.format("(%2.0f, %2.0f)", posX, posY);
    }

    /**
     * This method creates a ball by using Circle object from JavaFX and CircleShape from JBox2D
     */
    private Body create()
    {
        //Create an JBox2D body defination for ball.
        BodyDef bd = new BodyDef();
        bd.type = bodyType;
        bd.position.set(posX, posY);

        CircleShape cs = new CircleShape();
        cs.m_radius = radius;  //We need to convert radius to JBox2D equivalent

        // Create a fixture for ball
        FixtureDef fd = new FixtureDef();
        fd.shape = cs;
        fd.density = 0.1f;
        fd.friction = 0.3f;
        fd.restitution = 0.6f;

        /**
         * Virtual invisible JBox2D body of ball. Bodies have velocity and position.
         * Forces, torques, and impulses can be applied to these bodies.
         */
        Body body = Utils.world.createBody(bd);
        body.createFixture(fd);
        return body;
    }
}