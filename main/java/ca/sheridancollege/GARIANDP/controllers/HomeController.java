package ca.sheridancollege.GARIANDP.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.GARIANDP.beans.Ticket;
import ca.sheridancollege.GARIANDP.repositories.TicketRepository;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class HomeController {

	private TicketRepository ticketRepo;

	@GetMapping("/")
	public String homePage() {
		return "home.html";
	}

	@GetMapping("/add")
	public String addTicket(Model model) {
		model.addAttribute("ticket", new Ticket());
		return "add.html";
	}

	@PostMapping("/add")
	public String processTicket(@ModelAttribute Ticket ticket, Model model) {
		ticketRepo.addTicket(ticket);
		return "redirect:/add";
	}

	@GetMapping("/view")
	public String viewTickets(Model model) {
		model.addAttribute("tickets", ticketRepo.getTickets());
		return "view.html";
	}

	@GetMapping("/edit/{id}")
	public String editRecord(@PathVariable int id, Model model) {
		Ticket t = ticketRepo.findById(id);
		model.addAttribute("ticket", t);
		return "edit.html";
	}

	@PostMapping("/edit")
	public String processEdit(@ModelAttribute Ticket ticket, Model model) {
		ticketRepo.editTicket(ticket);
		return "redirect:/view";
	}

	@GetMapping("/delete/{id}")
	public String deleteRecord(@PathVariable int id, Model model) {
		ticketRepo.deleteTicket(id);
		model.addAttribute("ticket", ticketRepo.getTickets());
		return "redirect:/view";
	}

	// THIS MAPPING WILL BE LONG BUT IT WORKS :D -PAT
	// JUST LEARNED THAT EDITING/DELETING A RECORD UPDATES THE STAT PAGE IN REAL
	// TIME
	@GetMapping("/stats")
	public String countGender(Model model) {
		ArrayList<Ticket> tickets = ticketRepo.getTickets();

		/* GENDER STATS */
		double females = 0, males = 0, unicorn = 0, other = 0;
		for (Ticket t : tickets) {
			// System.out.print(t);
			if (t.getGender().equalsIgnoreCase("female")) {
				females += 1;
			} else if (t.getGender().equalsIgnoreCase("male")) {
				males += 1;
			} else if (t.getGender().equalsIgnoreCase("unicorn")) {
				unicorn += 1;
			} else {
				other += 1;
			}
		}

		double total = females + males + unicorn + other;
		females = (females / total) * 100;
		males = (males / total) * 100;
		unicorn = (unicorn / total) * 100;
		other = (other / total) * 100;

		model.addAttribute("female", String.format("%.2f", females));
		model.addAttribute("male", String.format("%.2f", males));
		model.addAttribute("unicorn", String.format("%.2f", unicorn));
		model.addAttribute("other", String.format("%.2f", other));

		/* AGE GROUP STATS */
		double ag1 = 0, ag2 = 0, ag3 = 0, ag4 = 0, ag5 = 0;
		for (Ticket t : tickets) {
			if (t.getAgeGroup().equalsIgnoreCase("below 18")) {
				ag1 += 1;
			} else if (t.getAgeGroup().equalsIgnoreCase("18-34")) {
				ag2 += 1;
			} else if (t.getAgeGroup().equalsIgnoreCase("35-50")) {
				ag3 += 1;
			} else if (t.getAgeGroup().equalsIgnoreCase("51-66")) {
				ag4 += 1;
			} else {
				ag5 += 1;
			}
		}

		double total2 = ag1 + ag2 + ag3 + ag4 + ag5;
		ag1 = (ag1 / total2) * 100;
		ag2 = (ag2 / total2) * 100;
		ag3 = (ag3 / total2) * 100;
		ag4 = (ag4 / total2) * 100;
		ag5 = (ag5 / total2) * 100;

		model.addAttribute("ag1", String.format("%.2f", ag1));
		model.addAttribute("ag2", String.format("%.2f", ag2));
		model.addAttribute("ag3", String.format("%.2f", ag3));
		model.addAttribute("ag4", String.format("%.2f", ag4));
		model.addAttribute("ag5", String.format("%.2f", ag5));

		/* EVENT DATE */
		double fri = 0, sat = 0, sun = 0, all = 0;
		for (Ticket t : tickets) {
			if (t.getEventDate().equalsIgnoreCase("August 9, 2024 - Friday")) {
				fri += 1;
			} else if (t.getEventDate().equalsIgnoreCase("August 10, 2024 - Saturday")) {
				sat += 1;
			} else if (t.getEventDate().equalsIgnoreCase("August 11, 2024 - Sunday")) {
				sun += 1;
			} else {
				all += 1;
			}
		}

		double total3 = fri + sat + sun + all;
		fri = (fri / total3) * 100;
		sat = (sat / total3) * 100;
		sun = (sun / total3) * 100;
		all = (all / total3) * 100;

		model.addAttribute("fri", String.format("%.2f", fri));
		model.addAttribute("sat", String.format("%.2f", sat));
		model.addAttribute("sun", String.format("%.2f", sun));
		model.addAttribute("all", String.format("%.2f", all));

		/* TICKET PACKAGE */
		double price1 = 0, price2 = 0, price3 = 0, price4 = 0;
		for (Ticket t : tickets) {
			if (t.getTicketPrice() == 18.99) {
				price1 += 1;
			} else if (t.getTicketPrice() == 19.99) {
				price2 += 1;
			} else if (t.getTicketPrice() == 20.99) {
				price3 += 1;
			} else {
				price4 += 1;
			}
		}

		double total4 = price1 + price2 + price3 + price4;
		price1 = (price1 / total4) * 100;
		price2 = (price2 / total4) * 100;
		price3 = (price3 / total4) * 100;
		price4 = (price4 / total4) * 100;

		model.addAttribute("price1", String.format("%.2f", price1));
		model.addAttribute("price2", String.format("%.2f", price2));
		model.addAttribute("price3", String.format("%.2f", price3));
		model.addAttribute("price4", String.format("%.2f", price4));

		/* HOW DID YOU HEAR ABOUT US ? */
		double media1 = 0, media2 = 0, media3 = 0, media4 = 0;
		for (Ticket t : tickets) {
			if (t.getSurvey().equalsIgnoreCase("social media")) {
				media1 += 1;
			} else if (t.getSurvey().equalsIgnoreCase("flyer")) {
				media2 += 1;
			} else if (t.getSurvey().equalsIgnoreCase("word of mouth")) {
				media3 += 1;
			} else {
				media4 += 1;
			}
		}

		double total5 = media1 + media2 + media3 + media4;
		media1 = (media1 / total5) * 100;
		media2 = (media2 / total5) * 100;
		media3 = (media3 / total5) * 100;
		media4 = (media4 / total5) * 100;

		model.addAttribute("media1", String.format("%.2f", media1));
		model.addAttribute("media2", String.format("%.2f", media2));
		model.addAttribute("media3", String.format("%.2f", media3));
		model.addAttribute("media4", String.format("%.2f", media4));

		return "stats.html";
	}

}
