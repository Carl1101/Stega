import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PPMImage {
	private File image;
	private String keyImageFormat;
	private int keyImageColums, keyImageRows, keyImageMaxTone; 
	private ArrayList<Pixel> pixels;
	
	@SuppressWarnings("unused")
	private PPMImage(){}
	
	public PPMImage(File image) {
		this.image = image;
		readKeyImage();
	}
	
	public String getKeyImageFormat() {
		return keyImageFormat;
	}

	private void setKeyImageFormat(String keyImageFormat) {
		this.keyImageFormat = keyImageFormat;
	}

	public ArrayList<Pixel> getPixels() {
		return pixels;
	}

	private void setPixels(ArrayList<Pixel> pixels) {
		this.pixels = pixels;
	}

	public int getKeyImageColums() {
		return keyImageColums;
	}

	private void setKeyImageColums(int keyImageColums) {
		this.keyImageColums = keyImageColums;
	}

	public int getKeyImageRows() {
		return keyImageRows;
	}

	private void setKeyImageRows(int keyImageRows) {
		this.keyImageRows = keyImageRows;
	}

	public int getKeyImageMaxTone() {
		return keyImageMaxTone;
	}

	private void setKeyImageMaxTone(int keyImageMaxTone) {
		this.keyImageMaxTone = keyImageMaxTone;
	}

	private void readKeyImage() { 
		ArrayList<Pixel> data = new ArrayList<Pixel>();
		
		try {
			Scanner reader = new Scanner(this.image);
			
			setKeyImageFormat(reader.next());
			setKeyImageColums(reader.nextInt());
			setKeyImageRows(reader.nextInt());
			setKeyImageMaxTone(reader.nextInt());
			
			while(reader.hasNext())
			data.add(new Pixel(reader.nextInt(), reader.nextInt(), reader.nextInt()));	
			
			reader.close();
			setPixels(data);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
