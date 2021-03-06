package fabe0940.ai;

import fabe0940.ai.Search;
import fabe0940.ai.Board;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class BFS implements Search {
	private List<Board> goals;
	private String name;

	public BFS() {
		name = "BFS       ";

		goals = new LinkedList<Board>();
		goals.add(new Board());

		return;
	}

	public int solve(Board puzzle) {
		boolean valid;
		int moves;
		Board node;
		Board child;
		Queue<Board> frontier;

		moves = 0;
		node = puzzle;
		frontier = new LinkedList<Board>();

		for (Board goal : goals) {
			if (Board.compare(node, goal)) return moves;
		}

		frontier.add(node);

		while (true) {
			if (frontier.size() == 0) return -1;

			moves++;
			node = frontier.remove();

			for (Board goal : goals) {
				if (Board.compare(node, goal)) return moves;
			}

			for (int move : node.getMoves()) {
				child = node.move(move);

				valid = child != null;
				for (Board b : frontier) {
					valid &= !Board.compare(child, b);
				}

				if (valid) frontier.add(child);
			}
		}
	}

	public String getName() {
		return name;
	}
}
