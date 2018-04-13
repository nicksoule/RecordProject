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

import com.gc.model.Person;
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
	public String registerForm(Model model, @RequestParam("month") String month, @RequestParam("day1") 
	String day1, @RequestParam("day2") String day2, @RequestParam("year") String year) {
		String pageNum = "0";
		int i = 1;
		ArrayList<String> idArray = new ArrayList<String>();

		try {

			JSONObject json = APIBuild.ebayAPI(i, month, day1, day2, year);

			pageNum = json.getJSONArray("findCompletedItemsResponse").getJSONObject(0).getJSONArray("paginationOutput")
					.getJSONObject(0).getJSONArray("totalPages").get(0).toString();
			model.addAttribute("page", pageNum);

			for (int j = 1; j < Integer.parseInt(pageNum); j++) {
				JSONObject jsonID = APIBuild.ebayAPI(j, month, day1, day2, year);
				int resultNum = Integer.parseInt(jsonID.getJSONArray("findCompletedItemsResponse").getJSONObject(0)
						.getJSONArray("searchResult").getJSONObject(0).get("@count").toString());
				for (int k = 0; k < resultNum; k++) {
					idArray.add(jsonID.getJSONArray("findCompletedItemsResponse").getJSONObject(0)
							.getJSONArray("searchResult").getJSONObject(0).getJSONArray("item").getJSONObject(k)
							.getJSONArray("itemId").get(0).toString());
				}
			}

			System.out.println(idArray);

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "index";
	}

	@RequestMapping(value = "success", method = RequestMethod.POST)
	// this method call will process the data from the form and push it back to the
	// success.jsp
	public ModelAndView registerSuccess(@RequestParam("fName") String firstName, @RequestParam("lName") String lastName,
			@RequestParam("gender") String gender, @RequestParam("phone") String phone,
			@RequestParam("favorites") String checkbox, Model model) { // added model to be able to pass my arraylist in
																		// later

		Person p = new Person(firstName, lastName, phone, gender, checkbox.split(","));

		String sayHello = "Hello, " + p.getFirstName() + " " + p.getGender() + " " + p.getFavorites()[0];

		// Example showing ArrayList push to front end using JSTL to loop through
		ArrayList<Person> personList = new ArrayList<Person>();
		personList.add(new Person("Antonella", "Solomon", "313.555.1212", "female", "Decaf,Mocha".split(",")));
		personList.add(new Person("Cara", "Test", "313.555.3312", "female", "Decaf,Chocolate".split(",")));
		personList.add(new Person("James", "test2", "313.553.1212", "male", "Marshmallows,Mocha".split(",")));
		personList.add(new Person("Ron", "Test3", "313.545.1212", "male", "Decaf,Mocha".split(",")));
		personList.add(new Person("Zach", "Test4", "313.655.1215", "female", "Decaf,Mocha".split(",")));
		personList.add(new Person("Ben", "Test5", "313.555.1212", "male", "Decaf,Regular".split(",")));
		model.addAttribute("pList", personList); // this is used to pass the arraylist into the jsp page

		// first parameter : the name of the jsp
		// second parameter : name of the EL tag variable name
		// third parameter : object or data that should go back into our jsp page
		return new ModelAndView("success", "helloMsg", sayHello);
	}
}
