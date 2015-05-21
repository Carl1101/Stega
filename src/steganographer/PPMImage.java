package steganographer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PPMImage {

    private String type;
    private int width, height, maxColors;
    private long size;
    private final String path;
    private ArrayList<Pixel> pixels;
    private File image;

    public PPMImage(File image) {
        this.image = image;
        this.path = image.getAbsolutePath();
        this.pixels = new ArrayList<>();
        readFile();
    }
    
    public void readFile(){
        try {
            Scanner scan = new Scanner(this.image);
            
            this.type = scan.nextLine();
            String[] wH = scan.nextLine().split(" ");
            this.width = Integer.parseInt(wH[0]);
            this.height = Integer.parseInt(wH[1]);
            this.maxColors = Integer.parseInt(scan.nextLine());
            
            while (scan.hasNext()) {
                String[] line = scan.nextLine().split(" ");
                for (int i = 0; i < line.length; i += 3) {
                    this.pixels.add(new Pixel(
                            Integer.parseInt(line[i]),
                            Integer.parseInt(line[i+1]),
                            Integer.parseInt(line[i+2])
                    ));
                }
            }
            scan.close();
            this.size = pixels.size() * 24;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PPMImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getMaxColors() {
        return maxColors;
    }

    public void setMaxColors(int maxColors) {
        this.maxColors = maxColors;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public ArrayList<Pixel> getPixels() {
        return pixels;
    }

    public void setPixels(ArrayList<Pixel> pixels) {
        this.pixels = pixels;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }
    
    public String getPath() {
        return path;
    }
}
