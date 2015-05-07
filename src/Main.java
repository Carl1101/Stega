import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		File keyImage = new File(JOptionPane.showInputDialog(null,"Please enter the key image file name with extention"));
		Object[] options = {"Text File", "Image File"};
		int dataType = JOptionPane.showOptionDialog(null, "Please chose message type", 
				"Message Type",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, options, options[0]);
		File dataFile = new File(JOptionPane.showInputDialog(null,"Please enter the data file name with extention"));
		//File stegaImageFile = new File(JOptionPane.showInputDialog(null,"Please enter the output file name with extention"));
		
		Steganography steg = new Steganography(dataType, new PPMImage(keyImage), fileToBits(dataFile));
		
	}
	
	public static ArrayList<Integer> fileToBits(File file) {
		 ArrayList<Integer> data = new ArrayList<>();
		
		try {
			Scanner reader = new Scanner(file);
			
			while(reader.hasNext()){
				byte[] tmp = reader.nextLine().getBytes();
		
				for (int i = 0; i < tmp.length; i++) {
					String tmp2 = String.format("%8s", Integer.toBinaryString(tmp[i])).replace(" ", "0");
					//System.out.println(new Character ((char)Integer.parseInt(String.format("%8s", Integer.toBinaryString(tmp[i])).replace(" ", "0"),2)));
					for (int j = 0; j < tmp2.length(); j++) 
						data.add(Integer.parseInt(Character.toString(tmp2.charAt(j))));		
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}