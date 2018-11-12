package edu.macalester.comp124.breakout;

import comp124graphics.CanvasWindow;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Main program for the breakout game.
 *
 */
public class BreakoutGame extends CanvasWindow implements MouseMotionListener, MouseListener {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 1000;
    private Ball ball = new Ball(this);
    private BunchOBricks wall = new BunchOBricks(this);
    private Paddle paddle = new Paddle();
    private OtherElements graphics = new OtherElements(this);
    private boolean startGame;

    public BreakoutGame() {
        super("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);
        super.setBackground(Color.BLACK);

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    public static void main(String[] args) {
        BreakoutGame prog = new BreakoutGame();
        prog.addElements();
        prog.run(false);

    }

    /**
     * Runs the game while the player has turns left, there are still bricks
     * and the startGame boolean is true. After one of these becomes false,
     * the game is over and the graphics for if you won or lost appear.
     * @param start a boolean for whether or not you want the game to start.
     */
    private void run(boolean start) {
        startGame = start;
        while (!startGame){
            pause(100);
        }
        while (!ball.ballsGone() && !wall.bricksGone() && startGame) {
                moveBall();
                resetTurn();
            }
        pause(100);
        remove(ball);
        remove(paddle);
        if (wall.bricksGone()){
            graphics.ifWon();
        }
        else{
            graphics.ifLost();
        }
    }

    /**
     * Adds the bricks, paddle, ball, and initial graphics to the canvas
     */
    private void addElements(){
        wall.draw();
        add(ball);
        add(paddle);
        graphics.drawTitle();
        graphics.drawStartGame();
        graphics.drawBallCount(3);
    }

    /**
     * Starts the ball moving and while the ball is above the paddle checks if
     * a wall or the paddle were hit then updates the ball's position and its slopes
     */
    private void moveBall(){
        ball.startBall();
        pause(1000);
        while (ball.inBounds()) {
            wall.ifHit(ball);
            ball.paddleHit(paddle);
            ball.updatePosition(getBallSpeed());
            ball.changeSlopes();
            wall.ifHit(ball);
        }
    }

    /**
     * Resets the ball, paddle, and ball counter
     */
    private void resetTurn(){
        ball.resetBall();
        paddle.reset();
        pause(1000);
        graphics.clearBallCount(ball.ballCount + 1);
        graphics.drawBallCount(ball.ballCount);
    }


    /**
     * Slows down the ball over time to adjust for how it goes
     * faster as bricks are broken
     */
    private double getBallSpeed(){
        return 1 +  ((double) wall.getNumberOfBricks()/160);
    }

    /**
     * Sets the center of the paddle to be the mouse's x position
     */
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

    /**
     * If the start "button" is clicked, the initial graphics go away
     * and the startGame boolean is set to true
     */
    public void mouseClicked(java.awt.event.MouseEvent e) {
        if (getElementAt(e.getX(), e.getY()).equals(graphics.startBox) ||
                getElementAt(e.getX(), e.getY()).equals(graphics.startText)){
            graphics.removeTitle();
            startGame = true;
        }

    }
    public void mouseDragged(java.awt.event.MouseEvent e){
    }

}



