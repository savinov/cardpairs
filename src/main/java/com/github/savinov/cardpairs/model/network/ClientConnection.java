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
import java.net.Socket;

import com.github.savinov.cardpairs.model.CardTable;

/**
 * The connection on the client side.
 * 
 * @author Guram Savinov
 * 
 */
public class ClientConnection extends Connection {

    /**
     * Instantiates a new client connection.
     * 
     * @param model
     *            the client game model
     */
    ClientConnection(ClientGameModel model) {
        super(model);
        this.model = model;
    }

    /**
     * Start cards exchanging with the server. It'll be executed in the
     * separated thread.
     */
    public synchronized void run() {
        try {
            Socket socket = new Socket(model.getHost(), model.getPort());

            try {
                setObjectStreams(socket);

                // Receive card table from server and set it
                table = (CardTable) in.readObject();
                model.setCardTable(table);

                // Receive turn variable from server and set it
                boolean turn = (Boolean) in.readObject();
                System.out.println("Received turn: " + turn);
                model.setTurn(turn, false);

                // Dispaly card table
                displayTable();

                // Start send/receive card indexes
                sendReceiveIndexes();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ClientGameModel model;

}
