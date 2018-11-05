package edu.macalester.comp124.breakout;


import comp124graphics.CanvasWindow;

import java.awt.event.MouseMotionListener;

/**
 * Main program for the breakout game.
 *
 */
public class BreakoutGame extends CanvasWindow implements MouseMotionListener {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 1000;
    Ball ball = new Ball(this);
    BunchOBricks one = new BunchOBricks(this);
    Paddle paddle = new Paddle();

    public BreakoutGame() {
        super("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        addMouseMotionListener(this);
    }

    public static void main(String[] args) {
        BreakoutGame prog = new BreakoutGame();
        prog.run();

    }

    public void run() {
        one.draw();
        add(ball);
        add(paddle);
        while (!ball.ballsGone() && !one.bricksGone()){
            ball.startBall();
            pause(1000);
            while (ball.inBounds()) {
                if (ball.getYPosition() > 400) {
                    ball.paddleHit(paddle);
                    ball.updatePosition();
                    ball.changeSlopes();

                } else {
                    one.ifHit(ball);
                    ball.updatePosition();
                    ball.changeSlopes();
                    one.ifHit(ball);

                }
            }
            ball.resetBall();
            paddle.reset();
            pause(1000);
        }
        ball.resetGameBall();
        one.resetBricks();
        paddle.reset();
        pause(1000);
    }

    public void mouseMoved(java.awt.event.MouseEvent e) {
        if (e.getX() - 50 >=0 && e.getX() + 50 <= 800) {
            paddle.setPosition(e.getX() - 50, 700);
        }
    }
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    public void mouseClicked(java.awt.event.MouseEvent e) {
    }
    public void mouseDragged(java.awt.event.MouseEvent e){
    }

}



