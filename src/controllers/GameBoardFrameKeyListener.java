package src.controllers;

import src.configs.Fonts;
import src.configs.GameBoard;
import src.configs.Strings;
import src.services.animation.AbstractImageAnimationService;
import src.services.animation.GhostsAnimationManager;
import src.services.animation.PacmanAnimationService;
import src.services.bonuses.BonusService;
import src.services.utils.PacmanMapGenerator;
import src.services.event_handling.DEHMGameBoard;
import src.services.movement.AbstractMovingService;
import src.services.movement.PacmanMovingService;
import src.types.Bonus;
import src.types.BonusEnum;
import src.types.EventEnum;
import src.types.Pair;
import src.views.GameBoardView;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class GameBoardFrameKeyListener extends JFrame implements KeyListener {
    public static ArrayList<Pair> BUSY_COORDS = new ArrayList<>();
    public static ArrayList<Pair> AVAILABLE_COORDS = new ArrayList<>();
    public static BonusEnum BONUS;
    public static ArrayList<Bonus> RENDERED_BONUSES = new ArrayList<>();
    public int score = 0;
    public int currentHealth = 3;
    public int fullHealth = 3;
    public JTable tableMap;
    private GhostsAnimationManager ghostsAnimationManager;
    private PacmanMovingService pacmanMovingService;
    private PacmanAnimationService pacmanAnimationService;
    private PacmanKeyController pacmanKeyController;
    private BonusService bonusService;
    private long startTime = System.currentTimeMillis();
    private JLabel detailsLabel = new JLabel();
    private JLabel scoreLabel = new JLabel();
    private Dimension tableDimensions;
    private long gameDuration;
    private JPanel panel = new JPanel(new BorderLayout());
    private EventQueue eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();


    public GhostsAnimationManager getGhostsAnimationManager() {
        return ghostsAnimationManager;
    }

    public PacmanMovingService getPacmanMovingService() {
        return this.pacmanMovingService;
    }


    public int getScore() {
        return score;
    }

    public long getStartTime() {
        return startTime;
    }

    public Dimension getTableDimensions() {
        return tableDimensions;
    }

    public long getGameDuration() {
        return gameDuration;
    }

    public PacmanAnimationService getPacmanAnimationService() {
        return this.pacmanAnimationService;
    }

    public JTable getTableMap() {
        return tableMap;
    }

    private ImageIcon reserveBorderAt(Pair pair) {
        if (!BUSY_COORDS.contains(pair)) {
            BUSY_COORDS.add(pair);
        }
        return GameBoardView.border;
    }

    public static boolean isReservedAt(int x, int y) {
        return BUSY_COORDS.stream().anyMatch(point -> point.x == x && point.y == y);
    }


    public GameBoardFrameKeyListener(Dimension tableSize) {
        this.tableDimensions = tableSize;
        DEHMGameBoard dehmGameBoard = new DEHMGameBoard(this);
        detailsLabel.setFont(Fonts.DEFAULT_FONT);
        scoreLabel.setFont(Fonts.DEFAULT_FONT);
        // Start a new thread to update the timer
        new Thread(() -> {
            while (true) {
                long elapsedTime = System.currentTimeMillis() - startTime;
                long minutes = (elapsedTime / 1000) / 60;
                long seconds = (elapsedTime / 1000) % 60;
                this.gameDuration = elapsedTime;
                detailsLabel.setText(String.format("TIME:[%02d:%02d] SCORE:[%d] LIVES:[%s/%s]", minutes, seconds, score, currentHealth, fullHealth));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        GameBoardView.border.setDescription(Strings.GameBoard.BORDER);
        GameBoardView.background.setDescription(Strings.GameBoard.BACKGROUND);

        addKeyListener(this);
        setFocusable(true); // Add this line

        setTitle(Strings.GameBoard.TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Calculate the table size
        int tableWidth = tableDimensions.width;
        int tableHeight = tableDimensions.height;
        int cellSize = 14;
        int blankSpace = 5;

        // Calculate the number of cells that can fit in the table
        int numColumns = tableWidth / cellSize;
        int numRows = tableHeight / cellSize;

        // Adjust the panel size to accommodate the remaining space caused by the last column and blank space
        int panelHeight = tableHeight;
        numRows++;
        panelHeight = numRows * cellSize;
        int finalNumRows = numRows;
        ArrayList<Pair> lines = PacmanMapGenerator.generateLines(finalNumRows, numColumns);
        BUSY_COORDS = lines;

        AbstractTableModel tableModel = new AbstractTableModel() {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            public ImageIcon performBorderSelection(int rowIndex, int columnIndex) {
                if (rowIndex == 0 || columnIndex == 0) {
                    return reserveBorderAt(new Pair(rowIndex, columnIndex));
                }
                if (rowIndex == getRowCount()-1 || columnIndex == getColumnCount()-1) {
                    return reserveBorderAt(new Pair(rowIndex, columnIndex));
                }
                return null;
            }

            @Override
            public int getRowCount() {
                return finalNumRows;
            }

            @Override
            public int getColumnCount() {
                return numColumns;
            }

            @Override
            public Icon getValueAt(int rowIndex, int columnIndex) {
                Pair currentPair = new Pair(columnIndex, rowIndex);
                if (score == AVAILABLE_COORDS.size()-1 && score != 0) {
                    dehmGameBoard.ctrlShiftQHandler(EventEnum.WIN);
                }
                if (currentHealth == 0 && score != 0) {
                    dehmGameBoard.ctrlShiftQHandler(EventEnum.LOSE);
                }
                AtomicReference<ImageIcon> background = new AtomicReference<>(GameBoardView.background);
                for (Pair pair: lines) {
                    if (pair.x == columnIndex && pair.y == rowIndex) {
                        return reserveBorderAt(pair);
                    }
                }
                boolean isPointAvailable = AVAILABLE_COORDS.stream().anyMatch(pair -> pair.x == columnIndex && pair.y == rowIndex);
                if (!isPointAvailable) {
                    AVAILABLE_COORDS.add(currentPair);
                } else {
                    AVAILABLE_COORDS.forEach(pair -> {
                        if (pair.x == columnIndex && pair.y == rowIndex) {
                            if (!pair.isTouched) {
                                background.set(GameBoardView.backgroundScorePoint);
                            }
                        }
                    });
                    RENDERED_BONUSES.forEach(bonus -> {
                        if (bonus.getLocation().x == columnIndex && bonus.getLocation().y == rowIndex) {
                            background.set(BonusService.getImageIconByType(bonus.getType()));
                        }
                    });
                }
                ImageIcon pacmanSelection = pacmanAnimationService.modeSelection(rowIndex, columnIndex);
                ImageIcon ghostSelection = ghostsAnimationManager.performGhostSelectionForCell(rowIndex, columnIndex);
                ImageIcon border = performBorderSelection(rowIndex, columnIndex);
                if (border != null) {
                    return reserveBorderAt(currentPair);
                }
                if (pacmanSelection != null) {
                    return pacmanSelection;
                }
                if (ghostSelection != null) {
                    return ghostSelection;
                }
                return background.get();
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return ImageIcon.class;
            }
        };

        // Create the table using the table model
        JTable table = new JTable(tableModel);
        this.tableMap = table;
        this.bonusService = new BonusService(new Dimension(table.getColumnCount(), table.getRowCount()));
        this.ghostsAnimationManager = new GhostsAnimationManager(this);
        this.pacmanMovingService = new PacmanMovingService(this);
        this.pacmanAnimationService = new PacmanAnimationService(this);
        this.pacmanMovingService.setPacmanAnimationService(this.pacmanAnimationService);
        this.pacmanKeyController = new PacmanKeyController(pacmanMovingService);
        table.setShowGrid(false);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setRowHeight(cellSize);

        // Set the column width to match the row height
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(cellSize);
            column.setMinWidth(cellSize);
            column.setMaxWidth(cellSize);
        }

        // Hide table headers
        table.getTableHeader().setVisible(false);
        // Create a panel and add the table to it

        // Add blank space above the table using an empty border
        panel.setBorder(BorderFactory.createEmptyBorder(blankSpace, 0, 0, 0));
        add(detailsLabel, BorderLayout.NORTH);
        panel.add(table, BorderLayout.SOUTH);

        // Set the panel size to match the table size precisely
        panel.setPreferredSize(new Dimension(tableWidth - GameBoard.WIDTH_ADJUSTMENT_CONSTANT, panelHeight + blankSpace));
        add(panel);
        pack();

        setVisible(true);
        setResizable(false); // Allow resizing of the frame
        setLocationRelativeTo(null);
        this.executeServices();
    }

    // KeyListener methods
    public void keyPressed(KeyEvent e) {
        pacmanKeyController.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    private void executeServices() {
        pacmanAnimationService.start();
        pacmanMovingService.start();
        ghostsAnimationManager.executeGhosts();
        bonusService.start();
    }

    public void stopServices() {
        AbstractMovingService.FORCE_STOP = true;
        AbstractImageAnimationService.FORCE_STOP = true;
    }

    public static void reset() {
        BUSY_COORDS = new ArrayList<>();
        AVAILABLE_COORDS = new ArrayList<>();
        AbstractMovingService.FORCE_STOP = false;
        AbstractImageAnimationService.FORCE_STOP = false;
    }
}
