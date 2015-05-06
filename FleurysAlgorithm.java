import java.util.ArrayList;
import java.util.Random;


public class FleurysAlgorithm{

    private Random randomGenerator;
    private int size;
    private ArrayList<Vertex> vlist;
    private ArrayList<Edge> elist;
    
    // temporary main block for testing
    public static void main(String[] args) {
        Edge edgeOne = new Edge(new Vertex(0, 0), new Vertex(4, 4));
        Edge edgeTwo = new Edge(new Vertex(4, 0), new Vertex(0, 4));
        boolean intersection = intersects(edgeOne, edgeTwo);
        System.out.println(intersection);
    }

    public FleurysAlgorithm(int n){
        this.size = n;
        this.vlist = new ArrayList<Vertex>();
        this.elist = new ArrayList<Edge>();
        this.randomGenerator = new Random();

        // create all vertices. each vertex keeps count of visits.
        for (int x = 0; x < n; x++){
            for (int y = 0; y < n; y++){
                Vertex coord = new Vertex(x, y);
                vlist.add(coord);
            }
        }
    }

    // remove static from all of the following after debugging 

    public static boolean intersects(Edge a, Edge b){
        int ax1 = a.getStart().getX(),
            ay1 = a.getStart().getY(),
            ax2 = a.getEnd().getX(),
            ay2 = a.getEnd().getY(),
            bx1 = b.getStart().getX(),
            by1 = b.getStart().getY(),
            bx2 = b.getEnd().getX(),
            by2 = b.getEnd().getY();

        

        int aSlope = (ay1 - ay2) / (ax1 - ax2);  // slope
        int aLeftAxisIntersect = ay1 - aSlope*ax1;  // y = mx + b, b = mx + y

        int bSlope = (by1 - by2) / (bx1 - bx2);  
        int bLeftAxisIntersect = by1 - bSlope*bx1;
        System.out.println(aSlope);
        System.out.println(aLeftAxisIntersect);
        System.out.println(bSlope);
        System.out.println(bLeftAxisIntersect);

        double xIntercept = (aLeftAxisIntersect-bLeftAxisIntersect) / (bSlope-aSlope);
        // whether you use the slopes and y axis intercept of edge a or b does not matter
        double yIntercept = aSlope*(xIntercept) - aLeftAxisIntersect;
        System.out.println(xIntercept);
        System.out.println(yIntercept);


        return ((ax1 < xIntercept && xIntercept < ax2 && bx2 < xIntercept && xIntercept < bx1) &&
                 (by2 < yIntercept && yIntercept < by1 && ay2 < yIntercept && yIntercept < ay1));
    }


    public void generate(){
        for (int i=0; i < this.vlist.size(); i++){

            Vertex start = this.vlist.get(i);
            
            // TODO:
            // while degree of start and end is less than 2 and the edge does not intersect
            while (start.getDegree() < 2){
                int temp = randomGenerator.nextInt(size);
                Vertex end = this.vlist.get(temp);
                if (end != start){
                    start.visit();
                    Edge edge = new Edge(start, end);
                    this.elist.add(edge);
                    break;
                }
            }
        }
    }

    public static class Edge{
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

    public static class Vertex{
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

    public void makeLines(ArrayList<Vertex> current) {
        for (int i=0; i < current.size(); i++ ) {
            for (int j = i+1; j < current.size(); j++){
                Edge e = new Edge(this.vlist.get(i), this.vlist.get(j));
                this.elist.add(e);
            }
        }
    }
}
