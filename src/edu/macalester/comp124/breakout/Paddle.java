package edu.macalester.comp124.breakout;

import comp124graphics.Rectangle;

public class Paddle extends Rectangle{

    //private BreakoutGame canvas;
    private static final int START_X = 350;
    private static final int START_Y = 700;


    public Paddle(){
        super(START_X, START_Y, 100, 10);
        super.setFilled(true);
    }

    protected void reset(){
        setPosition(START_X, START_Y);
}

    public double getB(double x, double xSlope){
        double padCen = (getX() + 50);
        double diff = (padCen - x);
//        if (Math.abs(diff) >= 0 && Math.abs(diff) < 10){
//            return 1;
//        }
//        if (Math.abs(diff) >= 10 && Math.abs(diff) < 20){
//            return 1.2;
//        }
        if (((xSlope) > 0 && diff < 0) || (xSlope <= 0 && diff > 0)){
            return (1 + Math.abs(diff)/50);
        }
        if ((xSlope > 0 && diff >= 0) || (xSlope <= 0 && diff < 0)){
            return -(1 + Math.abs(diff)/50);
        }
        else{
            return 1;
        }
    }

}
