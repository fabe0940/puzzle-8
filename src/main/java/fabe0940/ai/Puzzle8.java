package fabe0940.ai;

import fabe0940.ai.*;
import java.util.*;
import java.io.*;

class Puzzle8 {
	public static void main(String[] args) {
		final int iterations = 10;
		final int randomizations = 30;
		int i;
		double count;
		double mean;
		double stddev;
		double sum;
		Board puzzle;
		Map<Search, List<Integer>> searches;

		searches = new HashMap<Search, List<Integer>>();
		searches.put(new BFS(), new LinkedList<Integer>());
		searches.put(new BFSClosed(), new LinkedList<Integer>());

		System.out.format("Running %d iterations on boards randomized %d times:\n",
			iterations, randomizations);

		for (i = 0; i < iterations; i++) {
			puzzle = Board.randomize(randomizations);

			for (Map.Entry<Search, List<Integer>> search : searches.entrySet()) {
				search.getValue().add(search.getKey().solve(puzzle));
			}
		}

		for (Map.Entry<Search, List<Integer>> search : searches.entrySet()) {
			System.out.format("%s: ", search.getKey().getName());

			for (Integer val : search.getValue()) {
				System.out.format("%5d ", val);
			}

			sum = 0;
			count = 0;
			for (Integer val : search.getValue()) {
				sum += val;
				count++;
			}
			mean = sum / count;
			System.out.format(" | AVG: %7.2f", mean);

			sum = 0;
			count = 0;
			for (Integer val : search.getValue()) {
				sum += (val - mean) * (val - mean);
				count++;
			}
			stddev = Math.sqrt(sum / (count - 1));
			System.out.format(" | STDDEV: %7.2f", stddev);

			System.out.print("\n");
		}

		return;
	}
}
