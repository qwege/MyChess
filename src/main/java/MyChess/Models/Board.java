package MyChess.Models;


import MyChess.JFrame.ChessFrame;
import MyChess.Models.Figures.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board {
    private Pool[][] pools;
    private ActionListener actionListener;
    private final ActionListener update;

    private boolean whiteTurn = true;

    private Point activeCoords = new Point(0, 0);

    /**
     * Update Frame
     */
    public void updateFrame() {
        //Frame update after last changes
        update.actionPerformed(new ActionEvent(this, 0, ""));
    }

    public Board(ActionListener actionListener1) {
        initActionListener();
        update = actionListener1;
        initPools();


    }

    /**
     * Create new match
     */
    private void initPools() {
        //Create new Pools of Board
        pools = new Pool[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pools[i][j] = new Pool(actionListener);
            }
        }
        //Set starting Positions of Pawns
        for (int i = 0; i < 8; i++) {
            pools[1][i].setFigure(new Pawn(ColorFigure.BLACK));
            pools[6][i].setFigure(new Pawn(ColorFigure.WHITE));
        }
        //Set starting Positions of Figures
        pools[0][0].setFigure(new Rock(ColorFigure.BLACK));
        pools[0][7].setFigure(new Rock(ColorFigure.BLACK));
        pools[7][0].setFigure(new Rock(ColorFigure.WHITE));
        pools[7][7].setFigure(new Rock(ColorFigure.WHITE));
        pools[0][1].setFigure(new Knight(ColorFigure.BLACK));
        pools[0][6].setFigure(new Knight(ColorFigure.BLACK));
        pools[7][1].setFigure(new Knight(ColorFigure.WHITE));
        pools[7][6].setFigure(new Knight(ColorFigure.WHITE));
        pools[0][2].setFigure(new Bishop(ColorFigure.BLACK));
        pools[0][5].setFigure(new Bishop(ColorFigure.BLACK));
        pools[7][2].setFigure(new Bishop(ColorFigure.WHITE));
        pools[7][5].setFigure(new Bishop(ColorFigure.WHITE));
        pools[0][3].setFigure(new Queen(ColorFigure.BLACK));
        pools[7][3].setFigure(new Queen(ColorFigure.WHITE));
        pools[0][4].setFigure(new King(ColorFigure.BLACK));
        pools[7][4].setFigure(new King(ColorFigure.WHITE));
    }

    private void initActionListener() {
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // if == 1. Init values to show possible moves from pools clicked
                if (e.getID() == 1) {
                    Pool f = (Pool) e.getSource();
                    //check it is correct colour move
                    if (whiteTurn && f.getFigure().getColor() == ColorFigure.BLACK) {
                        ChessFrame.actionEnabled=true;
                        return;
                    }
                    if (!whiteTurn && f.getFigure().getColor() == ColorFigure.WHITE) {
                        ChessFrame.actionEnabled=true;
                        return;
                    }
                    // find pool clicked and show possible moves from it.
                    boolean flag = false;
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (f == pools[i][j]) {
                                showPossibleMoves(i, j);
                                flag = true;
                                break;
                            }
                        }
                        if (flag) break;
                    }
                }
                //if == 0 move figure clicked before to this pool
                else if (e.getID() == 0) {
                    Pool f = (Pool) e.getSource();
                    boolean flag = false;

                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (f == pools[i][j]) {
                                //check "is it end of game?"
                                if (pools[i][j].getFigure() != null) {
                                    if (pools[i][j].getFigure().getClass().equals(King.class) && pools[i][j].getFigure().getColor() == ColorFigure.BLACK) {
                                        endGame(ColorFigure.WHITE);
                                        return;
                                    }
                                    if (pools[i][j].getFigure().getClass().equals(King.class) && pools[i][j].getFigure().getColor() == ColorFigure.WHITE) {
                                        endGame(ColorFigure.BLACK);
                                        return;
                                    }
                                }
                                //do Move
                                pools[i][j].setFigure(pools[activeCoords.x][activeCoords.y].getFigure());
                                pools[activeCoords.x][activeCoords.y].setFigure(null);

                                flag = true;
                                break;

                            }
                        }
                        if (flag) break;
                    }
                    //disable possible moves before enable new move.
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            pools[i][j].setPossibleMove(false);
                        }
                    }
                    whiteTurn = !whiteTurn;

                }
                updateFrame();
            }

        };
    }

    /**
     * End match and init new.
     * @param colorFigure - Color winning
     */
    private void endGame(ColorFigure colorFigure) {
        JOptionPane.showMessageDialog(null, colorFigure.toString() + " is winning", "EndGame", JOptionPane.INFORMATION_MESSAGE);
        initPools();
        updateFrame();
    }


    public Pool getPool(int x, int y) {
        return pools[x][y];
    }

    /**
     * Init pool to show where figure can move from coords x,y
     * @param x - height position of clicked pool
     * @param y - width position of clicked pool
     */
    public void showPossibleMoves(int x, int y) {
        //disable possible moves before show new possible moves other figure
        activeCoords = new Point(x, y);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pools[i][j].setPossibleMove(false);
            }
        }
        //check with figure which clicked
        Figure f = pools[x][y].getFigure();
        if (f == null) return;
        else if (f.getClass() == Knight.class) potentialKnight(x, y, f.getColor());
        else if (f.getClass() == King.class) potentialKing(x, y, f.getColor());
        else if (f.getClass() == Pawn.class) potentialPawn(x, y, f.getColor());
        else if (f.getClass() == Rock.class) potentialRock(x, y, f.getColor());
        else if (f.getClass() == Bishop.class) potentialBishop(x, y, f.getColor());
        else if (f.getClass() == Queen.class) {
            potentialBishop(x, y, f.getColor());
            potentialRock(x, y, f.getColor());
        }
    }

    /**
     * Init pool to show where bishop from coords x,y can move
     * @param x - height position of clicked pool
     * @param y - width position of clicked pool
     * @param color - Colour of figure clicked
     */
    private void potentialBishop(int x, int y, ColorFigure color) {
        try {
            for (int i = 1; i < 8; i++) {
                if (pools[x + i][y + i].getFigure() == null) {
                    pools[x + i][y + i].setPossibleMove(true);
                } else if (pools[x + i][y + i].getFigure().getColor() != color) {
                    pools[x + i][y + i].setPossibleMove(true);
                    break;
                } else break;
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            for (int i = 1; i < 8; i++) {
                if (pools[x - i][y + i].getFigure() == null) {
                    pools[x - i][y + i].setPossibleMove(true);
                } else if (pools[x][y + i].getFigure().getColor() != color) {
                    pools[x - i][y + i].setPossibleMove(true);
                    break;
                } else break;
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            for (int i = 1; i < 8; i++) {
                if (pools[x + i][y - i].getFigure() == null) {
                    pools[x + i][y - i].setPossibleMove(true);
                } else if (pools[x + i][y - i].getFigure().getColor() != color) {
                    pools[x + i][y - i].setPossibleMove(true);
                    break;
                } else break;
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            for (int i = 1; i < 8; i++) {
                if (pools[x - i][y - i].getFigure() == null) {
                    pools[x - i][y - i].setPossibleMove(true);
                } else if (pools[x - i][y - i].getFigure().getColor() != color) {
                    pools[x - i][y - i].setPossibleMove(true);
                    break;
                } else break;
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
    }
    /**
     * Init pool to show where rock from coords x,y can move
     * @param x - height position of clicked pool
     * @param y - width position of clicked pool
     * @param color - Colour of figure clicked
     */
    private void potentialRock(int x, int y, ColorFigure color) {
        try {
            for (int i = 1; i < 8; i++) {
                if (pools[x + i][y].getFigure() == null) {
                    pools[x + i][y].setPossibleMove(true);
                } else if (pools[x + i][y].getFigure().getColor() != color) {
                    pools[x + i][y].setPossibleMove(true);
                    break;
                } else break;
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            for (int i = 1; i < 8; i++) {
                if (pools[x][y + i].getFigure() == null) {
                    pools[x][y + i].setPossibleMove(true);
                } else if (pools[x][y + i].getFigure().getColor() != color) {
                    pools[x][y + i].setPossibleMove(true);
                    break;
                } else break;
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            for (int i = -1; i > -8; i--) {
                if (pools[x + i][y].getFigure() == null) {
                    pools[x + i][y].setPossibleMove(true);
                } else if (pools[x + i][y].getFigure().getColor() != color) {
                    pools[x + i][y].setPossibleMove(true);
                    break;
                } else break;
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            for (int i = -1; i > -8; i--) {
                if (pools[x][y + i].getFigure() == null) {
                    pools[x][y + i].setPossibleMove(true);
                } else if (pools[x][y + i].getFigure().getColor() != color) {
                    pools[x][y + i].setPossibleMove(true);
                    break;
                } else break;
            }
        } catch (IndexOutOfBoundsException ignored) {
        }
    }
    /**
     * Init pool to show where pawn from coords x,y can move
     * @param x - height position of clicked pool
     * @param y - width position of clicked pool
     * @param color - Colour of figure clicked
     */
    private void potentialPawn(int x, int y, ColorFigure color) {
        if (color == ColorFigure.BLACK) {

            if (pools[x + 1][y].getFigure() == null) {
                pools[x + 1][y].setPossibleMove(true);
                if (x == 1)
                    if (pools[x + 2][y].getFigure() == null) pools[x + 2][y].setPossibleMove(true);
            }
            try {
                if (pools[x + 1][y + 1].getFigure() != null)
                    if (pools[x + 1][y + 1].getFigure().getColor() != color) {
                        pools[x + 1][y + 1].setPossibleMove(true);
                    }
            } catch (IndexOutOfBoundsException ignored) {
            }
            try {

                if (pools[x + 1][y - 1].getFigure() != null)
                    if (pools[x + 1][y - 1].getFigure().getColor() != color) {
                        pools[x + 1][y - 1].setPossibleMove(true);
                    }
            } catch (IndexOutOfBoundsException ignored) {
            }
        } else {
            if (pools[x - 1][y].getFigure() == null) {
                pools[x - 1][y].setPossibleMove(true);
                if (x == 6)
                    if (pools[x - 2][y].getFigure() == null) pools[x - 2][y].setPossibleMove(true);
            }
            try {
                if (pools[x - 1][y + 1].getFigure() != null)
                    if (pools[x - 1][y + 1].getFigure().getColor() != color) {
                        pools[x - 1][y + 1].setPossibleMove(true);
                    }
            } catch (IndexOutOfBoundsException ignored) {
            }
            try {
                if (pools[x - 1][y - 1].getFigure() != null)
                    if (pools[x - 1][y - 1].getFigure().getColor() != color) {
                        pools[x - 1][y - 1].setPossibleMove(true);
                    }
            } catch (IndexOutOfBoundsException ignored) {
            }

        }

    }
    /**
     * Init pool to show where king from coords x,y can move
     * @param x - height position of clicked pool
     * @param y - width position of clicked pool
     * @param color - Colour of figure clicked
     */
    private void potentialKing(int x, int y, ColorFigure color) {
        Point[] points = {
                new Point(-1, -1),
                new Point(0, -1),
                new Point(1, -1),
                new Point(1, 0),
                new Point(-1, 0),
                new Point(1, 1),
                new Point(0, 1),
                new Point(-1, 1),
        };
        for (Point p : points) {
            try {
                if (pools[x + p.x][y + p.y].getFigure() == null || pools[x + p.x][y + p.y].getFigure().getColor() != color)
                    pools[x + p.x][y + p.y].setPossibleMove(true);
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
    }
    /**
     * Init pool to show where knight from coords x,y can move
     * @param x - height position of clicked pool
     * @param y - width position of clicked pool
     * @param color - Colour of figure clicked
     */
    private void potentialKnight(int x, int y, ColorFigure color) {
        Point[] points = {
                new Point(-2, -1),
                new Point(-1, -2),
                new Point(1, -2),
                new Point(2, -1),
                new Point(-1, 2),
                new Point(-2, 1),
                new Point(1, 2),
                new Point(2, 1)
        };
        for (Point p : points) {
            try {
                if (pools[x + p.x][y + p.y].getFigure() == null || pools[x + p.x][y + p.y].getFigure().getColor() != color)
                    pools[x + p.x][y + p.y].setPossibleMove(true);
            } catch (IndexOutOfBoundsException ignored) {
            }
        }

    }

}
