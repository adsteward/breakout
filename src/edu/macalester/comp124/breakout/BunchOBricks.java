package edu.macalester.comp124.breakout;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BunchOBricks {

    private BreakoutGame canvas;
    private List<Brick> bricks;
    private int numberOfBricks;

    public BunchOBricks(BreakoutGame canvas){
        bricks = new ArrayList<>();
        this.canvas = canvas;
        draw();
    }

    /**
     * Draws all of bricks
     */
    protected void draw(){
        Color pink1 = new Color(254, 27, 225);
        Color pink2 = new Color(234, 32, 233);
        Color pink3 = new Color(215, 38, 241);
        Color pink4 = new Color(191, 36, 245);
        Color purple1 = new Color(168, 33, 249);
        Color purple2 = new Color(153, 29, 245);
        Color purple3 = new Color(138, 27, 241);
        Color purple4 = new Color(122, 28, 234);
        Color blue1 = new Color(104, 29, 227);
        Color blue2= new Color(88, 30, 220);

        Color[] colors = new Color[10];
        colors[0] = pink1; colors[1] = pink2;
        colors[2] = pink3; colors[3] = pink4;
        colors[4] = purple1; colors[5] = purple2;
        colors[6] = purple3; colors[7] = purple4;
        colors[8] = blue1; colors[9] = blue2;

        for (int j=0; j<10; j++){
            for (int i = 0; i < 8; i++){
                Brick brick = new Brick(colors[j], i*100, 100+j*23);
                canvas.add(brick);
                bricks.add(brick);
            }
        }
    }


    /**
     * If a brick is intersected by a corner of the ball,
     * the brick is broken by breakB
     * @param ball the ball
     */
    protected void ifHit(Ball ball){
        for (Point.Double p : ball.corners){
            if (ball.brickHit(p)){
                breakB(ball.getBrick(p));
            }
        }
    }

    /**
     * Removes a brick from the canvas and the brick array
     * @param brick
     */
    public void breakB(Brick brick){
        canvas.remove(brick);
        bricks.remove(brick);
    }

    /**
     * @return Gets the number of bricks left
     */
    protected int getNumberOfBricks(){
        numberOfBricks = bricks.size();
        return numberOfBricks;
    }

    /**
     * @return true if there are no more bricks on the
     * canvas/in the array, false if not
     */
    protected boolean bricksGone(){
        return getNumberOfBricks() == 0;
        }
    }





