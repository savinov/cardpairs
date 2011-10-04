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
package com.github.savinov.cardpairs.controller;

import com.github.savinov.cardpairs.model.GameModel;
import com.github.savinov.cardpairs.model.network.ClientGameModel;
import com.github.savinov.cardpairs.model.network.NetworkGameModel;
import com.github.savinov.cardpairs.model.network.ServerGameModel;
import com.github.savinov.cardpairs.model.single.SingleGameModel;

/**
 * The game controller. It's responsible for the starting and stopping game.
 * 
 * @author Guram Savinov
 * 
 */
public class GameController {

    /**
     * Starts game in the single mode.
     * 
     * @param param
     *            the game parameters
     */
    public static void startSingleGame(GameParameters param) {
        GameModel model = new SingleGameModel(param.boardSize);

        model.setUserInterface(param.ui);
        param.ui.setGameModel(model);

        model.startGame();
    }

    /**
     * Starts game in the network mode.
     * 
     * @param param
     *            the game parameters
     */
    public static void startNetworkGame(GameParameters param) {
        stopNetworkGame();

        if (param.server) {
            networkModel = new ServerGameModel(param.port, param.boardSize);
        } else {
            networkModel = new ClientGameModel(param.host, param.port);
        }

        networkModel.setUserInterface(param.ui);
        param.ui.setGameModel(networkModel);

        networkModel.startGame();
    }

    /**
     * Stops network game.
     */
    public static void stopNetworkGame() {
        if (networkModel != null) {
            networkModel.stopGame();
        }
    }

    private static NetworkGameModel networkModel;

}
