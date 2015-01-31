package com.jim.boot.algorithm.c04;

import java.io.File;
/*
 * 图
 */
public class Graph {
	private final int V;//顶点的数目
	private final int E;//边的数目
	private Bag<Integer>[] adj;
		
	//创建一个只有顶点的图
	public void Graph(int V){
		this.V=V;
		this.E=0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v=0;v<V;v++)
			adj[v] = new Bag<Integer>();
	}
	
	//从文件中创建图
	public void Graph(File in){
		
	}
	
	int V(){
		return V;}//图的顶点
	int E(){
		return E;}//图的边
	
	void addEdge(int v ,int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}//创建一条连接v和w的边 v-w
	Iterable<Integer> adj(int v){
		return null;}//返回顶点v的所有相邻顶点
	public String toString11(){
		return null;}//备用未知
	
	public static int degree(Graph G,int v){
		int degree = 0;
		for (int w:G.adj(v))degree++;
		return degree;
	}//计算v的度数
	
	public static int maxDrgree(Graph G){
		int max =0;
		for(int v=0;v<G.V();v++)
			if (degree(G,v)>max)
				max=degree(G,v);
		return max;
	}//计算所有顶点中的最大度数
	
	public static double avgDrgree(Graph G){
		return 2*G.E()/G.V();
	}//计算平均度数
	
	public static int numberOfSelfLoops(Graph G){
		int count = 0;
		for(int v=0;v<G.V();v++)
			for (int w:G.adj(v))
				if(v==w)count++;
		return count/2;
	}//计算自环的个数
	
	public String toString(){
		return null;
	}//用临接表的字符串表示（Graph的实例方法）
	
	
}

private class Bag(){
}
