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
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The scores dialog window. It appears when game over.
 */
@SuppressWarnings("serial")
public class ScoresDialog extends JDialog {

    /**
     * Instantiates a new scores dialog.
     * 
     * @param owner
     *            the dialog owner
     * @param pairs
     *            the number of pairs
     * @param totalFlips
     *            the total flips
     * @param totalTime
     *            the total time
     * @param message
     *            the message
     */
    public ScoresDialog(JFrame owner, int pairs, int totalFlips,
            long totalTime, String message) {
        super(owner, "Game scores", true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        StringWriter formatTime = new StringWriter();
        PrintWriter out = new PrintWriter(formatTime);
        out.printf("%02d:%02d", totalTime / (1000 * 60),
                (totalTime / 1000) % 60);

        JLabel label = new JLabel();
        label.setText("<html><center>" + "<h2>" + message + "</h2>" + "<table>"
                + "<tr><td>Pairs:</td><td>" + pairs + "</td></tr>"
                + "<tr><td>Flips:</td><td>" + totalFlips + "</td></tr>"
                + "<tr><td>Time:</td>" + formatTime + "</td></tr>" + "</table>"
                + "</center></html>");
        panel.add(label, BorderLayout.CENTER);

        JPanel okPanel = new JPanel();

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });

        okPanel.add(okButton);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        okPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        panel.add(okPanel, BorderLayout.SOUTH);
        add(panel);

        pack();
        setLocationRelativeTo(owner);
    }

}
