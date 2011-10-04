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
package com.github.savinov.cardpairs.model.network;

import com.github.savinov.cardpairs.model.BoardSize;
import com.github.savinov.cardpairs.model.single.SingleGameModel;
import com.github.savinov.cardpairs.ui.UserInterface;

/**
 * The basic network game model implementation. Client and server game models
 * extends this abstract class.
 * 
 * @author Guram Savinov
 * 
 */
abstract public class NetworkGameModel extends SingleGameModel {

    /**
     * Instantiates a new network game model.
     * 
     * @param boardSize
     *            the board size
     */
    public NetworkGameModel(BoardSize boardSize) {
        super(boardSize);
    }

    /**
     * Start a new connection thread and game in network mode.
     */
    public void startGame() {
        initGame();
        thread = new Thread(connection);
        thread.start();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.github.savinov.cardpairs.model.single.SingleGameModel#checkOpenedCard
     * (int)
     */
    public void checkOpenedCard(int index) {
        if (turn) {
            setTurn(false, true);
        } else {
            return;
        }

        connection.sendCardIndex(index);
        super.checkOpenedCard(index, true);
    }

    /**
     * Sets the start time.
     * 
     * @param startTime
     *            the new start time
     */
    void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    /**
     * Check received card.
     * 
     * @param index
     *            the card index
     */
    public void checkReceivedCard(int index) {
        super.checkOpenedCard(index, false);
    }

    /**
     * Stop the game and stop a connection thread.
     */
    public void stopGame() {
        thread.interrupt();
    }

    /**
     * Gets the port.
     * 
     * @return the port
     */
    int getPort() {
        return port;
    }

    /**
     * Gets the user interface.
     * 
     * @return the user interface
     */
    UserInterface getUserInterface() {
        return ui;
    }

    /**
     * Sets the turn.
     * 
     * @param turn
     *            the turn
     * @param display
     *            the display
     */
    public void setTurn(boolean turn, boolean display) {
        this.turn = turn;
        if (display) {
            ui.displayTurn(turn);
        }
    }

    /**
     * Gets the turn.
     * 
     * @return the turn
     */
    boolean getTurn() {
        return turn;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.github.savinov.cardpairs.model.single.SingleGameModel#getMessage()
     */
    protected String getMessage() {
        final int half = table.getTotalPairs() / 2;

        if (pairs > half) {
            return "You win";
        } else if (pairs < half) {
            return "You lose";
        }

        return "Draw";
    }

    private Thread thread;

    /** The port. */
    protected int port;

    /** The turn. */
    protected boolean turn;

    /** The connection. */
    protected Connection connection;
}
