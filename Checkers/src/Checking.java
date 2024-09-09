import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.JPanel;

public class Checking extends JPanel implements MouseListener {
	char[][] Board = new char[8][8];
	int bx = 80;
	int by = 60;
	boolean isWhiteChip = false;
	boolean isBlackChip = false;

	public Checking() {
		// TODO Auto-generated constructor stub
		this.addMouseListener(this);
		this.setFocusable(true);
		printBoard();
		setBoard();
		printBoard();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(Board[i][j]);
			}
			System.out.println();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		g.fillRect(40, 20, 720, 720);// 40, 20 meant to center the board
		g.setColor(Color.WHITE);
		g.fillRect(40 + 40, 20 + 40, 640, 640);// for now bx, by = 80, 60 will be base of the grid (stands for 0,0)
		// make black grid
		g.setColor(Color.BLACK);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				g.fillRect(bx + 80 + 160 * i, by + 160 * j, 80, 80);
				g.fillRect(bx + 160 * i, by + 80 + 160 * j, 80, 80);
			}
		}
		// paint board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (Board[i][j] == 'w') {
					g.setColor(Color.WHITE);
					g.fillOval(bx + 10 + 80 * j, by + 10 + 80 * i, 60, 60);

				}
				else if (Board[i][j] == 'b') {
					g.setColor(Color.WHITE);
					g.drawOval(bx + 10 + 80 * j, by + 10 + 80 * i, 60, 60);

				}
			}
		}
		repaint();

	}

	public void setBoard() {
		// clear board
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Board[i][j] = 'o';
			}
		}
		// set chip 1
		for (int i = 1; i < 8; i = i + 2) {
			Board[i][0] = 'w';
			Board[i][2] = 'w';
			Board[i][6] = 'b';
		}
		// set chip 2
		for (int i = 0; i < 8; i = i + 2) {
			Board[i][1] = 'w';
			Board[i][5] = 'b';
			Board[i][7] = 'b';
		}
	}
	public boolean isPlayable(int x,int y) {
		if(y > 7 ||x > 7) {
			return false;
		}
		else {
			//if choose white
			if(Board[y][x]=='w') {
				//if roll 0
				if (y==0&&x!=7) {
					if (Board[y+1][x+1] == 'o') {
						return true;
					}
					//if tacking
					else if(Board[y+1][x+1] == 'b'){
						if (Board[y+2][x+2]== 'o') {
							return true;
						}
					}
				}
			}else {
				return false;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int inputX = e.getX()-bx;
		int inputY = e.getY()-by;
		int inputIndexX = inputX/80;
		int inputIndexY = inputY/80;
		System.out.print(inputIndexY);
		System.out.println(inputIndexX);
		System.out.println(isPlayable(inputIndexX, inputIndexY));

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
