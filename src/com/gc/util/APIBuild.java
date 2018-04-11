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

	public static JSONObject ebayAPI(int pageNum) throws ClientProtocolException, IOException {
		HttpClient http = HttpClientBuilder.create().build();
		HttpHost host = new HttpHost("svcs.ebay.com", 80, "http");
		HttpGet getPage = new HttpGet("/services/search/FindingService/v1?OPERATION-NAME="
				+ "findCompletedItems&SERVICE-VERSION=1.7.0&SECURITY-APPNAME=" + APICredentials.EBAYAPI_KEY + "&RESPONSE-DATA-FORMAT=JSON&REST-PAYLOAD&categoryId=176985&itemFilter(0)."
				+ "name=SoldItemsOnly&itemFilter(0).value=true&itemFilter(1).name=MinPrice&itemFilter(1)."
				+ "value=50.00&itemFilter(2).name=EndTimeFrom&itemFilter(2).value=2018-04-09T19:09:02.768Z&itemFilter(3)"
				+ ".name=EndTimeTo$itemFilter(3).value=2018-04-10T19:09:02.768Z&paginationInput.pageNumber=" + pageNum);
		HttpResponse resp = http.execute(host, getPage);
		String jsonString = EntityUtils.toString(resp.getEntity());
		JSONObject json = new JSONObject(jsonString);
		return json;
	}
}
