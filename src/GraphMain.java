/*
Kelompok 7
Anggota: 
1. 535230092 - Sammy Ferdinand
2. 535250064 - Ivan Ozora Nelson Kho
3. 535250094 - Kenny Martin Holiem
4. 535250097 - Diana
 */

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
        MyVertex v0 = new MyVertex("V0", 0);
        MyVertex v1 = new MyVertex("V1", 1);
        MyVertex v2 = new MyVertex("V2", 2);
        MyVertex v3 = new MyVertex("V3", 3);
        MyVertex v4 = new MyVertex("V4", 4);
        MyVertex v5 = new MyVertex("V5", 5);
        MyVertex v6 = new MyVertex("V6", 6);

        Graph<MyVertex> WG = new Graph<MyVertex>(true);

        WG.addEdge(v2, v0, 1);
        WG.addEdge(v2, v5, 1);
        WG.addEdge(v0, v1, 1);
        WG.addEdge(v0, v3, 1);
        WG.addEdge(v1, v3, 1);
        WG.addEdge(v1, v4, 1);
        WG.addEdge(v3, v2, 1);
        WG.addEdge(v3, v4, 1);
        WG.addEdge(v3, v5, 1);
        WG.addEdge(v3, v6, 1);
        WG.addEdge(v4, v6, 1);
        WG.addEdge(v6, v5, 1);
        
        WG.dijkstra(v2, v6);
    }
}

/*
=== Output ===
jarak dari V2 ke V6 = 3
Path: V2 --> V0 --> V3 --> V6
 */