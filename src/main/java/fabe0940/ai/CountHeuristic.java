package fabe0940.ai;

import fabe0940.ai.Board;
import fabe0940.ai.Heuristic;

public class CountHeuristic implements Heuristic {
	private String name;

	public CountHeuristic() {
		name = "count";

		return;
	}

	public int estimate(Board b) {
		int est;
		int col;
		int row;
		Board goal;

		est = 0;
		goal = new Board();

		for (row = 0; row < Board.dim; row++) {
			for (col = 0; col < Board.dim; col++) {
				if (b.getEntry(row, col) != 0) {
					if (b.getEntry(row, col) != goal.getEntry(row, col)) {
						est++;
					}
				}
			}
		}

		return est;
	}

	public String getName() {
		return name;
	}
}
