package Tanks.mapElements;

public class UnbreakableObject extends GameObject {

	protected Boolean breakable = false;
	
	public UnbreakableObject(int x, int y, int width, int height, Boolean bulletPassability) {
		super(x, y, width, height, false, bulletPassability);
		// TODO Auto-generated constructor stub
	}


}
