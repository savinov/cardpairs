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
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * The card representation.
 * 
 * @author Guram Savinov
 * 
 */
@SuppressWarnings("serial")
public class Card extends JPanel {

    /**
     * Instantiates a new card.
     * 
     * @param background
     *            the background image
     * @param number
     *            the number
     * @param table
     *            the card table
     */
    public Card(Image background, int number, final CardTablePanel table) {
        this.background = background;
        this.image = background;
        this.number = number;

        int width = background.getWidth(null);
        int height = background.getHeight(null);
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (opened) {
                    return;
                }
                table.notifyModel(Card.this);
            }
        });
    }

    /**
     * Paints card background or foreground with current alpha composite.
     * 
     * @param g
     *            the graphics context
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                alpha));

        g2d.drawImage(image, 0, 0, null);
    }

    /**
     * Sets the opened flag.
     * 
     * @param opened
     *            the new opened flag
     */
    void setOpened(boolean opened) {
        this.opened = opened;
        if (opened) {
            image = ResourceLoader.getImage("deck/fg/" + number + ".png");
        } else {
            image = background;
        }
        repaint();
    }

    /**
     * Sets the composite alpha.
     * 
     * @param alpha
     *            the new composite alpha
     */
    void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    private Image background;
    private Image image;
    private boolean opened = false;
    private int number;
    private float alpha = 1.0f;

}
