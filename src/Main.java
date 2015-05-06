import java.io.File;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		File keyImage = new File(JOptionPane.showInputDialog(null,"Please enter the key image file name with extention")); 
		File data = new File(JOptionPane.showInputDialog(null,"Please enter the data file name with extention"));
		File stegaImage = new File(JOptionPane.showInputDialog(null,"Please enter the output file name with extention"));
		
		Steganography steg = new Steganography(keyImage, data, stegaImage);

		System.out.println(steg.getKeyImageFormat());


















	}
}