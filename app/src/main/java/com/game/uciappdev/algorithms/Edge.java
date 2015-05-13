package test;

public class Edge{
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
