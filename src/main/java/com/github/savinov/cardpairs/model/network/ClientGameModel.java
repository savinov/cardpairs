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
import com.github.savinov.cardpairs.model.CardTable;

/**
 * The network game model implementation on the client side.
 * 
 * @author Guram Savinov
 * 
 */
public class ClientGameModel extends NetworkGameModel {

    /**
     * Instantiates a new client game model.
     * 
     * @param host
     *            the host
     * @param port
     *            the port
     */
    public ClientGameModel(String host, int port) {
        super(BoardSize.DEFAULT_SIZE);
        this.host = host;
        this.port = port;
        connection = new ClientConnection(this);
    }

    /**
     * Gets the host.
     * 
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the card table.
     * 
     * @param table
     *            the new card table
     */
    public void setCardTable(CardTable table) {
        this.table = table;
    }

    private String host;

}
