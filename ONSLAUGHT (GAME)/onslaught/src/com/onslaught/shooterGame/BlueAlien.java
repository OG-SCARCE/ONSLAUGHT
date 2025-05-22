/*********************************************************************************
*File: BlueAlien.java
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

import java.awt.image.BufferedImage;
import java.io.IOException;

public class BlueAlien extends Alien {
	private final int scorePoint = 200;
	
	protected int strength, shotCount;
	private int limit;// will be used as second living limit.
	
	// Constructor calls Class Alien's constructer with given arguments and
	//		half of the given frameLivingLimit argument because BlueAlien has
	//		a strength value which means that after one shot its shape will be changed
	//		but it will not die untill the shotCount value reaches to strength value.
	public BlueAlien(BufferedImage[] frames, int frameLivingLimit, int x, int y) {
		super(frames, frameLivingLimit / 2, x, y);
		
		setMoveSpeed(6);
		limit = frameLivingLimit;
		shotCount = 0;
		
		// Die after 3 shots.
		setStrength(3);
	}
	
	public BlueAlien(String filePath, int row, int col, int frameLivingLimit,
			int x, int y) throws IOException {
		this(SpriteSheetLoader.createSprites(filePath, row, col), frameLivingLimit, x, y);
	}
	
	// Strength value may be greater or equal to 0 otherwise it throws an exception.
	public void setStrength(int strength) {
		if(strength >= 0)
			this.strength = strength;
		else
			throw new IllegalArgumentException("Value of strength CANNOT BE a negative number!");
	}

	@Override
	public void shooting() {
		// After the first shot the animation will start from 6th frame so
		//		the shape will be changed.
		// When shotCount value equals to strength value the manIsDown will be become to true,
		//		so it will be dead.
		if(++shotCount < strength) {
			frameStart = 6;
			frameLivingLimit = limit;
		}
		else
			manIsDown = true;
		repaint();
	}

	// BlueAlien has own scorePoint value which is different from Alien's default value 100.
	@Override
	public int getScorePoint() {
		return this.scorePoint;
	}
}
