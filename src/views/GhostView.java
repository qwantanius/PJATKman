package src.views;

import src.services.images.ImageService;
import src.types.GhostEnum;
import javax.swing.*;

public class GhostView extends BasicView {
    public String images;
    public GhostEnum ghost;
    public ImageIcon full;
    public ImageIcon lhs;
    public ImageIcon rhs;
    public ImageIcon lhsUp;
    public ImageIcon rhsUp;

    public GhostView(GhostEnum ghostType) {
        this.ghost = ghostType;
        this.images = BasicView.getPacmanImagePath() + "\\ghosts";
        String fileSelection = ghostType.name().toLowerCase();
        System.out.println("INITING");
        this.full = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s.png", BasicView.getCwd(), images, fileSelection, fileSelection)));
        this.lhs = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_LHS.png", BasicView.getCwd(), images, fileSelection, fileSelection)));
        this.rhs = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_RHS.png", BasicView.getCwd(), images, fileSelection, fileSelection)));
        this.lhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_LHS_UP.png", BasicView.getCwd(), images, fileSelection, fileSelection)));
        this.rhsUp = new ImageIcon(ImageService.readAndScale(String.format("%s\\%s\\%s\\%s_RHS_UP.png", BasicView.getCwd(), images, fileSelection, fileSelection)));
    }
}
