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
package com.github.savinov.cardpairs.ui;

import com.github.savinov.cardpairs.model.CardTable;
import com.github.savinov.cardpairs.model.GameModel;

/**
 * The interface represents user interface.
 * 
 * @author Guram Savinov
 * 
 */
public interface UserInterface {

    /**
     * Sets the game model.
     * 
     * @param model
     *            the new game model
     */
    public void setGameModel(GameModel model);

    /**
     * Display card table.
     * 
     * @param table
     *            the card table
     */
    public void displayCardTable(CardTable table);

    /**
     * Display scores.
     * 
     * @param pairs
     *            the pairs
     * @param totalFlips
     *            the total flips
     * @param totalTime
     *            the total time
     * @param message
     *            the message
     */
    public void displayScores(int pairs, int totalFlips, long totalTime,
            String message);

    /**
     * Display turn.
     * 
     * @param turn
     *            the turn
     */
    public void displayTurn(boolean turn);

    /**
     * Sets card opened/closed state.
     * 
     * @param index
     *            the card index
     * @param opened
     *            the card opening status
     */
    public void setCardOpened(int index, boolean opened);

    /**
     * Removes the card.
     * 
     * @param index
     *            the card index
     */
    public void removeCard(int index);

}
