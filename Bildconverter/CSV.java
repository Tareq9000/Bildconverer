package Bildconverter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSV {

	ArrayList<String[]> content = new ArrayList<String[]>();

	File csvFile;
	String[] einzelZeilen;

	public void load() throws IOException {
		FileReader fr = new FileReader(csvFile);
		char[] a = new char[(int) csvFile.length()];

		fr.read(a);
		fr.close();

		String strFile = new String(a);
		einzelZeilen = strFile.split("\r\n");
		for (int i = 0; i < einzelZeilen.length; i++) {
			content.add(einzelZeilen[i].split(","));
		}
	}

	public void ausgeben() {
		for (String[] strings : content) {
			for (String string : strings) {
				System.out.print(string + " ");
			}
			System.out.println();
		}
	}

	public String[] getCont(int index) {
		String[] line = content.get(index);
		return line;
	}

	public CSV(String dateiName) throws IOException {
		csvFile = new File(dateiName);
	}

}