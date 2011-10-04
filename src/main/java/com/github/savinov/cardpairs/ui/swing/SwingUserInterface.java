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

import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.savinov.cardpairs.model.CardTable;
import com.github.savinov.cardpairs.model.GameModel;
import com.github.savinov.cardpairs.ui.UserInterface;

/**
 * The user interface implementation based on the Swing framework.
 * 
 * @author Guram Savinov
 * 
 */
public class SwingUserInterface implements UserInterface {

    /**
     * Instantiates a new swing user interface.
     */
    public SwingUserInterface() {
        frame = new CentrableFrame();
        background = new BackPanel(ResourceLoader.getImage("texture.jpg"));
        background.setLayout(new BorderLayout());
        menuBar = new GameMenuBar(frame, this);

        frame.setContentPane(background);
        frame.setTitle("Card Pairs");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);

        paintLogo();
        frame.MoveToCenter();
        frame.setVisible(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.github.savinov.cardpairs.ui.UserInterface#displayScores(int,
     * int, long, java.lang.String)
     */
    public void displayScores(int pairs, int totalFlips, long totalTime,
            String message) {
        JDialog dialog = new ScoresDialog(frame, pairs, totalFlips, totalTime,
                message);
        dialog.setVisible(true);
        paintLogo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.github.savinov.cardpairs.ui.UserInterface#setGameModel(com.github
     * .savinov.cardpairs.model.GameModel)
     */
    public void setGameModel(GameModel model) {
        this.model = model;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.github.savinov.cardpairs.ui.UserInterface#displayCardTable(com.github
     * .savinov.cardpairs.model.CardTable)
     */
    public void displayCardTable(CardTable table) {
        background.removeAll();

        tablePanel = new CardTablePanel(table, model);
        background.add(tablePanel, BorderLayout.CENTER);

        if (networkMode) {
            prepareNetworkGame();
        }

        frame.pack();
    }

    private void prepareNetworkGame() {
        NetworkDialog networkDialog = menuBar.getNetworkDialog();
        networkDialog.setVisible(false);
        networkDialog.setEditable(true);

        JPanel turnPanel = new JPanel();
        turnPanel.setOpaque(false);
        Image myImage = ResourceLoader.getImage("myturn.png");
        Image oppImage = ResourceLoader.getImage("oppturn.png");
        myTurnPanel = new TransparentPanel(myImage, background);
        oppTurnPanel = new TransparentPanel(oppImage, background);
        turnPanel.add(myTurnPanel);
        turnPanel.add(oppTurnPanel);
        turnPanel.setBorder(BorderFactory.createEmptyBorder(0, 25, 25, 25));
        background.add(turnPanel, BorderLayout.SOUTH);
    }

    /**
     * Sets the network mode flag.
     * 
     * @param mode
     *            the new network mode flag
     */
    void setNetworkMode(boolean mode) {
        networkMode = mode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.github.savinov.cardpairs.ui.UserInterface#displayTurn(boolean)
     */
    public void displayTurn(final boolean turn) {
        if (turn) {
            myTurnPanel.setRatio(100);
            oppTurnPanel.setRatio(50);
        } else {
            myTurnPanel.setRatio(50);
            oppTurnPanel.setRatio(100);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.github.savinov.cardpairs.ui.UserInterface#setCardOpened(int,
     * boolean)
     */
    public void setCardOpened(int index, boolean opened) {
        Card card = tablePanel.getCard(index);
        card.setOpened(opened);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.github.savinov.cardpairs.ui.UserInterface#removeCard(int)
     */
    public void removeCard(int index) {
        final Card card = tablePanel.getCard(index);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (int ratio = 100; ratio > 0; ratio--) {
                    card.setAlpha(ratio * 0.01f);
                    background.repaint();

                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                card.setVisible(false);
                background.repaint();
            }
        });
        thread.start();
    }

    /**
     * Paint logo image on the background.
     */
    private void paintLogo() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel(ResourceLoader.getIcon("logo.png"));

        panel.setLayout(new BorderLayout());
        panel.add(label, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.setOpaque(false);

        background.removeAll();
        background.add(panel, BorderLayout.CENTER);

        frame.pack();
    }

    private CentrableFrame frame;
    private JPanel background;
    private GameMenuBar menuBar;
    private CardTablePanel tablePanel;
    private GameModel model;
    private TransparentPanel myTurnPanel;
    private TransparentPanel oppTurnPanel;
    private boolean networkMode;

}
