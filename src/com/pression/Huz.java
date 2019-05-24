package com.pression;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.PriorityQueue;

public class Huz{
	static PriorityQueue<Tree> pq1 = new PriorityQueue<Tree>();
	static int[] freq1 = new int[300];
	static String[] ss1 = new String[300];
	static String[] btost = new String[300];
	static String bigone;
	static String temp;
	static int exbits1;

	static int putit;
	static int cntu;

	static class Tree implements Comparable<Tree> {
		Tree Lchild;
		Tree Rchild;
		public String deb;
		public int Bite;
		public int freq1nc;

		public int compareTo(Tree t) {
			if (this.freq1nc < t.freq1nc)
				return -1;
			if (this.freq1nc > t.freq1nc)
				return 1;
			return 0;
		}
	}

	static Tree root;

	public static void initHunzipping() {
		int i;
		if (root != null)
			fredfs1(root);
		for (i = 0; i < 300; i++)
			freq1[i] = 0;
		for (i = 0; i < 300; i++)
			ss1[i] = "";
		pq1.clear();
		bigone = "";
		temp = "";
		exbits1 = 0;

		putit = 0;
		cntu = 0;
	}

	public static void fredfs1(Tree now) {

		if (now.Lchild == null && now.Rchild == null) {
			now = null;
			return;
		}
		if (now.Lchild != null)
			fredfs1(now.Lchild);
		if (now.Rchild != null)
			fredfs1(now.Rchild);
	}

	public static void dfs1(Tree now, String st) {
		now.deb = st;
		if ((now.Lchild == null) && (now.Rchild == null)) {
			ss1[now.Bite] = st;
			return;
		}
		if (now.Lchild != null)
			dfs1(now.Lchild, st + "0");
		if (now.Rchild != null)
			dfs1(now.Rchild, st + "1");
	}

	public static void MakeNode1() {
		int i;
		cntu = 0;
		for (i = 0; i < 300; i++) {
			if (freq1[i] != 0) {

				Tree Temp = new Tree();
				Temp.Bite = i;
				Temp.freq1nc = freq1[i];
				Temp.Lchild = null;
				Temp.Rchild = null;
				pq1.add(Temp);
				cntu++;
			}

		}
		Tree Temp1, Temp2;

		if (cntu == 0) {
			return;
		} else if (cntu == 1) {
			for (i = 0; i < 300; i++)
				if (freq1[i] != 0) {
					ss1[i] = "0";
					break;
				}
			return;
		}

		while (pq1.size() != 1) {
			Tree Temp = new Tree();
			Temp1 = pq1.poll();
			Temp2 = pq1.poll();
			Temp.Lchild = Temp1;
			Temp.Rchild = Temp2;
			Temp.freq1nc = Temp1.freq1nc + Temp2.freq1nc;
			pq1.add(Temp);
		}
		root = pq1.poll();
	}

	public static void readfreq1(String cc) {

		File filei = new File(cc);
		int fey, i;
		Byte baital;
		try {
			FileInputStream file_input = new FileInputStream(filei);
			DataInputStream data_in = new DataInputStream(file_input);
			cntu = data_in.readInt();

			for (i = 0; i < cntu; i++) {
				baital = data_in.readByte();
				fey = data_in.readInt();
				freq1[to(baital)] = fey;
			}
			data_in.close();
			file_input.close();
		} catch (IOException e) {
			System.out.println("IO exception = " + e);
		}

		MakeNode1();
		if (cntu > 1)
			dfs1(root, "");

		for (i = 0; i < 256; i++) {
			if (ss1[i] == null)
				ss1[i] = "";
		}
		filei = null;
	}

	public static void createbin() {
		int i, j;
		String t;
		for (i = 0; i < 256; i++) {
			btost[i] = "";
			j = i;
			while (j != 0) {
				if (j % 2 == 1)
					btost[i] += "1";
				else
					btost[i] += "0";
				j /= 2;
			}
			t = "";
			for (j = btost[i].length() - 1; j >= 0; j--) {
				t += btost[i].charAt(j);
			}
			btost[i] = t;

		}
		btost[0] = "0";
	}

	public static int got() {
		int i;

		for (i = 0; i < 256; i++) {
			if (ss1[i].compareTo(temp) == 0) {
				putit = i;
				return 1;
			}
		}
		return 0;

	}

	public static int to(Byte b) {
		int ret = b;
		if (ret < 0) {
			ret = ~b;
			ret = ret + 1;
			ret = ret ^ 255;
			ret += 1;
		}
		return ret;
	}

	public static String makeeight(String b) {
		String ret = "";
		int i;
		int len = b.length();
		for (i = 0; i < (8 - len); i++)
			ret += "0";
		ret += b;
		return ret;
	}

	public static void readbin(String zip, String unz) {
		File f1 = null, f2 = null;
		int ok, bt;
		Byte b;
		int j, i;
		bigone = "";
		f1 = new File(zip);
		f2 = new File(unz);
		try {
			FileOutputStream file_output = new FileOutputStream(f2);
			DataOutputStream data_out = new DataOutputStream(file_output);
			FileInputStream file_input = new FileInputStream(f1);
			DataInputStream data_in = new DataInputStream(file_input);
			try {
				cntu = data_in.readInt();
				System.out.println(cntu);
				for (i = 0; i < cntu; i++) {
					b = data_in.readByte();
					j = data_in.readInt();

					
				}
				exbits1 = data_in.readInt();
				System.out.println(exbits1);

			} catch (EOFException eof) {
				System.out.println("End of File");
			}

			while (true) {
				try {
					b = data_in.readByte();
					bt = to(b);
					bigone += makeeight(btost[bt]);

					while (true) {
						ok = 1;
						temp = "";
						for (i = 0; i < bigone.length() - exbits1; i++) {
							temp += bigone.charAt(i);
							
							if (got() == 1) {
								data_out.write(putit);
								ok = 0;
								String s = "";
								for (j = temp.length(); j < bigone.length(); j++) {
									s += bigone.charAt(j);
								}
								bigone = s;
								break;
							}
						}

						if (ok == 1)
							break;
					}
				} catch (EOFException eof) {
					System.out.println("End of File");
					break;
				}
			}
			file_output.close();
			data_out.close();
			file_input.close();
			data_in.close();
		} catch (IOException e) {
			System.out.println("IO Exception =: " + e);
		}

		f1 = null;
		f2 = null;
	}

	public static void beginHunzipping(String arg1) {
		initHunzipping();
		readfreq1(arg1);
		createbin();
		int n = arg1.length();
		String arg2 = arg1.substring(0, n - 6);
		readbin(arg1, arg2);
		initHunzipping();
	}

}
