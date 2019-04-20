package com.enrico.microservices.got.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.enrico.microservices.got.aop.LogExecution;
import com.enrico.microservices.got.aop.LogRequest;
import com.enrico.microservices.got.dto.Person;
import com.enrico.microservices.got.utils.CustomClientHttpRequestFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
@RequestMapping(path = "/drug", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PersonDrugController {

	@Autowired
	RestTemplate restTemplate;

	@PreAuthorize("hasAuthority('role_admin')")
	@GetMapping(path = "/v2/{id}")
//	public Person token(HttpServletRequest req, @PathVariable("id") Long id) {
	public Person token(@PathVariable("id") Long id) {
//		String token = req.getHeader("authorization");
//		System.out.println(token);
//		restTemplate.setRequestFactory(CustomClientHttpRequestFactory.getClientHttpRequestFactory(req));
		restTemplate.setRequestFactory(CustomClientHttpRequestFactory.getClientHttpRequestFactory());
//		String restUrl = "http://localhost:9092/ms-got/people/" + id;
		String restUrl = "http://ms-got/people/" + id;
		Person person = restTemplate.getForObject(restUrl, Person.class);
		return person;
	}

	@SuppressWarnings("unchecked")
	@LogExecution
	@LogRequest
	@GetMapping
	@ApiOperation(value = "View a list of all available person", response = Iterable.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	public List<Person> getAll() {
		String restUrl = "http://ms-got/people";
		List<Person> persons = restTemplate.getForObject(restUrl, List.class);
		return persons;
	}

	@LogExecution
	@LogRequest
	@PreAuthorize("hasAuthority('role_admin')")
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Search a person with an ID", response = Person.class)
	public Person getOne(HttpServletRequest req, @PathVariable("id") Long id) {

		restTemplate.setRequestFactory(CustomClientHttpRequestFactory.getClientHttpRequestFactory(req));
		String restUrl = "http://ms-got/people/" + id;
		Person person = restTemplate.getForObject(restUrl, Person.class);
		return person;
	}
	
	public Person getOne(@PathVariable("id") Long id) {
		String restUrl = "http://ms-got/people/" + id;
		Person person = restTemplate.getForObject(restUrl, Person.class);
		return person;
	}

}