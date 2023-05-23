package MyChess.JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class PoolFrame extends JLabel {

    /**
     * @param imageIcon Image of figure in this pool (can be null)
     * @param color Color of background
     * @param possibleMove Is on this pool possible of moves
     * @param mouseListener Listener of click in this pool
     */
    public PoolFrame (ImageIcon imageIcon, Color color, boolean possibleMove,MouseListener mouseListener){
        super();
       update(imageIcon,color,possibleMove);
       this.addMouseListener(mouseListener);
    }

    /**
     * Update View of Pool
     * @param imageIcon Image of figure in this pool (can be null)
     * @param color Color of background
     * @param possibleMove Is on this pool possible of moves
     */
    public void update(ImageIcon imageIcon, Color color, boolean possibleMove){
        if(imageIcon!=null)
            if(imageIcon.getImage()!=null) setIcon(imageIcon);
        else setIcon(null);
        if(possibleMove)setBackground(new Color(255,110,110));
        else this.setBackground(color);
    }
}
