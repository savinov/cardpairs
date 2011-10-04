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
package com.github.savinov.cardpairs.ui.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

import com.github.savinov.cardpairs.controller.GameController;
import com.github.savinov.cardpairs.controller.GameParameters;
import com.github.savinov.cardpairs.model.BoardSize;

/**
 * The game menu bar.
 */
@SuppressWarnings("serial")
public class GameMenuBar extends JMenuBar {

    /**
     * Instantiates a new game menu bar.
     * 
     * @param frame
     *            the window frame
     * @param ui
     *            the Swing user interface
     */
    public GameMenuBar(final JFrame frame, final SwingUserInterface ui) {
        // Game menu
        JMenu gameMenu = new JMenu("Game");

        gameMenu.add(new AbstractAction("Single") {
            public void actionPerformed(ActionEvent event) {
                ui.setNetworkMode(false);
                GameParameters param = new GameParameters();
                param.ui = ui;
                param.boardSize = boardSize;
                GameController.startSingleGame(param);
            }
        });

        networkDialog = new NetworkDialog(frame, ui);
        gameMenu.add(new AbstractAction("Network") {
            public void actionPerformed(ActionEvent event) {
                ui.setNetworkMode(true);
                networkDialog.setBoardSize(boardSize);
                networkDialog.setLocationRelativeTo(frame);
                networkDialog.setVisible(true);
            }
        });

        gameMenu.add(new AbstractAction("Exit") {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        // Size selection menu
        sizeMenu = new JMenu("Size");

        group = new ButtonGroup();

        addSizeButton("Small", BoardSize.SMALL);
        addSizeButton("Medium", BoardSize.MEDIUM);
        addSizeButton("Large", BoardSize.LARGE);
        addSizeButton("Extra large", BoardSize.EXTRA_LARGE);

        // Help menu
        JMenu helpMenu = new JMenu("Help");

        final AboutDialog aboutDialog = new AboutDialog(frame);
        helpMenu.add(new AbstractAction("About") {
            public void actionPerformed(ActionEvent event) {
                aboutDialog.setLocationRelativeTo(frame);
                aboutDialog.setVisible(true);
            }
        });

        // Add menus game to menu bar
        add(gameMenu);
        add(sizeMenu);
        add(helpMenu);
    }

    /**
     * Gets the network dialog.
     * 
     * @return the network dialog
     */
    public NetworkDialog getNetworkDialog() {
        return networkDialog;
    }

    private void addSizeButton(String name, final BoardSize size) {
        boolean selected = size == BoardSize.DEFAULT_SIZE;
        JRadioButtonMenuItem button = new JRadioButtonMenuItem(name, selected);

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                boardSize = size;
            }
        };
        button.addActionListener(listener);

        group.add(button);
        sizeMenu.add(button);
    }

    private BoardSize boardSize = BoardSize.DEFAULT_SIZE;
    private ButtonGroup group;
    private JMenu sizeMenu;
    private NetworkDialog networkDialog;

}
