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
import imagereader.Runner;

public class ImageRunner {
    public static void main(String[] args) {
    	IImageIOImplement imageIo = new IImageIOImplement();
    	ImageProcessImplement imageProcess = new ImageProcessImplement();
    	//call class Runner's static method run.
    	Runner.run(imageIo, imageProcess);
    }
}
