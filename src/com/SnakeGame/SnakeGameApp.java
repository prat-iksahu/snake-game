package com.SnakeGame;

import javax.swing.JFrame;

import com.SnakeGame.util.GameConst;

public class SnakeGameApp {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Snake Game");
		frame.setVisible(true);
		frame.setSize(GameConst.WIDTH, GameConst.HEIGHT);
		frame.setLocationRelativeTo(null);// To open Window at center
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SnakeGame snakeGame = new SnakeGame();
		frame.add(snakeGame);
		frame.pack();
		snakeGame.requestFocus();
		snakeGame.startGame();

	}
}
