package com.gc.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gc.dao.RecordDao;
import com.gc.dao.RecordDaoImp;
import com.gc.model.Record;
import com.gc.util.APIBuild;
import com.gc.util.APICredentials;

/*
 * @author: Nicholas Soule, Andrew Calabro-Cavin
 *
 */

@Controller

public class HomeController {

	@RequestMapping("/")
	public String homePage() {
		return "index";
	}

	@RequestMapping("/update")
	public String registerForm(Model model, @RequestParam("month") String month, @RequestParam("day1") String day1,
			@RequestParam("year") String year) {
		String pageNum = "0";
		int i = 1;
		ArrayList<String> idArray = new ArrayList<String>();
		ArrayList<Record> recArray = new ArrayList<Record>();

		try {

			JSONObject json = APIBuild.ebayAPI(i, month, day1, year);

			pageNum = json.getJSONArray("findCompletedItemsResponse").getJSONObject(0).getJSONArray("paginationOutput")
					.getJSONObject(0).getJSONArray("totalPages").get(0).toString();
			model.addAttribute("page", pageNum);

			for (int j = 1; j < Integer.parseInt(pageNum); j++) {
				JSONObject jsonID = APIBuild.ebayAPI(j, month, day1, year);
				int resultNum = Integer.parseInt(jsonID.getJSONArray("findCompletedItemsResponse").getJSONObject(0)
						.getJSONArray("searchResult").getJSONObject(0).get("@count").toString());
				for (int k = 0; k < resultNum; k++) {
					idArray.add(jsonID.getJSONArray("findCompletedItemsResponse").getJSONObject(0)
							.getJSONArray("searchResult").getJSONObject(0).getJSONArray("item").getJSONObject(k)
							.getJSONArray("itemId").get(0).toString());
				}
			}

			System.out.println(idArray);

			for (int m = 0; m <= 3; m++) {
				System.out.println(idArray.get(m));
				JSONObject jRec = APIBuild.getRecordInfo(idArray.get(m).toString());
				System.out.println(jRec.toString());
				Record rec = new Record();
				rec.setTitle(jRec.getJSONObject("Item").get("Title").toString());
				String description = jRec.getJSONObject("Item").get("Description").toString();
				if (description.length() > 5000) {
					description = description.substring(0, 4999);
				}
				rec.setBody(description);
				rec.setDate(jRec.getJSONObject("Item").get("EndTime").toString());
				rec.setPrice(jRec.getJSONObject("Item").getJSONObject("ConvertedCurrentPrice").get("Value").toString());
				rec.setImage(jRec.getJSONObject("Item").getJSONArray("PictureURL").get(0).toString());

				RecordDaoImp dao = new RecordDaoImp();
				dao.addRec(rec);
			}	
			

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		idArray.clear();
		return "index";
	}

}
