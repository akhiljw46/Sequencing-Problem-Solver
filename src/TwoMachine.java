/*..........................................
 * TWO MACHINE SEQUENCING PROBLEM SOLVER
 * .........................................
 * created by Akhil                         */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class TwoMachine extends JFrame implements ActionListener{
	int jobs;
	JLabel lab1 = new JLabel("Enter the details below:");
	JLabel jobLab[];
	JLabel machineA = new JLabel("Machine A");
	JLabel machineB = new JLabel("Machine B");
	JTextField machineField[][];
	JTextField optimumField[];
	JTextField optimumField1[];
	JLabel errHandle = new JLabel(" ");
	JPanel rows[] = new JPanel[8];
	JPanel solTable[] = new JPanel[3];
	JPanel machineAPane = new JPanel();
	JPanel machineBPane = new JPanel();
	JPanel machineAInOut = new JPanel();
	JPanel machineBInOut = new JPanel();
	JPanel machineAIn = new JPanel();
	JPanel machineAOut = new JPanel();
	JPanel machineBIn = new JPanel();
	JPanel machineBOut = new JPanel();
	JTextField machineAInField[];
	JTextField machineAOutField[];
	JTextField machineBInField[];
	JTextField machineBOutField[];
	JLabel in = new JLabel("In");
	JLabel out= new JLabel("Out");
	JButton solve = new JButton("Solve");
	JTextField totalTime = new JTextField(5);
	JTextField idleTimeA = new JTextField(5);
	JTextField idleTimeB = new JTextField(5);
	int tableValues[][];
	int tempTableValues[][];
	int optimumSequence[];
	int machineAInValues[];
	int machineAOutValues[];
	int machineBInValues[];
	int machineBOutValues[];
	int totalTimeValue;
	int idleTimeAValue;
	int idleTimeBValue;
	public TwoMachine(int jobNo){
		super("Two Machine Problem Solver");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Container pane = getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		jobs = jobNo;
		machineField = new JTextField[2][jobNo];
		jobLab = new JLabel[jobNo];
		optimumField = new JTextField[jobNo];
		optimumField1 = new JTextField[jobNo];
		machineAInField = new JTextField[jobNo];
		machineAOutField = new JTextField[jobNo];
		machineBInField = new JTextField[jobNo];
		machineBOutField = new JTextField[jobNo];
		solve.addActionListener(this);
		for(int i = 0; i < 8; i++)
			rows[i] = new JPanel();
		rows[0].add(lab1);
		rows[1].setLayout(new GridLayout(3, jobNo + 1, 5, 5));
		rows[1].add(new JLabel());
		for(int i = 0; i < jobNo; i++){
			jobLab[i] = new JLabel("Job" + Integer.toString(i + 1));
			rows[1].add(jobLab[i]);
		}
		rows[1].add(machineA);
		for(int i = 0; i < 2; i++){
			if(i == 1)
				rows[1].add(machineB);
			for(int j = 0; j < jobNo; j++){
				machineField[i][j] = new JTextField(5);
				rows[1].add(machineField[i][j]);
			}
		}
		rows[2].setLayout(new BoxLayout(rows[2], BoxLayout.Y_AXIS));
		rows[2].add(solve);
		rows[2].add(errHandle);
		rows[3].setLayout(new BoxLayout(rows[3], BoxLayout.X_AXIS));
		rows[3].add(new JLabel("Optimum sequence is:"));
		for(int i = 0; i < jobNo; i++){
			optimumField[i] = new JTextField(5);
			rows[3].add(optimumField[i]);
		}
		rows[4].setLayout(new GridLayout(1, 3));
		for(int i = 0; i < 3; i++)
			solTable[i] = new JPanel();
		solTable[0].setLayout(new GridLayout(jobNo + 2, 1, 0, 0));
		solTable[0].add(new JLabel(""));
		solTable[0].add(new JLabel("Job"));
		for(int i = 0; i < jobNo; i++){
			optimumField1[i] = new JTextField(5);
			solTable[0].add(optimumField1[i]);
		}
		GridLayout inOutArrange = new GridLayout(1, 2);
		GridLayout inOut = new GridLayout(jobNo + 1, 1);
		solTable[1].setLayout(new BoxLayout(solTable[1], BoxLayout.Y_AXIS));
		machineAPane.setLayout(new GridLayout(1,1));
		machineAPane.add(new JLabel("Machine A"));
		solTable[1].add(machineAPane);
		machineAInOut.setLayout(inOutArrange);
		machineAIn.setLayout(inOut);
		machineAIn.add(in);
		for(int i = 0; i < jobNo; i++){
			machineAInField[i] = new JTextField(5);
			machineAIn.add(machineAInField[i]);
		}
		machineAOut.setLayout(inOut);
		machineAOut.add(out);
		for(int i = 0; i < jobNo; i++){
			machineAOutField[i] = new JTextField(5);
			machineAOut.add(machineAOutField[i]);
		}
		machineAInOut.add(machineAIn);
		machineAInOut.add(machineAOut);
		solTable[1].add(machineAInOut);
		solTable[2].setLayout(new BoxLayout(solTable[2], BoxLayout.Y_AXIS));
		machineBPane.setLayout(new GridLayout(1,1));
		machineBPane.add(new JLabel("Machine B"));
		solTable[2].add(machineBPane);
		machineBInOut.setLayout(inOutArrange);
		machineBIn.setLayout(inOut);
		machineBIn.add(new JLabel("In"));
		for(int i = 0; i < jobNo; i++){
			machineBInField[i] = new JTextField(5);
			machineBIn.add(machineBInField[i]);
		}
		machineBOut.setLayout(inOut);
		machineBOut.add(new JLabel("Out"));
		for(int i = 0; i < jobNo; i++){
			machineBOutField[i] = new JTextField(5);
			machineBOut.add(machineBOutField[i]);
		}
		machineBInOut.add(machineBIn);
		machineBInOut.add(machineBOut);
		solTable[2].add(machineBInOut);
		for(int i = 0; i < 3; i++)
			rows[4].add(solTable[i]);
		rows[5].add(new JLabel("Total elapsed time ="));
		rows[5].add(totalTime);
		rows[6].add(new JLabel("Idle time for Machine A ="));
		rows[6].add(idleTimeA);
		rows[7].add(new JLabel("Idel time for Machine B ="));
		rows[7].add(idleTimeB);
		for(int i = 0; i < 8; i++)
			pane.add(rows[i]);
		 pack();
		 setResizable(false);
	}
	public void solver(){
		sequenceFinder();
		tableBuilder();
	}
	public void sequenceFinder(){
		tableValues = new int[2][jobs];
		tempTableValues = new int[2][jobs];
		int found[][] = new int[2 * jobs][2];
		optimumSequence = new int[jobs];
		int min, max, k,jobValue, left = 0, right = jobs - 1;
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < jobs; j++){
				tableValues[i][j] = Integer.parseInt(machineField[i][j].getText());
				tempTableValues[i][j] = Integer.parseInt(machineField[i][j].getText());
			}
		}
		for(int f = 0; f < jobs; f++){
			k = 0;
			min = minimumFinder(tempTableValues);
			max = maximumFinder(tempTableValues) + 1;
			for(int i = 0; i < 2; i++){
				for(int j = 0; j < jobs; j++){
					if(tempTableValues[i][j] == min){
						found[k][0] = i;
						found[k][1] = j;
						k++;
					}
				}
			}			
			for(int i = 0; i < jobs; i++){
				if(found[i][0] == 0){					
					jobValue = found[i][1];
					optimumSequence[left] = jobValue;
					tempTableValues[0][jobValue] = max;
					tempTableValues[1][jobValue] = max;
					optimumField[left].setText(Integer.toString(jobValue + 1));
					optimumField1[left].setText(Integer.toString(jobValue + 1));
					left++;
					break;
				}else if(found[i][0] == 1){
					jobValue = found[i][1];
					optimumSequence[right] = jobValue;
					tempTableValues[0][jobValue] = max;
					tempTableValues[1][jobValue] = max;
					optimumField[right].setText(Integer.toString(jobValue + 1));
					optimumField1[right].setText(Integer.toString(jobValue + 1));
					right--;
					break;
				}
			}
		}		
	}
	public void tableBuilder(){
		machineAInValues = new int[jobs];
		machineAOutValues = new int[jobs];
		machineBInValues = new int[jobs];
		machineBOutValues = new int[jobs];
		machineAInValues[0] = 0;
		machineAOutValues[0] = tableValues[0][optimumSequence[0]];
		machineAInField[0].setText("0");
		machineAOutField[0].setText(Integer.toString(machineAOutValues[0]));
		for(int i = 1; i < jobs; i++){
			machineAInValues[i] = machineAOutValues[i - 1];
			machineAOutValues[i] = machineAInValues[i] + tableValues[0][optimumSequence[i]];
			machineAInField[i].setText(Integer.toString(machineAInValues[i]));
			machineAOutField[i].setText(Integer.toString(machineAOutValues[i]));
		}
		machineBInValues[0] = machineAOutValues[0];
		machineBOutValues[0] = machineBInValues[0] + tableValues[1][optimumSequence[0]];
		machineBInField[0].setText(Integer.toString(machineBInValues[0]));
		machineBOutField[0].setText(Integer.toString(machineBOutValues[0]));
		for(int i = 1; i < jobs; i++){
			if(machineBOutValues[i  - 1] > machineAOutValues[i]){
				machineBInValues[i] = machineBOutValues[i - 1];
			}else{
				machineBInValues[i] = machineAOutValues[i];
			}
		machineBOutValues[i] = machineBInValues[i] + tableValues[1][optimumSequence[i]];
		machineBInField[i].setText(Integer.toString(machineBInValues[i]));
		machineBOutField[i].setText(Integer.toString(machineBOutValues[i]));
		}
		totalTimeValue = machineBOutValues[jobs - 1];
		totalTime.setText(Integer.toString(totalTimeValue));
		idleTimeAValue = machineBOutValues[jobs - 1] - machineAOutValues[jobs - 1];
		idleTimeA.setText(Integer.toString(idleTimeAValue));
		idleTimeBValue = machineBInValues[0];
		for(int i = 1; i < jobs; i++)
			idleTimeBValue += machineBInValues[i] - machineBOutValues[i - 1];
		idleTimeB.setText(Integer.toString(idleTimeBValue));
	}
	public int minimumFinder(int givenMatrix[][]){
		int min;
		min = givenMatrix[0][0];
		for(int i = 0; i < givenMatrix.length; i++){
			for(int j = 0; j < givenMatrix[i].length; j++){
				min = Math.min(min, givenMatrix[i][j]);
			}
		}
		return min;
	}
	public int maximumFinder(int givenMatrix[][]){
		int max;
		max = givenMatrix[0][0];
		for(int i = 0; i < givenMatrix.length; i++){
			for(int j = 0; j < givenMatrix[i].length; j++){
				max = Math.max(max, givenMatrix[i][j]);
			}
		}
		return max;
	}
	public void actionPerformed(ActionEvent evt){
		try{
			Object src = evt.getSource();
			if(src == solve)
				solver();
		}catch(NumberFormatException e){
			errHandle.setText("Please enter valid data");
		}
	}	
}
