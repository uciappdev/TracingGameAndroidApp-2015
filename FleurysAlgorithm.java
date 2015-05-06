import java.util.Random;

public class FleurysAlgorithm{

	private Random r;
	private int size;
	private ArrayList<Vertex> vlist;
	private ArrayList<Edge> elist;

	public FleurysAlgorithm(int n){
		this.size = n;
		this.vlist = new ArrayList<Vertex>();
		this.r = new Random();
		for (int x = 0; x < n; x++){
			for (int y = 0; y < n; y++){
				coord = new Vertex(x, y);
				vlist.add(coord)
			}
		}
	}

	public void generate(){
		Vertex start = vlist.get(0);
		int temp = r.nextInt(size);
		if (vlist.get(temp) != start){
			
			//we stopped here;
	}

	public static ArrayList<Edge> make_lines(ArrayList<Vertex> current) {

		elist = new ArrayList<Edge>();

		for (int i = 0; i < current.size(); i++ ) {
			for (int j = i+1; j < current.size(); j++){
				Edge e = new Edge(vlist.get(i), vlist.get(j));
				elist.add(e);
			}
		}

		return lines;
	}

	private class Edge{
		private Vertex start;
		private Vertex end;

		public Edge(Vertex s, Vertex e){
			this.start = s;
			this.end = e;
		}

		public Vertex getStart(){
			return this.start;
		}

		public Vertex getEnd(){
			return this.end;
		}
	}

	private class Vertex{
		private int x;
		private int y;
		private int degree = 0;

		public Vertex(int x, int y){
			this.x = x;
			this.y = y;
		}

		public int getX(){
			return this.x;
		}

		public int getY(){
			return this.y;
		}

		public visit(){
			this.degree++;
		}

		public int getDegree(){
			return this.degree;
		}
	}
}
