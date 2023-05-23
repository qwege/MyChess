package MyChess.Models;

import MyChess.JFrame.ChessFrame;
import MyChess.Models.Figures.Figure;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Pool {
    private Figure figure;
    MouseListener mouseListener;
    private boolean possibleMove = false;

    private Pool getThis() {
        return this;
    }

    public Pool(ActionListener doMoveThere) {
        mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (ChessFrame.actionEnabled) {
                    ChessFrame.actionEnabled=false;
                    if (possibleMove) doMoveThere.actionPerformed(new ActionEvent(getThis(), 0, "Moved "));
                    else if (figure == null) { ChessFrame.actionEnabled=true;
                    } else doMoveThere.actionPerformed(new ActionEvent(getThis(), 1, "Check Move "));
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        };
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public boolean isPossibleMove() {
        return possibleMove;
    }

    public void setPossibleMove(boolean possibleMove) {
        this.possibleMove = possibleMove;
    }

    public MouseListener getMouseListener() {
        return mouseListener;
    }
}
