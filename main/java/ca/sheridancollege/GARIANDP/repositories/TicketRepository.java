package ca.sheridancollege.GARIANDP.repositories;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.GARIANDP.beans.Ticket;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class TicketRepository {

	private NamedParameterJdbcTemplate jdbc;

	public void addTicket(Ticket ticket) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO music_tickets(name, phone, email, gender, age_group, event_date, ticket_price, survey) VALUES "
				+ "(:na, :pn, :em, :ge, :ag, :da, :tp, :su)";

		parameters.addValue("na", ticket.getName());
		parameters.addValue("pn", ticket.getPhone());
		parameters.addValue("em", ticket.getEmail());
		parameters.addValue("ge", ticket.getGender());
		parameters.addValue("ag", ticket.getAgeGroup());
		parameters.addValue("da", ticket.getEventDate());
		parameters.addValue("tp", ticket.getTicketPrice());
		parameters.addValue("su", ticket.getSurvey());
		jdbc.update(query, parameters);
	}

	public ArrayList<Ticket> getTickets() {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM music_tickets";

		ArrayList<Ticket> tickets = (ArrayList<Ticket>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Ticket>(Ticket.class));
		return tickets;
	}

	public Ticket findById(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM music_tickets WHERE id=:ticket";

		parameters.addValue("ticket", id);

		ArrayList<Ticket> tickets = (ArrayList<Ticket>) jdbc.query(query, parameters,
				new BeanPropertyRowMapper<Ticket>(Ticket.class));
		if (tickets.size() > 0) {
			return tickets.get(0);
		} else {
			return null;
		}
	}

	public void editTicket(Ticket ticket) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "UPDATE music_tickets SET name=:na, phone=:pn, email=:em, gender=:ge, age_group=:ag, event_date=:da, ticket_price=:tp, survey=:su WHERE id=:id";

		parameters.addValue("id", ticket.getId());
		parameters.addValue("na", ticket.getName());
		parameters.addValue("pn", ticket.getPhone());
		parameters.addValue("em", ticket.getEmail());
		parameters.addValue("ge", ticket.getGender());
		parameters.addValue("ag", ticket.getAgeGroup());
		parameters.addValue("da", ticket.getEventDate());
		parameters.addValue("tp", ticket.getTicketPrice());
		parameters.addValue("su", ticket.getSurvey());

		jdbc.update(query, parameters);

	}

	public void deleteTicket(int id) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "DELETE FROM music_tickets WHERE id=:id";

		parameters.addValue("id", id);

		jdbc.update(query, parameters);
	}

}
