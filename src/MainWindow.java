/*..........................................
 *       SEQUENCING PROBLEM SOLVER
 * .........................................
 * created by Akhil                         */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class MainWindow extends JFrame implements ActionListener{
	TwoMachine TW;
	JLabel lab = new JLabel("Two Machine Problem Solver");
	JLabel enter = new JLabel("Enter the number of jobs:");
	JTextField fld = new JTextField(15);
	JButton ok = new JButton("Ok");
	JLabel errHandler = new JLabel(" ");
	public MainWindow(){
		super("Sequencing Proble Solver");
		setVisible(true);
		setSize(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		ok.addActionListener(this);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		pane.add(lab);
		pane.add(enter);
		pane.add(fld);
		pane.add(ok);
	}
	public void actionPerformed(ActionEvent evt){
		try{
			Object src = evt.getSource();
			if(src == ok)
				TW = new TwoMachine(Integer.parseInt(fld.getText()));
		}catch(NumberFormatException e){
			errHandler.setText("Please enter valid data");
		}
	}
	public static void main(String args[]){
		MainWindow MW = new MainWindow();
	}

}
