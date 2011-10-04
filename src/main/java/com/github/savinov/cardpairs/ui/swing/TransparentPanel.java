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

import java.awt.AlphaComposite;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * The {@code JPanel} element with the transparency feature.
 * 
 * @author Guram Savinov
 * 
 */
@SuppressWarnings("serial")
public class TransparentPanel extends JPanel {

    /**
     * Instantiates a new transparent panel.
     * 
     * @param image
     *            the image that will draw on the panel
     * @param background
     *            the background that will draw on the panel
     */
    public TransparentPanel(Image image, Container background) {
        this.image = image;
        this.background = background;

        int width = image.getWidth(null);
        int height = image.getHeight(null);
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                ratio * 0.01f));

        g2d.drawImage(image, 0, 0, null);
    }

    /**
     * Sets the ratio of the transparency.
     * 
     * @param stopRatio
     *            the new ratio, deciding value when to stop transparency
     *            animation
     */
    public void setRatio(final int stopRatio) {
        final int step = (stopRatio > ratio) ? 1 : -1;

        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (ratio != stopRatio) {
                    ratio += step;
                    background.repaint();

                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    private Image image;
    private int ratio = 100;
    private Container background;

}
