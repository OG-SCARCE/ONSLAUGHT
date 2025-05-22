/*********************************************************************************
*File: EnemyGenerator.java
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
import java.util.Random;

public class EnemyGenerator {
	private final String fileGreenAlien = "images/creatures/greenalien.png",
			fileBlueAlien = "images/creatures/bluealien.png";
	
	private BufferedImage[] greenFrames, blueFrames;
	
	private Random rand;
	public EnemyGenerator() throws IOException {
		rand = new Random(1000000000);
		greenFrames = SpriteSheetLoader.createSprites(fileGreenAlien, 2, 5);
		blueFrames = SpriteSheetLoader.createSprites(fileBlueAlien, 3, 6);
	}
	
	public Alien generateNewEnemy() {
		// Randomly generates new creatures.
		int creatureType = rand.nextInt(2);
		
		switch (creatureType) {
		case 0:
			return new GreenAlien(greenFrames, 5, (rand.nextInt(100) + (Game.WIDTH - 100)), rand.nextInt(Game.HEIGHT - 200) + 50);
		case 1:
			return new BlueAlien(blueFrames, 12, (rand.nextInt(100) + (Game.WIDTH - 100)), rand.nextInt(Game.HEIGHT - 200) + 50);
		}
		return null;
	}

}
