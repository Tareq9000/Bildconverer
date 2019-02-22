package Bildconverter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.imageio.ImageIO;

public class AsciiConverter {
	byte[] daten;

	public void convert() throws IOException {

		char[] buchstaben = { 'W', 'H', 'X', 'K', 'Z', 'Y', 'T', 'Q', 'O', 'I', 'i', 'J', ';', '.', ',', '-', '´',
				' ' };
		File file = new File("asciibild.txt");

		file.createNewFile();

		GrayScaleImage image = new GrayScaleImage(buchstaben.length);
		image.loadImage(daten);

		byte[][] pixels = image.getPixels();
		Writer writer = new FileWriter(file);

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				System.out.print(buchstaben[pixels[i][j]]);
				writer.write(buchstaben[pixels[i][j]]);

			}
			writer.write("\n");
			System.out.println();

		}
		writer.close();
	}

	public void load(String pfad) throws IOException {

		BufferedImage originalImage = ImageIO.read(new File(pfad));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(originalImage, "jpg", baos);
		baos.flush();
		daten = baos.toByteArray();
		baos.close();

	}
}

//
//
//
// import java.awt.Color;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.PrintWriter;
//
// import javax.imageio.ImageIO;
//
// public class AsciiConverter{
//
// private BufferedImage img;
// private double pixval;
// private PrintWriter prntwrt;
// private FileWriter filewrt;
//
// public AsciiConverter() {
// try {
// prntwrt = new PrintWriter(filewrt = new FileWriter("asciiart.txt",
// true));
// } catch (IOException ex) {
// }
// }
//
// public void convertToAscii(String imgname) {
// try {
// img = ImageIO.read(new File(imgname));
// } catch (IOException e) {
// }
//
// for (int i = 0; i < img.getHeight(); i++) {
// for (int j = 0; j < img.getWidth(); j++) {
// Color pixcol = new Color(img.getRGB(j, i));
// pixval = (((pixcol.getRed() * 0.30) + (pixcol.getBlue() * 0.59) + (pixcol
// .getGreen() * 0.11)));
// print(strChar(pixval));
// }
// try {
// prntwrt.println("");
// prntwrt.flush();
// filewrt.flush();
// } catch (Exception ex) {
// }
// }
// }
//
// public String strChar(double g) {
// String str = " ";
// if (g >= 240) {
// str = " ";
// } else if (g >= 210) {
// str = ".";
// } else if (g >= 190) {
// str = "*";
// } else if (g >= 170) {
// str = "+";
// } else if (g >= 120) {
// str = "^";
// } else if (g >= 110) {
// str = "&";
// } else if (g >= 80) {
// str = "8";
// } else if (g >= 60) {
// str = "#";
// } else {
// str = "@";
// }
// return str;
// }
//
// public void print(String str) {
// try {
// prntwrt.print(str);
// prntwrt.flush();
// filewrt.flush();
// } catch (Exception ex) {
// }
// }
//
// public static void main(String[] args) {
// AsciiConverter obj = new AsciiConverter();
// obj.convertToAscii("C:\\UBS\\Dev\\ws47\\Woche3\\Images.png");
// }
// }