package src.types;

import src.views.PacmanView;

import javax.swing.*;

public interface Animatable {
    abstract public ImageIcon getLhsAnimationImage();
    abstract public ImageIcon getRhsAnimationImage();
    abstract public ImageIcon getClosedFullAnimationImage();
}
