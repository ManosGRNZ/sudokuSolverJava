public class SudokuArray {
	
	public int[][] sPanel = new int[9][9]; 
	
	public void setSudokuArray(int[][] inputArray) {  		
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				sPanel[x][y]=inputArray[x][y];				
			}
		}
	}	
	
	public boolean checkCell(int x0, int y0) {
		boolean check = false; // it means that the value at (x,y) is OK
		for (int i = 0; i < 9; i++) {
			if (i!=x0 && sPanel[x0][y0]==sPanel[i][y0]) check=true;
			if (i!=y0 && sPanel[x0][y0]==sPanel[x0][i]) check=true;
		} // end loop (rows & columns)
		int x1 = x0-(x0 % 3);
		int y1 = y0-(y0 % 3);		
		for (int xi = x1; xi < x1+3; xi++) {
			for (int yi = y1; yi < y1+3; yi++) {
				if ((xi!=x0 || yi!=y0) && sPanel[x0][y0]==sPanel[xi][yi]) check=true;
			}			
		} // end two for loops
		if (sPanel[x0][y0]==0) check=true;
		return check;
	} // close checkCell method
	
	public boolean fullCheck() {
		boolean check = false;
		int x=0;
		int y =0;
		while (!check && y<9) {
			check = checkCell(x++,y);			
			if (x==9) {
				x=0;
				y++;				
			} // close if
		} // close while
		return check;
	} // close fullCheck method
} // close class