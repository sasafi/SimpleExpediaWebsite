<!DOCTYPE html>
<%@page import="com.expedia.model.Hotel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<html>
<head>
<title>Expedia Hotels</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 200px;
  margin: auto;
  text-align: center;
  font-family: arial;
}

.container {
  padding: 0 16px;
}

.container::after {
  content: "";
  clear: both;
  display: table;
}

.title {
  color: grey;
  font-size: 18px;
}

button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
  font-size: 10px;
}

a {
  text-decoration: none;
  font-size: 10px;
  color: black;
}

h1 {
font-size: 15px;
}

button:hover, a:hover {
  opacity: 0.7;
}

input[type=text],input[type=date], select {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=radio]{
    width: 20%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 50%;
    background-color: blue;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
</style>
</head>
<body>
<%
	 List<Hotel> hotels = new ArrayList<Hotel>();
	 if (request.getAttribute("hotels") != null) {
		 hotels = (List<Hotel>)request.getAttribute("hotels");
	 }
%>
  
  
Expedia Offers:  
<p/>
Use the form below to search for offers, you can specify your length of stay, minimum start date, maximum start date, also, 
you can specify the location, and the minimum hotel rating as required.
In addition to that you can sort the results, on your preferred option. 

  
<form action="FrontController" method="post">

<fieldset>

Length of Stay: 
<select name="lengthOfStay">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
  <option value="6">6</option>
  <option value="7">7</option>
  <option value="8">8</option>
  <option value="9">9</option>
  <option value="10">10</option>
</select>	

Min Trip Start Date:
<input type="date" name="minTripStartDate"/>

Max Trip Start Date: 
<input type="date" name="maxTripStartDate"/>

Destination:
<input type="text" name="destinationName"/>

Minimum Star Rating: 
<select name="minStarRating">
  <option value="1">1</option>
  <option value="2">2</option>
  <option value="3">3</option>
  <option value="4">4</option>
  <option value="5">5</option>
</select>	

Order By:	
<select name="sort">
  <option value="">No-Sort</option>
  <option value="reviewTotal">Number of reviews (Descending)</option>
  <option value="starRating">Star Rating (Descending) </option>
  <option value="guestReviewRating">Guest Review Rating (Descending)</option>
  <option value="averagePriceValue">Average Price Value (Ascending)</option>
  <option value="percentSavings">Percent Savings (Descending)</option>
</select>	
	
	<input type="hidden" name="COMMAND_NAME" value="SEARCH_COMMAND" />
	<input type="submit" name="search" value="Search">
</fieldset>
</form>
<table>

<%
if (hotels != null && hotels.size()>0) {
%>
	<tr>
<%	
	for(int i=0;i<hotels.size();i++){
		
		Hotel hotel = hotels.get(i);
		
		String name = hotel.getName();
		String imageUrl = hotel.getImageUrl();
		String hotelCity = hotel.getHotelCity();
		
		long reviewTotal = hotel.getReviewTotal();
		double starRating = hotel.getStarRating();
		double guestReviewRating = hotel.getGuestReviewRating();
		
		double averagePriceValue = hotel.getAveragePriceValue();
		double originalPricePerNight = hotel.getOriginalPricePerNight();
		double percentSavings = hotel.getPercentSavings();
		
		String almostSoldStatus = hotel.getAlmostSoldStatus();
		String hotelUrl = hotel.getHotelUrl();
				
		if(i%5 == 0 && i!=0){	
	
%>
</tr><tr>
<% } %>
<td>

<div class="card">
  <img src="<%= imageUrl %>" style="width:40%">
  <div class="container">
    <h1><%= name %></h1>
    <h3><%= hotelCity %></h3>
    	
    	<h6>Review Total: <%= reviewTotal%></h6>
    	<h6>Star Rating: <%= starRating%></h6>
    	<h6>Guest Review Rating: <%= guestReviewRating%></h6>
    	
    	<h6>Average Price Value: <%= averagePriceValue%></h6> 
    	<h6>Original Price Per Night: <%= originalPricePerNight%></h6>   	
    	<h6>Percent Savings: <%= percentSavings%>%</h6>
    	
    	<h6>Almost sold out: <%= almostSoldStatus%></h6>
    
    <div style="margin: 24px 0;">
      <a href="<%= hotelUrl %>">Link</a> 
   </div>
  </div>
</div>
		
</td>
		

<%
	}
}
%>


</table>
</body>
</html>