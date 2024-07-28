package src.views;
import src.configs.Strings;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;

public class BoardSizeDialog extends JDialog {

    private final JSpinner widthSpinner = getSpinnerModel();
    private final JSpinner heightSpinner = getSpinnerModel();
    private final JLabel widthLabel = new JLabel(GameBoardView.widthLabel);
    private final JLabel heightLabel = new JLabel(GameBoardView.heightLabel);
    private final JPanel sizePanel = new JPanel(new GridLayout(2, 2, 10, 10)){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(GameBoardView.dimensionSelectorBackground.getImage(), 0, 0, this);
        }
    };
    private final JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(GameBoardView.dimensionSelectorBackground.getImage(), 0, 0, this);
        }
    };;
    private final JButton okButton = new JButton(Strings.BoardSizeDialog.ANSWER_DIALOG_OK){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(GameBoardView.okButton.getImage(), 0, 0, this);
        }
    };
    private final JButton cancelButton = new JButton(Strings.BoardSizeDialog.CANCEL_DIALOG){
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(GameBoardView.cancelButton.getImage(), 0, 0, this);
        }
    };
    private final MainMenuFrame parentFrame;
    
    public BoardSizeDialog(MainMenuFrame parentFrame) {
        super(parentFrame, Strings.BoardSizeDialog.TITLE, true);
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setupSpinnerSizePanel();
        setupButtonPanel();

        add(sizePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(parentFrame);
        setVisible(true);
    }

    private JSpinner getSpinnerModel() {
        SpinnerModel defModel = new SpinnerNumberModel(10, 10, 100, 1);
        JSpinner jSpinner = new JSpinner(defModel);
        jSpinner.setUI(new BasicSpinnerUI() {
            @Override
            protected Component createNextButton() {
                Component c = super.createNextButton();
                if (c instanceof JButton) {
                    ((JButton) c).setBackground(Color.BLACK);
                }
                return c;
            }

            @Override
            protected Component createPreviousButton() {
                Component c = super.createPreviousButton();
                if (c instanceof JButton) {
                    ((JButton) c).setBackground(Color.BLACK);
                }
                return c;
            }
        });
        JComponent editor = jSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor)editor).getTextField().setBackground(Color.BLACK);
            ((JSpinner.DefaultEditor)editor).getTextField().setDisabledTextColor(Color.WHITE);
        }
        return jSpinner;
    }

    private void setupSpinnerSizePanel() {
        sizePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        sizePanel.add(widthLabel);
        sizePanel.add(widthSpinner);
        sizePanel.add(heightLabel);
        sizePanel.add(heightSpinner);
    }

    private void setupButtonPanel() {
        okButton.addActionListener(e -> {
            MainMenuFrame.blockGame = false;
            int width = (int) widthSpinner.getValue();
            int height = (int) heightSpinner.getValue();
            System.out.println(width + " : " + height);
            parentFrame.getSettingsService().setGameBoardHeight(height);
            parentFrame.getSettingsService().setGameBoardWidth(width);
            dispose();
        });
        cancelButton.addActionListener(e -> {
            MainMenuFrame.blockGame = true;
            dispose();
        });
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
    }
}
