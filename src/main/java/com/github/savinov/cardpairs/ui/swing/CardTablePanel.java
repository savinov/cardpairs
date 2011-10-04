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

import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.github.savinov.cardpairs.model.CardTable;
import com.github.savinov.cardpairs.model.GameModel;

/**
 * The card table panel.
 * 
 * @author Guram Savinov
 * 
 */
@SuppressWarnings("serial")
public class CardTablePanel extends JPanel {

    /**
     * Instantiates a new card table panel.
     * 
     * @param table
     *            the card table
     * @param model
     *            the game model
     */
    public CardTablePanel(CardTable table, GameModel model) {
        this.table = table;
        this.model = model;
        preparePanel();

        List<Integer> cardNumbers = table.getCardNumbers();
        for (int number : cardNumbers) {
            cards.add(new Card(cardBackground, number, this));
        }

        for (Card card : cards) {
            add(card);
        }
    }

    /**
     * Test cards flipping.
     */
    public void testFlipping() {
        for (Card card : cards) {
            card.setOpened(true);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            card.setOpened(false);
        }
    }

    /**
     * Notify game model about opened card.
     * 
     * @param card
     *            the card instance
     */
    void notifyModel(Card card) {
        model.checkOpenedCard(cards.indexOf(card));
    }

    /**
     * Gets the card.
     * 
     * @param index
     *            the card index
     * @return the card instance
     */
    Card getCard(int index) {
        return cards.get(index);
    }

    /**
     * Generic cards panel preparing actions.
     */
    private void preparePanel() {
        setOpaque(false);
        setLayout(new GridLayout(table.getRows(), table.getCols(), 5, 5));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        cardBackground = ResourceLoader.getImage("deck/bg/b1fv.png");

        cards = new ArrayList<Card>();
    }

    private List<Card> cards;
    private Image cardBackground;
    private CardTable table;
    private GameModel model;

}
