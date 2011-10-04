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

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

/**
 * The resource loader. Loads needed images from the file system.
 * 
 * @author Guram Savinov
 * 
 */
public class ResourceLoader {

    /**
     * Gets image icon from resources.
     * 
     * @param name
     *            the image name
     * @return the icon
     */
    public static ImageIcon getIcon(String name) {
        URL url = cl.getResource("resources/" + name);
        return new ImageIcon(url);
    }

    /**
     * Gets the image from resources.
     * 
     * @param name
     *            the image name
     * @return the image
     */
    public static Image getImage(String name) {
        return getIcon(name).getImage();
    }

    private static ClassLoader cl = ClassLoader.getSystemClassLoader();

}
