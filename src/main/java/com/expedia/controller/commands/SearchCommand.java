package com.expedia.controller.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.expedia.data.DataTransfer;
import com.expedia.model.Hotel;
import com.expedia.utils.Constants;

/**
 * This command is responsible for searching for Hotel Deals, depending on parameters sent.
 * Also, after retrieving the hotel deals, it will sort them based on the user preference.
 * 
 * @author sasafi
 *
 */
public class SearchCommand extends CommandBase {

	
	private final String RESULTS_PAGE = "index.jsp";

	private List<String> searchParams;

	public SearchCommand() {
		searchParams = new ArrayList<String>();

		searchParams.add(Constants.MIN_TRIP_START_DATE);
		searchParams.add(Constants.MAX_TRIP_START_DATE);
		searchParams.add(Constants.DESTINATION_NAME);
		searchParams.add(Constants.MIN_STAR_RATING);
		searchParams.add(Constants.LENGTH_OF_STAY);

	}

	/**
	 * Command execute method.
	 */
	@Override
	public CommandResult execute(DataTransfer ds) {

		List<Hotel> hotels = getHotels(ds);

		CommandResult commandResult = new CommandResult();

		commandResult.setAttribute(Constants.HOTELS, hotels);

		commandResult.setPath(RESULTS_PAGE);

		return commandResult;

	}

	/**
	 * Consult Expedia APIs to get the corresponding deals.
	 * @param ds - DataTransfer Object that is used to get parameters from http request and pass them to the command tier.
	 * @return - a list of Hotel objects that represents the matching deals
	 */
	private List<Hotel> getHotels(DataTransfer ds) {
		List<Hotel> hotels = new ArrayList();

		try {
			HttpClient client = HttpClientBuilder.create().build();

			String url = generateURL(ds);
			HttpGet request = new HttpGet(url);

			HttpResponse response = client.execute(request);

			if (response != null && response.getStatusLine().getStatusCode() == 200) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuilder result = new StringBuilder();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				
				
				JSONObject jsonResult = new JSONObject(result.toString());
				JSONArray jsonArray = jsonResult.getJSONObject(Constants.OFFERS).getJSONArray(Constants.HOTEL);

				for (int i = 0; i < jsonArray.length(); i++) {
					JSONObject jsonData = jsonArray.getJSONObject(i);

					Hotel hotel = new Hotel();
					JSONObject hotelInfo = jsonData.getJSONObject(Constants.HOTEL_INFO);

					if (hotelInfo != null) {

						hotel.setName(hotelInfo.getString(Constants.HOTEL_NAME));
						hotel.setImageUrl(hotelInfo.getString(Constants.HOTEL_IMAGE_URL));
						hotel.setHotelCity(hotelInfo.getString(Constants.HOTEL_CITY));
						

						hotel.setStarRating(hotelInfo.getDouble(Constants.HOTEL_STAR_RATING));
						hotel.setGuestReviewRating(hotelInfo.getDouble(Constants.HOTEL_GUEST_REVIEW_RATING));
						hotel.setReviewTotal(hotelInfo.getLong(Constants.HOTEL_REVIEW_TOTAL));

					}

					JSONObject hotelPricingInfo = jsonData.getJSONObject(Constants.HOTEL_PRICING_INFO);
					if (hotelPricingInfo != null) {

						hotel.setAveragePriceValue(hotelPricingInfo.getDouble(Constants.AVERAGE_PRICE_VALUE));
						hotel.setOriginalPricePerNight(hotelPricingInfo.getDouble(Constants.ORIGINAL_PRICE_PER_NIGHT));
						hotel.setPercentSavings(hotelPricingInfo.getDouble(Constants.PERCENT_SAVINGS));

					}

					JSONObject hotelUrgencyInfo = jsonData.getJSONObject(Constants.HOTEL_URGENCY_INFO);
					if (hotelUrgencyInfo != null) {

						hotel.setAlmostSoldStatus(hotelUrgencyInfo.getString(Constants.ALMOST_SOLD_STATUS));

					}
					
					JSONObject hotelUrls = jsonData.getJSONObject(Constants.HOTEL_URLS);
					if (hotelUrls != null) {
						
						String hotelUrl = hotelUrls.getString(Constants.HOTEL_INFOSITE_URL);
						hotel.setHotelUrl(URLDecoder.decode(hotelUrl, Constants.UTF8));
					}

					hotels.add(hotel);

				}
				sortHotels(hotels, ds);

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return hotels;

	}	

	/**
	 * Sort the hotels based on the value of the field "order by"
	 * @param hotels
	 * @param ds
	 */
	private void sortHotels(List<Hotel> hotels, DataTransfer ds) {
		
		String sortValue = ds.getParameter(Constants.SORT);

		Comparator<Hotel> c = null;

		if (sortValue == null || sortValue.equals(""))
			return;
		 		
		if (sortValue.equals(Constants.AVERAGE_PRICE_VALUE)) {
			c = Hotel.averagePriceValueComp;
		} else if (sortValue.equals(Constants.REVIEW_TOTAL)) {
			c = Hotel.reviewTotalComp;
		} else if (sortValue.equals(Constants.STAR_RATING)) {
			c = Hotel.starRatingComp;
		} else if (sortValue.equals(Constants.GUEST_REVIEW_RATING)) {
			c = Hotel.guestReviewRatingComp;
		} else if (sortValue.equals(Constants.PERCENT_SAVINGS)) {
			c = Hotel.percentSavingsComp;
		} else {
			return;
		}
		
		
		Collections.sort(hotels, c);
	}

	/**
	 * Generate URL to be used to connect to expedia APIs
	 * @param ds
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String generateURL(DataTransfer ds) throws UnsupportedEncodingException {
		StringBuilder url = new StringBuilder();
		url.append(Constants.API_URL);

		Set<String> keys = ds.getParamNames();

		for (String key : keys) {
			if (searchParams.contains(key) && ds.getParameter(key) != null && !ds.getParameter(key).equals("")) {
				url.append(String.format(Constants.NEW_PARAM, key, URLEncoder.encode(ds.getParameter(key), Constants.UTF8)));
			}
		}
		
		System.out.println(url.toString());
		return url.toString();
	}

}
