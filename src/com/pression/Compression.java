package com.pression;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Compression extends JFrame implements ActionListener {

	static public File opened_file, other_file;
	static long past, future;
	static JLabel tenFileLabel, sizeFileBFLabel,sizeFileAFLabel,hieuSuatLable, nameScore,  sizeFileBFScore, sizeFileAFScore, hieuSuatScore;
	static JPanel buttonPanel, titlePanel, scorePanel;
	static JButton ZH, UH, ZL, UL, ZR, UR, EX;

	public JPanel createContentPane() {

		JPanel totalGUI = new JPanel();

		totalGUI.setLayout(null);

		titlePanel = new JPanel();
		titlePanel.setLayout(null);
		titlePanel.setLocation(80, 40);
		titlePanel.setSize(250, 100);
		titlePanel.setBackground(Color.WHITE);
		totalGUI.add(titlePanel);
		totalGUI.setBackground(Color.WHITE);
		
		tenFileLabel = new JLabel("Tên file :");
		tenFileLabel.setForeground(Color.BLUE);
		tenFileLabel.setLocation(125,0);
		tenFileLabel.setSize(150,30);
		tenFileLabel.setHorizontalAlignment(0);
		titlePanel.add(tenFileLabel);
		
		sizeFileBFLabel = new JLabel("Kích cỡ file : ");
		sizeFileBFLabel.setForeground(Color.blue);
		sizeFileBFLabel.setLocation(115, 25);
		sizeFileBFLabel.setSize(150, 30);
		sizeFileBFLabel.setHorizontalAlignment(0);// thiết lập căn chỉnh ngang cho text
		titlePanel.add(sizeFileBFLabel);

		sizeFileAFLabel = new JLabel(" File sau nén/giải nén :");
		sizeFileAFLabel.setForeground(Color.blue);
		sizeFileAFLabel.setLocation(76, 50);
		sizeFileAFLabel.setSize(170, 30);
		sizeFileAFLabel.setHorizontalAlignment(0);
		titlePanel.add(sizeFileAFLabel);
		
		hieuSuatLable = new JLabel(" Hiệu suất nén :");
		hieuSuatLable.setForeground(Color.blue);
		hieuSuatLable.setLocation(95, 75);
		hieuSuatLable.setSize(170, 30);
		hieuSuatLable.setHorizontalAlignment(0);
		titlePanel.add(hieuSuatLable);

		scorePanel = new JPanel();
		scorePanel.setLayout(null);
		scorePanel.setLocation(305, 40);
		scorePanel.setSize(200,100);
		scorePanel.setBackground(Color.WHITE);
		totalGUI.add(scorePanel);

		nameScore = new JLabel("");
		nameScore.setLocation(0, 0);
		nameScore.setSize(100, 30);
		nameScore.setHorizontalAlignment(0);
		scorePanel.add(nameScore);

		sizeFileBFScore = new JLabel("");
		sizeFileBFScore.setLocation(0, 25);
		sizeFileBFScore.setSize(100, 30);
		sizeFileBFScore.setHorizontalAlignment(0);
		scorePanel.add(sizeFileBFScore);
		
		sizeFileAFScore = new JLabel("");
		sizeFileAFScore.setLocation(0, 50);
		sizeFileAFScore.setSize(100, 30);
		sizeFileAFScore.setHorizontalAlignment(0);
		scorePanel.add(sizeFileAFScore);

		hieuSuatScore = new JLabel("");
		hieuSuatScore.setLocation(0, 75);
		hieuSuatScore.setSize(100, 30);
		hieuSuatScore.setHorizontalAlignment(0);
		scorePanel.add(hieuSuatScore);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		buttonPanel.setLocation(10, 130);
		buttonPanel.setSize(5200, 300);
		buttonPanel.setBackground(Color.white);
		totalGUI.add(buttonPanel);

		ZH = new JButton("Nén HM");
		ZH.setToolTipText("Nén Huffman");
		ZH.setBackground(Color.GREEN);
		ZH.setForeground(Color.black);
		ZH.setLocation(60, 25);
		ZH.setSize(120, 30);
		ZH.addActionListener(this);
		buttonPanel.add(ZH);

		UH = new JButton("Giải nén HM");
		UH.setToolTipText("Giải nén Huffman");
		UH.setLocation(60, 75);
		UH.setSize(120, 30);
		UH.setBackground(Color.GREEN);
		UH.setForeground(Color.black);
		UH.addActionListener(this);
		buttonPanel.add(UH);

		ZL = new JButton("Nén LZW");
		ZL.setToolTipText("Nén Lampel-Ziv-Welch");
		ZL.setLocation(260, 25);
		ZL.setSize(120, 30);
		ZL.setBackground(Color.GREEN);
		ZL.setForeground(Color.black);
		ZL.addActionListener(this);
		buttonPanel.add(ZL);

		UL = new JButton("Giải nén LZW");
		UL.setToolTipText("Giải nén Lampel-Ziv-Welch");
		UL.setLocation(260, 75);
		UL.setSize(120, 30);
		UL.setBackground(Color.GREEN);
		UL.setForeground(Color.black);
		UL.addActionListener(this);
		buttonPanel.add(UL);

		ZR = new JButton("Nén RLC");
		ZR.setToolTipText("Nén Run-Length-Coding");
		ZR.setLocation(460, 25);
		ZR.setSize(120, 30);
		ZR.setBackground(Color.GREEN);
		ZR.setForeground(Color.black);
		ZR.addActionListener(this);
		buttonPanel.add(ZR);

		UR = new JButton("Giải nén RLC");
		UR.setToolTipText("Giải nén Run-Length-Coding");
		UR.setLocation(460, 75);
		UR.setSize(120, 30);
		UR.setBackground(Color.GREEN);
		UR.setForeground(Color.BLACK);
		UR.addActionListener(this);
		buttonPanel.add(UR);

		EX = new JButton("THOÁT");
		EX.setLocation(260, 150);
		EX.setSize(120, 30);
		EX.setBackground(Color.green);
		EX.setForeground(Color.black);
		EX.addActionListener(this);
		buttonPanel.add(EX);

		totalGUI.setOpaque(true);
		return totalGUI;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ZH) {
			Hz.beginHzipping(opened_file.getPath());
			JOptionPane.showMessageDialog(null, "File của bạn đã được nén xong", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			sizeFileBFScore.setText(opened_file.length() + "Bytes");
			other_file = new File(opened_file.getPath() + ".huffz");
			future = other_file.length();
			sizeFileAFScore.setText(future + "Bytes");
			float a = opened_file.length();
			float b = future;
			float c = 0;
			c =(1-(b/a))*100;
			hieuSuatScore.setText(c + "%");
		} else if (e.getSource() == UH) {
			Huz.beginHunzipping(opened_file.getPath());
			JOptionPane.showMessageDialog(null, "File của bạn đã được giải nén xong", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			sizeFileBFScore.setText(opened_file.length() + "Bytes");
			String s = opened_file.getPath();
			s = s.substring(0, s.length() - 6);
			other_file = new File(s);
			future = other_file.length();
			sizeFileAFScore.setText(future + "Bytes");
		} else if (e.getSource() == ZL) {
			Lz.beginLzipping(opened_file.getPath());
			JOptionPane.showMessageDialog(null, "File của bạn đã được nén xong", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			sizeFileBFScore.setText(opened_file.length() + "Bytes");
			other_file = new File(opened_file.getPath() + ".LmZWp");
			future = other_file.length();
			sizeFileAFScore.setText(future + "Bytes");
			float a = opened_file.length();
			float b = future;
			float c = 0;
			c =(1-(b/a))*100;
			hieuSuatScore.setText(c + "%");
		} else if (e.getSource() == UL) {
			Luz.beginLunzipping(opened_file.getPath());
			JOptionPane.showMessageDialog(null, "File của bạn đã được giải nén xong", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			sizeFileBFScore.setText(opened_file.length() + "Bytes");
			String s = opened_file.getPath();
			s = s.substring(0, s.length() - 6);
			other_file = new File(s);
			future = other_file.length();
			sizeFileAFScore.setText(future + "Bytes");
		} else if (e.getSource() == ZR) {
			Rz.beginRzipping(opened_file.getPath());
			JOptionPane.showMessageDialog(null, "File của bạn đã được nén xong", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			sizeFileBFScore.setText(opened_file.length() + "Bytes");
			other_file = new File(opened_file.getPath() + ".RLCz");
			future = other_file.length();
			sizeFileAFScore.setText(future + "Bytes");
			float a = opened_file.length();
			float b = future;
			float c = 0;
			c =(1-(b/a))*100;
			hieuSuatScore.setText(c + "%");
		} else if (e.getSource() == UR) {
			Ruz.beginURzipping(opened_file.getPath());
			JOptionPane.showMessageDialog(null, "File của bạn đã giải nén xong", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			sizeFileBFScore.setText(opened_file.length() + "Bytes");
			other_file = new File(opened_file.getPath() + ".txt");
			future = other_file.length();
			sizeFileAFScore.setText(future + "Bytes");
		} else if (e.getSource() == EX) {
			System.exit(0);
		}
	}

	private static void createAndShowGUI() {

		JFrame frame = new JFrame("PHẦN MỀM NÉN TỆP TIN");

		Compression demo = new Compression();
		frame.setContentPane(demo.createContentPane());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(350, 170, 680, 420);
		frame.setResizable(false);
		frame.setVisible(true);

		JMenu fileMenu = new JMenu("File");
		fileMenu.setForeground(Color.white);

		JMenuBar bar = new JMenuBar();
		bar.setBackground(Color.decode("#5882FA"));
		frame.setJMenuBar(bar);
		bar.add(fileMenu);

		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		openItem.addActionListener(

				new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
						fileChooser.showOpenDialog(null);
						opened_file = fileChooser.getSelectedFile();
						nameScore.setText(opened_file.getName());
						past = opened_file.length();
						sizeFileBFScore.setText(past + "Bytes");
						sizeFileAFScore.setText(".....");
						hieuSuatScore.setText(".....");
					}
				});

		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		exitItem.addActionListener(

				new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						System.exit(0);
					}
				});

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setForeground(Color.white);
		frame.setJMenuBar(bar);
		bar.add(helpMenu);

		JMenuItem helpItem = new JMenuItem("Hướng dẫn");
		helpMenu.add(helpItem);
		helpItem.addActionListener(

				new ActionListener() {

					public void actionPerformed(ActionEvent event) {
						JOptionPane.showMessageDialog(null,
								"Để nén tập tin" + "\n" + "B1:Chọn tệp cần nén." + "\n"
										+ "B2:Chọn Nén HM hoặc Nén LZW hoặc Nén RLC để nén" + "\n" + "Để giải nén tập tin" + "\n"
										+ "B1:Chọn tệp cần giải nén." + "\n" + "B2:Chon Giải nén HM hoặc Giải nén LZW hoặc Giải nén RLC"
										+ "\n" + "Chú ý:\n" + "-Tệp có phần mở rộng .huffz và .LmZWp sẽ được" + "\n"
										+ "tạo trong cùng một thư mục đó là tệp nén." + "\n"
										+ "-Trong khi giải nén, bạn phải sử dụng." + "\n"
										+ "cùng một thuật toán mà bạn đã dùng để nén.",
								"Thông báo", JOptionPane.PLAIN_MESSAGE);
					}
				});

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
