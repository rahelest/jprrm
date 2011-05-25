package Tanks.mapElements;

public class UnbreakableWall extends ObjectBase {

	protected Boolean breakable = false;
	
	public UnbreakableWall(int x, int y, int width, int height, Boolean bulletPassability) {
		super(x, y, width, height, false, bulletPassability);
		// TODO Auto-generated constructor stub
	}


}
