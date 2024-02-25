package com.SnakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import com.SnakeGame.model.Food;
import com.SnakeGame.model.Snake;
import com.SnakeGame.util.GameConst;

public class SnakeGame extends JPanel implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 553335161076992094L;
	private static boolean gameover = false;
	private Snake snake;
	private Food food;

	SnakeGame() {
		setPreferredSize(new Dimension(GameConst.WIDTH, GameConst.HEIGHT));
		setBackground(Color.black);

		addKeyListener(this);
		setFocusable(true);

		snake = new Snake();

		food = new Food();
		food.updatePosition();

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	void draw(Graphics g) {
		food.draw(g);
		snake.draw(g);
		drawScore(g);
	}

	private void drawScore(Graphics g) {
		g.setFont(new Font(Font.SERIF, Font.BOLD, 16));
		if (gameover) {
			g.setColor(Color.RED);
			g.drawString("GAME OVER: " + snake.getLength(), GameConst.SQUARE_SIZE - 16, GameConst.SQUARE_SIZE);
		} else {
			g.drawString("Score: " + snake.getLength(), GameConst.SQUARE_SIZE - 16, GameConst.SQUARE_SIZE);

		}
	}

	void gameStatusUpdate() {
		if (snake.getHead().checkCollision(food.getPosition())) {
			snake.grow();
			food.updatePosition();
		}

		snake.updatePosition();
		checkGameOver();
	}

	private void checkGameOver() {
		if (snake.checkCollision()) {
			gameover = true;
		}

	}

	public void startGame() {
		while (!gameover) {
			gameStatusUpdate();
			repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		snake.move(e);

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
