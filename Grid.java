import java.util.LinkedList;


public class Grid {
	public Box[][] grid;

	public Grid(int w, int h){
		grid = new Box[w][h];
		for (int i = 0; i < w; i++){
			for (int j = 0; j < h; j++){
				grid[i][j] = new Box(i, j);
			}
		}
	}
public LinkedList<Box> getFree(){
	LinkedList<Box> ret = new LinkedList<Box>();
	for (int i = 0; i < grid.length; i++){
		for (int j = 0; j < grid[0].length; j++){
			if(!grid[i][j].occupied){
				ret.add(grid[i][j]);
			}
		}
	}
	return ret;
}
}
