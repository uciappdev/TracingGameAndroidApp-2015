package test;

import java.util.ArrayList;

public class temp {
	private static ArrayList<Vertex> vlist;
	private static ArrayList<Edge> elist;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		vlist = new ArrayList<Vertex>();
		for (int x = 0; x < 3; x++){
			for (int y = 0; y < 3; y++){
				Vertex coord = new Vertex(x, y);
				vlist.add(coord);
			}
		}
		
		ArrayList<Edge> temp = make_lines(vlist);
		for (Edge e: temp){
			System.out.println(e.getStart().getX() + " , "
					+ e.getStart().getY() + " - " + e.getEnd().getX()
					+ " , " + e.getEnd().getY());
		}
	}
	
	public static ArrayList<Edge> make_lines(ArrayList<Vertex> current) {

		elist = new ArrayList<Edge>();

		for (int i = 0; i < current.size(); i++ ) {
			for (int j = i+1; j < current.size(); j++){
				Edge e1 = new Edge(vlist.get(i), vlist.get(j));
				Edge e2 = new Edge(vlist.get(j), vlist.get(i));
				elist.add(e1);
				elist.add(e2);
				
			}
		}

		return elist;
	}

	

	
}
