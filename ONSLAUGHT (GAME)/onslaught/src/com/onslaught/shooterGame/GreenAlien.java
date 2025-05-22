/*********************************************************************************
*File: GreenAlien.java
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

public class GreenAlien extends Alien {

	// Constructor calls Class Alien's constructer with given arguments
	//		and sets speed to 5.
	public GreenAlien(BufferedImage[] frames, int frameLivingLimit, int x, int y) {
		super(frames, frameLivingLimit, x, y);
		
		setMoveSpeed(5);
	}

	// Constructor creates a BufferedImage array with given filePath argument
	//		and calls the constructor GreenAlien(BufferedImage[] frames, int frameLivingLimit, int x, int y)
	public GreenAlien(String filePath, int row, int col, int frameLivingLimit,
			int x, int y) throws IOException {
		this(SpriteSheetLoader.createSprites(filePath, row, col), frameLivingLimit, x, y);
	}

	// shooting function only changes manIsDown field to true,
	//		because the strength of the GreenAlien is 0 which
	//		means that it will die after one shot.
	@Override
	public void shooting() {
		manIsDown = true;
	}

}
