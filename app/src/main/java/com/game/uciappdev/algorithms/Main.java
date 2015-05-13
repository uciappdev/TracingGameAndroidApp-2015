
public class Main {

    public static void main(String[] args) {
        // testing intersects
//      FleurysAlgorithm.Vertex a1 = createVertex(2, 0);
//      FleurysAlgorithm.Vertex a2 = createVertex(0, 1);
//      FleurysAlgorithm.Vertex b1 = createVertex(1, 0);
//      FleurysAlgorithm.Vertex b2 = createVertex(1, 1);
//      FleurysAlgorithm.Edge a = new FleurysAlgorithm.Edge(a1,a2);
//      FleurysAlgorithm.Edge b = new FleurysAlgorithm.Edge(b1,b2);
//      boolean bool = FleurysAlgorithm.intersects(a, b);
//      System.out.println(bool);
        
        FleurysAlgorithm path = new FleurysAlgorithm(3);
        for (FleurysAlgorithm.Vertex v : path.completeCircuit) {
            System.out.println(v);
        }

    }
    
    // for testing. to be deleted.
    public static FleurysAlgorithm.Vertex createVertex(int x, int y) {
        return new FleurysAlgorithm.Vertex(x, y);
    }
    
}

