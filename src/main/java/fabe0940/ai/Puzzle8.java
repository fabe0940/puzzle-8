package fabe0940.ai;

import fabe0940.ai.*;
import java.util.*;
import java.io.*;

class Puzzle8 {
	public static void main(String[] args) {
		boolean running;
		int move;
		Board puzzle;
		Board next;
		BufferedReader input;
		List<Board> goals;

		input = new BufferedReader(new InputStreamReader(System.in));

		puzzle = Board.randomize();

		goals = new LinkedList<Board>();
		goals.add(new Board(new int[][] {
			{ 0, 1, 2 },
			{ 3, 4, 5 },
			{ 6, 7, 8 },
		}));
		goals.add(new Board(new int[][] {
			{ 1, 2, 3 },
			{ 4, 5, 6 },
			{ 7, 8, 0 },
		}));

		move = 0;
		running = true;

		while(running) {
			System.out.print("Current position:\n");
			Board.print(puzzle);

			System.out.print("Your move: ");
			try {
				move = Integer.parseInt(input.readLine());
			} catch (Exception e) {
				/* do nothing */
			}

			next = puzzle.move(move);
			if (next == null) {
				System.out.print("Invalid move\n");
			} else {
				puzzle = next;

				for (Board goal : goals) {
					running = running && !Board.compare(puzzle, goal);
				}
			}

			System.out.println();
		}

		System.out.print("The puzzle is solved\n");
		Board.print(puzzle);

		return;
	}
}
