package src.views;

import src.controllers.GameBoardFrameKeyListener;
import src.services.utils.SerializationUtil;
import src.types.ResultDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ScoresFrame extends JFrame {

    public ScoresFrame(ArrayList<Object> results) {
        // Initialize the frame
        this.setSize(400, 500);
        this.setLayout(new BorderLayout());

        // Initialize the table
        String[] columnNames = {"name", "score", "duration", "started_at", "board_size"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable scoresTable = new JTable(model);
        scoresTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Set the table's properties
        scoresTable.setFillsViewportHeight(true);
        scoresTable.setBackground(Color.BLACK);
        scoresTable.setForeground(Color.WHITE);
        results.sort((resultLhs, resultRhs) -> ((ResultDto)resultRhs).getResult() - ((ResultDto)resultLhs).getResult());
        // Add data to the table
        for (Object result : results) {
            Object[] row = new Object[5];
            ResultDto resultCasted = (ResultDto) result;
            row[0] = resultCasted.getGameName();
            row[1] = resultCasted.getResult();
            row[2] = SerializationUtil.fromMillisToMinutesAndSeconds(resultCasted.getDurationInMillis());
            row[3] = SerializationUtil.fromMillisToDate(resultCasted.getStartedAt());
            row[4] = resultCasted.getSelectedBoardSize().width + "x" + resultCasted.getSelectedBoardSize().height;
            model.addRow(row);
        }
        scoresTable.setCellSelectionEnabled(false);
        scoresTable.setCellEditor(null);
        scoresTable.setRowSelectionAllowed(false);
        // Create a scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(scoresTable);
        scrollPane.setPreferredSize(new Dimension(700, 500));

        // Add the scroll pane to the frame
        this.add(scrollPane, BorderLayout.CENTER);
        setTitle("SCORES");
        setIconImage(PacmanView.closedRight.getImage());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(true);
        this.setVisible(true);
    }
}
