/*
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 */
import imagereader.IImageIO;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.io.FileInputStream;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class IImageIOImplement implements IImageIO {
    private byte[] tagBITMAPFILEHEADER;
    private byte[] tagBITMAPINFOHEADER;
    private byte[] biRgb;
    private Image bitImage;
    private static int tf = 24;
    private static int st = 16;
    private static int et = 8;
    private static int ff = 0xff;
    private static int ft = 14;
    private static int fty = 40;
    
    public IImageIOImplement() {
        tagBITMAPFILEHEADER = new byte[ft];
        tagBITMAPINFOHEADER = new byte[fty];
    }
    public Image myRead(String filePath) {
        try {
            File imageFile = new File(filePath);
            if (imageFile.exists()) {
                /*
                 *get the value of image's width, height, size
                 * and the addtional bytes that need to
                 * be add to the row so as to make the number of 
                 * bytes in a row is the time of 4
                 */
                FileInputStream in = new FileInputStream(imageFile);
                in.read(tagBITMAPFILEHEADER, 0, ft);
                in.read(tagBITMAPINFOHEADER, 0, fty);

                int biWidth = (((int) tagBITMAPINFOHEADER[7] & ff) << tf)
                | (( (int) tagBITMAPINFOHEADER[6] & ff) << st)
                | (( (int)tagBITMAPINFOHEADER[5] & ff) << et)
                | (( (int)tagBITMAPINFOHEADER[4] & ff));

                int biHeight = (((int) tagBITMAPINFOHEADER[11] & ff) << tf)
                | (( (int) tagBITMAPINFOHEADER[10] & ff) << st)
                | (( (int)tagBITMAPINFOHEADER[9] & ff) << et)
                | (( (int)tagBITMAPINFOHEADER[8] & ff));
            
                int biSizeImage = (((int)tagBITMAPINFOHEADER[23] & ff) << tf)
                | (((int)tagBITMAPINFOHEADER[22] & ff) << st)
                | (((int)tagBITMAPINFOHEADER[21] & ff) << et)
                | (int)tagBITMAPINFOHEADER[20] & ff;
                //bipad is the number of biytes that need to add to a row
                int bipad = (biSizeImage/biHeight)-biWidth*3;
                int bidata[] = new int[biHeight*biWidth];
                biRgb = new byte[(biWidth*3+bipad)*biHeight];
                in.read(biRgb, 0, (biWidth*3+bipad)*biHeight);
                int biIndex = 0;
                for (int i = 0; i < biHeight; i++) {
                    for (int j = 0; j < biWidth; j++) {
                    //the index is biWidth * (biHeight-i-1)+j is to
                    //guarrentee the order of from down to up
                    //form left to right
                    bidata[biWidth * (biHeight-i-1)+j] = 
                    (255 & ff) << 24
                    | (((int)biRgb[biIndex+2] & ff) << st)
                    | (((int)biRgb[biIndex+1] & ff) << et)
                    | (int)biRgb[biIndex] & ff;
                    biIndex += 3;
                    }
                    biIndex += bipad;
                }
                //producing the image that contains in the bmp file.
                bitImage = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(
                biWidth, biHeight, bidata, 0, biWidth));

                in.close();
                return bitImage;
            }
        } catch (Exception e) {
            e.getMessage();
        }
       
        return (Image)null;
    }
    public Image myWrite(Image image, String filePath) {
     try {
        int w = image.getWidth(null);
        int h = image.getHeight(null);

        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = bi.getGraphics();
        //draw out the image by an instance of Class Graphics
        g.drawImage(image, 0, 0, null);
        //write image of type bmp to disk
        ImageIO.write(bi, "bmp", new File(filePath));
        return image;
     } catch(Exception e) {
        e.getMessage();
     }
     return (Image)null;
    }
}
