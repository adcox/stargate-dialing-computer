package adcox.stargatecomputer.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
/**
 *  Dialing Computer: A simulation of the dialing computer found on the TV show
 *  Stargate SG-1
 * 
 *  Copyright (C) 2010 Andrew Cox
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
class JScrollPaneToTopAction implements ActionListener {
  JScrollPane scrollPane;
  public JScrollPaneToTopAction(JScrollPane scrollPane) {
    if (scrollPane == null) {
      throw new IllegalArgumentException(
        "JScrollPaneToTopAction: null JScrollPane");
    }
    this.scrollPane = scrollPane;
  }
  public void actionPerformed(ActionEvent actionEvent) {
    JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
    JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
    verticalScrollBar.setValue(verticalScrollBar.getMinimum());
    horizontalScrollBar.setValue(horizontalScrollBar.getMinimum());
  }
}


public class JScrollPaneToTopActionDemo {

  public static void main(String args[]) {
    JFrame frame = new JFrame("Tabbed Pane Sample");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel label = new JLabel("Label");
    label.setPreferredSize(new Dimension(1000, 1000));
    JScrollPane jScrollPane = new JScrollPane(label);

    JButton bn = new JButton("Move");

    bn.addActionListener(new JScrollPaneToTopAction(jScrollPane));

    frame.add(bn, BorderLayout.SOUTH);
    frame.add(jScrollPane, BorderLayout.CENTER);
    frame.setSize(400, 150);
    frame.setVisible(true);
  }
}