package MyChess.Resources;

import MyChess.Models.Figures.ColorFigure;
import MyChess.Models.Figures.Figure;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Resource {
    /**
     * Get BufferedImage of figure
     * @param figure Figure of chess
     * @return  Buffered Image
     */
    public static BufferedImage getFigureImageIcon(Figure figure) throws IOException {

       Resource resource = new Resource();
        File f = null;
        try {
            f = resource.getFileFromResource(figure.getClass().getSimpleName()+".png");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        BufferedImage bufferedImage= ImageIO.read(f);

        return colored(bufferedImage,figure.getColor());
    }


    /**
     * Set Color of figure in buffered image as this from parameter
     * @param bufferedImage Image
     * @param colorFigure  Color of figure
     * @return Buffered Image
     */

    private static BufferedImage colored(BufferedImage bufferedImage, ColorFigure colorFigure) {
        for(int i=0;i<bufferedImage.getWidth();i++){
            for (int j=0;j<bufferedImage.getHeight();j++){
                if(bufferedImage.getRGB(i,j)== Color.WHITE.getRGB())bufferedImage.setRGB(i,j,new Color(255,255,255,0).getRGB());
                else {
                    if(colorFigure==ColorFigure.BLACK) bufferedImage.setRGB(i,j,Color.BLACK.getRGB());
                    else  bufferedImage.setRGB(i,j,Color.WHITE.getRGB());
                }
            }
        }
        return bufferedImage;
    }

    /**
     * Get File from path in folder resources
     * @param fileName path of fILE
     * @return File
     */
    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }

    }
}
