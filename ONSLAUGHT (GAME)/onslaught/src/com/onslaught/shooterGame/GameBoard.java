/*********************************************************************************
 * File: GameBoard.java
 * Author: SCARCE
 * Description: Modified version of the shooter game originally by Onur Ozuduru.
 *
 * License: The MIT License (MIT)
 *
 * Original work copyright (c) 2014 Onur Ozuduru
 * Modified by SCARCE (2025)
 *
 * The original software is licensed under the MIT License.
 * For original source: github.com/onurozuduru
 *********************************************************************************/

package com.onslaught.shooterGame;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import com.onslaught.shooterGame.Game.GameState;

public class GameBoard extends JPanel {
	private final String backgroundPath = "images/backgrounds/background.png";
	private EnemyGenerator generator;
	protected static boolean isGameOver = false;
	private boolean isStopped;
	private int score;

	private Clip bangClip;
	private Cannon cannon;
	private JLabel lblScore;
	private JPanel headerPanel;
	private JButton restartButton, stopButton;
	private Timer mainTimer;
	private Font defaultFont;
	private JCheckBox soundEffectCheck;

	public GameBoard() throws IOException {
		isStopped = false;
		generator = new EnemyGenerator();
		isGameOver = false;
		score = 0;
		defaultFont = new Font(Font.SERIF, Font.BOLD, 24);

		initSoundEffectOption();
		initSoundClip();
		setBounds(0, 0, Game.WIDTH, Game.HEIGHT);
		setLayout(null);
		setCursor(Game.CURSOR_UNLOCKED);

		setupHeader();
		setupCannon();
	}

	private void initSoundEffectOption() {
		soundEffectCheck = new JCheckBox("Sound Effects", true);
		soundEffectCheck.setBackground(new Color(0, 0, 0, 0));
		soundEffectCheck.setFont(defaultFont);
		soundEffectCheck.setFocusable(false);
	}

	private void initSoundClip() {
		URL clipUrl = getClass().getResource("sound/laser.wav");
		if (clipUrl != null) {
			try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(clipUrl)) {
				bangClip = AudioSystem.getClip();
				bangClip.open(audioIn);
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
				soundEffectCheck.setSelected(false);
				soundEffectCheck.setEnabled(false);
				e.printStackTrace();
			}
		} else {
			soundEffectCheck.setSelected(false);
			soundEffectCheck.setEnabled(false);
		}
	}

	private void setupHeader() {
		headerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		headerPanel.setBounds(0, 0, Game.WIDTH, 50);
		headerPanel.setOpaque(true);
		headerPanel.setBackground(new Color(0, 0, 0, 0));

		JLabel scoreMsg = new JLabel("Your Score: ");
		scoreMsg.setForeground(Color.BLACK);
		scoreMsg.setFont(defaultFont);

		lblScore = new JLabel(String.valueOf(score));
		lblScore.setForeground(Color.BLACK);
		lblScore.setFont(defaultFont);

		headerPanel.add(soundEffectCheck);
		headerPanel.add(scoreMsg);
		headerPanel.add(lblScore);

		try {
			BufferedImage[] buttonIcons = SpriteSheetLoader.createSprites("images/buttons/innerbuttons.png", 2, 3);
			ImageIcon[] icons = new ImageIcon[buttonIcons.length];
			for (int i = 0; i < buttonIcons.length; i++) {
				icons[i] = new ImageIcon(buttonIcons[i]);
			}
			restartButton = createButton(icons[0], icons[1], icons[2]);
			stopButton = createButton(icons[3], icons[4], icons[5]);
			headerPanel.add(restartButton);
			headerPanel.add(stopButton);
		} catch (IOException e) {
			e.printStackTrace();
		}

		add(headerPanel);
	}

	private JButton createButton(Icon icon0, Icon icon1, Icon icon2) {
		JButton button = new JButton(icon0);
		button.setPreferredSize(new Dimension(icon0.getIconWidth(), icon0.getIconHeight()));
		button.setBorderPainted(false);
		button.setFocusable(false);
		button.setFocusPainted(false);
		button.setRolloverEnabled(true);
		button.setRolloverIcon(icon1);
		button.setPressedIcon(icon2);
		button.setContentAreaFilled(false);
		button.addActionListener(new ButtonHandler());
		return button;
	}

	private void setupCannon() {
		cannon = new Cannon();
		add(cannon);

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				cannon.rotate(e.getX(), e.getY(), false);
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override public void mousePressed(MouseEvent e) { Cannon.setFire(true); }
			@Override public void mouseReleased(MouseEvent e) { Cannon.setFire(false); }
		});
	}

	public Thread generator() {
		for (int i = 0; i < 4; i++) add(generator.generateNewEnemy());

		return new Thread(() -> {
			int delay = 1000;
			while (!isGameOver && !isStopped) {
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null,
							"Error: " + e.getMessage(),
							"Oops! Something went wrong!", JOptionPane.ERROR_MESSAGE);
				}
				if (isStopped) return;
				if (!isGameOver) add(generator.generateNewEnemy());
				delay = Math.max(Game.REFRESH_TIME + 50, delay - 4);
			}
		});
	}

	public void gameLoop() {
		generator().start();
		mainTimer = new Timer(Game.REFRESH_TIME, e -> {
			repaint();
			if (isGameOver) {
				gameOver();
				return;
			}
			for (Component comp : getComponents()) {
				if (comp instanceof Creature creature && !creature.isAlive()) {
					if (soundEffectCheck.isSelected() && bangClip != null) {
						if (bangClip.isRunning()) bangClip.stop();
						bangClip.setFramePosition(0);
						bangClip.start();
					}
					score += creature.getScorePoint();
					lblScore.setText(String.valueOf(score));
					remove(comp);
				}
			}
		});
		mainTimer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(backgroundPath)), 0, 0, null);
	}

	public void gameOver() {
		mainTimer.stop();
		for (Component c : getComponents()) {
			if (c instanceof Creature creature) creature.getAnimationTimer().stop();
			remove(c);
		}

		int option = JOptionPane.showConfirmDialog(this,
				"Your Score: " + score + "\nDo you want to play a new Game?",
				"GAME OVER", JOptionPane.YES_NO_OPTION);

		Game.setHighScore(score);
		Game.setState(option == JOptionPane.YES_OPTION ? GameState.CONTINUE : GameState.OVER);
	}

	private class ButtonHandler implements ActionListener {
		private void stopGame() {
			isStopped = true;
			mainTimer.stop();
			for (Component c : getComponents()) {
				if (c instanceof Creature creature) creature.getAnimationTimer().stop();
				remove(c);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == restartButton) {
				stopGame();
				Game.setState(GameState.CONTINUE);
			} else if (e.getSource() == stopButton) {
				stopGame();
				Game.setState(GameState.OVER);
			}
		}
	}
}