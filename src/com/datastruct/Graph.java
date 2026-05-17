package com.datastruct;
import java.util.*;

class Edge<T> { 
	private T neighbor;
	private int weight;
	
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
	
	@Override
	public String toString() {
		return "(" + neighbor + "," + weight + ")";
	}
}

public class Graph<T> { 
	private Map<T, MyLinearList<Edge<T>>> adj;
	boolean directed;
	
	public Graph (boolean type) { 
        adj = new HashMap<>();
		directed = type;
	}

	public void addEdge(T a, T b, int w) {
		adj.putIfAbsent(a, new MyLinearList<>());
		adj.putIfAbsent(b, new MyLinearList<>());
		Edge<T> edge1 = new Edge<>(b, w);
		adj.get(a).pushQ(edge1);
		if (!directed) {
			Edge<T> edge2 = new Edge<>(a, w);
			adj.get(b).pushQ(edge2);
		}			
	}

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

	public void DFS(T src) {
		System.out.print("DFS: ");
		MyLinearList<T> visited = new MyLinearList<>();
		DFSUtil(src, visited);
		System.out.println();
	}

	private void DFSUtil(T v, MyLinearList<T> visited) {
		visited.pushQ(v);
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

	public void dijkstra(T source, T destination) {
		Map<T, Integer> distances = new HashMap<>();
		Map<T, T> previous = new HashMap<>();
		MyLinearList<T> unvisited = new MyLinearList<>();

		for (T vertex : adj.keySet()) {
			distances.put(vertex, Integer.MAX_VALUE);
			previous.put(vertex, null);
			unvisited.pushQ(vertex);
		}
		
		distances.put(source, 0);

		while (!unvisited.isEmpty()) {
			T u = null;
			int minDistance = Integer.MAX_VALUE;
			Node<T> curr = unvisited.head;
			
			while (curr != null) {
				T vertex = curr.getData();
				int dist = distances.get(vertex);
				if (dist < minDistance) {
					minDistance = dist;
					u = vertex;
				}
				curr = curr.getNext();
			}

			if (u == null) break;

			unvisited.remove(u);

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

		if (distances.get(destination) == Integer.MAX_VALUE) {
			return;
		}

		System.out.println("jarak dari " + source + " ke " + destination + " = " + distances.get(destination));

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