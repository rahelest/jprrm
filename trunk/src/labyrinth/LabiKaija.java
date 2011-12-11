package labyrinth;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class LabiKaija {
	
	Maze m;	ArrayList<Info> info = new ArrayList<LabiKaija.Info>();
	InfoList infod;
	List<Dimension> suunad = new ArrayList<Dimension>();
	
	public LabiKaija(Maze m) {
		this.m = m;
		alusta(m.entrance);
		
		suunad.add(Maze.NORTH);
		suunad.add(Maze.EAST);
		suunad.add(Maze.SOUTH);
		suunad.add(Maze.WEST);
	}
	
	class Info {
		public Info(Dimension location, Dimension direction) {
			ristmikA = location;
			kaidudSuunad.add(direction);
		}
		public Info() {
		}
		Dimension ristmikA;
		List<Dimension> kaidudSuunad = new ArrayList<Dimension>();
		List<Dimension> vabadSuunad = new ArrayList<Dimension>();
		Dimension ristmikB;
	}
	
	class InfoList {
		List<Info> info = new ArrayList<LabiKaija.Info>();
		
		void add(Info i) {
			info.add(i);
		}

		public Info getKaimataRistmik() {
			for (int i = 0; i < info.size(); i++) {
				if (!info.get(i).vabadSuunad.isEmpty()) {
					return info.get(i);
				}
			}
			return null;
		}

		public void lisaInfo(Dimension lisatavRistmikA, Dimension direction) {
			for (Info i : info) {
				if (i.ristmikB.equals(lisatavRistmikA)) {
					
				}
			}
			
		}
	}
	
	private void alusta(Dimension entrance) {
		infod = new InfoList();
		Info i = new Info();
		infod.add(i);
		i.ristmikA = entrance;
		while (i != null) {
			i.vabadSuunad = vaataKuhuMinna(i.ristmikA, null);
			List<Dimension> vS = i.vabadSuunad;
			while (!vS.isEmpty()) {
				Dimension suund = vS.remove(0);
				i.ristmikB = mineSinnaSuunasJargmiseRistmikuni(i.ristmikA, suund);
				i.kaidudSuunad.add(suund);
			}
			i = infod.getKaimataRistmik();
		}
	}

	private Dimension mineSinnaSuunasJargmiseRistmikuni(Dimension location,
			Dimension direction) {
		List<Dimension> vastus = vaataKuhuMinna(location, direction);
		while (vastus.size() == 1) {
			location = m.moveOneStep(location, vastus.get(0));
			direction = vastandSuund(vastus.get(0));
			vastus = vaataKuhuMinna(location, direction);
		}
		infod.add(new Info(location, direction));
		return location;
	}
	private Dimension vastandSuund (Dimension suund) {
		if (suund.equals(Maze.NORTH)) return Maze.SOUTH;
		if (suund.equals(Maze.EAST)) return Maze.WEST;
		if (suund.equals(Maze.SOUTH)) return Maze.NORTH;
		if (suund.equals(Maze.WEST)) return Maze.EAST;
		else return null;
	}

	private List<Dimension> vaataKuhuMinna(Dimension location, Dimension kaidudSuund) {
		List<Dimension> vabadSuunad = new ArrayList<Dimension>();
		for (Dimension suund : suunad) {
			if ((kaidudSuund == null || !suund.equals(kaidudSuund)) && !m.isThisDirectionWall(location, suund)) {
				vabadSuunad.add(suund);
			}
		}
		return vabadSuunad;
	}
}