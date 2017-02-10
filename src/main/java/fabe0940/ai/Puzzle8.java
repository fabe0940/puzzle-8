package fabe0940.ai;

import fabe0940.ai.*;
import java.util.*;
import java.io.*;

class Puzzle8 {
	public static void main(String[] args) {
		final int iterations = 10;
		int i;
		Board puzzle;
		Map<Search, List<Integer>> searches;

		searches = new HashMap<Search, List<Integer>>();
		searches.put(new BFS(), new LinkedList<Integer>());

		for (i = 0; i < iterations; i++) {
			puzzle = Board.randomize();

			for (Map.Entry<Search, List<Integer>> search : searches.entrySet()) {
				search.getValue().add(search.getKey().solve(puzzle));
			}
		}

		for (Map.Entry<Search, List<Integer>> search : searches.entrySet()) {
			System.out.format("%s: ", search.getKey().getName());
			for (Integer val : search.getValue()) {
				System.out.format("%3d ", val);
			}
			System.out.print("\n");
		}

		return;
	}
}
