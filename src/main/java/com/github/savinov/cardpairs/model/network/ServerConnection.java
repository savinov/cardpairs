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
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The connection on the server side.
 * 
 * @author Guram Savinov
 * 
 */
public class ServerConnection extends Connection {

    /**
     * Instantiates a new server connection.
     * 
     * @param model
     *            the server game model
     */
    ServerConnection(ServerGameModel model) {
        super(model);
        this.model = model;
        table = model.getCardTable();
    }

    /**
     * Start cards exchanging with the client. It'll be executed in the
     * separated thread.
     */
    public synchronized void run() {
        try {
            ServerSocket socket = new ServerSocket(model.getPort());
            Socket incoming = socket.accept();
            socket.close();

            try {
                setObjectStreams(incoming);

                // Send card table to client
                out.writeObject(table);
                out.flush();

                // Send turn variable to client
                out.writeObject(!model.getTurn());
                out.flush();
                System.out.println("Sended turn: " + !model.getTurn());

                // Dispaly card table
                displayTable();

                // Start send/receive card indexes
                sendReceiveIndexes();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                socket.close();
                incoming.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ServerGameModel model;

}
