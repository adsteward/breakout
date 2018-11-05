package edu.macalester.comp124.breakout;

import comp124graphics.Ellipse;
import comp124graphics.GraphicsObject;

import java.awt.geom.Point2D;


import java.awt.*;
import java.util.Random;


public class Ball extends Ellipse {

    private static final int START_X = 400;
    private static final int START_Y = 400;
    private double xPos;
    private double yPos;
    private static final int BALL_RADIUS = 10;
    private BreakoutGame canvas;
    private Point.Double upLeft;
    private Point.Double upRight;
    private Point.Double botLeft;
    private Point.Double botRight;
    private double xSlope;
    private double ySlope;
    private int ballCount;
    Point.Double[] corners = new Point.Double[4];
    private double c;
    private double dx;
    private double dy;
    private Random rand1;
    private Random rand2;

    public Ball(BreakoutGame canvas){
        super(START_X, START_Y, BALL_RADIUS, BALL_RADIUS);
        super.setFilled(true);
        this.canvas = canvas;
        ballCount = 3;
//        xPos = START_X;
//        yPos = START_Y;
        upLeft = new Point.Double(getXPosition(), getYPosition());
        upRight = new Point.Double(getXPosition() + 2*BALL_RADIUS, getYPosition());
        botLeft = new Point.Double(getXPosition(), getYPosition() + 2*BALL_RADIUS);
        botRight = new Point.Double(getXPosition() + 2*BALL_RADIUS, getYPosition() + 2*BALL_RADIUS);
        corners[0] = upLeft; corners[1] = upRight; corners[2] = botLeft; corners[3] = botRight;
    }
    protected void startBall(){
        setPosition(START_X, START_Y);
        xPos = START_X;
        yPos = START_Y;
        rand1 = new Random();
        rand2 = new Random();
        xSlope = -4.0 + rand1.nextInt(8);
        ySlope = 1.0 + rand2.nextInt(4);
    }

    protected double getXPosition(){
       return xPos;
    }

    protected double getYPosition(){
        return yPos;
    }
    protected double getXSlope(){
        return xSlope;
    }
    protected double getySlope(){
        return ySlope;
    }

    protected void updateCorners(){
        upLeft.x = xPos;   upLeft.y = yPos;
        upRight.x = xPos + 2*BALL_RADIUS;   upRight.y = yPos;
        botLeft.x = xPos;   botLeft.y = yPos + 2*BALL_RADIUS;
        botRight.x = xPos + 2*BALL_RADIUS;   botRight.y = yPos + 2*BALL_RADIUS;
    }


//returns true if hits a brick
    protected boolean brickHit(Point.Double point){
        return ((canvas.getElementAt(point.x, point.y) instanceof Brick));
    }

    protected boolean inBounds(){
        return (yPos < 800);
    }

// true if inputted point is the only one touching something (w/ checkints)
    protected boolean justOneInt(Point.Double point){
        if (point == upLeft){
            return (brickHit(upLeft) && !brickHit(upRight) && !brickHit(botRight) && !brickHit(botLeft));
        }
        if (point == upRight){
            return (brickHit(upRight) && !brickHit(upLeft) && !brickHit(botRight) && !brickHit(botLeft));
        }
        if (point == botLeft){
            return (brickHit(botLeft) && !brickHit(upRight) && !brickHit(botRight) && !brickHit(upLeft));
        }
        if (point == botRight){
            return (brickHit(botRight) && !brickHit(upRight) && !brickHit(upLeft) && !brickHit(botLeft));
        }
        else{
            return false;
        }
    }

    protected void changeSlopes() {
        hitWall();
        if (brickHit(upLeft) && brickHit(botLeft)) {
            sideBounce();
        }
        if  (brickHit(upRight) && brickHit(botRight)){
            sideBounce();

        }
        if (brickHit(upLeft) && brickHit(upRight)){
            Bounce();

        }
        if (brickHit(botLeft) && brickHit(botRight)) {
            Bounce();

        }
        else {
            if (justOneInt(upLeft) || justOneInt(botLeft)) {
                if (xSlope > 0) {
                    Bounce();
                } else {
                    sideBounce();
                }
            }
            if (justOneInt(upRight) || justOneInt(botRight)) {
                if (xSlope > 0) {
                    sideBounce();
                } else {
                    Bounce();
                }
            }
        }
    }

    //moves the ball when it's not hitting anything
    protected void updatePosition(){
        double oldX = getXPosition();
        double oldY = getYPosition();
        double a = getA();
        dx = a * xSlope;
        dy = a * ySlope;
        double newX = oldX + dx;
        double newY = oldY + dy;
        xPos = newX;
        yPos = newY;
        updateCorners();
        setPosition(oldX, oldY);
        //canvas.pause(10);
    }

    protected double getA(){
        double b = xSlope*xSlope + ySlope*ySlope;
        double d = 7.0/b;
        c = Math.sqrt(d);
        return c;
    }

    protected Brick getBrick(Point.Double point) {
        return (Brick) canvas.getElementAt(point.x, point.y);
    }

    protected void resetBall(){
        ballCount --;
        setPosition(START_X, START_Y);
        xSlope = 1;
        ySlope = -1;
        xPos = START_X;
        yPos = START_Y;
    }

    protected void resetGameBall(){
        resetBall();
        ballCount = 3;
    }

    protected boolean ballsGone(){
        return ballCount == 0;
    }
// true if out of bounds
    protected void hitWall(){
        if (getXPosition() < 0 || getXPosition() + 2*BALL_RADIUS > 800){
            sideBounce();
        }
        if (getYPosition() < 0){
            Bounce();
        }
    }


    protected void sideBounce(){
        xSlope = -xSlope;
    }
    protected void Bounce(){
        ySlope = -ySlope;
    }

    protected boolean hitPaddle(Point.Double point){
        return (canvas.getElementAt(point.x, point.y) instanceof Paddle);
    }

    protected void paddleHit(Paddle paddle){
        if (hitPaddle(botLeft) || hitPaddle(botRight)){
            Bounce();
//            xSlope = (xSlope * paddle.getB(xPos, xSlope));
        }
    }

}
