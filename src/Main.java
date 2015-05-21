import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import steganographer.Stega;
import imageconverter.ImageConverter;

public class Main {
    
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) throws IOException {
    	while(true){
    		menu();
    	}
    }
    
    public static void menu(){
    	Object[] options = {"Hide text", "Hide image", "Reveal text from stegoimage",
    			"Reveal image from stegoimage", "Convert image",
    			"Exit"};
    	
		String choice = (String) JOptionPane.showInputDialog(null, "Please select an option", 
				"Options", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
		
		handleOption(choice);
		
    }
    
    public static void handleOption(String option) {
       
            switch(option) {
                case "Hide text":
                    hideText();
                    break;
                case "Hide image":
                    hideImage();
                    break;
                case "Reveal text from stegoimage":
                    revealText();
                    break;
                case "Reveal image from stegoimage":
                    revealImage();
                    break;
                case "Convert image":
                    convertImage();
                    break;
                case "Exit":
                    System.exit(0);
                    break;
            }
    }
    
    public static void convertImage() {
  
        try {
        	String filename = JOptionPane.showInputDialog(null,"Please enter name of file to convert");
        	Object[] formats = {"ppm", "jpg", "jpeg", "png", "gif"};
        	String format = (String) JOptionPane.showInputDialog(null, "Please select a format", 
    				"Formats", JOptionPane.QUESTION_MESSAGE, null, formats, formats[0]);
            
            ImageConverter.convert(filename, format);
            JOptionPane.showMessageDialog(null, "Succes!!");
     
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void hideText() {
    	File f = new File(JOptionPane.showInputDialog(null,"Please enter key image file"));
        String text = JOptionPane.showInputDialog(null,"Please enter text to hide");
        
        Stega steg = new Stega(f);
        boolean hidden = steg.hide(text.getBytes(), "text");
        
        if(hidden) 
        	JOptionPane.showMessageDialog(null, "Success, stego image: stego-image.ppm");
         
    }
    
    public static void hideImage() {
    		
    		try {
    			File f = new File(JOptionPane.showInputDialog(null,"Please enter key image file"));
        		String secret = JOptionPane.showInputDialog(null,"Please enter image file to hide");
				byte[] image = Files.readAllBytes(Paths.get(secret));
				
				Stega steg = new Stega(f);
	            boolean hidden = steg.hide(image, "image");
	            
	            if (hidden)
	            	JOptionPane.showMessageDialog(null, "Success, stego image: stego-image.ppm");
	            
			} catch (IOException ex) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
			}
    }
    
     public static void revealText() {
    	File f = new File(JOptionPane.showInputDialog(null,"Please enter key image file"));
    	File f2 = new File(JOptionPane.showInputDialog(null,"Please enter stego image"));        
        
        Stega steg = new Stega(f);
        JOptionPane.showMessageDialog(null, "Hidden Message: " + steg.reveal(f2, "text"));
    }
    
    public static void revealImage() {
    	File f = new File(JOptionPane.showInputDialog(null,"Please enter key image file"));
    	File f2 = new File(JOptionPane.showInputDialog(null,"Please enter stego image"));         
        
        Stega steg = new Stega(f);
        steg.reveal(f2, "photo");
        
        JOptionPane.showMessageDialog(null, "Success, image has been revealed into: revealed-image.ppm");
    }
    
    public static String getFileExtensionFromPath(String path) {
        int i = path.lastIndexOf('.');
        if (i > 0) {
            return path.substring(i + 1);
        }
        return "";
    }
}
