package src.services.event_handling;

import src.controllers.GameBoardFrameKeyListener;
import src.services.utils.SerializationUtil;
import src.types.EventEnum;
import src.types.ResultDto;
import src.views.MainMenuFrame;

import javax.swing.*;
import java.awt.*;

public class DEHMGameBoard extends DEHM {
    private long lastExecution = 0;
    private static final long DEBOUNCE_TIME = 4000;
    protected GameBoardFrameKeyListener frame;

    public DEHMGameBoard(GameBoardFrameKeyListener frame) {
        super();
        this.frame = frame;
    }



    @Override
    public void ctrlShiftQHandler(EventEnum eventEnum) {

        long current = System.currentTimeMillis();
        if (current - lastExecution < DEBOUNCE_TIME) {
            // Less than 4 seconds has passed since the last execution, so we debounce this call
            return;
        }
        lastExecution = current;

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.stopServices();
                JLabel message = new JLabel("Enter a name for saving results");
                message.setForeground(Color.WHITE);
                String title = eventEnum == EventEnum.WIN ? "You win!" : eventEnum == EventEnum.LOSE ? "Sorry, try again :(" : "Save Results";

                String name = JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
                SerializationUtil.serialize(new ResultDto(
                    name,
                    frame.getScore(),
                    frame.getGameDuration(),
                    frame.getStartTime(),
                    frame.getTableDimensions()
                ));
                frame.dispose();
            }
        });
    }
}
