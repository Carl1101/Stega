import java.awt.Color;

public class Pixel {
	private int red, green, blue;

	public Pixel(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public Color getColor() {
		return new Color(red, green, blue);
	}

	@Override
	public String toString() {
		return "Pixel [red=" + red + ", green=" + green + ", blue=" + blue
				+ "]";
	}
}
