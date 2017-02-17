package fabe0940.ai;

import fabe0940.ai.Search;
import fabe0940.ai.Board;
import java.util.List;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class DFSLimit implements Search {
	private static final int DEPTH_MAX = 35;
	private int moves;
	private List<Board> goals;
	private List<Board> explored;
	private String name;

	public DFSLimit() {
		name = "DFS Limit ";

		moves = 0;
		goals = new LinkedList<Board>();
		goals.add(new Board());

		return;
	}

	private boolean _solve(Board puzzle, int depth) {
		boolean result;
		boolean valid;
		Board child;

		moves++;

		if (depth > DEPTH_MAX) return false;

		for (Board goal : goals) {
			if (Board.compare(puzzle, goal)) return true;
		}

		for (int move : puzzle.getMoves()) {
			child = puzzle.move(move);

			valid = child != null;
			for (Board b : explored) {
				valid &= !Board.compare(child, b);
			}

			if (valid) {
				result = _solve(child, depth + 1);
				if (result) return true;
			}
		}

		return false;
	}

	public int solve(Board puzzle) {
		int result;

		moves = 0;
		explored = new LinkedList<Board>();

		if (_solve(puzzle, 0)) {
			result = moves;
		} else {
			result = -1;
		}

		return result;
	}

	public String getName() {
		return name;
	}
}
