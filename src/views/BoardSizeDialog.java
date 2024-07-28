package src.views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoardSizeDialog extends JDialog {

    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    
    public BoardSizeDialog(MainMenu parentFrame) {
        
        super(parentFrame, "Select Board Size", true);
        setLayout(new BorderLayout());

        // Create panel for the spinners
        JPanel sizePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        sizePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create label and spinner for selecting width
        JLabel widthLabel = new JLabel("Width:");
        sizePanel.add(widthLabel);
        SpinnerModel widthModel = new SpinnerNumberModel(10, 10, 100, 1);
        widthSpinner = new JSpinner(widthModel);
        sizePanel.add(widthSpinner);

        // Create label and spinner for selecting height
        JLabel heightLabel = new JLabel("Height:");
        sizePanel.add(heightLabel);
        SpinnerModel heightModel = new SpinnerNumberModel(10, 10, 100, 1);
        heightSpinner = new JSpinner(heightModel);
        sizePanel.add(heightSpinner);

        // Create panel for the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            int width = (int) widthSpinner.getValue();
            int height = (int) heightSpinner.getValue();
            parentFrame.getSettingsService().setGameBoardHeight(height);
            parentFrame.getSettingsService().setGameBoardWidth(width);
            dispose();
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        // Add panels to the dialog
        add(sizePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Pack and center the dialog on the parent frame
        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }
}
