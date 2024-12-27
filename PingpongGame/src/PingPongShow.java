import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PingPongShow extends JPanel implements ActionListener, KeyListener {
    boolean xMotion = false;
    boolean yMotion = false;
    int randomCache;
    int delay = 20;
    Timer ballTimer = new Timer(20, this);
    public static int aScore = 0;
    public static int bScore = 0;
    public int xBall = 800 / 2 - 15;
    public int yBall = 500 / 2 - 15;
    int yOfARacket = 100;
    int yOfBRacket = 100;

    boolean isUp;
    boolean isDown;
    boolean isW;
    boolean isS;

    // ballRadius=15
    public PingPongShow() {
        // TODO Auto-generated constructor stub
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        Random random = new Random();
        randomCache = random.nextInt(2);
        if (randomCache == 1) {
            xMotion = true;
        }
        randomCache = random.nextInt(2);
        if (randomCache == 1) {
            yMotion = true;
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);
        // Ball
        g.setColor(Color.WHITE);
        g.fillOval(xBall, yBall, 30, 30);
        // aRacket
        g.setColor(Color.RED);
        g.fillRect(30, yOfARacket, 20, 100);
        // bRacket
        g.fillRect(getWidth() - 50, yOfBRacket, 20, 100);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            ballTimer.start();
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {

            isUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            isDown = true;
        }
        if (e.getKeyChar() == 'w') {

            isW = true;
        }
        if (e.getKeyChar() == 's') {

            isS = true;
        }
        repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_UP) {

            isUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {

            isDown = false;
        }
        if (e.getKeyChar() == 'w') {

            isW = false;
        }
        if (e.getKeyChar() == 's') {

            isS = false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == ballTimer) {
            if (xMotion == true) {
                xBall += 5;
                repaint();
                if (xBall + 30 >= getWidth() - 50) {
                    if (yBall + 15 >= yOfBRacket && yBall + 15 <= yOfBRacket + 100) {
                        xMotion = false;
                        delay -= 1;
                        if (delay >= 0) {
                            ballTimer.setDelay(delay);
                        }
                    } else {
                        aScore++;
                        ballTimer.stop();
                        delay = 20;
                        ballTimer.setDelay(delay);
                        xBall = getWidth() / 2 - 15;
                        yBall = getHeight() / 2 - 15;
                    }
                }
            }
            if (xMotion == false) {
                xBall -= 5;
                repaint();
                if (xBall <= 50) {
                    if (yBall >= yOfARacket && yBall <= yOfARacket + 100) {
                        xMotion = true;
                        delay -= 1;
                        if (delay >= 0) {
                            ballTimer.setDelay(delay);
                        }
                    } else {
                        bScore++;
                        ballTimer.stop();
                        delay = 20;
                        ballTimer.setDelay(delay);
                        xBall = getWidth() / 2 - 15;
                        yBall = getHeight() / 2 - 15;
                    }
                }
            }
            if (yMotion == true) {
                yBall += 5;
                repaint();
                if (yBall + 30 >= getHeight()) {
                    yMotion = false;
                }
            }
            if (yMotion == false) {
                yBall -= 5;
                repaint();
                if (yBall <= 0) {
                    yMotion = true;
                }
            }
            if(isUp == true) {
                yOfBRacket -= 10;
                repaint();
            }
            if(isDown == true) {
                yOfBRacket += 10;
                repaint();
            }
            if(isW == true) {
                yOfARacket -= 10;
                repaint();
            }
            if(isS == true) {
                yOfARacket += 10;
                repaint();
            }

        }
    }

}
