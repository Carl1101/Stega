package steganographer;

public class Pixel {
    
    private int R,G,B;
    
    public Pixel(int r, int g, int b){
        this.R = r;
        this.G = g;
        this.B = b;
    }

    public Pixel(Pixel other) {
        this(other.getR(), other.getG(), other.getB());
    }

    public int getR() {
        return R;
    }

    public void setR(int R) {
        this.R = R;
    }

    public int getG() {
        return G;
    }

    public void setG(int G) {
        this.G = G;
    }

    public int getB() {
        return B;
    }

    public void setB(int B) {
        this.B = B;
    }

    @Override
    public boolean equals(Object obj) {
        Pixel otherPixel = (Pixel) obj;
        return this.R == otherPixel.getR()
            && this.G == otherPixel.getG()
            && this.B == otherPixel.getB();
    }
    
    @Override
    public String toString() {
        return this.getR() + " "
             + this.getG() + " "
             + this.getB();
    }
}
