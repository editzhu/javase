package com.jim.boot.algorithm.c04;

import java.util.ArrayList;
import java.util.List;

/*
 * 图
 */
public class Graph {

	private int V;// 顶点的数目
	private int E;// 边的数目
	private Bag[] adj;

	public Graph(gIn gIn) {
		adj = new Bag[100];
		// for(int v=0;v<10;v++){
		// adj[v] = new Bag();
		// System.out.println("v:"+v);
		// }
		System.out.println(gIn.get());
		for (String s : gIn.get()) {
			String[] ss = s.split(",");
			// adj[Integer.parseInt(ss[0])] = new Bag();
			for (int i = 0; i < 100; i++)
				adj[i] = new Bag();
			addEdge(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]));
		}
		// for(Map.Entry<Integer,Integer> m:gIn.get().entrySet()){
		// addEdge(m.getKey(), m.getValue());
		// }

	}

	// 创建一个只有顶点的图
	Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Bag[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag();
	}

	int V() {
		return V;
	}// 图的顶点

	int E() {
		return E;
	}// 图的边

	void addEdge(int v, int w) {
		System.out.println(v + ":" + w);
		// System.out.println(w);
		// System.out.println(adj[v]);
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}// 创建一条连接v和w的边 v-w

	List<Integer> getPoint(int v) {
		List<Integer> a = adj[v].get();
		System.out.println("adj[v].get()" + adj[v].get());
		return a;
	}// 返回顶点v的所有相邻顶点

	public static int degree(Graph G, int v) {
		int degree = 0;
		for (int w : G.getPoint(v))
			degree++;
		return degree;
	}// 计算v的度数

	public static int maxDrgree(Graph G) {
		int max = 0;
		for (int v = 0; v < G.V(); v++)
			if (degree(G, v) > max)
				max = degree(G, v);
		return max;
	}// 计算所有顶点中的最大度数

	public static double avgDrgree(Graph G) {
		return 2 * G.E() / G.V();
	}// 计算平均度数

	public static int numberOfSelfLoops(Graph G) {
		int count = 0;
		for (int v = 0; v < G.V(); v++)
			for (int w : G.getPoint(v))
				if (v == w)
					count++;
		return count / 2;
	}// 计算自环的个数

	public String toString() {
		String s = V + " vertices, " + E + " edges\n";
		for (int v = 0; v < V; v++) {
			s += v + ": ";
			for (int w : this.getPoint(v))
				s += w + " ";
			s += "\n";
		}
		return s;
	}// 用临接表的字符串表示（Graph的实例方法）

	public static void main(String[] args) {
		Graph graph = new Graph(new gIn());
		System.out.println(degree(graph, 0));
		System.out.println("E()=" + graph.E());
		System.out.println("V()=" + graph.V());
	}

}

class Bag {
	List<Integer> list = new ArrayList<Integer>();

	void add(Integer i) {
		list.add(i);
		System.out.println("list.add(i)" + list);
	}

	List<Integer> get() {
		System.out.println("list" + list);
		return list;
	}
}

class gIn {
	private static List<String> s = new ArrayList<String>();

	gIn() {

		s.add("0,1");
		s.add("0,2");
		s.add("0,5");
		s.add("0,6");
		s.add("1,0");
		s.add("2,0");
		s.add("3,4");
		s.add("3,5");
		s.add("4,3");
		s.add("4,5");
		s.add("4,6");
		s.add("5,0");
		s.add("5,3");
		s.add("5,4");
		s.add("6,0");
		s.add("6,4");
		System.out.println(s);
	}

	// private static Map<Integer,Integer> map = new
	// IdentityHashMap<Integer,Integer>();
	// gIn(){
	// map.put(0,1 );
	// map.put( 0,2 );
	// map.put(0,5);
	// map.put( 0,6 );
	// map.put( 3,4 );
	// map.put( 3,5 );
	// map.put( 4,5 );
	// map.put( 4,6 );
	// System.out.println(map);
	// }
	//
	List<String> get() {
		return s;
	}

}
