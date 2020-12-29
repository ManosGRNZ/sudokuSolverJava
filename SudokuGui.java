import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class SudokuGui {
	
	private int[][] initialArray_empty = { 	{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
											{ 0, 0, 0,  0, 0, 0,  0, 0, 0 }, 
											{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
											{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
											{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
											{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
											{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
											{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
											{ 0, 0, 0,  0, 0, 0,  0, 0, 0 }
										};
	
	private int[][] initialArray = { 	{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 }, 
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 }
									};
	
	public int[][] arrayToSolve;
	
	private JFrame frame;
	private JPanel buttonPanel;
	// private JScrollPane scroller;
	private JTable sudokuTable;
	private JButton clearButton;
	private JButton resetButton;
	private JButton solveButton;
	
	public static void main (String[] args) {
		SudokuGui sudokuApp = new SudokuGui();
		sudokuApp.setupGui();
	} 
	
	public void setupGui() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buttonPanel = new JPanel();
		// scroller = new JScrollPane();
		sudokuTable = new JTable();
		clearButton = new JButton("Clear");
		resetButton = new JButton("Reset");
		solveButton = new JButton("Solve");
		
		clearButton.addActionListener(new ClearListener());
		resetButton.addActionListener(new ResetListener());
		solveButton.addActionListener(new SolveListener());
		
		sudokuTable = new JTable(9,9);
		sudokuTable.setRowHeight(30);
		sudokuTable.setAlignmentX(JTable.CENTER_ALIGNMENT);
		sudokuTable.setAlignmentY(JTable.CENTER_ALIGNMENT);
								
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.add(clearButton);
		buttonPanel.add(resetButton);
		buttonPanel.add(solveButton);
		frame.getContentPane().add(BorderLayout.CENTER, sudokuTable);
		frame.getContentPane().add(BorderLayout.EAST, buttonPanel);
		frame.setSize(400, 300);
		frame.setVisible(true);
		
		// sudokuTable.setValueAt(value, y, z);
		
	}
	
	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			for	(int x=0; x<9 ; x++) {
				for (int y=0; y<9; y++) sudokuTable.setValueAt("", y, x);
			}			
		}
	}
	
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			int val=0;
			for	(int x=0; x<9 ; x++) {
				for (int y=0; y<9; y++) {
					val=initialArray[y][x];
					if (val==0) sudokuTable.setValueAt("", y, x);
					else sudokuTable.setValueAt(val, y, x);
				}
			}
		}
	}
	
	class SolveListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			SolverEngine seng = new SolverEngine();
			//seng.setInitialArray();			
			for (int x=0; x<9; x++) {
				for (int y=0; y<9; y++) {
					if ((sudokuTable.getValueAt(x, y))==null) initialArray[x][y]=0; 
					else {
						try {
							initialArray[x][y]= Integer.parseInt((sudokuTable.getValueAt(x, y)).toString());
						} catch(Exception ex) {
							sudokuTable.setValueAt(null, x, y);
							initialArray[x][y]=0;
						}
					} // end if else
					if (seng.initialArray[x][y]<0 || seng.initialArray[x][y]>9) seng.initialArray[x][y]=0;
				}
			}
			seng.initialArray=initialArray;
			seng.bruteForceSolver();
			if (seng.solved) {
				seng.printArray();
				int val=0;
				for	(int x=0; x<9 ; x++) {
					for (int y=0; y<9; y++) {
						val=seng.finalArray[x][y];
						sudokuTable.setValueAt(val, x, y);						
					}
				}
			} else System.out.println("No solution");
		}
	}
		

}
