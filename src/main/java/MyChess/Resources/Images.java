package MyChess.Resources;

import MyChess.Models.Figures.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Images {
    ImageIcon bishopWhite;
    ImageIcon bishopBlack;
    ImageIcon kingWhite;
    ImageIcon kingBlack;
    ImageIcon queenWhite;
    ImageIcon queenBlack;
    ImageIcon rockBlack;
    ImageIcon rockWhite;
    ImageIcon knightWhite;
    ImageIcon knightBlack;
    ImageIcon pawnWhite;
    ImageIcon pawnBlack;

    public ImageIcon getBishopWhite() {
        return bishopWhite;
    }

    public ImageIcon getBishopBlack() {
        return bishopBlack;
    }

    public ImageIcon getKingWhite() {
        return kingWhite;
    }

    public ImageIcon getKingBlack() {
        return kingBlack;
    }

    public ImageIcon getQueenWhite() {
        return queenWhite;
    }

    public ImageIcon getQueenBlack() {
        return queenBlack;
    }

    public ImageIcon getRockBlack() {
        return rockBlack;
    }

    public ImageIcon getRockWhite() {
        return rockWhite;
    }

    public ImageIcon getKnightWhite() {
        return knightWhite;
    }

    public ImageIcon getKnightBlack() {
        return knightBlack;
    }

    public ImageIcon getPawnWhite() {
        return pawnWhite;
    }

    public ImageIcon getPawnBlack() {
        return pawnBlack;
    }

    /**
     * Load imageIcons
     * @param x - height of size image
     * @param y - width of size image
     */
    public void load(int x,int y){
        try {
            bishopBlack = new ImageIcon( Resource.getFigureImageIcon(new Bishop(ColorFigure.BLACK)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            kingBlack = new ImageIcon( Resource.getFigureImageIcon(new King(ColorFigure.BLACK)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            knightBlack = new ImageIcon( Resource.getFigureImageIcon(new Knight(ColorFigure.BLACK)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            pawnBlack = new ImageIcon( Resource.getFigureImageIcon(new Pawn(ColorFigure.BLACK)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            queenBlack = new ImageIcon( Resource.getFigureImageIcon(new Queen(ColorFigure.BLACK)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            rockBlack = new ImageIcon( Resource.getFigureImageIcon(new Rock(ColorFigure.BLACK)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            bishopWhite = new ImageIcon( Resource.getFigureImageIcon(new Bishop(ColorFigure.WHITE)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            kingWhite = new ImageIcon( Resource.getFigureImageIcon(new King(ColorFigure.WHITE)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            knightWhite = new ImageIcon( Resource.getFigureImageIcon(new Knight(ColorFigure.WHITE)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            pawnWhite = new ImageIcon( Resource.getFigureImageIcon(new Pawn(ColorFigure.WHITE)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            queenWhite = new ImageIcon( Resource.getFigureImageIcon(new Queen(ColorFigure.WHITE)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
            rockWhite = new ImageIcon( Resource.getFigureImageIcon(new Rock(ColorFigure.WHITE)).getScaledInstance(x,y, Image.SCALE_SMOOTH));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     *
     * @param figure - Figure of chess
     * @return ImageIcon of figure
     */
    public ImageIcon get(Figure figure) {

        if(figure==null)return new ImageIcon();
        Class<? extends Figure> clas= figure.getClass();
        ColorFigure color =figure.getColor();
        if(clas.equals(Pawn.class)&&color.equals(ColorFigure.BLACK))return getPawnBlack();
        if(clas.equals(Pawn.class)&&color.equals(ColorFigure.WHITE))return getPawnWhite();
        if(clas.equals(Knight.class)&&color.equals(ColorFigure.BLACK))return getKnightBlack();
        if(clas.equals(Bishop.class)&&color.equals(ColorFigure.BLACK))return getBishopBlack();
        if(clas.equals(King.class)&&color.equals(ColorFigure.BLACK))return getKingBlack();
        if(clas.equals(Queen.class)&&color.equals(ColorFigure.BLACK))return getQueenBlack();
        if(clas.equals(Rock.class)&&color.equals(ColorFigure.BLACK))return getRockBlack();
        if(clas.equals(Knight.class)&&color.equals(ColorFigure.WHITE))return getKnightWhite();
        if(clas.equals(Bishop.class)&&color.equals(ColorFigure.WHITE))return getBishopWhite();
        if(clas.equals(King.class)&&color.equals(ColorFigure.WHITE))return getKingWhite();
        if(clas.equals(Queen.class)&&color.equals(ColorFigure.WHITE))return getQueenWhite();
        if(clas.equals(Rock.class)&&color.equals(ColorFigure.WHITE))return getRockWhite();
        return null;
    }
}
