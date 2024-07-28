package src.views;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class GameBoardJTable extends AbstractTableModel {
    private int numRows;
    private int numCols;
    private Object[][] data;

    public GameBoardJTable(Dimension dimension) {
        int numRows = (int) dimension.getHeight();
        int numCols = (int) dimension.getWidth();
        this.numRows = numRows;
        this.numCols = numCols;
        this.data = new Object[numRows][numCols];
    }

    @Override
    public int getRowCount() {
        return numRows;
    }

    @Override
    public int getColumnCount() {
        return numCols;
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }
}