package fabe0940.ai;

public interface Heuristic {
	public int estimate(Board b);
	public String getName();
}
