/*
 * Copyright (c) 2014 Onur Ozuduru
 *
 * All source codes (all .java files) of this software are licensed under
 * The MIT License (MIT).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * For more information about license of the files in
 *   - /src/com/ozuduru/shooterGame/images
 *   - /src/com/ozuduru/shooterGame/sound
 *
 * Please see the file /src/com/ozuduru/shooterGame/data/credits.txt
 *
 * Modified by SCARCE in 2025 for enhancements and modernization.
 */

package com.onslaught.shooterGame;

import javax.swing.JOptionPane;
import java.awt.HeadlessException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			Game game = new Game();
			game.startGame();
		} catch (HeadlessException | IllegalArgumentException e) {
			showError("We are sorry about that!\nError: " + e.getMessage());
		} catch (IOException e) {
			showError("It seems that there is a problem with your file system!\nError: " + e.getMessage());
		}
	}

	private static void showError(String message) {
		JOptionPane.showMessageDialog(
				null,
				message,
				"Oops!! Something went wrong!",
				JOptionPane.ERROR_MESSAGE
		);
	}
}
