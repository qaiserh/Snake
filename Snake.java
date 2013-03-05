import java.util.LinkedList;


public abstract class Snake {
	public Direction dir; //Direction the snake is moving in
	protected Segment head;
	protected LinkedList<Segment> tail;
	protected Box newposition; //the position in which a new tail segment would be added
	protected Grid grid;
	public Snake(Grid grid, Box position){ //position of head
		this.grid = grid;
		head = new Segment(position, null);
		tail = new LinkedList<Segment>();
		dir = Direction.RIGHT;
		int i = position.geti();
		int j = position.getj();
		Box position2 = grid.grid[i - 1][j]; //position of first tail segment
		Box position3 = grid.grid[i - 2][j]; // position of second tail segment
		tail.addLast(new Segment(position2, head));
		tail.addLast(new Segment(position3, tail.getLast()));
		position.occupied = true;
		position2.occupied = true;
		position3.occupied = true;
		newposition = grid.grid[position3.geti() - 1][j];
	}
	public abstract boolean move();
	public boolean eats(Fruit fruit) {
		if(head.position.equals(fruit.getPosition())){
			return true;
		}
		return false;
	}
	public void lengthen() {
		Segment s = new Segment(newposition, tail.getLast());
		tail.addLast(s);
		newposition = s.position;
		s.position.occupied = true;
	}
	public void draw(){
		head.draw();
		for(int i = 0; i < tail.size(); i++){
			tail.get(i).draw();
		}
	}
}
