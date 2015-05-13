package test;

public class Vertex{
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

	public void visit(){
		this.degree++;
	}

	public int getDegree(){
		return this.degree;
	}
}

