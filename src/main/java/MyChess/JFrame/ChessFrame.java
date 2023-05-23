package MyChess.JFrame;

import MyChess.Resources.Images;
import MyChess.Models.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChessFrame extends JFrame {
    public static boolean actionEnabled=true;
    private PoolFrame[][] poolFrame;
    private Board board;
    private final Images images;

    public ChessFrame(){
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Color[] color = {new Color(160, 160, 160), new Color(0, 150, 75)};
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        poolFrame[i][j].update(images.get(board.getPool(i, j).getFigure()), color[(i + j) % 2], board.getPool(i, j).isPossibleMove());
                    }
                }

                repaint();
                actionEnabled = true;

            }
        };
        this.board=new Board(actionListener);
        images=new Images();
        setSize(1000,998);
        images.load((getWidth()-50)/8,(getHeight()-50)/8);
        setLayout(null);
        this.setTitle("My Chess");
        initPoolFrame();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        revalidate();




    }

    /**
     * Init view of board
     */
    private void initPoolFrame() {
        poolFrame=new PoolFrame[8][8];
        int width = (getWidth()-5)/8,height=(getHeight()-35)/8;
        Color[] color  = {new Color(160,160,160),new Color(0,150,75)};
        for (int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                poolFrame[i][j]= new PoolFrame(images.get(board.getPool(i,j).getFigure()),color[(i+j)%2],board.getPool(i,j).isPossibleMove(),board.getPool(i,j).getMouseListener());
                poolFrame[i][j].setBounds(j*width,i*height,width,height);
                poolFrame[i][j].setOpaque(true);
                this.add(poolFrame[i][j]);
            }
        }
    }
}
