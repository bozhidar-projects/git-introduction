package com.spaghettisoft.component.connectthefour;

public class Grid {

	private String[][] grid;
	private int symbolRow = 0;

	public Grid() {
		String[][] offset = new String[6][7];
		this.grid = offset;
	}

	public void dropCoin(int n, String ch) {
		boolean flag = false;
		int i = grid.length - 1;
		while(flag == false) {
			if (grid[i][n].contains("X") || grid[i][n].contains("O")){
				i--;
			} else {
				grid[i][n] = ch;
				flag = true;
			}
		}
	}

	public boolean fourCheck(int currentI, int currentJ, String ch) {

		if (grid[currentI][currentJ].equals(ch)) {
			symbolRow++;
			if (symbolRow == 4) {
				return true;
			}
		} else {
			symbolRow = 0;
		}
		return false;
	}

	public boolean checkDiagonals(String ch) {
		int currentI = 0;
		int currentJ = 0;
		int n = 3;
		for (int k = 0; k < 7; k++) {
			symbolRow = 0;
			if (k < 3) {
				currentI = 2 - k;
				currentJ = currentI - 2 + k;
				n++;
			}
			if (k >= 3) {
				currentI = 0;
				currentJ = k - 2;
				if (k > 3) {
					n--;
				}
			}
			for (int i = 0; i < n; i++) {
				boolean check = fourCheck(currentI + i, currentJ + i, ch);
				if (check) {
					return true;
				}
			}
		}
		currentI = 0;
		currentJ = 0;
		n = 3;

		for (int k = 0; k < 7; k++) {
			symbolRow = 0;
			if (k < 4) {
				currentI = 0;
				currentJ = k + 3;
				if (k != 3) {
					n++;
				}
			}
			if (k >= 4) {
				currentI = k - 3;
				currentJ = 6;
				n--;
			}
			for (int i = 0; i < n; i++) {
				boolean check = fourCheck(currentI + i, currentJ - i, ch);
				if (check) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkRowsAndColumns(String ch) {
		for (int i = 0; i < grid.length; i++) {
			symbolRow = 0;
			for (int j = 0; j < grid[0].length; j++) {
				boolean check = fourCheck(i, j, ch);
				if (check) {
					return true;
				}
			}
		}
		symbolRow = 0;
		for (int j = 0; j < grid[0].length; j++) {
			symbolRow = 0;
			for (int i = 0; i < grid.length; i++) {
				boolean check = fourCheck(i, j, ch);
				if (check) {
					return true;
				}
			}
		}
		symbolRow = 0;
		return false;
	}

	public boolean check(String ch) {
		boolean check1 = checkDiagonals(ch);
		boolean check2 = checkRowsAndColumns(ch);
		if (check1 == false && check2 == false) {
			return false;
		} else {
			return true;
		}
	}

	public int columnsLength() {
		return grid[0].length;
	}

	public int rowsLength() {
		return grid.length;
	}

	public void setElement(int i, int j, String ch) {
		this.grid[i][j] = ch;
	}

	public String getElement(int i, int j) {
		return grid[i][j];
	}

	public String[][] getGrid() {
		return grid;
	}
}
