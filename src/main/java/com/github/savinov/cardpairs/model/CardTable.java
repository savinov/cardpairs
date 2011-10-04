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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the card table.
 * 
 * @author Guram Savinov
 * 
 */
public class CardTable implements Serializable {

    /**
     * Creates a new card table.
     * 
     * @param size
     *            the board size
     */
    public CardTable(BoardSize size) {
        switch (size) {
        case SMALL:
            rows = 4;
            cols = 6;
            break;
        case MEDIUM:
        default:
            rows = 5;
            cols = 8;
            break;
        case LARGE:
            rows = 6;
            cols = 10;
            break;
        case EXTRA_LARGE:
            rows = 8;
            cols = 12;
            break;
        }

        totalPairs = (cols * rows) / 2;

        List<Integer> randomNumbers = new ArrayList<Integer>();
        for (int i = 1; i < 55; i++)
            randomNumbers.add(i);
        Collections.shuffle(randomNumbers);

        cardNumbers = new ArrayList<Integer>();
        for (int i = 0; i < totalPairs; i++) {
            int number = randomNumbers.get(i);
            cardNumbers.add(number);
            cardNumbers.add(number);
        }
        Collections.shuffle(cardNumbers);

    }

    /**
     * Gets the number of the rows.
     * 
     * @return the number of the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gets the number of the columns.
     * 
     * @return the number of the columns
     */
    public int getCols() {
        return cols;
    }

    /**
     * Gets the number of the total pairs.
     * 
     * @return the number of the total pairs
     */
    public int getTotalPairs() {
        return totalPairs;
    }

    /**
     * Gets the card number.
     * 
     * @param index
     *            the index of the card
     * @return the card number
     */
    public int getCardNumber(int index) {
        return cardNumbers.get(index);
    }

    /**
     * Gets the list of the card numbers.
     * 
     * @return the list of the card numbers
     */
    public List<Integer> getCardNumbers() {
        return cardNumbers;
    }

    private int rows;
    private int cols;
    private int totalPairs;
    private List<Integer> cardNumbers;
    private static final long serialVersionUID = 1L;

}
