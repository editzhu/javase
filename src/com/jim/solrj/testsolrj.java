package com.jim.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class testsolrj {
    public static void main(String[] args) throws Exception {
	String url = "http://10.0.2.16:8081/solr";
	SolrServer server = new HttpSolrServer(url);
	SolrQuery query = new SolrQuery();
	query.setQuery("java");
	query.setRows(20);
	QueryResponse response = server.query(query);
	SolrDocumentList docs = response.getResults();
	System.out.println("文档个数：" + docs.getNumFound());
	System.out.println("查询时间：" + response.getQTime());
	for (SolrDocument doc : docs) {
	    // System.out.println("id: " + doc.getFieldValue("id"));
	    System.out.println("title: " + doc.getFieldValue("title"));
	    // System.out.println("content: " + doc.getFieldValue("DOCS"));
	    // System.out.println();
	}
    }
}
