import java.io.File;
import java.util.ArrayList;

public class Steganography {
	private int dataType;
	private PPMImage key;
	private ArrayList<Integer> message;

	@SuppressWarnings("unused")
	private Steganography() {}

	public Steganography(int dataType, PPMImage key ,ArrayList<Integer> message) {
		setDataType(dataType);
		setKey(key);
		setMessage(message);
	}

	public int getDataType() {
		return dataType;
	}

	private void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public ArrayList<Integer> getMessage() {
		return message;
	}

	private void setMessage(ArrayList<Integer> message) {
		this.message = message;
	}

	public PPMImage getKey() {
		return key;
	}

	private void setKey(PPMImage key) {
		this.key = key;
	}
	
	public boolean canHide() {
		return key.getKeyImageRows() * key.getKeyImageColums() * 24 > message.size();
	}
	
	public void hide(File file) {
		//Aqui va el print al archivo, recuerda verficar si cabe primero
	}
}
