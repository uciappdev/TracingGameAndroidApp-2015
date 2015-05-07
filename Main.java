
public class Main {

    public static void main(String[] args) {
        FleurysAlgorithm path = new FleurysAlgorithm(3);
        for (FleurysAlgorithm.Vertex v : path.completeCircuit) {
            System.out.println(v);
        }
    }
}
