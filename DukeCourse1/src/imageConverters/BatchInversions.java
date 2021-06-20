package imageConverters;

import java.io.File;
import edu.duke.*;

public class BatchInversions {
	
	public ImageResource makeInversion(ImageResource inImage) {
		
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		for (Pixel pix : outImage.pixels()) {
			Pixel inPix = inImage.getPixel(pix.getX(), pix.getY());
			int red = inPix.getRed();
			int green = inPix.getGreen();
			int blue = inPix.getBlue();
			pix.setRed(255-red);
			pix.setGreen(255-green);
			pix.setBlue(255-blue);
		}
		return outImage;
	}
	
	public void selectConvertAndSave() {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource ir = new ImageResource(f);
			ImageResource inverted = makeInversion(ir);
			String fname = ir.getFileName();
			String newName = "inverted-" + fname;
			inverted.setFileName(newName);
			inverted.draw();
			inverted.save();
		}
	}

	public static void main(String[] args) {
		BatchInversions bi = new BatchInversions();
		bi.selectConvertAndSave();
	}

}
