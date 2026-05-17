/*
Kelompok 7
Anggota: 
1. 535230092 - Sammy Ferdinand
2. 535250064 - Ivan Ozora Nelson Kho
3. 535250094 - Kenny Martin Holiem
4. 535250097 - Diana
 */

import com.datastruct.*;

class MyVertex{
	String nodeName;
	MyVertex(String name)
	{
		this.nodeName = name;
	}

    @Override
    public String toString() {
        return (nodeName);
    }
}


public class GraphMain {
    public static void main(String[] args) {
        //create vertex dari V0 sampai V6 (slide 6)
        MyVertex v0 = new MyVertex("V0");
        MyVertex v1 = new MyVertex("V1");
        MyVertex v2 = new MyVertex("V2");
        MyVertex v3 = new MyVertex("V3");
        MyVertex v4 = new MyVertex("V4");
        MyVertex v5 = new MyVertex("V5");
        MyVertex v6 = new MyVertex("V6");

        Graph<MyVertex> WG = new Graph<MyVertex>(true); // directed graph (digraph)
        
        // Memasukkan unweighted edges, diasumsikan bobot/jarak masing-masing = 1
        WG.addEdge(v0, v1, 1);
        WG.addEdge(v0, v3, 1);
        WG.addEdge(v1, v3, 1);
        WG.addEdge(v1, v4, 1);
        WG.addEdge(v2, v0, 1);
        WG.addEdge(v2, v5, 1);
        WG.addEdge(v3, v2, 1);
        WG.addEdge(v3, v4, 1);
        WG.addEdge(v3, v5, 1);
        WG.addEdge(v3, v6, 1);
        WG.addEdge(v4, v6, 1);
        WG.addEdge(v6, v5, 1);

        // Langsung tampilkan hasil algoritma Dijkstra tanpa mencetak hashmap graf 
        WG.dijkstra(v2, v6);
    }
}

/*
=== Output ===
jarak dari V2 ke V6 = 3
Path: V2 --> V0 --> V3 --> V6
 */