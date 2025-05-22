/*********************************************************************************
*File: Creature.java
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

import javax.swing.Timer;

public interface Creature {
	public void move();// Calculates new position of the Creature.
	public boolean isAlive();
	public void update();// Decides which frame should be current.
	public int getScorePoint();
	Timer getAnimationTimer();
}
