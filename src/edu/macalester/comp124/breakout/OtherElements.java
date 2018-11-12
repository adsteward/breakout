package edu.macalester.comp124.breakout;

import comp124graphics.*;
import comp124graphics.Image;
import comp124graphics.Rectangle;
import java.awt.*;


public class OtherElements{
    private BreakoutGame canvas;
    private Color pink;
    private Font font;
    protected Image title; protected Rectangle titleBox; protected Rectangle titleBorder;
    protected Rectangle startBox; protected Rectangle startBorder; protected GraphicsText startText;
    protected Rectangle ballBox;


    public OtherElements(BreakoutGame canvas){
        this.canvas = canvas;
        pink = new Color(254, 27, 225);
        font = new Font("Courier", Font.PLAIN, 24);

    }

    /**
     * Draws title boxes and adds them to the canvas
     */
    protected void drawTitle(){
        titleBox = new Rectangle(155,380, 490, 200);
        titleBorder = new Rectangle(150, 375, 500, 210);
        titleBorder.setFillColor(pink);
        titleBorder.setFilled(true);
        titleBox.setFillColor(Color.BLACK);
        titleBox.setFilled(true);
        title = new Image(206, 400, "124-hw4/BreakoutText/breakoutText.png");

        canvas.add(titleBorder);
        canvas.add(titleBox);
        canvas.add(title);
    }

    /**
     * Draws a box and balls in the corner representing how
     * many turns the player has left
     * @param ballCount the number of balls the player has left
     */
    protected void drawBallCount(int ballCount){
        ballBox = new Rectangle(55, 725, 50, 20);
        ballBox.setFillColor(pink);
        ballBox.setFilled(true);
        canvas.add(ballBox);
        int radius = 10;
        for (int i = 0; i < ballCount; i++){
            Ellipse circle = new Ellipse(60 + i*(radius + 5), 730, radius, radius);
            circle.setFillColor(Color.black);
            circle.setFilled(true);
            canvas.add(circle);
        }

    }

    /**
     * Erases the box and balls left
     * @param ballCount how many balls there are to be erased
     */
    protected void clearBallCount(int ballCount){
        canvas.remove(ballBox);
        for (int i = 0; i < ballCount; i++){
            if (canvas.getElementAt(60 + i*(15), 730) instanceof Ellipse){
                canvas.remove(canvas.getElementAt(60 + i*(15), 730));
            }
        }
    }

    /**
     * Draws the start "button"
     */
    protected void drawStartGame(){
        startBorder = new Rectangle(355, 525, 90, 50);
        startBorder.setFillColor(pink);
        startBorder.setFilled(true);

        startBox = new Rectangle(360, 530, 80, 40);
        startBox.setFillColor(Color.black);
        startBox.setFilled(true);

        startText = new GraphicsText("START", 365, 555);
        startText.setFont(font);
        startText.setStrokeColor(new Color(88, 30, 220));

        canvas.add(startBorder);
        canvas.add(startBox);
        canvas.add(startText);
    }

    /**
     * Removes the title box, title, and start elements
     */
    protected void removeTitle(){
        canvas.remove(titleBorder);
        canvas.remove(titleBox);
        canvas.remove(title);
        canvas.remove(startBorder);
        canvas.remove(startBox);
        canvas.remove(startText);
    }

    /**
     * Adds the title box and "you won" image
     */
    protected void ifWon(){
        canvas.add(titleBorder);
        canvas.add(titleBox);
        Image wonText = new Image(205, 400, "124-hw4/BreakoutText/wonText.png");
        canvas.add(wonText);
    }

    /**
     * Adds the title box and "you lost" image
     */
    protected void ifLost(){
        canvas.add(titleBorder);
        canvas.add(titleBox);
        Image lostText = new Image(213, 400, "124-hw4/BreakoutText/lostText.png");
        canvas.add(lostText);
    }
}
