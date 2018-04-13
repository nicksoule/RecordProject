package com.gc.util;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class APIBuild {

	public static JSONObject ebayAPI(int pageNum, String month, String day1, String year)
			throws ClientProtocolException, IOException {
		HttpClient http = HttpClientBuilder.create().build();
		HttpHost host = new HttpHost("svcs.ebay.com", 80, "http");
		HttpGet getPage = new HttpGet("/services/search/FindingService/v1?OPERATION-NAME="
				+ "findCompletedItems&SERVICE-VERSION=1.7.0&SECURITY-APPNAME=" + APICredentials.EBAYAPI_KEY
				+ "&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&categoryId=176985&itemFilter(0)."
				+ "name=SoldItemsOnly&itemFilter(0).value=true&itemFilter(1).name=MinPrice&itemFilter(1)."
				+ "value=50.00&itemFilter(2).name=EndTimeFrom&itemFilter(2).value=" + year + "-" + month + "-" + day1
				+ "T19:09:02.768Z&paginationInput.pageNumber=" + pageNum);
		HttpResponse resp = http.execute(host, getPage);
		String jsonString = EntityUtils.toString(resp.getEntity());
		JSONObject json = new JSONObject(jsonString);
		return json;
	}
	
	public static JSONObject getRecordInfo(String id) throws ClientProtocolException, IOException {
		HttpClient http = HttpClientBuilder.create().build();
		HttpHost host = new HttpHost("open.api.ebay.com", 80, "http");
		HttpGet getPage = new HttpGet("shopping?callname=GetSingleItem&responseencoding=JSON&appid=" 
				+ APICredentials.EBAYAPI_KEY + "&siteid=0&version=967&ItemID=" 
				+ id + "IncludeSelector=Description");
		HttpResponse resp = http.execute(host, getPage);
		String jsonString = EntityUtils.toString(resp.getEntity());
		JSONObject json = new JSONObject(jsonString);
		return json;
	}
}
