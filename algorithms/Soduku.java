package favourites;

public class Soduku {
	
	public static boolean isSafe(int[][]board,int row,int col,int num) {
	
		//row clash
		for(int i=0;i<board.length;i++) {
		      if(board[row][i]==num) {
		    	  return false;
		      }
		}
		
		//col clash
		for(int i=0;i<board.length;i++) {
		      if(board[i][col]==num) {
		    	 return false;
		      }
	      }
		
		//box clash
		
		int sqrt=(int) Math.sqrt(board.length);
	     int boxRowStart=row-row%sqrt;
	     int boxColStart=col-col%sqrt;
	     
	     for(int i=boxRowStart;i<boxRowStart+sqrt;i++) {
	    	 for(int j=boxColStart;j<boxColStart+sqrt;j++) {
	    		 if(board[i][j]==num) {
	    			 return false;
	    		 }
	    	 }
	     }
	     
		 return true;     
		
	}
	
	public static boolean solveSoduku(int[][]board,int n) {
		
		boolean isEmpty=true;
		int row=-1;
		int col=-1;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(board[i][j]==0) {
					row=i;
					col=j;
					isEmpty=false;
					break;
				}
			}
			if(!isEmpty) {
				break;
			}
		}
		//no empty space left
		if(isEmpty) {
			return true;
		}
		for(int num=1;num<=n;num++) {
			if(isSafe(board,row,col,num)) {
				board[row][col]=num;
			
			if(solveSoduku(board,n)) {
				return true;
			}
			else {
				board[row][col]=0;
			}
		}
		}
		return false;
		
	}
	
	public static void print(int[][] board,int n) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(board[i][j]+" ");
			}
			
			System.out.print("\n");
			if((i+1)%(int)Math.sqrt(n)==0) {
				System.out.print("");
			}
		}
	}

	public static void main(String[] args) {
		int [][] board= new int [][]{
			{ 3, 0, 6, 5, 0, 8, 4, 0, 0 },
			{ 5, 2, 5, 0, 0, 0, 0, 0, 0 },
			{ 0, 8, 7, 0, 0, 0, 0, 3, 1 },
			{ 0, 0, 3, 0, 1, 0, 0, 8, 0 },
			{ 9, 0, 0, 8, 6, 3, 0, 0, 5 },
			{ 0, 5, 0, 0, 9, 0, 6, 0, 0 },
			{ 1, 3, 0, 0, 0, 0, 2, 5, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 7, 4 },
			{ 0, 0, 5, 2, 0, 6, 3, 0, 0 }
				
		};
		
		int n =board.length;
		
		if(solveSoduku(board,n)) {
			print(board,n);
		}else {
			System.out.println("No solution");
		}

	}

}
