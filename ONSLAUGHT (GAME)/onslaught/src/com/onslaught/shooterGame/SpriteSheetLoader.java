/*********************************************************************************
*File: SpriteSheetLoader.java
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
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import javax.imageio.ImageIO;

public class SpriteSheetLoader {
	
	public SpriteSheetLoader() {		
	}
	
	// It reads the image file from filePath and creates a BufferedImage array
	//		with dividing the image file into subimages according to given row and col values.
	public static BufferedImage[] createSprites(String filePath, int row, int col) 
			throws IOException {
		if(row <= 0 || col <= 0)
			throw new IllegalArgumentException("row and col must be positive!");
		
		// Size of array will be col * row
		BufferedImage[] sprites = new BufferedImage[row * col];
		
		// Read the Image
		BufferedImage spriteSheet = ImageIO.read(SpriteSheetLoader.class.getResourceAsStream(filePath)); //ImageIO.read(new File(filePath));
		
		// Calculate width and height of the each sprite.
		//		We assume that sprite sheet consists of all same sized sprites.
		int width = spriteSheet.getWidth() / col;
		int height = spriteSheet.getHeight() / row;

		// Get sprites (sub images) by left to right order from sprite sheet. 
		for(int i = 0; i < row; ++i)
			for(int j = 0; j < col; ++j)
				sprites[(i * col) + j] = spriteSheet.getSubimage(j * width, i * height, width, height);
		
		return sprites;
	}
}
