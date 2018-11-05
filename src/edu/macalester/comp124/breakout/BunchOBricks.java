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

    protected void draw(){
        Color color1 = new Color(255, 241,0);
        Color color2 = new Color(255, 178,0);
        Color color3 = new Color(255, 28,0);
        Color color4 = new Color(255,0, 218);
        Color color5 = new Color(137,0, 255);
        Color[] colors = new Color[5];
        colors[0] = color1; colors[1] = color2; colors[2] = color3; colors[3] = color4; colors[4] = color5;
        for (int j=0; j<10; j++){
            for (int i = 0; i < 8; i++){
                Brick brick = new Brick(colors[j/2], i*100, 100+j*23);
                canvas.add(brick);
                bricks.add(brick);
            }
        }
    }

    protected void resetBricks(){
        for(Brick b : bricks) {
            canvas.remove(b);
        }
        bricks.clear();
    }
    protected void ifHit(Ball ball){
        for (Point.Double p : ball.corners){
            if (ball.brickHit(p)){
                breakB(ball.getBrick(p));
            }
        }
    }
    public void breakB(Brick brick){
        canvas.remove(brick);
        bricks.remove(brick);
    }

    protected int getNumberOfBricks(){
        numberOfBricks = bricks.size();
        return numberOfBricks;
    }

    protected boolean bricksGone(){
        return getNumberOfBricks() == 0;
        }
    }





