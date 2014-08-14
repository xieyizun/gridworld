import static org.junit.Assert.*;
import org.junit.Test;

import imagereader.IImageIO;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.io.FileInputStream;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class ImageTest {
    public IImageIOImplement imageIo = new IImageIOImplement();
    public ImageProcessImplement imageProcess = new ImageProcessImplement();
    @Test
    public void testShowchanleB() {
        //test width and height of the two images
        String filePath1 = "/home/xyz/shixun/stage3/bmptest/1.bmp";
        String filePath2 = "/home/xyz/shixun/stage3/bmptest/goal/1_blue_goal.bmp";
        Image image = imageIo.myRead(filePath1);
        Image imageB = imageProcess.showChanelB(image);
        Image blueGoal = imageIo.myRead(filePath2);
        int imageBw = imageB.getWidth(null);
        int imageBh = imageB.getHeight(null);
        int blueGoalw = blueGoal.getWidth(null);
        int blueGoalh = blueGoal.getHeight(null);
        assertEquals(imageBw, blueGoalw);
        assertEquals(imageBh, blueGoalh);

        //test each pixel of the two images
        BufferedImage imageBbi = new BufferedImage(imageBw, imageBh, BufferedImage.TYPE_3BYTE_BGR);
        Graphics bgs = imageBbi.getGraphics();
        bgs.drawImage(imageB, 0, 0, null);

        BufferedImage blueGoalbi = new BufferedImage(blueGoalw, blueGoalh, BufferedImage.TYPE_3BYTE_BGR);
        Graphics bggs = blueGoalbi.getGraphics();
        bggs.drawImage(blueGoal, 0, 0, null);

        for (int i = 0; i < imageBw; i++) {
            for (int j = 0; j < imageBh; j++) {
                int rgb = imageBbi.getRGB(i, j);
                int grgb = blueGoalbi.getRGB(i, j);
                assertEquals(rgb, grgb);
            }
        }
    }
    @Test
    public void testShowchanelR() {
        //test width and height of the two images
        String filePath1 = "/home/xyz/shixun/stage3/bmptest/1.bmp";
        String filePath2 = "/home/xyz/shixun/stage3/bmptest/goal/1_red_goal.bmp";
        Image image = imageIo.myRead(filePath1);
        Image imageB = imageProcess.showChanelR(image);
        Image blueGoal = imageIo.myRead(filePath2);
        int imageBw = imageB.getWidth(null);
        int imageBh = imageB.getHeight(null);
        int blueGoalw = blueGoal.getWidth(null);
        int blueGoalh = blueGoal.getHeight(null);
        assertEquals(imageBw, blueGoalw);
        assertEquals(imageBh, blueGoalh);

        //test each pixel of the two images
        BufferedImage imageBbi = new BufferedImage(imageBw, imageBh, BufferedImage.TYPE_3BYTE_BGR);
        Graphics bgs = imageBbi.getGraphics();
        bgs.drawImage(imageB, 0, 0, null);

        BufferedImage blueGoalbi = new BufferedImage(blueGoalw, blueGoalh, BufferedImage.TYPE_3BYTE_BGR);
        Graphics bggs = blueGoalbi.getGraphics();
        bggs.drawImage(blueGoal, 0, 0, null);

        for (int i = 0; i < imageBw; i++) {
            for (int j = 0; j < imageBh; j++) {
                int rgb = imageBbi.getRGB(i, j);
                int grgb = blueGoalbi.getRGB(i, j);
                assertEquals(rgb, grgb);
            }
        }
    }
    @Test
    public void testShowchanelG() {
        //test width and height of the two images
        String filePath1 = "/home/xyz/shixun/stage3/bmptest/1.bmp";
        String filePath2 = "/home/xyz/shixun/stage3/bmptest/goal/1_green_goal.bmp";
        Image image = imageIo.myRead(filePath1);
        Image imageB = imageProcess.showChanelG(image);
        Image blueGoal = imageIo.myRead(filePath2);
        int imageBw = imageB.getWidth(null);
        int imageBh = imageB.getHeight(null);
        int blueGoalw = blueGoal.getWidth(null);
        int blueGoalh = blueGoal.getHeight(null);
        assertEquals(imageBw, blueGoalw);
        assertEquals(imageBh, blueGoalh);

        //test each pixel of the two images
        BufferedImage imageBbi = new BufferedImage(imageBw, imageBh, BufferedImage.TYPE_3BYTE_BGR);
        Graphics bgs = imageBbi.getGraphics();
        bgs.drawImage(imageB, 0, 0, null);

        BufferedImage blueGoalbi = new BufferedImage(blueGoalw, blueGoalh, BufferedImage.TYPE_3BYTE_BGR);
        Graphics bggs = blueGoalbi.getGraphics();
        bggs.drawImage(blueGoal, 0, 0, null);

        for (int i = 0; i < imageBw; i++) {
            for (int j = 0; j < imageBh; j++) {
                int rgb = imageBbi.getRGB(i, j);
                int grgb = blueGoalbi.getRGB(i, j);
                assertEquals(rgb, grgb);
            }
        }
    }
    @Test
    public void testShowGray() {
        //test width and height of the two images
        String filePath1 = "/home/xyz/shixun/stage3/bmptest/1.bmp";
        String filePath2 = "/home/xyz/shixun/stage3/bmptest/goal/1_gray_goal.bmp";
        Image image = imageIo.myRead(filePath1);
        Image imageB = imageProcess.showGray(image);
        Image blueGoal = imageIo.myRead(filePath2);
        int imageBw = imageB.getWidth(null);
        int imageBh = imageB.getHeight(null);
        int blueGoalw = blueGoal.getWidth(null);
        int blueGoalh = blueGoal.getHeight(null);
        assertEquals(imageBw, blueGoalw);
        assertEquals(imageBh, blueGoalh);

        //test each pixel of the two images
        BufferedImage imageBbi = new BufferedImage(imageBw, imageBh, BufferedImage.TYPE_3BYTE_BGR);
        Graphics bgs = imageBbi.getGraphics();
        bgs.drawImage(imageB, 0, 0, null);

        BufferedImage blueGoalbi = new BufferedImage(blueGoalw, blueGoalh, BufferedImage.TYPE_3BYTE_BGR);
        Graphics bggs = blueGoalbi.getGraphics();
        bggs.drawImage(blueGoal, 0, 0, null);

        for (int i = 0; i < imageBw; i++) {
            for (int j = 0; j < imageBh; j++) {
                int rgb = imageBbi.getRGB(i, j);
                int grgb = blueGoalbi.getRGB(i, j);
                assertEquals(rgb, grgb);
            }
        }
    }

}
