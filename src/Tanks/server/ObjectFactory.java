package Tanks.server;

import Tanks.shared.mapElements.*;

public class ObjectFactory {

	int id = 0;
	
	public Water createWater(int x, int y) {
		return new Water("a" + ++id, x, y);
	}
	
	
	public Tree createTree(int x, int y) {
		return new Tree("a" + ++id, x, y);
	}
	
	public BrickWall createBrickWall(int x, int y) {
		return new BrickWall("a" + ++id, x, y);
	}
	
	public IronWall createIronWall(int x, int y) {
		return new IronWall("a" + ++id, x, y);
	}
}
