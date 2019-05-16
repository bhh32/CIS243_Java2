// This is a variation of the 8 queens board class that provides an animation
// of the recursive backtracking.

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

public class BoardFrame2 extends Board {
    private JButton[][] myButtons;
    private boolean nextStep = false;
    private static int checkTime = 400;
    private JFrame f;

    public BoardFrame2(int size) {
        super(size);
        f = new JFrame();
        f.setSize(60 * size + 50, 60 * size + 80);
        f.setTitle("Eight Queens Animation");
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        Container contentPane = f.getContentPane();

        // initialize step to control it at bottom
        
        final JButton step = new JButton("step");
        step.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            nextStep = true;          
          }
        });
        JPanel p = new JPanel();
        p.add(step);
        contentPane.add(p, "South");

        // add buttons in the middle for the chess squares
        p = new JPanel(new GridLayout(size, size, 1, 1));
        contentPane.add(p, "Center");
        p.setBackground(Color.black);
        myButtons = new JButton[size][size];
        Font f24 = new Font("Serif", Font.BOLD, 24);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++) {
                JButton b = new JButton();
                b.setFont(f24);
                p.add(b);
                myButtons[i][j] = b;
            }

        // bring it on...
        f.setVisible(true);
        f.toFront();
    }

    public void place(int row, int col) {
        super.place(row, col);
        myButtons[row - 1][col - 1].setText("Q");
        pause();
    }

    public boolean safe(int row, int col) {
        myButtons[row - 1][col - 1].setText("?");
        pause();
        myButtons[row - 1][col - 1].setText("");
        return super.safe(row, col);
    }

    public void remove(int row, int col) {
        super.remove(row, col);
        myButtons[row - 1][col - 1].setText("undo");
        pause();
        myButtons[row - 1][col - 1].setText("");
    }

    public void print() {
        for (int i = 0; i < 6; i++) {
            for (int col = 0; col < size(); col++) {
                int row = getQueenRowForColumn(col);
                myButtons[row][col].setText("^_^");
                myButtons[row][col].setForeground(Color.BLUE);
            }
            pause(500);
            for (int col = 0; col < size(); col++) {
                int row = getQueenRowForColumn(col);
                myButtons[row][col].setText("Q");
                myButtons[row][col].setForeground(Color.BLACK);
            }
            pause(500);
        }
    }

    // pause using button
    private void pause() {
        nextStep = false;
        try {
            while (!nextStep) {
              Thread.sleep(checkTime);
            }
        } catch (Exception e) {
            throw new InternalError();
        }
    }
    // time-based pause
    private void pause(int delay) {
      try {          
            Thread.sleep(delay);
      } catch (Exception e) {
          throw new InternalError();
      }
  }
}
