import com.datastruct.*;

class MyVertex {
	String nodeName;
	int orderIndex;

	MyVertex(String name, int orderIndex) {
		this.nodeName = name;
		this.orderIndex = orderIndex;
	}

    @Override
    public String toString() {
        return (nodeName);
    }

    @Override
    public int hashCode() {
        return orderIndex;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MyVertex other = (MyVertex) obj;
        return this.nodeName.equals(other.nodeName);
    }
}

public class GraphMain {
    public static void main(String[] args) {
        MyVertex vE = new MyVertex("E", 0);
        MyVertex vD = new MyVertex("D", 1);
        MyVertex vI = new MyVertex("I", 2);
        MyVertex vC = new MyVertex("C", 3);
        MyVertex vG = new MyVertex("G", 4);
        MyVertex vF = new MyVertex("F", 5);
        MyVertex vB = new MyVertex("B", 6);
        MyVertex vH = new MyVertex("H", 7);
        MyVertex vA = new MyVertex("A", 8);

        Graph<MyVertex> WG = new Graph<MyVertex>(true);

        WG.addEdge(vE, vF, 1);
        WG.addEdge(vE, vH, 1);
        WG.addEdge(vD, vG, 1);
        WG.addEdge(vI, vF, 1);
        WG.addEdge(vC, vB, 1);
        WG.addEdge(vG, vH, 1);
        WG.addEdge(vF, vC, 1);
        WG.addEdge(vF, vH, 1);
        WG.addEdge(vB, vE, 1);
        WG.addEdge(vH, vI, 1);
        WG.addEdge(vA, vB, 1);
        WG.addEdge(vA, vD, 1);
        WG.addEdge(vA, vE, 1);

        System.out.println("Directed Graph:"); 
        WG.printGraph();
        
        System.out.println();
        WG.DFS(vA);
        WG.BFS(vA);
    }
}