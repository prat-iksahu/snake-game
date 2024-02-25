package com.SnakeGame.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.SnakeGame.util.GameConst;
import com.SnakeGame.util.Position;

public class Food {
	private Position position;
	Random rand;

	public Food() {
		rand = new Random();
		position = new Position(0, 0);
		updatePosition();
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(position.getX() * GameConst.SQUARE_SIZE, position.getY() * GameConst.SQUARE_SIZE,
				GameConst.SQUARE_SIZE, GameConst.SQUARE_SIZE);
	}

	public void updatePosition() {

		position.setX(rand.nextInt(GameConst.WIDTH / GameConst.SQUARE_SIZE));
		position.setY(rand.nextInt(GameConst.HEIGHT / GameConst.SQUARE_SIZE));
	}

	public Position getPosition() {
		return position;
	}
}
