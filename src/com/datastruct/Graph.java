package com.datastruct;
/* * Struktur data Graph dengan bobot pada setiap edge
 * sources: https://www.lavivienpost.net/weighted-graph-as-adjacency-list/  
 */

import java.util.*; // Bawaan dari file asli praktikum, biarkan saja

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
	private Map<T, MyLinearList<Edge<T>>> adj;
	boolean directed;
	
	//Constructor, Time O(1) Space O(1)
	public Graph (boolean type) { 
        adj = new HashMap<>(); // Tetap gunakan HashMap bawaan
		directed = type; // false: undirected, true: directed
	}

    //Add edges including adding nodes, Time O(1) Space O(1)
	public void addEdge(T a, T b, int w) {
		adj.putIfAbsent(a, new MyLinearList<>()); //add node
		adj.putIfAbsent(b, new MyLinearList<>()); //add node
		Edge<T> edge1 = new Edge<>(b, w);
		adj.get(a).pushQ(edge1); //add edge
		if (!directed) { //undirected
			Edge<T> edge2 = new Edge<>(a, w);
			adj.get(b).pushQ(edge2);
		}			
	}

    //Print graph as hashmap, Time O(V+E), Space O(1)
	public void printGraph() {
		for (T key: adj.keySet()) {
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

    // HELPER METHOD: Mengecek apakah node sudah di-visit menggunakan MyLinearList
    private boolean isVisited(MyLinearList<T> visitedList, T node) {
        Node<T> curr = visitedList.head;
        while(curr != null) {
            if(curr.getData().equals(node)) {
                return true;
            }
            curr = curr.getNext();
        }
        return false;
    }

	// DFS
	public void DFS(T src) {
		System.out.print("DFS: ");
		MyLinearList<T> visited = new MyLinearList<>();
		DFSUtil(src, visited);
		System.out.println();
	}

	private void DFSUtil(T v, MyLinearList<T> visited) {
		visited.pushQ(v); // Tandai sebagai visited
		System.out.print(v + " ");
		
		MyLinearList<Edge<T>> list = adj.get(v);
		if(list != null) {
			Node<Edge<T>> curr = list.head;
			while(curr != null) {
				T neighbor = curr.getData().getNeighbor();
				if(!isVisited(visited, neighbor)) {
					DFSUtil(neighbor, visited);
				}
				curr = curr.getNext();
			}
		}
	}

	// BFS
	public void BFS(T src) { 
		System.out.print("BFS: ");
		MyLinearList<T> visited = new MyLinearList<>();
		MyLinearList<T> queue = new MyLinearList<>(); 
		
		visited.pushQ(src);
		queue.pushQ(src);
		
		while(!queue.isEmpty()) {
			T v = queue.remove();
			System.out.print(v + " ");
			
			MyLinearList<Edge<T>> list = adj.get(v);
			if(list != null) {
				Node<Edge<T>> curr = list.head;
				while(curr != null) {
					T neighbor = curr.getData().getNeighbor();
					if(!isVisited(visited, neighbor)) {
						visited.pushQ(neighbor);
						queue.pushQ(neighbor);
					}
					curr = curr.getNext();
				}
			}
		}
		System.out.println();
	}
}