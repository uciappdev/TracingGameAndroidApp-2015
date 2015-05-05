import java.util.Random;

public class FleurysAlgorithm{

	private Random r;
	private int size;
	private ArrayList<Coordinate> list;

	public FleurysAlgorithm(int n){
		this.size = n;
		this.list = new ArrayList<Coordinate>();
		this.r = new Random();
		for (int x = 0; x < n; x++){
			for (int y = 0; y < n; y++){
				coord = new Coordinate(x, y);
				list.add(coord)
			}
		}
	}

	public void generate(){
		Coordinate start = list.get(0);
		int temp = r.nextInt(size);
		if (list.get(temp) != start){
			//we stopped here;
	}

	private class Coordinate{
		private int x;
		private int y;

		public Coordinate(int x, int y){
			this.x = x;
			this.y = y;
		}

		public int getX(){
			return this.x;
		}

		public int getY(){
			return this.y;
		}
	}
}
