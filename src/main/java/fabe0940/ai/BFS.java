package fabe0940.ai;

import fabe0940.ai.Search;
import fabe0940.ai.Board;
import java.util.List;
import java.util.LinkedList;

public class BFS implements Search {
	private List<Board> goals;
	private String name;

	public BFS() {
		name = "BFS";

		goals = new LinkedList<Board>();
		goals.add(new Board(new int[][] {
			{ 0, 1, 2 },
			{ 3, 4, 5 },
			{ 6, 7, 8 },
		}));

		return;
	}

	public int solve(Board b) {
		return 0;
	}

	public String getName() {
		return name;
	}
}
