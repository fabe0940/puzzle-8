package fabe0940.ai;

import fabe0940.ai.Search;
import fabe0940.ai.Board;
import fabe0940.ai.Heuristic;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class AStar implements Search {
	private Heuristic heuristic;
	private List<Board> goals;
	private String name;

	public AStar(Heuristic h) {
		heuristic = h;

		name = String.format("A* [%s]", heuristic.getName());

		goals = new LinkedList<Board>();
		goals.add(new Board());

		return;
	}

	public int solve(Board puzzle) {
		boolean valid;
		int moves;
		BoardCost node;
		Board child;
		List<BoardCost> frontier;
		List<BoardCost> explored;

		moves = 0;
		frontier = new LinkedList<BoardCost>();
		explored = new LinkedList<BoardCost>();

		for (Board goal : goals) {
			if (Board.compare(puzzle, goal)) return moves;
		}

		frontier.add(new BoardCost(puzzle, 0 + heuristic.estimate(puzzle)));

		while (true) {
			if (frontier.size() == 0) return -1;

			node = null;
			for (BoardCost bc : frontier) {
				if (node == null) {
					node = bc;
				} else if (
					(bc.cost + heuristic.estimate(bc.board))
					< (node.cost + heuristic.estimate(node.board))
				) {
					node = bc;
				}
			}
			frontier.remove(node);
			explored.add(node);
			moves++;

			for (Board goal : goals) {
				if (Board.compare(node.board, goal)) return moves;
			}

			for (int move : node.board.getMoves()) {
				child = node.board.move(move);

				valid = child != null;
				for (BoardCost bc : frontier) {
					valid &= (!Board.compare(child, bc.board))
						|| (Board.compare(child, bc.board) 
						&& node.cost + 1 < bc.cost);
				}
				for (BoardCost bc : explored) {
					valid &= (!Board.compare(child, bc.board))
						|| (Board.compare(child, bc.board) 
						&& node.cost + 1 < bc.cost);
				}

				if (valid) {
					frontier.add(new BoardCost(child, node.cost + 1));
				}
			}
		}
	}

	public String getName() {
		return name;
	}
}
