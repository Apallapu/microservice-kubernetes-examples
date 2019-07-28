package com.poc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.poc.exception.CustomResponseErrorHandler;
import com.poc.model.Ticket;

@Service
public class MyBusService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	CustomResponseErrorHandler customResponseErrorHandler;

	
	//private String apsrtcUrl;


	public Ticket createTicket(Ticket ticket) {
		String url = new StringBuilder("http://localhost:8080").append("/create-bus-ticket").toString();
		restTemplate.setErrorHandler(customResponseErrorHandler);
		return restTemplate.postForObject(url, ticket, Ticket.class);
	}

	public void updateTicket(Ticket ticket, Long id) {
		String url = new StringBuilder("http://localhost:8080").append("/update-bus-ticket/").append(id).toString();
		restTemplate.setErrorHandler(customResponseErrorHandler);
		restTemplate.put(url, ticket);
	}

	public Ticket getTicketById(Long id) {
		String url = new StringBuilder("http://localhost:8080").append("/get-bus-ticket/").append(id).toString();
		restTemplate.setErrorHandler(customResponseErrorHandler);
		return restTemplate.getForObject(url, Ticket.class);
	}

	public List<Ticket> getAllTickets() {
		String url = new StringBuilder("http://localhost:8080").append("/get-All-tickets").toString();
		restTemplate.setErrorHandler(customResponseErrorHandler);
		return restTemplate.getForObject(url, List.class);
	}

	public void deleteTicketById(Long id) {
		String url = new StringBuilder("http://localhost:8080").append("/delete-bus-ticket/").append(id).toString();
		restTemplate.setErrorHandler(customResponseErrorHandler);
		restTemplate.delete(url);
	}

}
