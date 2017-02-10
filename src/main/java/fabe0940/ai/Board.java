package fabe0940.ai;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

class Board {
	private static final int dim = 3;
	private int[][] vals;

	/* Print a board to stdout */
	public static void print(Board b) {
		int col;
		int row;
		int val;

		for (row = 0; row < dim; row++) {
			for (col = 0; col < dim; col++) {
				val = b.getEntry(row, col);
				if (val != 0) {
					System.out.format("%d", val);
				} else {
					System.out.format(" ");
				}
				if (dim - col > 1) System.out.format(" ");
			}
			System.out.format("\n");
		}
		
		return;
	}

	/* Compare two boards for equality */
	public static boolean compare(Board a, Board b) {
		boolean result;
		int col;
		int row;

		result = true;

		for (row = 0; row < dim; row++) {
			for (col = 0; col < dim; col++) {
				result &= a.getEntry(row, col) == b.getEntry(row, col);
			}
		}
		
		return result;
	}

	/* Create a randomized board */
	public static Board randomize(int randomMoves) {
		int moves;
		int tile;
		Board randomized;
		Random rng;
		List<Integer> tiles;

		randomized = new Board();
		rng = new Random();

		moves = 0;
		while (moves < randomMoves) {
			tiles = randomized.getMoves();
			tile = tiles.get(rng.nextInt(tiles.size()));
			randomized = randomized.move(tile);
			moves++;
		}

		return randomized;
	}

	/* Default constructor */
	public Board() {
		int col;
		int row;

		vals = new int[dim][dim];

		for (row = 0; row < dim; row++) {
			for (col = 0; col < dim; col++) {
				vals[row][col] = (dim * row) + col;
			}
		}

		return;
	}

	/* Construct from int[][] */
	public Board(int[][] initial) {
		int col;
		int row;

		vals = new int[dim][dim];

		for (row = 0; row < dim; row++) {
			for (col = 0; col < dim; col++) {
				vals[row][col] = initial[row][col];
			}
		}

		return;
	}

	/* Copy constructor */
	public Board(Board b) {
		int col;
		int row;

		vals = new int[dim][dim];

		for (row = 0; row < dim; row++) {
			for (col = 0; col < dim; col++) {
				vals[row][col] = b.getEntry(row, col);
			}
		}

		return;
	}

	/* Make a valid move */
	public Board move(int val) {
		int col;
		int row;
		int from_col;
		int from_row;
		int to_col;
		int to_row;
		int[][] after;
		Board result;
		List<Integer> moves;

		col = 0;
		row = 0;
		from_col = 0;
		from_row = 0;
		to_col = 0;
		to_row = 0;

		moves = getMoves();

		/* Check if the requested move is legal */
		if (moves.contains(val)) {
			after = getValues();

			/* Get the coordinates of the tile to be moved */
			for (row = 0; row < dim; row++) {
				for (col = 0; col < dim; col++) {
					if (getEntry(row, col) == val) {
						to_col = col;
						to_row = row;
					}
				}
			}

			/* Get the coordinates of the blank tile */
			for (row = 0; row < dim; row++) {
				for (col = 0; col < dim; col++) {
					if (getEntry(row, col) == 0) {
						from_col = col;
						from_row = row;
					}
				}
			}

			/* Move the tile */
			after[from_row][from_col] = val;
			after[to_row][to_col] = 0;

			result = new Board(after);
		} else {
			result = null;
		}

		return result;
	}

	/* Get the board data */
	public int[][] getValues() {
		int col;
		int row;
		int[][] copy;

		copy = new int[dim][dim];

		for (row = 0; row < dim; row++) {
			for (col = 0; col < dim; col++) {
				copy[row][col] = vals[row][col];
			}
		}

		return copy;
	}

	public List<Integer> getMoves() {
		int col;
		int row;
		int from_col;
		int from_row;
		List<Integer> moves;

		col = 0;
		row = 0;
		from_col = 0;
		from_row = 0;

		moves = new LinkedList<Integer>();

		/* Find the empty square */
		for (row = 0; row < dim; row++) {
			for (col = 0; col < dim; col++) {
				if (getEntry(row, col) == 0) {
					from_col = col;
					from_row = row;
				}
			}
		}

		/* Find all legal moves */
		if (isLegal(from_row + 1, from_col)) {
			moves.add(getEntry(from_row + 1, from_col));
		}
		if (isLegal(from_row - 1, from_col)) {
			moves.add(getEntry(from_row - 1, from_col));
		}
		if (isLegal(from_row, from_col + 1)) {
			moves.add(getEntry(from_row, from_col + 1));
		}
		if (isLegal(from_row, from_col - 1)) {
			moves.add(getEntry(from_row, from_col - 1));
		}

		return moves;
	}

	/* Get a specific entry */
	public int getEntry(int row, int col) {
		return vals[row][col];
	}

	/* Check if a (row, col) pair is inbounds */
	private boolean isLegal(int row, int col) {
		return (row < dim && row >= 0) && (col < dim && col >= 0);
	}
}
