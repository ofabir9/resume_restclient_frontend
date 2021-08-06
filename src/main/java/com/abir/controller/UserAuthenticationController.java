package com.abir.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.abir.dto.UserLoginDTO;
import com.abir.dto.UserRegistrationDTO;
import com.abir.dto_to_model_converter.DTOToModelConverter;
import com.abir.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
@RequestMapping("/auth")
public class UserAuthenticationController {

	RestTemplate restTemplate;
	ObjectMapper mapper;
	String uri ;
	
	@Autowired
	public UserAuthenticationController()
	{
		
		restTemplate = new RestTemplate();
		mapper = new ObjectMapper();
		uri="http://localhost:8081/resume_restapi_backend/api/v1/auth";
		
	}
	
	@PostMapping("/register")
	public String registration(@ModelAttribute("userRegistrationDTO") UserRegistrationDTO userRegistrationDTO, HttpSession session, Model model) throws JsonProcessingException
	{
		Client client = Client.create();
        WebResource webResource = client.resource(uri+"/register");
        String userRegistrationDTOJson = mapper.writeValueAsString(userRegistrationDTO);
        System.out.println(userRegistrationDTOJson);
        ClientResponse response = webResource.type("application/json")
        		.post(ClientResponse.class, userRegistrationDTOJson);
        
        if(response.getStatus()!= HttpStatus.CREATED.value())
	    {
        	Map<String,List<String>> map = mapper.readValue(response.getEntity(String.class), new TypeReference<Map<String,List<String>>>() {}) ;
        	List<String> errors= map.get("errors");
        	System.out.println("Erorr in registration");
        	System.out.println(errors);
	        return "redirect:/employees";
	    }
        System.out.println("Here1");
        Employee employee = mapper.readValue(response.getEntity(String.class), Employee.class) ;
        System.out.println("Here2");
        session.setAttribute("loggedEmployee",employee);
		return "redirect:/employees";
	}
	
	@PostMapping("/login")
	public String logout(@ModelAttribute("userLoginDTO") UserLoginDTO userLoginDTO, HttpSession session, Model model) throws JsonProcessingException
	{
		Client client = Client.create();
        WebResource webResource = client.resource(uri+"/login");
        String userLoginDTOJson = mapper.writeValueAsString(userLoginDTO);
        System.out.println(userLoginDTOJson);
        ClientResponse response = webResource.type("application/json")
        		.post(ClientResponse.class, userLoginDTOJson);
        
        if(response.getStatus()!= HttpStatus.OK.value())
	    {
        	Map<String,List<String>> map = mapper.readValue(response.getEntity(String.class), new TypeReference<Map<String,List<String>>>() {}) ;
        	List<String> errors= map.get("errors");
        	System.out.println("Erorr in Login");
        	System.out.println(errors);
	        return "redirect:/employees";
	    }
        Employee employee = mapper.readValue(response.getEntity(String.class), Employee.class) ;
        session.setAttribute("loggedEmployee",employee);
		return "redirect:/employees";
	}
	@PostMapping("/logout")
	public String logout(HttpSession session,Model model) {
		session.invalidate();
		return "redirect:/employees";
	}
}
