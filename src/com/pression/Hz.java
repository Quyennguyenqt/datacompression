package com.pression;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.PriorityQueue;

public class Hz {
	static PriorityQueue<Tree> pq = new PriorityQueue<Tree>();
	static int[] freq = new int[300];
	static String[] ss = new String[300];
	static int exbits;
	static byte bt;
	static int cnt;
	static Tree root;
	
	static class Tree implements Comparable<Tree> {
		Tree left;
		Tree right;
		public String deb;
		public int Bite;
		public int freq;

		public int compareTo(Tree t) {
			if (this.freq < t.freq) {
				return -1;
			} else if (this.freq > t.freq) {
				return 1;
			} else
				return 0;
		}
	}

	

	public static void calFreq(String fname) {
		File file = null;
		Byte bt;

		file = new File(fname);
		try {
			FileInputStream file_input = new FileInputStream(file);
			DataInputStream data_in = new DataInputStream(file_input);
			while (true) {
				try {
					
					bt = data_in.readByte();
					freq[convert(bt)]++;
				} catch (EOFException eof) {
					System.out.println("End of File");
					break;
				}
			}
			file_input.close();
			data_in.close();
		} catch (IOException e) {
			System.out.println("IO Exception =: " + e);
		}
		file = null;
	}
//chuyển đổi ASCII
	public static int convert(Byte b) {
		int ret = b;
		if (ret < 0) {
			ret = ~b;
			ret = ret + 1;
			ret = ret ^ 255;
			ret += 1;
		}
		return ret;
	}
	
//giải phóng bộ nhớ
	public static void initHzipping() {
		int i;
		cnt = 0;
		if (root != null)
			fredfs(root);
		for (i = 0; i < 300; i++)
			freq[i] = 0;
		for (i = 0; i < 300; i++)
			ss[i] = "";
		pq.clear();
	}
	
	//duyệt cây
	
	public static void fredfs(Tree now) {

		if (now.left == null && now.right == null) {
			now = null;
			return;
		}
		if (now.left != null) {
			fredfs(now.left);
		}

		if (now.right != null) {
			fredfs(now.right);

		}
	}

	public static void dfs(Tree now, String st) {
		now.deb = st;
		if ((now.left == null) && (now.right == null)) {
			ss[now.Bite] = st;
			return;
		}
		if (now.left != null)
			dfs(now.left, st + "0");
		if (now.right != null)
			dfs(now.right, st + "1");
	}

	public static void makeNode() {
		int i;
		pq.clear();

		for (i = 0; i < 300; i++) {
			if (freq[i] != 0) {
				Tree temp = new Tree();
				temp.Bite = i;
				temp.freq = freq[i];
				temp.left = null;
				temp.right = null;
				pq.add(temp);
				cnt++;
			}

		}
		Tree temp1, temp2;

		if (cnt == 0) {
			return;
		} else if (cnt == 1) {
			for (i = 0; i < 300; i++)
				if (freq[i] != 0) {
					ss[i] = "0";
					break;
				}
			return;
		}

		while (pq.size() != 1) {
			Tree Temp = new Tree();
			temp1 = pq.poll();
			temp2 = pq.poll();
			Temp.left = temp1;
			Temp.right = temp2;
			Temp.freq = temp1.freq + temp2.freq;
			pq.add(Temp);
		}
		root = pq.poll();
	}

	public static void encrypt(String fname) {
		File file = null;

		file = new File(fname);
		try {
			FileInputStream file_input = new FileInputStream(file);
			DataInputStream data_in = new DataInputStream(file_input);
			while (true) {
				try {
					bt = data_in.readByte();
					freq[bt]++;
				} catch (EOFException eof) {
					System.out.println("End of File");
					break;
				}
			}
			file_input.close();
			data_in.close();

		} catch (IOException e) {
			System.out.println("IO Exception =: " + e);
		}
		file = null;
	}

	public static void fakezip(String fname) {

		File filei, fileo;

		filei = new File(fname);
		fileo = new File("fakezipped.txt");
		try {
			FileInputStream file_input = new FileInputStream(filei);
			DataInputStream data_in = new DataInputStream(file_input);
			PrintStream ps = new PrintStream(fileo);

			while (true) {
				try {
					bt = data_in.readByte();
					ps.print(ss[convert(bt)]);
				} catch (EOFException eof) {
					System.out.println("End of File");
					break;
				}
			}

			file_input.close();
			data_in.close();
			ps.close();

		} catch (IOException e) {
			System.out.println("IO Exception =: " + e);
		}
		filei = null;
		fileo = null;

	}

	public static void realzip(String fname, String fname1) {
		File filei, fileo;
		int i = 10;
		Byte btt;

		filei = new File(fname);
		fileo = new File(fname1);

		try {
			FileInputStream file_input = new FileInputStream(filei);
			DataInputStream data_in = new DataInputStream(file_input);
			FileOutputStream file_output = new FileOutputStream(fileo);
			DataOutputStream data_out = new DataOutputStream(file_output);

			data_out.writeInt(cnt);
			for (i = 0; i < 256; i++) {
				if (freq[i] != 0) {
					btt = (byte) i;
					data_out.write(btt);
					data_out.writeInt(freq[i]);
				}
			}
			long texbits;
			texbits = filei.length() % 8;
			texbits = (8 - texbits) % 8;
			exbits = (int) texbits;
			data_out.writeInt(exbits);
			while (true) {
				try {
					bt = 0;
					byte ch;
					for (exbits = 0; exbits < 8; exbits++) {
						ch = data_in.readByte();
						bt *= 2;
						if (ch == '1')
							bt++;
					}
					data_out.write(bt);

				} catch (EOFException eof) {
					int x;
					if (exbits != 0) {
						for (x = exbits; x < 8; x++) {
							bt *= 2;
						}
						data_out.write(bt);
					}

					exbits = (int) texbits;
					System.out.println("extrabits: " + exbits);
					System.out.println("End of File");
					break;
				}
			}
			data_in.close();
			data_out.close();
			file_input.close();
			file_output.close();
			System.out.println("output file's size: " + fileo.length());

		} catch (IOException e) {
			System.out.println("IO exception = " + e);
		}
		filei.delete();
		filei = null;
		fileo = null;
	}

	public static void beginHzipping(String arg1) {
		initHzipping();
		calFreq(arg1);
		makeNode();
		if (cnt > 1)
			dfs(root, "");
		fakezip(arg1);

		realzip("fakezipped.txt", arg1 + ".huffz");

		initHzipping();
	}
}
