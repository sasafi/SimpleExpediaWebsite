
For this website I used Java as my programming language, because I am a Java developer with more than 11 years of experience.

Although, I have some knowledge in other programming languages, but I am comfortable in developing in Java.

In this website, I wanted to give the user the ability to search for Hotels' deals, when the user visits the home page of the website, he will see a form that he can fill in the fields and then hit search.

The user can specify the following:

- Length of Stay
- Min Trip Start Date
- Max Trip Start Date
- Destination 
- Minimum Star Rating

And I added the following function to sort the deals for the user based on what he prefers:

Order By: 
 - Number of reviews (descending): this will order the results by number of total reviews for this hotel starting with the largest number to the smallest.

 - Star Rating (decending): this will order the results by star rating for the hotels starting from the highest rate to the lowest rate.

 - Guest Review Rating (decending): this will order the results by guest review rating for the hotels starting from the highest rate to the lowest rate.

 - Average Price Value (Ascending): this will order the results by average price value for the hotels starting from the lowest rate to the highest value.

 - Percent Saving (decending): This will order the results by Percent saving to indicate how much the user will save if he book this deal.


Display of the results:
I display the results as cards, a card for each hotel with the following information:

Image
Name
city
Review Total
Star rating
Guest review rating
average price value
Original price per night
percent savings
Almost sold out
Hotel page link


Development:

I focused on a scalable architecture, with the famous MVC in my mind.
So I created a Servlet class that acts as a FrontController, with a complete commands layer, that consists of command factory, and the corresponding command hierarchy and helper classes.
Implemented only one command which is the Search Command.



Development time:
5 hours.


Notes:
When I started working with the API, i was not sending the length of stay parameter, and the API was responding with weird results.
For example:
when i send a request without distination nor lenght of stay, and with minimum rating 3, i received 3 results only.
and then when i changed the minimum rating to 4, i recieved 25 results. This confused me a little bit.
 









