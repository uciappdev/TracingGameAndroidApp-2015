import java.util.ArrayList;
import java.util.Random;


public class FleurysAlgorithm{

    private Random randomGenerator;
    private int size;
    private ArrayList<Vertex> vlist;
    private ArrayList<Edge> elist;
    public ArrayList<Vertex> completeCircuit;

    public FleurysAlgorithm(int size){
        this.size = size;
        this.vlist = new ArrayList<Vertex>();
        this.elist = new ArrayList<Edge>();
        this.randomGenerator = new Random();
        this.completeCircuit = new ArrayList<Vertex>();

        buildGrid(this.size);
        generatePath();
        System.out.println(vlist);
    }

    // remove static from all of the following after debugging 
    public void buildGrid(int size){
        // create all vertices.
        for (int x = 0; x < size; x++){
            for (int y = 0; y < size; y++){
                Vertex coord = new Vertex(x, y);
                this.vlist.add(coord);
            }
        }      
    }

    public void generatePath(){
        int r = this.randomGenerator.nextInt(this.size*this.size);
        Vertex originalStart = this.vlist.get(r);
        this.completeCircuit.add(originalStart);
        Vertex currentStart = originalStart;

        while (true){
            r = this.randomGenerator.nextInt(this.size*this.size);
            Vertex currentEnd = this.vlist.get(r);            
            if (currentEnd.getDegree() == 2){
                continue; // and try a different currentEnd.
            }
            if (currentEnd == currentStart){
                continue;
            }

            Edge potentialEdge = new Edge(currentStart, currentEnd);
            if (intersectsAnyEdge(potentialEdge)){
                continue; // and try a different currentEnd
            }   
            if (currentEnd == originalStart){
                if (this.completeCircuit.size() >= 3){
                    createEdge(currentStart, currentEnd, potentialEdge);           
                    break;
                }
                else if (this.completeCircuit.size() < 3){
                    continue;
                }
            }            
            createEdge(currentStart, currentEnd, potentialEdge);           
            currentStart = currentEnd;

        }
    }

    public void createEdge(Vertex currentStart, Vertex currentEnd, Edge potentialEdge){
        currentStart.visit();
        currentEnd.visit();
        this.elist.add(potentialEdge);
        this.completeCircuit.add(currentEnd);        
    }
    
    public static boolean intersects(Edge a, Edge b){
        int ax1 = a.getStart().getX(),
            ay1 = a.getStart().getY(),
            ax2 = a.getEnd().getX(),
            ay2 = a.getEnd().getY(),
            bx1 = b.getStart().getX(),
            by1 = b.getStart().getY(),
            bx2 = b.getEnd().getX(),
            by2 = b.getEnd().getY();

        int aSlope;
        int aLeftAxisIntersect;
        int bSlope;
        int bLeftAxisIntersect;   
           
        int bottomSlopeFormulaA = ax1 - ax2;
        int bottomSlopeFormulaB = bx1 - bx2;


        double xIntercept, yIntercept;

        if (bottomSlopeFormulaA == bottomSlopeFormulaB){
            return false; // if both edges are vertical.
        }
        // if edge a is vertical.
        if (bottomSlopeFormulaA == 0){
            xIntercept = ax1;
            bSlope = (by1 - by2) / (bx1 - bx2);
            bLeftAxisIntersect = by1 - bSlope*bx1;  
            yIntercept = bSlope*(xIntercept) - bLeftAxisIntersect;
            return ((ax1 < xIntercept && xIntercept < ax2 && bx2 < xIntercept && xIntercept < bx1) &&
                    (by1 < yIntercept && yIntercept < by2 && ay1 < yIntercept && yIntercept < ay2));  
        }
        // if edge b is vertical
        if (bottomSlopeFormulaB == 0){
            xIntercept = bx1;
            aSlope = (ay1 - ay2) / (ax1 - ax2);
            aLeftAxisIntersect = ay1 - aSlope*ax1;
            yIntercept = aSlope*(xIntercept) - aLeftAxisIntersect;
            return ((ax1 < xIntercept && xIntercept < ax2 && bx2 < xIntercept && xIntercept < bx1) &&
                    (by1 < yIntercept && yIntercept < by2 && ay1 < yIntercept && yIntercept < ay2));  
        }  

        aSlope = (ay1 - ay2) / (ax1 - ax2);
        bSlope = (by1 - by2) / (bx1 - bx2);
        if (aSlope == bSlope){
            return false; // parallel lines do not intersect.
        }
        // if all the above cases are passed the only case left is:
        //      both edges' LINES intersect and none of them are vertical.

        // b = mx + y
        aLeftAxisIntersect = ay1 - aSlope*ax1;
        bLeftAxisIntersect = by1 - bSlope*bx1;  
        xIntercept = (aLeftAxisIntersect-bLeftAxisIntersect) / (bSlope-aSlope);

        // whether you use the slopes and y axis intercept of edge a or b does not matter
        yIntercept = aSlope*(xIntercept) - aLeftAxisIntersect;

        // return whether or not the intersection coordinates are within the boundaries of edges.
        return ((ax1 < xIntercept && xIntercept < ax2 && bx2 < xIntercept && xIntercept < bx1) &&
                (by1 < yIntercept && yIntercept < by2 && ay1 < yIntercept && yIntercept < ay2));            

    }

    /*checks if the given edge intersects any existing edge in the grid.*/
    public boolean intersectsAnyEdge(Edge potentialEdge){
        for (Edge edge : this.elist){
            if (intersects(potentialEdge, edge)){
                return true;
            }
        }
        return false;
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

        @Override public String toString(){
            return String.format("Vertex(%d, %d)", this.x, this.y);
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
