package fabe0940.ai;

import fabe0940.ai.Board;
import fabe0940.ai.Heuristic;

public class DistanceHeuristic implements Heuristic {
	private String name;

	public DistanceHeuristic() {
		name = "dist.";

		return;
	}

	public int estimate(Board b) {
		int est;
		int i;
		int col;
		int row;
		int pos_row;
		int pos_col;
		int goal_row;
		int goal_col;
		Board goal;

		est = 0;
		goal_col = 0;
		goal_row = 0;
		pos_col = 0;
		pos_row = 0;
		goal = new Board();

		for (i = 1; i < Board.dim * Board.dim; i++) {
			for (row = 0; row < Board.dim; row++) {
				for (col = 0; col < Board.dim; col++) {
					if (b.getEntry(row, col) == i) {
						pos_row = row;
						pos_col = col;
					}
					if (goal.getEntry(row, col) == i) {
						goal_row = row;
						goal_col = col;
					}
				}
			}

			est += Math.abs(goal_row - pos_row) + Math.abs(goal_col - pos_col);
		}

		return est;
	}

	public String getName() {
		return name;
	}
}
