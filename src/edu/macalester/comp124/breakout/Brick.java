package edu.macalester.comp124.breakout;

import comp124graphics.GraphicsGroup;
import comp124graphics.Rectangle;
import java.awt.Color;

public class Brick extends GraphicsGroup {

    private Color c; private int xPos; private  int yPos;

    public Brick(Color color, int x, int y){
        c = color;
        xPos = x;
        yPos = y;
        draw();
    }

    private void draw(){
        Rectangle brick = new Rectangle(xPos, yPos, 98, 20);
        brick.setFilled(true);
        brick.setFillColor(c);
        add(brick);
    }

}
