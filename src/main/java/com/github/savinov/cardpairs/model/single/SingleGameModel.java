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
package com.github.savinov.cardpairs.model.single;

import com.github.savinov.cardpairs.model.BoardSize;
import com.github.savinov.cardpairs.model.CardTable;
import com.github.savinov.cardpairs.model.GameModel;
import com.github.savinov.cardpairs.ui.UserInterface;

/**
 * The single game model implementation.
 * 
 * @author Guram Savinov
 * 
 */
public class SingleGameModel implements GameModel {

    /**
     * Instantiates a new single game model.
     * 
     * @param boardSize
     *            the board size
     */
    public SingleGameModel(BoardSize boardSize) {
        table = new CardTable(boardSize);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.github.savinov.cardpairs.model.GameModel#setUserInterface(com.github
     * .savinov.cardpairs.ui.UserInterface)
     */
    public void setUserInterface(UserInterface ui) {
        this.ui = ui;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.github.savinov.cardpairs.model.GameModel#startGame()
     */
    public void startGame() {
        initGame();
        ui.displayCardTable(table);
        startTime = System.currentTimeMillis();
    }

    /**
     * Initialize the game variables.
     */
    protected void initGame() {
        prevIndex = -1;
        pairs = 0;
        totalPairs = 0;
        totalFlips = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.github.savinov.cardpairs.model.GameModel#checkOpenedCard(int)
     */
    public void checkOpenedCard(int index) {
        checkOpenedCard(index, true);
    }

    /**
     * Check opened card.
     * 
     * @param index
     *            the card index
     * @param countPairs
     *            the count pairs flag
     */
    protected void checkOpenedCard(int index, boolean countPairs) {
        ui.setCardOpened(index, true);
        totalFlips++;

        if (prevIndex == -1) {
            prevIndex = index;
            return;
        }

        int prevNumber = table.getCardNumber(prevIndex);
        int curNumber = table.getCardNumber(index);

        if (prevNumber == curNumber) {
            ui.removeCard(prevIndex);
            ui.removeCard(index);
            prevIndex = -1;

            totalPairs++;
            if (countPairs) {
                pairs++;
            }

            if (totalPairs == table.getTotalPairs()) {
                long totalTime = System.currentTimeMillis() - startTime;
                ui.displayScores(pairs, totalFlips, totalTime, getMessage());
            }
        } else {
            ui.setCardOpened(prevIndex, false);
            prevIndex = index;
        }
    }

    /**
     * Gets the message for the game scores.
     * 
     * @return the message
     */
    protected String getMessage() {
        return "Game over";
    }

    private int prevIndex;
    private int totalFlips;
    private int totalPairs;

    /** The pairs. */
    protected int pairs;

    /** The start time. */
    protected long startTime;

    /** The user interface. */
    protected UserInterface ui;

    /** The card table. */
    protected CardTable table;

}
