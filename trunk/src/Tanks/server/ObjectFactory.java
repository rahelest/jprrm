package Tanks.server;

import Tanks.shared.mapElements.Tree;
import Tanks.shared.mapElements.Water;

public class ObjectFactory {

	int id = 0;
	
	public Water createWater(int x, int y) {
		return new Water("a" + ++id, x, y);
	}
	
	
	public Tree createTree(int x, int y) {
		return new Tree("a" + ++id, x, y);
	}
}
