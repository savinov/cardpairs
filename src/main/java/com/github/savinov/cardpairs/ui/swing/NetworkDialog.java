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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import com.github.savinov.cardpairs.controller.GameController;
import com.github.savinov.cardpairs.controller.GameParameters;
import com.github.savinov.cardpairs.model.BoardSize;
import com.github.savinov.cardpairs.ui.UserInterface;

/**
 * The network dialog window.
 * 
 * @author Guram Savinov
 * 
 */
@SuppressWarnings("serial")
public class NetworkDialog extends JDialog {

    /**
     * Instantiates a new network dialog.
     * 
     * @param owner
     *            the window owner
     * @param ui
     *            the user interface
     */
    public NetworkDialog(JFrame owner, final UserInterface ui) {
        super(owner, "Network game", true);

        // Settings panel
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new GridLayout(3, 2));

        group = new ButtonGroup();
        addRadioButton("Server", true);
        addRadioButton("Client", false);

        settingsPanel.add(new JLabel("Host: ", JLabel.TRAILING));
        settingsPanel.add(hostField = new JTextField("" + DEFAULT_HOST));

        settingsPanel.add(new JLabel("Port: ", JLabel.TRAILING));
        settingsPanel.add(portField = new JTextField("" + DEFAULT_PORT));

        settingsPanel.setBorder(BorderFactory
                .createTitledBorder("Network settings"));

        // OK/Cancel buttons panel
        JPanel buttonPanel = new JPanel();

        okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setEditable(false);

                GameParameters param = new GameParameters();
                param.ui = ui;
                param.boardSize = boardSize;
                param.host = hostField.getText();
                param.port = Integer.parseInt(portField.getText());
                param.server = server;

                GameController.startNetworkGame(param);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                closeDialog();
            }
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Closing window listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                closeDialog();
            }
        });

        add(settingsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
    }

    /**
     * Sets the board size.
     * 
     * @param boardSize
     *            the new board size
     */
    void setBoardSize(BoardSize boardSize) {
        this.boardSize = boardSize;
    }

    /**
     * Sets the editable flag. Make to able/disable parameters edition.
     * 
     * @param editable
     *            the new editable flag
     */
    void setEditable(boolean editable) {
        okButton.setEnabled(editable);
        hostField.setEditable(editable);
        portField.setEditable(editable);

        ArrayList<AbstractButton> buttonList = Collections.list(group
                .getElements());
        for (AbstractButton button : buttonList)
            button.setEnabled(editable);
    }

    private void addRadioButton(String name, final boolean server) {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                NetworkDialog.this.server = server;
            }
        };

        JRadioButtonMenuItem button = new JRadioButtonMenuItem(name, server);
        button.addActionListener(listener);

        group.add(button);
        settingsPanel.add(button);
    }

    private void closeDialog() {
        hostField.setText("" + DEFAULT_HOST);
        portField.setText("" + DEFAULT_PORT);
        setEditable(true);

        GameController.stopNetworkGame();
        setVisible(false);
    }

    private JButton okButton;
    private JTextField portField;
    private JTextField hostField;
    private JPanel settingsPanel;
    private ButtonGroup group;
    private boolean server = true;
    private BoardSize boardSize;
    private static final String DEFAULT_HOST = "127.0.0.1";
    private static final int DEFAULT_PORT = 5000;

}
