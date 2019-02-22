package Bildconverter;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		AsciiConverter converter = new AsciiConverter();

		converter.load("C:\\UBS\\Dev\\ws47\\Woche3\\laugh.jpg");
		converter.convert();
	
	}

}
	