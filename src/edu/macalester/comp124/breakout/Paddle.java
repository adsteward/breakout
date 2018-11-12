package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;
import java.awt.Color;

public class Paddle extends Rectangle{

    private static final int START_X = 350;
    private static final int START_Y = 700;


    public Paddle(){
        super(START_X, START_Y, 100, 12);
        super.setFillColor(new Color(168, 33, 249));
        super.setFilled(true);
    }

    /**
     * Sets the starting position of the paddle
     */
    protected void reset(){
        setPosition(START_X, START_Y);
}

    /**
     * Gets the scalar by which to multiply the xSlope if the paddle
     * is hit based on where on the paddle the ball hit
     * @return the scalar
     * @param ballX the ball's x position
     * @param xSlope the ball's x slope
     */
    public double getXSlopeScalar(double ballX, double xSlope){
        double padCen = (getX() + 50);
        double diff = (ballX - padCen);
        return diff/20.0;
    }

}
