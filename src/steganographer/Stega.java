package steganographer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.regex.Pattern;


public class Stega {

    private final PPMImage image;

    public Stega(File image) {
        this.image = new PPMImage(image);
    }

    public boolean canHide(long size) {
        return this.image.getPixels().size() > size;
    }

    private boolean canHideInPixel(Pixel pixel) {
        return pixel.getR() < 255 || pixel.getG() < 255 || pixel.getB() < 255;
    }
    
    private boolean pixelHasData(Pixel orig, Pixel imageWithMessage) {
        if (!orig.equals(imageWithMessage)) {
            return true;
        } else {
          return orig.getR() < 255 || orig.getG() < 255 || orig.getB() < 255;  
        }
    }

    private ArrayList<Integer> getBytesInBits(byte[] bytes) {
        ArrayList<Integer> bits = new ArrayList<>();
        for (int i = 0; i < bytes.length; i++) {
            String tmp = String.format("%8s", Integer.toBinaryString(bytes[i])).replace(" ", "0");
            for (int j = 0; j < tmp.length(); j++) {
                bits.add(Integer.parseInt(Character.toString(tmp.charAt(j))));
            }
        }
        return bits;
    }

    public boolean hide(byte[] bytes, String type) {
        ArrayList<Integer> bits = getBytesInBits(bytes);
        if (canHide(bits.size())) {
            int bitIndex = 0;
            ArrayList<Pixel> newImage = new ArrayList<>();
            int size = this.image.getPixels().size();
            for (int idx = 0; idx < size; idx++) {
                Pixel pixel = new Pixel(this.image.getPixels().get(idx));
                if (canHideInPixel(pixel)) {
                    if (pixel.getR() < 255 && bitIndex < bits.size()) {
                        pixel.setR(pixel.getR() + bits.get(bitIndex++));
                    }
                    if (pixel.getG() < 255 && bitIndex < bits.size()) {
                        pixel.setG(pixel.getG() + bits.get(bitIndex++));
                    }
                    if (pixel.getB() < 255 && bitIndex < bits.size()) {
                        pixel.setB(pixel.getB() + bits.get(bitIndex++));
                    }
                }
                newImage.add(pixel);
            }
            printPixelsToFile(newImage);
            return true;
        } else {
            System.err.println("Cannot hide!");
            return false;
        }
    }
    
    public String reveal(File image, String type) {
        PPMImage messageImage = new PPMImage(image);
        int size = this.image.getPixels().size();
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Pixel orig = this.image.getPixels().get(i);
            Pixel other = messageImage.getPixels().get(i);
            if (pixelHasData(orig, other)) {
                if (orig.getR() < 255)
                    data.add(Math.abs(orig.getR() - other.getR()));                    
                if (orig.getG() < 255)
                    data.add(Math.abs(orig.getG() - other.getG()));                
                if (orig.getB() < 255)
                    data.add(Math.abs(orig.getB() - other.getB()));                    
            }
        }
        if (type.equals("text"))
            return bitsToString(data);
        else {
            printBitsToFile(data);
            return null;
        }
    }
    
    public void printPixels(ArrayList<Pixel> pixels) {
        System.out.print(pixels.stream()
            .map(Pixel::toString)
            .collect(Collectors.joining(" ")));
    }
    
    public void printPixelsToFile(ArrayList<Pixel> pixels) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            lines.add(this.image.getType());
            lines.add(this.image.getWidth() + " " + this.image.getHeight());
            lines.add(Integer.toString(this.image.getMaxColors()));
            String pixs = "";
            for (int i = 0; i < pixels.size(); i++) {
                if (i != 0 && i%4 == 0) {
                    lines.add(pixs);
                    pixs = "";
                }
                pixs += pixels.get(i) + " ";
            }
            lines.add(pixs);
            Files.write(
                    Paths.get(
                            this.image.getPath().substring(0, this.image.getPath().lastIndexOf(File.separator)) + "/stego-image.ppm"),
                    lines);
        } catch (IOException ex) {
            Logger.getLogger(Stega.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String bitsToString(ArrayList<Integer> bits) {
        String message = "";
        for (int i = 0; i < bits.size(); i+=8) {
            if ((i + 8) < bits.size()) {
                String tmp = String.join("", bits.subList(i, i+8).stream().map(x -> Integer.toString(x)).collect(Collectors.joining("")));
                if (!Pattern.matches("[01]+", tmp)) {
                    continue;
                }
                String letter = new Character((char)Integer.parseInt(tmp, 2)).toString();
                message += letter;
            }
        }
        return message.trim();
    }
    
    private void printBitsToFile(ArrayList<Integer> bits) {
        try {
            ArrayList<String> lines = new ArrayList<>();
            ArrayList<String> bytes = new ArrayList<>();
            for (int i = 0; i < 120; i+=8) {
                if ((i + 8) < bits.size()) {
                    String tmp = String.join("", bits.subList(i, i+8).stream().map(x -> Integer.toString(x)).collect(Collectors.joining("")));
                    String character = new Character((char)Integer.parseInt(tmp, 2)).toString();
                    bytes.add(character);
                }
            }
            lines.add(bytes.get(0) + "" + bytes.get(1));
            lines.add(bytes.get(3) + "" + bytes.get(4) + "" + bytes.get(5) + " " + bytes.get(7) + "" + bytes.get(8) + "" + bytes.get(9));
            lines.add(bytes.get(11) + "" + bytes.get(12) + "" + bytes.get(13));
            bytes.clear();
            for (int i = 120; i < bits.size(); i+=8) {
                if ((i + 8) < bits.size()) {
                    String tmp = String.join("", bits.subList(i, i+8).stream().map(x -> Integer.toString(x)).collect(Collectors.joining("")));
                    String color = new Character((char)Integer.parseInt(tmp, 2)).toString();
                    bytes.add(color);
                }
            }
            
            lines.add(String.join(" ", bytes.stream().collect(Collectors.joining(""))).trim());
            Files.write(
                    Paths.get(
                            this.image.getPath().substring(0, this.image.getPath().lastIndexOf(File.separator)) + "/revealed-image.ppm"),
                    lines);
        } catch (IOException ex) {
            Logger.getLogger(Stega.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
