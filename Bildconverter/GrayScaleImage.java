package Bildconverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class GrayScaleImage {
	private static final int MAX_IMAGE_SIZE = 50;
	private static final byte GRAY_SCALE_STEPS = 2;

	private BufferedImage image;

	private byte[][] pixels;

	private int grayScaleSteps;

	/**
	 * Creates a new gray scale image decorator.
	 * 
	 * @param grayScaleSteps
	 *            Sub-sampling steps
	 */
	public GrayScaleImage(int grayScaleSteps) {
		this.grayScaleSteps = grayScaleSteps;
	}

	/**
	 * Creates a new gray scale image decorator.
	 */
	public GrayScaleImage() {
		this(GRAY_SCALE_STEPS);
	}

	/**
	 * Loads a color jpg and converts it into a grayscale image.
	 * 
	 * @param imageData
	 *            Raw jpg data
	 * @param maxImageSize
	 *            Maximum image size of width or height
	 * @throws IOException
	 *             Throws if data could not be read
	 */
	public void loadImage(byte[] imageData, int maxImageSize) throws IOException {
		image = byteArrayToBufferedImage(imageData);

		// scale with ratio
		int longerSide = image.getWidth() < image.getHeight() ? image.getHeight() : image.getWidth();
		double scaleFactor = MAX_IMAGE_SIZE / (double) longerSide;

		int width = (int) (image.getWidth() * scaleFactor);
		int height = (int) (image.getHeight() * scaleFactor);

		image = resizeImage(image, width, height);
		createGrayScale(image, (byte) (grayScaleSteps - 1));
	}

	/**
	 * Loads a color jpg and converts it into a grayscale image.
	 * 
	 * @param imageData
	 *            Raw jpg data
	 * @throws IOException
	 *             Throws if data could not be read
	 */
	public void loadImage(byte[] imageData) throws IOException {
		loadImage(imageData, MAX_IMAGE_SIZE);
	}

	/**
	 * Returns the grayscale pixel of the image.
	 * 
	 * @return Grayscale pixels.
	 */
	public byte[][] getPixels() {
		return pixels;
	}

	private void createGrayScale(BufferedImage image, byte grayScaleSteps) {
		pixels = new byte[image.getHeight()][image.getWidth()];

		for (int y = 0; y < pixels.length; y++) {
			for (int x = 0; x < pixels[y].length; x++) {
				// read pixel
				int rgb = image.getRGB(x, y);

				int r = (rgb >> 16) & 0xFF;
				int g = (rgb >> 8) & 0xFF;
				int b = (rgb & 0xFF); 

				// convert to grayscale
				int gray = (r + g + b) / 3;

				// subsample
				pixels[y][x] = (byte) Math.round(gray / 255d * (255d / (255d / grayScaleSteps)));
			}
		}
	}

	private BufferedImage byteArrayToBufferedImage(byte[] data) throws IOException {
		InputStream in = new ByteArrayInputStream(data);
		return ImageIO.read(in);
	}

	private BufferedImage resizeImage(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());

		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}
}
