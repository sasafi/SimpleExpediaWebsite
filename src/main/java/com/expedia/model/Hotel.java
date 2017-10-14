package com.expedia.model;

import java.util.Comparator;

/**
 * Hotel class, Objects of this class represents hotel deals return by expedia APIs
 * @author sasafi
 *
 */
public class Hotel {

	
	private String name;
	private String imageUrl;
	private String hotelCity;

	private long reviewTotal;
	private double starRating;
	private double guestReviewRating;

	private double averagePriceValue;
	private double originalPricePerNight;
	private double percentSavings;

	private String almostSoldStatus;

	private String hotelUrl;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @param imageUrl
	 *            the imageUrl to set
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	/**
	 * @return the hotelCity
	 */
	public String getHotelCity() {
		return hotelCity;
	}

	/**
	 * @param hotelCity
	 *            the hotelCity to set
	 */
	public void setHotelCity(String hotelCity) {
		this.hotelCity = hotelCity;
	}

	/**
	 * @return the reviewTotal
	 */
	public long getReviewTotal() {
		return reviewTotal;
	}

	/**
	 * @param reviewTotal
	 *            the reviewTotal to set
	 */
	public void setReviewTotal(long reviewTotal) {
		this.reviewTotal = reviewTotal;
	}

	/**
	 * @return the starRating
	 */
	public double getStarRating() {
		return starRating;
	}

	/**
	 * @param starRating
	 *            the starRating to set
	 */
	public void setStarRating(double starRating) {
		this.starRating = starRating;
	}

	/**
	 * @return the guestReviewRating
	 */
	public double getGuestReviewRating() {
		return guestReviewRating;
	}

	/**
	 * @param guestReviewRating
	 *            the guestReviewRating to set
	 */
	public void setGuestReviewRating(double guestReviewRating) {
		this.guestReviewRating = guestReviewRating;
	}

	/**
	 * @return the averagePriceValue
	 */
	public double getAveragePriceValue() {
		return averagePriceValue;
	}

	/**
	 * @param averagePriceValue
	 *            the averagePriceValue to set
	 */
	public void setAveragePriceValue(double averagePriceValue) {
		this.averagePriceValue = averagePriceValue;
	}

	/**
	 * @return the originalPricePerNight
	 */
	public double getOriginalPricePerNight() {
		return originalPricePerNight;
	}

	/**
	 * @param originalPricePerNight
	 *            the originalPricePerNight to set
	 */
	public void setOriginalPricePerNight(double originalPricePerNight) {
		this.originalPricePerNight = originalPricePerNight;
	}

	/**
	 * @return the percentSavings
	 */
	public double getPercentSavings() {
		return percentSavings;
	}

	/**
	 * @param percentSavings
	 *            the percentSavings to set
	 */
	public void setPercentSavings(double percentSavings) {
		this.percentSavings = percentSavings;
	}

	/**
	 * @return the almostSoldStatus
	 */
	public String getAlmostSoldStatus() {
		return almostSoldStatus;
	}

	/**
	 * @param almostSoldStatus
	 *            the almostSoldStatus to set
	 */
	public void setAlmostSoldStatus(String almostSoldStatus) {
		this.almostSoldStatus = almostSoldStatus;
	}

	/**
	 * @return the hotelUrl
	 */
	public String getHotelUrl() {
		return hotelUrl;
	}

	/**
	 * @param hotelUrl
	 *            the hotelUrl to set
	 */
	public void setHotelUrl(String hotelUrl) {
		this.hotelUrl = hotelUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Hotel [name=" + name + ", imageUrl=" + imageUrl + ", hotelCity=" + hotelCity + ", reviewTotal="
				+ reviewTotal + ", starRating=" + starRating + ", guestReviewRating=" + guestReviewRating
				+ ", averagePriceValue=" + averagePriceValue + ", originalPricePerNight=" + originalPricePerNight
				+ ", percentSavings=" + percentSavings + ", almostSoldStatus=" + almostSoldStatus + ", hotelUrl="
				+ hotelUrl + "]";
	}


	/**
	 * Comparator used to order hotels objects based on average price ascending.
	 */
	public static Comparator<Hotel> averagePriceValueComp = new Comparator<Hotel>() {

		public int compare(Hotel h1, Hotel h2) {

			double price1 = h1.getAveragePriceValue();
			double price2 = h2.getAveragePriceValue();

			/* For ascending order */

			if (price1 < price2) {
				return -1;
			} else if (price1 == price2) {
				return 0;
			} else {
				return 1;
			}

		}
	};

	/**
	 * Comparator used to order hotel objects based on the number of reviews descending.
	 */
	public static Comparator<Hotel> reviewTotalComp = new Comparator<Hotel>() {

		public int compare(Hotel h1, Hotel h2) {

			double reviewTotal1 = h1.getReviewTotal();
			double reviewTotal2 = h2.getReviewTotal();

			if (reviewTotal2 < reviewTotal1) {
				return -1;
			} else if (reviewTotal2 == reviewTotal1) {
				return 0;
			} else {
				return 1;
			}

		}
	};

	/**
	 * Comparator used to order hotel objects based on star rating descending 
	 */
	public static Comparator<Hotel> starRatingComp = new Comparator<Hotel>() {

		public int compare(Hotel h1, Hotel h2) {

			double starRating1 = h1.getStarRating();
			double starRating2 = h2.getStarRating();

			if (starRating2 < starRating1) {
				return -1;
			} else if (starRating2 == starRating1) {
				return 0;
			} else {
				return 1;
			}

		}
	};

	
	/**
	 * Comparator used to order hotel objects based on guest review rating descending 
	 */
	public static Comparator<Hotel> guestReviewRatingComp = new Comparator<Hotel>() {

		public int compare(Hotel h1, Hotel h2) {

			double starRating1 = h1.getGuestReviewRating();
			double starRating2 = h2.getGuestReviewRating();

			if (starRating2 < starRating1) {
				return -1;
			} else if (starRating2 == starRating1) {
				return 0;
			} else {
				return 1;
			}

		}
	};

	
	/**
	 * Comparator used to order hotel objects based on percent savings descending 
	 */
	public static Comparator<Hotel> percentSavingsComp = new Comparator<Hotel>() {

		public int compare(Hotel h1, Hotel h2) {

			double percentSavings1 = h1.getPercentSavings();
			double percentSavings2 = h2.getPercentSavings();

			if (percentSavings2 < percentSavings1) {
				return -1;
			} else if (percentSavings2 == percentSavings1) {
				return 0;
			} else {
				return 1;
			}

		}
	};

}
