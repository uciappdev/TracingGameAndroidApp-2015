import java.util.ArrayList;
import java.util.Random;

/*
TODO: 
-- intersects() still does not account for overlapping lines. e.g. 0,0 -> 1, 1 and 0, 0 -> 2, 2
*/

public class FleurysAlgorithm {
    private final int SEARCHLIMIT = 300;
    private Random randomGenerator;
    private int size;
    private ArrayList<Vertex> vlist;
    private ArrayList<Edge> elist;
    public ArrayList<Vertex> completeCircuit;
    private boolean pathIsGenerated;

    
    public FleurysAlgorithm(int size) {
        this.size = size;
        this.vlist = new ArrayList<Vertex>();
        this.elist = new ArrayList<Edge>();
        this.randomGenerator = new Random();
        this.completeCircuit = new ArrayList<Vertex>();

        buildGrid(this.size);
        this.pathIsGenerated = false;
        while (!pathIsGenerated) {
            this.pathIsGenerated = generatePath();
        }
    }

    
    /*Given size, creates a grid of vertices with dimensions size x size*/
    private void buildGrid(int size) {
        // create all vertices.
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Vertex coord = new Vertex(x, y);
                this.vlist.add(coord);
            }
        }      
    }
    
    
    /*Generates a eulerian path in the grid.
     * @returns: boolean representing whether or not generatePath is successful.*/
    private boolean generatePath() {
        int r = this.randomGenerator.nextInt(this.size*this.size);
        Vertex originalStart = this.vlist.get(r);
        this.completeCircuit.add(originalStart);
        Vertex currentStart = originalStart;
        int searches = 0;
        while (searches < SEARCHLIMIT) {
            searches++;
            r = this.randomGenerator.nextInt(this.size*this.size);
            Vertex currentEnd = this.vlist.get(r);            
            if (currentEnd.getDegree() == 2) {
                continue; // and try a different currentEnd.
            }
            if (currentEnd == currentStart) {
                continue;
            }

            Edge potentialEdge = new Edge(currentStart, currentEnd);
            if (intersectsAnyEdge(potentialEdge)) {
                continue; // and try a different currentEnd
            }   
            if (currentEnd == originalStart) {
                if (this.completeCircuit.size() >= 3) {
                    createEdge(currentStart, currentEnd, potentialEdge);           
                    return true; // complete the circuit.
                }
                else if (this.completeCircuit.size() < 3) {
                    continue;
                }
            }            
            createEdge(currentStart, currentEnd, potentialEdge);           
            currentStart = currentEnd;
        }
        return false;
    }

    
    /*Actually implements a valid edge. (adds it to this.elist)*/
    private void createEdge(Vertex start, Vertex end, Edge potentialEdge) {
        start.visit();
        end.visit();
        this.elist.add(potentialEdge);
        this.completeCircuit.add(end);        
    }

    
    /*Given two edges, returns whether or not the two edges intersect.*/
    public static boolean intersects(Edge a, Edge b) {
        double ax1 = a.getStart().getX(),
            ay1 = a.getStart().getY(),
            ax2 = a.getEnd().getX(),
            ay2 = a.getEnd().getY(),
            bx1 = b.getStart().getX(),
            by1 = b.getStart().getY(),
            bx2 = b.getEnd().getX(),
            by2 = b.getEnd().getY();     

        double aSlope;
        double aLeftAxisIntersect;
        double bSlope;
        double bLeftAxisIntersect;   
           
        double bottomSlopeFormulaA = ax1 - ax2;
        double bottomSlopeFormulaB = bx1 - bx2;

        double xIntercept, yIntercept;

        if ((bottomSlopeFormulaA == 0) &&
            (bottomSlopeFormulaA == bottomSlopeFormulaB)) {
            return false; // if both edges are vertical.
        }
        // edge a is vertical.
        if (bottomSlopeFormulaA == 0) {
            xIntercept = ax1;
            bSlope = (by1 - by2) / (bx1 - bx2);
            bLeftAxisIntersect = by1 - bSlope*bx1;  
            yIntercept = bSlope*(xIntercept) + bLeftAxisIntersect;
            return (isBetween(xIntercept, bx1, bx2) &&
                    isBetween(yIntercept, by1, by2) && 
                    isBetween(yIntercept, ay1, ay2));  
        }
        // edge b is vertical
        if (bottomSlopeFormulaB == 0) {
            xIntercept = bx1;
            aSlope = (ay1 - ay2) / (ax1 - ax2);
            aLeftAxisIntersect = ay1 - aSlope*ax1;
            yIntercept = aSlope*(xIntercept) + aLeftAxisIntersect;
            return (isBetween(xIntercept, ax1, ax2) &&
                    isBetween(yIntercept, by1, by2) && 
                    isBetween(yIntercept, ay1, ay2));  
        }  

        aSlope = (ay1 - ay2) / (ax1 - ax2);
        bSlope = (by1 - by2) / (bx1 - bx2);
        
        if (aSlope == bSlope) {
            return false; // parallel lines do not intersect.
        }
        // edge a is horizontal
        if (aSlope == 0) {
            yIntercept = ay1; // arbitrary y of horizontal edge.
            bLeftAxisIntersect = by1 - bSlope*bx1;
            xIntercept = (yIntercept-bLeftAxisIntersect) / bSlope;
            return (isBetween(xIntercept, bx1, bx2) && 
                    isBetween(xIntercept, ax1, ax2) &&
                    isBetween(yIntercept, by1, by2));           
        } 
        // edge b is horizontal
        if (bSlope == 0) {
            yIntercept = by1; // arbitrary y of horizontal edge.
            aLeftAxisIntersect = ay1 - aSlope*ax1;
            xIntercept = (yIntercept-aLeftAxisIntersect) / aSlope;
            return (isBetween(xIntercept, bx1, bx2) && 
                    isBetween(xIntercept, ax1, ax2) &&
                    isBetween(yIntercept, ay1, ay2));               
        }        
        // If all if blocks are passed then neither edges are horizontal nor vertical.
        aLeftAxisIntersect = ay1 - aSlope*ax1;
        bLeftAxisIntersect = by1 - bSlope*bx1;  
        xIntercept = (aLeftAxisIntersect-bLeftAxisIntersect) / (bSlope-aSlope);
        // whether you use edge a or edge b is arbitrary.
        yIntercept = aSlope*(xIntercept) + aLeftAxisIntersect;
        return (isBetween(xIntercept, ax1, ax2) &&
                isBetween(xIntercept, bx2, bx1) &&
                isBetween(yIntercept, by1, by2) &&
                isBetween(yIntercept, ay1, ay2));          

    }
    
    
    private static boolean isBetween(double x, double a, double b) {
        return (a<x && x<b) || (b<x && x<a);
    }

    
    /*checks if the given edge intersects any existing edge in the grid.*/
    private boolean intersectsAnyEdge(Edge potentialEdge) {
        for (Edge edge : this.elist) {
            if (intersects(potentialEdge, edge)) {
                return true;
            }
        }
        return false;
    }

    
    public static class Edge {
        private Vertex start;
        private Vertex end;

        public Edge(Vertex s, Vertex e) {
            this.start = s;
            this.end = e;
        }

        public Vertex getStart() {
            return this.start;
        }

        public Vertex getEnd() {
            return this.end;
        }
    }

    
    public static class Vertex {
        private int x;
        private int y;
        private int degree = 0;

        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override 
        public String toString() {
            return String.format("Vertex(%d, %d)", this.x, this.y);
        }

        public int getX() {
            return this.x;
        }

        public int getY() {
            return this.y;
        }

        public void visit() {
            this.degree++;
        }

        public int getDegree() {
            return this.degree;
        }
    }

    // currently not used.
    private void makeLines(ArrayList<Vertex> current) {
        for (int i=0; i < current.size(); i++ ) {
            for (int j = i+1; j < current.size(); j++) {
                Edge e = new Edge(this.vlist.get(i), this.vlist.get(j));
                this.elist.add(e);
            }
        }
    }
}
