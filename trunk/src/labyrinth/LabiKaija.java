package labyrinth;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class LabiKaija {
	
	Maze m;
	ArrayList<Info> info = new ArrayList<LabiKaija.Info>();

	public LabiKaija(Maze m) {
		this.m = m;
		alusta(m.entrance);
	}
	
	private void alusta(Dimension entrance) {
		List<char[]> suunad = leiaVabadSuunad(entrance);
	}

	private List<char[]> leiaVabadSuunad(Dimension location) {
		if (!m.isThisDirectionWall(location, Maze.NORTH)) {
			info.add(new Info(location, Maze.NORTH));
		}
		if (!m.isThisDirectionWall(location, Maze.EAST)) {
			info.add(new Info(location, Maze.EAST));
		}
		if (!m.isThisDirectionWall(location, Maze.SOUTH)) {
			info.add(new Info(location, Maze.SOUTH));
		}
		if (!m.isThisDirectionWall(location, Maze.WEST)) {
			info.add(new Info(location, Maze.WEST));
		}
	}

	class Info {
		Dimension algus;
		List<Dimension> suunad;
		Dimension lopp;
		
		Info (Dimension a, Dimension s) {
			algus = a;
			suunad = new ArrayList<Dimension>();
			suunad.add(s);
		}
	}
}
