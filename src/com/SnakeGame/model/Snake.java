package com.SnakeGame.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.SnakeGame.util.GameConst;
import com.SnakeGame.util.Position;

public class Snake {
	private ArrayList<Position> snakeBody;
	private static int xVelocity = 0;
	private static int yVelocity = 0;

	public Snake() {
		Position snakeHead = new Position(5, 5);
		snakeBody = new ArrayList<Position>();
		snakeBody.add(snakeHead);
	}

	public int getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(int xVel) {
		xVelocity = xVel;
	}

	public int getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(int yVel) {
		yVelocity = yVel;
	}

	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		for (int i = 0; i < snakeBody.size(); i++) {
			Position part = snakeBody.get(i);
			if (i != 0)
				g.fillOval(part.getX() * GameConst.SQUARE_SIZE, part.getY() * GameConst.SQUARE_SIZE,
						GameConst.SQUARE_SIZE, GameConst.SQUARE_SIZE);
			else
				g.fillRoundRect(part.getX() * GameConst.SQUARE_SIZE, part.getY() * GameConst.SQUARE_SIZE,
						GameConst.SQUARE_SIZE, GameConst.SQUARE_SIZE, 10, 10);
		}
	}

	public void updatePosition() {
		Position snakeHead = getHead();
		for (int i = snakeBody.size() - 1; i > 0; i--) {
			Position currPart = snakeBody.get(i);
			Position prevPart = snakeBody.get(i - 1);

			currPart.setX(prevPart.getX());
			currPart.setY(prevPart.getY());
		}
		snakeHead.setX(snakeHead.getX() + xVelocity);
		snakeHead.setY(snakeHead.getY() + yVelocity);

	}

	public void move(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP && yVelocity != 1) {
			xVelocity = 0;
			yVelocity = -1;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN && yVelocity != -1) {
			xVelocity = 0;
			yVelocity = 1;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT && xVelocity != 1) {
			xVelocity = -1;
			yVelocity = 0;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT && xVelocity != -1) {
			xVelocity = 1;
			yVelocity = 0;
		}
	}

	public Position getHead() {
		return snakeBody.get(0);
	}

	public int getLength() {
		return snakeBody.size() - 1;
	}

	public void grow() {
		Position tail = snakeBody.get(snakeBody.size() - 1);
		Position newTail = new Position(tail.getX(), tail.getY());
		snakeBody.add(newTail);
	}

	public boolean checkCollision() {
		Position snakeHead = getHead();
		for (int i = 1; i < snakeBody.size(); i++)
			if (snakeHead.checkCollision(snakeBody.get(i)))
				return true;
		if (snakeHead.getX() * GameConst.SQUARE_SIZE < 0 || snakeHead.getY() * GameConst.SQUARE_SIZE < 0
				|| snakeHead.getX() * GameConst.SQUARE_SIZE > GameConst.WIDTH
				|| snakeHead.getY() * GameConst.SQUARE_SIZE > GameConst.HEIGHT)
			return true;
		return false;
	}

}