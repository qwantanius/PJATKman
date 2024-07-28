package src.controllers;

import src.services.movement.PacmanMovingService;
import src.types.DirectionEnum;

import java.awt.event.KeyEvent;

public class PacmanKeyController {
    PacmanMovingService pacmanMovingService;
    public PacmanKeyController(PacmanMovingService pacmanMovingService) {
        this.pacmanMovingService = pacmanMovingService;
    }

    public void keyPressed(KeyEvent e) {
        // This method is called when a key is pressed
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            pacmanMovingService.setCurrentDirection(DirectionEnum.UP);
        } else if (keyCode == KeyEvent.VK_S) {
            pacmanMovingService.setCurrentDirection(DirectionEnum.DOWN);
        } else if (keyCode == KeyEvent.VK_A) {
            pacmanMovingService.setCurrentDirection(DirectionEnum.LEFT);
        } else if (keyCode == KeyEvent.VK_D) {
            pacmanMovingService.setCurrentDirection(DirectionEnum.RIGHT);
        }
    }

}
