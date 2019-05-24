package com.pression;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Rz {
	public static String readFile(String filePath) throws IOException {
		String line = null;
		StringBuilder dest = new StringBuilder();
		InputStream in = null;
		BufferedReader br = null;
		Reader reader = null;

		try {
			File file = new File(filePath);
			in = new FileInputStream(file);
			reader = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(reader);
			while ((line = br.readLine()) != null) {
				 int n = line.length();
				 for (int i = 0; i < n; i++) {
				 int count = 1;
				 while (i < n - 1 && line.charAt(i) == line.charAt(i + 1)) {
				 count++;
				 i++;
				 }
				 
				 dest.append(line.charAt(i));
				 dest.append(count);
				
				 }
				
				
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (in != null) {
				in.close();
			}
			if (reader != null) {
				reader.close();
			}
			if (br != null) {
				br.close();
			}
		}
		return dest.toString();
	}

	public static void writeFile(String fileis, String fname) throws IOException {
		File fileo = new File(fname);
		OutputStream file_output = null;
		BufferedWriter bw = null;
		Writer writer = null;

		try {
			file_output = new FileOutputStream(fileo);
			writer = new OutputStreamWriter(file_output, "UTF-8");
			bw = new BufferedWriter(writer);
			for (int i = 0; i < fileis.length(); i++) {
				bw.write(fileis.charAt(i));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (writer != null) {
					writer.close();
				}
				if (file_output != null) {
					file_output.close();
				}
			}

			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void beginRzipping(String arg1) {
		String str = null;
		try {
			str = readFile(arg1);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			writeFile(str, arg1 + ".RLCz");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
