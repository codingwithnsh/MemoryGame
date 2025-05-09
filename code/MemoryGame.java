package com.xxx.xxx; //replace the package came with yours

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A basic Memory Game (Flipping Tiles) in Java Swing.
 * 
 * The game layout is an even number of tiles (each tile has a matching pair).
 * All tiles start facing down. Flip two tiles: if they match, they remain revealed;
 * otherwise, they flip back down.
 *
 * Features:
 * - Even number of tiles with pairs
 * - Uses ArrayList to store and shuffle tile identifiers
 * - Basic Swing-based GUI to track revealed/matched tiles
 */
public class MemoryGame extends JFrame {

    private final int numPairs = 8; // total pairs (for a total of 16 tiles)
    private final ArrayList<Integer> tileValues = new ArrayList<>();
    private final ArrayList<JButton> buttons = new ArrayList<>();
    private JButton firstSelection = null;
    private JButton secondSelection = null;
    private Timer flipBackTimer;
    private int revealedCount = 0;

    public MemoryGame() {
        setTitle("Memory Game – Flipping Tiles");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4)); // 4x4 grid for 16 tiles

        // Prepare tile values (pairs of identical numbers)
        for (int i = 1; i <= numPairs; i++) {
            tileValues.add(i);
            tileValues.add(i);
        }
        Collections.shuffle(tileValues);

        // Create and add buttons
        for (int i = 0; i < tileValues.size(); i++) {
            JButton btn = createTileButton(tileValues.get(i));
            buttons.add(btn);
            add(btn);
        }

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        // Timer to flip tiles back if they don't match (using a 1-second delay)
        flipBackTimer = new Timer(1000, e -> flipBackTiles());
        flipBackTimer.setRepeats(false);
    }

    /**
     * Creates a tile button with an 'actionCommand' set to the tile's value.
     */
    private JButton createTileButton(int tileValue) {
        JButton btn = new JButton("?");
        btn.setFont(new Font("Arial", Font.BOLD, 24));
        btn.setActionCommand(String.valueOf(tileValue));
        btn.addActionListener(this::onTileClicked);
        return btn;
    }

    /**
     * Handles a tile click event.
     * Shows tile value if it's not already revealed, checks for matches.
     */
    private void onTileClicked(ActionEvent event) {
        JButton clickedButton = (JButton) event.getSource();

        // If the tile is already revealed or we're currently flipping back, ignore
        if (!clickedButton.getText().equals("?") || flipBackTimer.isRunning()) {
            return;
        }

        // Reveal the tile's value
        clickedButton.setText(clickedButton.getActionCommand());

        // If firstSelection == null, pick this as first choice
        if (firstSelection == null) {
            firstSelection = clickedButton;
            return;
        } 
        // If secondSelection == null, pick this as second choice
        else if (secondSelection == null) {
            secondSelection = clickedButton;
            // Check match
            checkMatch();
        }
    }

    /**
     * Checks if the selected tiles match or not.
     * If they match, keep them revealed; otherwise, flip back after a short delay.
     */
    private void checkMatch() {
        if (firstSelection.getActionCommand().equals(secondSelection.getActionCommand())) {
            // It's a match, keep them revealed
            firstSelection.setEnabled(false);
            secondSelection.setEnabled(false);
            
            firstSelection = null;
            secondSelection = null;
            revealedCount += 2;

            // Check if game is won
            if (revealedCount == tileValues.size()) {
                JOptionPane.showMessageDialog(this, "You Win! All Tiles Matched");
            }
        } else {
            // Not a match - start timer to flip them back down
            flipBackTimer.start();
        }
    }

    /**
     * Flips the selected tiles back down (called by the timer).
     */
    private void flipBackTiles() {
        if (firstSelection != null && secondSelection != null) {
            firstSelection.setText("?");
            secondSelection.setText("?");
        }
        firstSelection = null;
        secondSelection = null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MemoryGame::new);
    }
}
