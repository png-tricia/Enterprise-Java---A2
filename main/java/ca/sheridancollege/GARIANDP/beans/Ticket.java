package ca.sheridancollege.GARIANDP.beans;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Ticket {

	private int id;

	/* Mandatory */
	private String name;
	private double ticketPrice;

	/* 4 additional pieces of information */
	private String phone;
	private String email;
	private String eventDate;
	private String ageGroup;
	private String survey;
	private String gender;

	private String[] dates = { "August 9, 2024 - Friday", "August 10, 2024 - Saturday", "August 11, 2024 - Sunday",
			"All Weekend" };
	private double[] prices = { 18.99, 19.99, 20.99, 25.99 };
	private String[] ages = { "Below 18", "18 - 34", "35 - 50", "51 - 66", "67+" };
	private String[] surveyoptions = { "Social Media", "Flyer", "Word of Mouth", "Commercial" };
	private String[] genders = { "Female", "Male", "Unicorn", "Other" };

}
