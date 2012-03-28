/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils.writer;

/**
 *
 * @author obendavi
 */
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterO {

	private FileWriter fw;

	public void writeStringOnFile(java.lang.String s, java.lang.String path) {
		try {
			fw = new FileWriter(path, false);
			fw.write(s, 0, s.length());
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		FileWriterO fw = new FileWriterO();
		fw.writeStringOnFile("test", "/home/obendavi/Bureau/test.txt");

	}

}
