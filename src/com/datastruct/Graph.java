package com.datastruct;
/* * Struktur data Graph dengan bobot pada setiap edge
 * sources: https://www.lavivienpost.net/weighted-graph-as-adjacency-list/  
 * */

import java.util.*;

class Edge<T> { 
	private T neighbor; //connected vertex
	private int weight; //weight
	
	//Constructor, Time O(1) Space O(1)
	public Edge(T v, int w) {
		this.neighbor = v; 
		this.weight = w;
	}

	public void setNeighbor(T neighbor) {
		this.neighbor = neighbor;
	}
	public T getNeighbor() {
		return neighbor;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getWeight() {
		return weight;
	}
	
	//Time O(1) Space O(1)
	@Override
	public String toString() {
		return "(" + neighbor + "," + weight + ")";
	}
}

public class Graph<T> { 
    //Map<T, LinkedList<Edge<T>>> adj;
	private Map<T, MyLinearList<Edge<T>>> adj;
	boolean directed;
	
	//Constructor, Time O(1) Space O(1)
	public Graph (boolean type) { 
        adj = new HashMap<>();
		directed = type; // false: undirected, true: directed
	}

    //Add edges including adding nodes, Time O(1) Space O(1)
	public void addEdge(T a, T b, int w) {
		adj.putIfAbsent(a, new MyLinearList<>()); //add node
		adj.putIfAbsent(b, new MyLinearList<>()); //add node
		Edge<T> edge1 = new Edge<>(b, w);
		adj.get(a).pushQ(edge1);//add(edge1); //add edge
		if (!directed) { //undirected
			Edge<T> edge2 = new Edge<>(a, w);
			adj.get(b).pushQ(edge2);
		}			
	}

    //Print graph as hashmap, Time O(V+E), Space O(1)
	public void printGraph() {
		for (T key: adj.keySet()) {
			//System.out.println(key.toString() + " : " + adj.get(key).toString());
            System.out.print(key.toString() + " : ");
			MyLinearList<Edge<T>> thelist = adj.get(key);
			Node<Edge<T>> curr = thelist.head;
			while(curr != null) {
				System.out.print(curr.getData());
				curr = curr.getNext();
			}
			System.out.println();
		}
	}

	//DFS 
	public void DFS(T src) {
		
	}

	//BFS
	public void BFS(T src) { 
		 
	}

	// Method yang ditambahkan untuk tugas Praktikum 11: Dijkstra Shortest Path
	public void dijkstra(T source, T destination) {
		Map<T, Integer> distances = new HashMap<>();
		Map<T, T> previous = new HashMap<>();
		MyLinearList<T> unvisited = new MyLinearList<>();

		// 1. Inisialisasi jarak awal
		for (T vertex : adj.keySet()) {
			distances.put(vertex, Integer.MAX_VALUE);
			previous.put(vertex, null);
			unvisited.pushQ(vertex);
		}
		distances.put(source, 0);

		// 2. Loop pencarian menggunakan MyLinearList sebagai pengganti PriorityQueue
		while (!unvisited.isEmpty()) {
			T u = null;
			int minDistance = Integer.MAX_VALUE;
			Node<T> curr = unvisited.head;
			
			// Mencari node dengan bobot terendah secara manual
			while (curr != null) {
				T vertex = curr.getData();
				int dist = distances.get(vertex);
				if (dist < minDistance) {
					minDistance = dist;
					u = vertex;
				}
				curr = curr.getNext();
			}

			// Jika vertex tujuan tak terjangkau, hentikan
			if (u == null) break;

			unvisited.remove(u);

			// 3. Relaksasi edge/tetangga
			MyLinearList<Edge<T>> neighbors = adj.get(u);
			if (neighbors != null) {
				Node<Edge<T>> edgeNode = neighbors.head;
				while (edgeNode != null) {
					Edge<T> edge = edgeNode.getData();
					T v = edge.getNeighbor();
					int weight = edge.getWeight();

					if (distances.get(u) != Integer.MAX_VALUE) {
						int alt = distances.get(u) + weight;
						if (alt < distances.get(v)) {
							distances.put(v, alt);
							previous.put(v, u);
						}
					}
					edgeNode = edgeNode.getNext();
				}
			}
		}

		// 4. Proses format dan cetak sesuai dengan output yang diminta (slide 50 / file gambar)
		if (distances.get(destination) == Integer.MAX_VALUE) {
			return; // Hindari cetak jika tidak ada rute
		}

		System.out.println("jarak dari " + source + " ke " + destination + " = " + distances.get(destination));

		// Gunakan MyLinearList sebagai Stack (pushS) untuk merunut jalur terbalik
		MyLinearList<T> path = new MyLinearList<>();
		T currNode = destination;
		while (currNode != null) {
			path.pushS(currNode); 
			currNode = previous.get(currNode);
		}

		System.out.print("Path: ");
		while (!path.isEmpty()) {
			System.out.print(path.remove());
			if (!path.isEmpty()) System.out.print(" --> ");
		}
		System.out.println();
	}
}