import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MainGame extends JPanel implements ActionListener, KeyListener {
	// dino
	int dinoX = 50;
	int dinoY = 80;
	int jumpTimer = -8;
	boolean isJumping = false;
	boolean isShrinking = false;
	boolean stompStage = false;

	// cactus
	int cactusX = 900;
	int cactusY = 110;
	int speed = 5;

	// bird
	int birdX = 900;
	int birdY = 80;

	// timer
	Timer timer = new Timer(20, this);
	Timer cactusTimer = new Timer(20, this);
	Timer birdTimer = new Timer(20, this);
	Timer dinoTimer = new Timer(20, this);
	Timer stompTimer = new Timer(100, this);
	Timer speedTimer = new Timer(1000, this);

	// game
	int score = 0;
	boolean isLosing = false;
	Color losingColor = Color.WHITE;
	boolean obstruction = false;// false=cactus,true=birdJ
	int randomCache = 0;

	public MainGame() {
		// TODO Auto-generated constructor stub
		setBackground(Color.WHITE);
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		// ground
		g.fillRect(0, 150, 800, 50);
		// losing
		g.setColor(losingColor);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 150));
		g.drawString("YOU LOSE", 10, 140);
		// dino
		// g.setColor(Color.PINK);
		// g.fillRect(dinoX, dinoY, 40, 70);//hitbox
		g.setColor(Color.BLACK);
		if (isJumping == false) {
			if (stompStage == false) {
				g.fillRect(dinoX, dinoY + 50, 10, 15);// leg
				g.fillRect(dinoX + 20, dinoY + 50, 10, 20);// leg
			}
			if (stompStage == true) {
				g.fillRect(dinoX, dinoY + 50, 10, 20);// leg
				g.fillRect(dinoX + 20, dinoY + 50, 10, 15);// leg
			}
		} else {
			g.fillRect(dinoX, dinoY + 50, 10, 20);// leg
			g.fillRect(dinoX + 20, dinoY + 50, 10, 20);// leg
		}
		g.fillRect(dinoX - 10, dinoY + 20, 60, 30);// body
		g.fillRect(dinoX + 50, dinoY + 30, 10, 5);// arm
		if (isShrinking == false) {
			g.fillRect(dinoX + 30, dinoY - 5, 45, 25);// head
			g.setColor(Color.WHITE);
			g.fillOval(dinoX + 45, dinoY, 3, 3);// eye
		}
		if (isShrinking == true) {
			g.fillRect(dinoX + 50, dinoY + 20, 45, 25);// head
			g.setColor(Color.WHITE);
			g.fillOval(dinoX + 65, dinoY + 25, 3, 3);// eye
		}
		// cactus
		g.setColor(Color.GREEN);
		g.fillRect(cactusX, cactusY, 20, 40);
		// bird
		g.setColor(Color.RED);
		//g.fillRect(birdX, birdY, 5, 5);//hitbox
		g.setColor(Color.black);
		g.fillOval(birdX, birdY, 30, 20);
		g.fillArc(birdX-10, birdY+7, 50, 10, 90, 180);
		g.setColor(Color.WHITE);
		
		// score
		g.setColor(Color.white);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
		g.drawString("score : " + score, 10, 160);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			timer.start();
			dinoTimer.start();
			speedTimer.start();
			stompTimer.start();
			cactusTimer.start();
			cactusX = 900;
			birdX = 900;
			losingColor = Color.WHITE;
			score = 0;
			repaint();
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP
				|| e.getKeyCode() == KeyEvent.VK_W) {

			isJumping = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {

			isShrinking = true;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {

			isShrinking = false;

		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Random random = new Random();
		if (e.getSource() == timer) {
			score++;
			if(cactusTimer.isRunning()==false && birdTimer.isRunning()==false) {
				randomCache = random.nextInt(2);
				if(randomCache==0) {
					cactusTimer.start();
				}
				if(randomCache==1) {
					birdTimer.start();
				}
			}

			if (dinoX + 40 >= cactusX && dinoX <= cactusX) {
				if (dinoY + 70 >= cactusY) {
					losingColor = Color.RED;
					timer.stop();
					dinoTimer.stop();
					stompTimer.stop();
					speedTimer.stop();
					cactusTimer.stop();
					birdTimer.stop();
					speed = 5;
				}

			}
			if (dinoX + 40 >= birdX && dinoX <= birdX) {
				if (isShrinking==false) {
					losingColor = Color.RED;
					timer.stop();
					dinoTimer.stop();
					stompTimer.stop();
					speedTimer.stop();
					cactusTimer.stop();
					birdTimer.stop();
					speed = 5;
				}

			}

		}
		if (e.getSource() == cactusTimer) {
			cactusX -= speed;
			if (cactusX <= -20) {
				cactusX = 900;
				cactusTimer.stop();
			}

		}
		if (e.getSource() == birdTimer) {
			birdX -= speed;
			if (birdX <= -20) {
				birdX = 900;
				birdTimer.stop();
			}

		}
		if (e.getSource() == dinoTimer) {
			if (isJumping == true) {
				jumpTimer++;
				dinoY = (jumpTimer * jumpTimer);
				if (jumpTimer > 8) {
					isJumping = false;
					jumpTimer = -8;
				}
			}
		}
		if (e.getSource() == speedTimer) {
			speed += 1;
		}
		if (e.getSource() == stompTimer) {

			if (stompStage == false) {
				stompStage = true;

			} else if (stompStage == true) {
				stompStage = false;

			}
		}
		repaint();

	}

}
