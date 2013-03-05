
public class Snake1 extends Snake{
	public Snake1(Grid grid, Box position){
		super(grid, position);
	}
	public boolean move(){
		int i = head.position.geti();
		int j = head.position.getj();
		//Change in position according to direction
		if (dir == Direction.RIGHT) i++;
		if (dir == Direction.UP) j--;
		if (dir == Direction.LEFT) i--;
		if (dir == Direction.DOWN) j++;
		//Hits the wall? Game over
		if (i == grid.grid.length) return false;
		if (j == grid.grid[0].length) return false;
		if (i == -1) return false;
		if (j == -1) return false;
		//Game over if the head hits a tail segment
		if (grid.grid[i][j].occupied) return false;

		Box pos = head.position; //temp for current head position
		head.position = grid.grid[i][j]; //new position for head
		head.position.occupied = true; //new position is occupied
		for(int l = 0; l < tail.size(); l++){
			Segment s = tail.get(l); //gets the next tail segment
			Box temp = s.position; //stores the tail segments current position
			s.position = pos; //the tail segment's new position is the old position of the previous segment
			pos = temp; //the old position variable gets updated to the current segments old position
		}
		pos.occupied = false;
		newposition = pos;

		return true;


	}
}
