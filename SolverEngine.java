public class SolverEngine {


	public int[][] initialArray = { 	{ 0, 8, 0,  6, 0, 0,  0, 1, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 4 }, 
										{ 9, 0, 0,  5, 0, 0,  8, 2, 0 },
										{ 0, 0, 4,  0, 3, 0,  0, 0, 2 },
										{ 8, 0, 3,  0, 0, 0,  1, 0, 7 },
										{ 2, 0, 0,  0, 9, 0,  6, 0, 0 },
										{ 0, 7, 8,  0, 0, 6,  0, 0, 3 },
										{ 4, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 1, 0,  0, 0, 8,  0, 6, 0 }
									};
	private int[][] initialArray0 = { 	{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 }, 
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
										{ 0, 0, 0,  0, 0, 0,  0, 0, 0 }
									};

	private int[][] initialArray_easy = { 	{ 3, 2, 0,  5, 0, 0,  0, 4, 0 },
											{ 4, 0, 1,  3, 2, 0,  8, 6, 0 }, 
											{ 0, 7, 0,  4, 0, 8,  0, 0, 5 },
											{ 0, 9, 0,  0, 0, 5,  4, 0, 6 },
											{ 0, 0, 0,  0, 0, 0,  0, 0, 0 },
											{ 8, 0, 2,  9, 0, 0,  0, 1, 0 },
											{ 7, 0, 0,  6, 0, 2,  0, 5, 0 },
											{ 0, 4, 6,  0, 5, 1,  2, 0, 8 },
											{ 0, 1, 0,  0, 0, 3,  0, 7, 9 }
										};

	public int[][] finalArray = new int[9][9];
	public boolean solved=false;
	
/*	public static void main(String[] args) {				
		// GUI.go;
		SolverEngine seng = new SolverEngine();
		//seng.setInitialArray();
		seng.bruteForceSolver();
		if (seng.solved) seng.printArray(); else System.out.println("No solution");
	}  */
	
	// private void setInitialArray() {
		// Take the Array from GUI  -->  initialArray[][]
	//}
	
	public void printArray() {
		System.out.println();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(finalArray[i][j]+" ");
				if (j==2 || j==5) System.out.print("  ");
			}
			System.out.println();
			if (i==2 || i==5) System.out.println();
		}		
	}
	
	public void bruteForceSolver() {
		int x=0, y=0, i=0, j=0;
		//int y=0;
		//int i=0;
		boolean forward = true;
		SudokuArray array = new SudokuArray();
		array.setSudokuArray(initialArray);
		
		while (y<9 && y>=0) {
			i++;			
			if (initialArray[x][y]==0) {
				array.sPanel[x][y]++;
				while (array.checkCell(x, y)) {
					j++;
					array.sPanel[x][y]++;
					System.out.println(x+" "+y+" "+array.sPanel[x][y]+"   "+i+"    "+j);
				}
				      // && array.sPanel[x][y]<10 : is not needed, since array.check becomes true --> while loop ends
			} 
			
			if (array.sPanel[x][y]==10) {
				array.sPanel[x][y]=0;
				forward=false;
			} else if (initialArray[x][y]==0) forward=true;
			
			if (forward) x++; else x--;
						
			if (x<0) {
				x=8;
				y--;
			}
			if (x==9) {
				x=0;
				y++;
			}
						
		} // close while (y<9 && y>=0) 
		
		if (y<0) solved=false; else { 
			solved=true;
			finalArray = array.sPanel;
		}
	} // close bruteForceSolver

} // close class
