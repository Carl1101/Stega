import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Steganography {
	private File keyImage, stegaImage, data; 
	private ArrayList<Pixel> keyImageData;
	private String keyImageFormat;
	private int keyImageColums, keyImageRows, keyImageMaxTone;
	
	@SuppressWarnings("unused")
	private Steganography(){}
	
	public Steganography(File keyImage,File data, File stegaImage) {
		setKeyImage(keyImage);
		setData(data);
		setStegaImage(stegaImage);
	}

	public File getKeyImage() {
		return keyImage;
	}

	private void setKeyImage(File keyImage) {
		this.keyImage = keyImage;
		setKeyImageData(readFile(this.keyImage));
		
	}

	public File getStegaImage() {
		return stegaImage;
	}

	private void setStegaImage(File stegaImage) {
		this.stegaImage = stegaImage;
	}

	public File getData() {
		return data;
	}

	private void setData(File data) {
		this.data = data;
	}
	
	public ArrayList<Pixel> getKeyImageData() {
		return keyImageData;
	}

	private void setKeyImageData(ArrayList<Pixel> keyImageData) {
		this.keyImageData = keyImageData;
	}
	
	private ArrayList<Pixel> readFile(File file){
		ArrayList<Pixel> data = new ArrayList<Pixel>();
		
		try {
			Scanner reader = new Scanner(file);
			
			setKeyImageFormat(reader.next());
			setKeyImageColums(reader.nextInt());
			setKeyImageRows(reader.nextInt());
			setKeyImageMaxTone(reader.nextInt());
			
			while(reader.hasNext())
			data.add(new Pixel(reader.nextInt(), reader.nextInt(), reader.nextInt()));	
			
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return data;
	}

	public String getKeyImageFormat() {
		return keyImageFormat;
	}

	private void setKeyImageFormat(String keyImageFormat) {
		this.keyImageFormat = keyImageFormat;
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
}