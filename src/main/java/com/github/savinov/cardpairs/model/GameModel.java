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
package com.github.savinov.cardpairs.model;

import com.github.savinov.cardpairs.ui.UserInterface;

/**
 * Represents the model of the game.
 * 
 * @author Guram Savinov
 * 
 */
public interface GameModel {

    /**
     * Sets the user interface.
     * 
     * @param ui
     *            the user interface
     */
    public void setUserInterface(UserInterface ui);

    /**
     * Starts the game.
     */
    public void startGame();

    /**
     * Checks the opened card.
     * 
     * @param index
     *            the index of the card
     */
    public void checkOpenedCard(int index);

}
