package dot.underscore;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Skin extends JFrame implements ActionListener {

	private JTextArea area;

	public static void main(String[] args) {

		JFrame skin = new Skin();

	}

	private Skin() {

		JButton button = new JButton("select directory");
		button.addActionListener(this);

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(button);
		this.getContentPane().add(buttonPanel, BorderLayout.PAGE_START);

		
		area = new JTextArea(5, 80);
		JScrollPane scroll = new JScrollPane();
	    scroll.setViewportView(area);
		JPanel labelPanel = new JPanel();
		labelPanel.add(scroll);

		this.getContentPane().add(labelPanel, BorderLayout.PAGE_END);

		this.setTitle("dot underscore remover");
		this.setBounds(100, 500, 1000, 160);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int selected = fileChooser.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			if (!file.exists() || !file.isDirectory()) {
				return;
			}
			Remover remover = new SkinRemover();
			remover.delAllDotUnderscores(file);
		}
	}
	
	private class SkinRemover extends Remover {
		protected void log(String text) {
			area.append(text);
			area.append("\n");
		}
	}

}
