/*
 * CardPairs is an open source project that represents memory game variation with cards.
 * Copyright (C) 2011  Guram Savinov <savinov.guram@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.savinov.cardpairs.ui.swing;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * The frame that can be centred on the screen.
 * 
 * @author Guram Savinov
 * 
 */
@SuppressWarnings("serial")
public class CentrableFrame extends JFrame {

    /**
     * Move frame to the center of the screen
     */
    public void MoveToCenter() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setLocation((screenWidth / 2) - (getWidth() / 2), (screenHeight / 2)
                - (getHeight() / 2));
    }

}
