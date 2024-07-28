package src.views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.*;

public class GameBoardFrame extends JFrame implements KeyListener {
    public GameBoardFrame(Dimension size) {
        // Set the size and title of the frame
        setSize(size);
        setTitle("PACMAN");
        setResizable(false);
        setLocationRelativeTo(null);

        addKeyListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // KeyListener methods
    public void keyPressed(KeyEvent e) {
        // This method is called when a key is pressed
        System.out.println("Key pressed: " + e.getKeyChar());
    }

    public void keyReleased(KeyEvent e) {
        // This method is called when a key is released
        System.out.println("Key released: " + e.getKeyChar());
    }

    public void keyTyped(KeyEvent e) {
        // This method is called when a key is typed
        System.out.println("Key typed: " + e.getKeyChar());
    }
}
