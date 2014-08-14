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
import imagereader.IImageProcessor;
import java.awt.Image;
import java.awt.image.*;
import java.io.File;
import java.awt.Graphics;
import javax.imageio.ImageIO;

public class ImageProcessImplement implements IImageProcessor {
    private static int st = 16;
    private static int et = 8;
    private static int zero = 0;
    private static int ff = 0xff;
    private static int ffoo= 0xff00;
    private static int ffoooo = 0xff0000;

	public Image showChanelR(Image sourceImage) {
			try {
            //read in the image file and draw out it
	  		int rwidth = sourceImage.getWidth(null);
			int rheighth = sourceImage.getHeight(null);
			BufferedImage rbi = new BufferedImage(rwidth, rheighth, BufferedImage.TYPE_3BYTE_BGR);
    		Graphics rgs = rbi.getGraphics();
    		rgs.drawImage(sourceImage, 0, 0, null);
            /*
             * traveser each pixel in an image and get its value of RGB
             * then change the RGB of each pixel so as to change the
             * display of the image
             */
    		for (int i = 0; i < rwidth; i++) {
        		for (int j = 0; j < rheighth; j++) {
            		int rgb = rbi.getRGB(i, j);
            		int r = (rgb & ffoooo) >> st;
         			rgb = r << st | zero << et | zero;
            		rbi.setRGB(i,j,rgb);
       	 		}
    		}   
            //save the image that has been deal with to file		
    		String filePath = "/home/xyz/shixun/stage3/Red.bmp";
            ImageIO.write(rbi, "bmp", new File(filePath));
            IImageIOImplement imageTo = new IImageIOImplement();
    		return imageTo.myRead(filePath);
		} catch(Exception e) {
			e.getMessage();
		}
        //if image is not exisit, return null
		return (Image)null;
	}
	public Image showChanelG(Image sourceImage) {
		try {
	  		int gwidth = sourceImage.getWidth(null);
			int gheighth = sourceImage.getHeight(null);
			BufferedImage gbi = new BufferedImage(gwidth, gheighth, BufferedImage.TYPE_3BYTE_BGR);
    		Graphics ggs = gbi.getGraphics();
    		ggs.drawImage(sourceImage, 0, 0, null);
            /*
             * traveser each pixel in an image and get its value of RGB
             * then change the RGB of each pixel so as to change the
             * display of the image
             */
    		for (int i = 0; i < gwidth; i++) {
        		for (int j = 0; j < gheighth; j++) {
            		int rgb = gbi.getRGB(i, j);
            		int g = (rgb & ffoo) >> et;
            		rgb = zero << st | g << et | zero ;
            		gbi.setRGB(i,j,rgb);
       	 		}
    		}
            //save the image that has been deal with to file
    		String filePath = "/home/xyz/shixun/stage3/Green.bmp";
            ImageIO.write(gbi, "bmp", new File(filePath));
            IImageIOImplement imageTog = new IImageIOImplement();
            return imageTog.myRead(filePath);
	   } catch(Exception e) {
			e.getMessage();
	   }
       //if image is not exisit, return null
		return (Image)null;
    }
	public Image showChanelB(Image sourceImage) {
			try {
	  		int bwidth = sourceImage.getWidth(null);
			int bheighth = sourceImage.getHeight(null);
			BufferedImage bbi = new BufferedImage(bwidth, bheighth, BufferedImage.TYPE_3BYTE_BGR);
    		Graphics bgs = bbi.getGraphics();
    		bgs.drawImage(sourceImage, 0, 0, null);
            /*
             * traveser each pixel in an image and get its value of RGB
             * then change the RGB of each pixel so as to change the
             * display of the image
             */
    		for (int i = 0; i < bwidth; i++) {
        		for (int j = 0; j < bheighth; j++) {
            		int rgb = bbi.getRGB(i, j);
            		int b = (rgb & ff);
            		rgb = zero << st | zero << et | b;
            		bbi.setRGB(i,j,rgb);
       	 		}
    		}
            //save the image that has been deal with to file
    		String filePath = "/home/xyz/shixun/stage3/Blue.bmp";
            ImageIO.write(bbi, "bmp", new File(filePath));
            IImageIOImplement imageTob = new IImageIOImplement();
            return imageTob.myRead(filePath);
		} catch(Exception e) {
			e.getMessage();
		}
        //if image is not exisit, return null
		return (Image)null;
	}
	public Image showGray(Image sourceImage) {
		try {
	  		int ywidth = sourceImage.getWidth(null);
			int yheighth = sourceImage.getHeight(null);
			BufferedImage ybi = new BufferedImage(ywidth, yheighth, BufferedImage.TYPE_3BYTE_BGR);
    		Graphics ygs = ybi.getGraphics();
    		ygs.drawImage(sourceImage, 0, 0, null);
            /*
             * traveser each pixel in an image and get its value of RGB
             * then change the RGB of each pixel so as to change the
             * display of the image
             */
    		for (int i = 0; i < ywidth; i++) {
        		for (int j = 0; j < yheighth; j++) {
            		int rgb = ybi.getRGB(i, j);
            		int r = (rgb & ffoooo) >> st;
            		int g = (rgb & ffoo) >> et;
            		int b = (rgb & ff);
            		double gray = 0.299*r+0.587*g+0.114*b;
            		int intI= (int)gray;
            		rgb = (intI << st) | (intI << et) | intI;
            		ybi.setRGB(i,j,rgb);
       	 		}
    		}
            //save the image that has been deal with to file
    		String filePath = "/home/xyz/shixun/stage3/Gray.bmp";
    		ImageIO.write(ybi, "bmp", new File(filePath));
            IImageIOImplement imageToy = new IImageIOImplement();
            return imageToy.myRead(filePath);
		} catch(Exception e) {
			e.getMessage();
		}
        //if image is not exisit, return null
		return (Image)null;
	}
}
