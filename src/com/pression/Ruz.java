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


public class Ruz {
	
	public static String readFile(String filePath) throws IOException {
		String line = null;
		//StringBuilder dest = new StringBuilder();
		InputStream in = null;
		Reader reader = null;
		BufferedReader br = null;
		 String output = "";
		String l = "";
		try {
			File file = new File(filePath);
			in = new FileInputStream(file);
			reader = new InputStreamReader(in, "UTF-8");
			br = new BufferedReader(reader);

			while ((line = br.readLine()) != null) {
				int i, j = 0, k, c = 0, p = 0, m = line.length();
				for (i = 0; i < m; i++) {
					if (line.charAt(i) >= '0' && line.charAt(i) <= '9') {
						c++;
						if (c == 1)
							j = i - 1;
						l = l + line.charAt(i);
						p = Integer.valueOf(l);
						continue;
					} else if (p == 0)
						 output = output + line.charAt(i);
						//dest.append(line.charAt(i));
					if (p != 0) {
						for (k = 1; k < p; k++)
							 output = output + line.charAt(j);
							//dest.append(line.charAt(i));
						i--;
					}
					p = 0;
					c = 0;
					l = "";
				}
				if (p != 0) {
					for (i = 1; i < p; i++)
						 output = output + line.charAt(j);
						//dest.append(line.charAt(i));
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
		 return output;
		//return dest.toString();

	}

	public static void writeFile(String fileis, String fname) throws IOException {

		File fileo = new File(fname);
		OutputStream file_output = null;
		Writer writer = null;
		BufferedWriter bw = null;
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
	}

	public static void beginURzipping(String arg1) {

		String str = null;
		try {
			str = readFile(arg1);
		} catch (IOException e1) {

			e1.printStackTrace();
		}
		try {
			writeFile(str, arg1 + ".txt");
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
