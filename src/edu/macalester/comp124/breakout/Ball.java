package edu.macalester.comp124.breakout;

import comp124graphics.Ellipse;
import java.awt.*;
import java.util.Random;


public class Ball extends Ellipse {

    private static final int START_X = 394;
    private static final int START_Y = 340;
    private double xPos;
    private double yPos;
    private static final int BALL_RADIUS = 12;
    private BreakoutGame canvas;
    private Point.Double upLeft;
    private Point.Double upRight;
    private Point.Double botLeft;
    private Point.Double botRight;
    private double xSlope;
    private double ySlope;
    protected int ballCount;
    Point.Double[] corners = new Point.Double[4];

    public Ball(BreakoutGame canvas){
        super(START_X, START_Y, BALL_RADIUS, BALL_RADIUS);
        super.setFillColor(new Color(168, 33, 249));
        super.setFilled(true);
        this.canvas = canvas;
        ballCount = 3;

        upLeft = new Point.Double(getXPosition(), getYPosition());
        upRight = new Point.Double(getXPosition() + 2*BALL_RADIUS, getYPosition());
        botLeft = new Point.Double(getXPosition(), getYPosition() + 2*BALL_RADIUS);
        botRight = new Point.Double(getXPosition() + 2*BALL_RADIUS, getYPosition() + 2*BALL_RADIUS);

        corners[0] = upLeft; corners[1] = upRight; corners[2] = botLeft; corners[3] = botRight;
    }

    /**
     * Sets the ball's starting position, xSlope, and ySlope
     */
    protected void startBall(){
        Random rand1 = new Random();
        Random rand2 = new Random();
        setPosition(START_X, START_Y);
        xPos = START_X;
        yPos = START_Y;
        if (ballCount == 3){
            xSlope = -4.0 + rand1.nextInt(8);
            ySlope = 1.0 + rand2.nextInt(4);
        }
        else{
            xSlope = -2.0 + rand1.nextInt(4);
            ySlope = 1.0 + rand2.nextInt(1);
        }
    }

    /**
     * Gets the ball's x position
     * @return the x position in a double
     */
    protected double getXPosition(){
       return xPos;
    }

    /**
     * Gets the ball's y position
     * @return the y position in a double
     */
    protected double getYPosition(){
        return yPos;
    }

    /**
     * Sets the corner Point.Doubles to their appropriate values
     */
    protected void updateCorners(){
        upLeft.x = xPos;   upLeft.y = yPos;
        upRight.x = xPos + 2*BALL_RADIUS;   upRight.y = yPos;
        botLeft.x = xPos;   botLeft.y = yPos + 2*BALL_RADIUS;
        botRight.x = xPos + 2*BALL_RADIUS;   botRight.y = yPos + 2*BALL_RADIUS;
    }

    /**
     * True if a brick is hit, false if not
     * @return if a brick is hit by a point or not
     * @param point a corner of the ball
     */
    protected boolean brickHit(Point.Double point){
        return ((canvas.getElementAt(point.x, point.y) instanceof Brick));
    }

    protected boolean inBounds(){
        return (yPos < 800);
    }


    /**
     * Calls hitWall, checks which if any corners of the ball are hit
     * and calls bounce or sideBounce based on that.
     */
    protected void changeSlopes() {
        hitWall();
        boolean hitUpLeft = brickHit(upLeft);
        boolean hitUpRight = brickHit(upRight);
        boolean hitBotLeft = brickHit(botLeft);
        boolean hitBotRight = brickHit(botRight);
        if (hitUpLeft || hitUpRight || hitBotLeft || hitBotRight) {
            if ((hitUpLeft && hitBotLeft) || (hitUpRight && hitBotRight)) {
                sideBounce();
            }
            if ((hitUpLeft && hitUpRight) || (hitBotLeft && hitBotRight)) {
                bounce();
            }
            else {
                if (hitUpLeft){
                    Brick brick = (Brick) canvas.getElementAt(upLeft.x, upLeft.y);
                    if (upLeft.y >= (brick.getY() + brick.getHeight())){
                        bounce();
                    }
                    else{
                        sideBounce();
                    }
                }
                else if (hitUpRight){
                    Brick brick = (Brick) canvas.getElementAt(upRight.x, upRight.y);
                    if (upRight.y >= (brick.getY() + brick.getHeight())){
                        bounce();
                    }
                    else{
                        sideBounce();
                    }
                }
                else if (hitBotLeft){
                    Brick brick = (Brick) canvas.getElementAt(botLeft.x, botLeft.y);
                    if (botLeft.y <= (brick.getY())){
                        bounce();
                    }
                    else{
                        sideBounce();
                    }
                }
                else if (hitBotRight){
                    Brick brick = (Brick) canvas.getElementAt(botRight.x, botRight.y);
                    if (botRight.y >= (brick.getY() + brick.getHeight())){
                        bounce();
                    }
                    else{
                        sideBounce();
                    }
                }
            }
        }
    }


    /**
     * Changes the ball's position using the current xSlope and ySlope
     * and updates the values of the corners
     * @param dt multiplies  by the slopes to determine how far in each direction
     *           the ball will move
     */
    public void updatePosition(double dt) {
        double oldX = getXPosition();
        double oldY = getYPosition();
        double newX = oldX + (xSlope * dt);
        double newY = oldY + (ySlope * dt);
        xPos = newX;
        yPos = newY;
        updateCorners();
        setPosition(oldX, oldY);
    }

    /**
     * Gets the brick at a certain point
     * @return the Brick object
     * @param point the point hitting the brick
     */
    protected Brick getBrick(Point.Double point) {
        return (Brick) canvas.getElementAt(point.x, point.y);
    }

    /**
     * Resets the ball's position and lowers the ball count after a turn
     */
    protected void resetBall(){
        ballCount --;
        startBall();
    }

    /**
     * True if 3 turns are gone, false if not.
     * @return whether or not the player is out of turns
     */
    protected boolean ballsGone(){
        return ballCount == 0;
    }

    /**
     * If a wall is hit the bounce or sideBounce is called
     */
    protected void hitWall(){
        if (getXPosition() < 0 || getXPosition() + 2*BALL_RADIUS > 800){
            sideBounce();
        }
        if (getYPosition() < 0){
            bounce();
        }
    }

    /**
     * Switches the xSlope if the ball bounces off a side
     */
    protected void sideBounce(){
        xSlope = -xSlope;
    }

    /**
     * Switches the ySlope if the ball bounces vertically
     */
    protected void bounce(){
        ySlope = -ySlope;
    }

    /**
     * True if the paddle is hit by the ball
     * @return whether or not the point is hitting the paddle
     * @param point a bottom corner of the ball
     */
    protected boolean hitPaddle(Point.Double point){
        return (canvas.getElementAt(point.x, point.y) instanceof Paddle);
    }

    /**
     * If the paddle is hit, calls bounce and changes the xSlope
     * based on where on the paddle was hit
     */
    protected void paddleHit(Paddle paddle){
        if (hitPaddle(botLeft) || hitPaddle(botRight)){
            bounce();
            xSlope = paddle.getXSlopeScalar(xPos, xSlope);
        }
    }

}
