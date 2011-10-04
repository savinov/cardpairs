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

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.github.savinov.cardpairs.model.CardTable;
import com.github.savinov.cardpairs.ui.UserInterface;

/**
 * Provides basic functionality for the network connection. It implements the
 * {@code Runnable} interface. Client and server connections extends this
 * abstract class.
 * 
 * @author Guram Savinov
 * 
 */
abstract public class Connection implements Runnable {

    /**
     * Creates connection.
     * 
     * @param model
     *            the network model
     */
    public Connection(NetworkGameModel model) {
        this.model = model;
    }

    /**
     * Send card index.
     * 
     * @param index
     *            the index
     */
    public synchronized void sendCardIndex(int index) {
        outCardIndex = index;
        notifyAll();
    }

    /**
     * Sets the object streams.
     * 
     * @param socket
     *            the socket from what object I/O streams will got
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    protected void setObjectStreams(Socket socket) throws IOException {
        OutputStream outStream = socket.getOutputStream();
        InputStream inStream = socket.getInputStream();

        out = new ObjectOutputStream(outStream);
        in = new ObjectInputStream(inStream);
    }

    /**
     * Display card table.
     */
    protected void displayTable() {
        UserInterface ui = model.getUserInterface();
        ui.displayCardTable(table);
        ui.displayTurn(model.getTurn());
        model.setStartTime(System.currentTimeMillis());
    }

    /**
     * Send receive indexes.
     * 
     * @throws InterruptedException
     *             the interrupted exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    protected void sendReceiveIndexes() throws InterruptedException,
            IOException {
        sendData = model.getTurn();
        while (true) {
            if (sendData) {
                while (outCardIndex == -1) {
                    wait();
                }
                System.out.println("Sending cardIndex: " + outCardIndex);
                out.writeInt(outCardIndex);
                out.flush();
                outCardIndex = -1;
                sendData = false;
            } else {
                inCardIndex = in.readInt();
                System.out.println("Received cardIndex: " + inCardIndex);
                model.checkReceivedCard(inCardIndex);
                model.setTurn(true, true);
                sendData = true;
            }
        }
    }

    volatile private int outCardIndex = -1;
    private boolean sendData;
    private int inCardIndex;
    private NetworkGameModel model;

    /** The object input stream. */
    protected ObjectInputStream in;

    /** The object output stream. */
    protected ObjectOutputStream out;

    /** The card table. */
    protected CardTable table;

}