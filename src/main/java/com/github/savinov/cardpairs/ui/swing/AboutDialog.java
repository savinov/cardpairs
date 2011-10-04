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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The about dialog window.
 */
@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

    /**
     * Instantiates a new about dialog.
     * 
     * @param owner
     *            the dialog window owner
     */
    public AboutDialog(JFrame owner) {
        super(owner, "About Card Pairs", true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        panel.add(new JLabel("<html><center>" + "<h2>Card Pairs</h2>"
                + "CardPairs is an open source project that<br>"
                + "represents memory game variation with cards.<br>"
                + "Your task is openning two same cards.<br><br>"
                + "Project homepage:<br>"
                + "http://github.com/savinov/cardpairs<br><br>"
                + "</center></html>"), BorderLayout.CENTER);

        JPanel okPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });
        okPanel.add(okButton);
        panel.add(okPanel, BorderLayout.SOUTH);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(panel);

        pack();
    }

}
