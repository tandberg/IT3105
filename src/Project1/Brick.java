package Project1;

public class Brick {

	private Size size;
	private Color color;
	private Hole hole;
	private Shape shape;
	
	public Brick(Size size, Color color, Hole hole, Shape shape) {
		this.size = size;
		this.color = color;
		this.hole = hole;
		this.shape = shape;
	}
	
	public String toString() {
		if(size == null && color == null && shape == null && hole == null) {
			return "X";
		}
		String h = this.hole == Hole.HAS_HOLE ? "*" : "";
		String c = "";
		if(this.color == Color.BLUE) {
			c = this.size == Size.LARGE ? "B" : "b";
		} else {
			c = this.size == Size.LARGE ? "R" : "r";
		}
		return this.shape == Shape.ROUND ? "("+c+h+")" : c+h;
		
	}

	public Size getSize() {
		return size;
	}

	public Color getColor() {
		return color;
	}

	public Hole getHole() {
		return hole;
	}

	public Shape getShape() {
		return shape;
	}
}
