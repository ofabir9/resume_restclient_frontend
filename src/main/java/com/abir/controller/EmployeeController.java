package com.abir.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.abir.dto.EmployeeDTO;
import com.abir.model.Employee;
import com.abir.converter.Converter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	
	RestTemplate restTemplate;
	ObjectMapper mapper;
	String uri ;
	
	@Autowired
	public EmployeeController()
	{
		
		restTemplate = new RestTemplate();
		mapper = new ObjectMapper();
		uri="http://localhost:8081/resume_restapi_backend/api/v1/employees";
	}
	
	@ModelAttribute("employeeDTO")
	public EmployeeDTO getEmployeeDTO()
	{
		return new EmployeeDTO();
	}
	
	@GetMapping()
	public String allEmployees(Model model) throws JsonMappingException, JsonProcessingException
	{
//		ResponseEntity<String>response = restTemplate.getForEntity(uri,String.class);
//		List<Employee> employeeList = mapper.readValue(response.getBody(), new TypeReference<List<Employee>>() {}) ;
		Client client = Client.create();
        WebResource resource = client.resource(uri);
        ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
        
        if(response.getStatus()!= HttpStatus.OK.value())
        {
        	model.addAttribute("errors",response.getStatus()+" "+response.getEntity(String.class));
        	return "errors";
        }
		List<Employee> employeeList = mapper.readValue(response.getEntity(String.class), new TypeReference<List<Employee>>() {}) ;
		model.addAttribute("employees", employeeList);
		return "employeesTable";
	}
	
	@GetMapping("/show/{id}")
	public String showResume(@PathVariable("id") int id, Model model) throws JsonMappingException, JsonProcessingException
	{
//		ResponseEntity<String>response = restTemplate.getForEntity(uri+"/show/"+id,String.class);
//		if(response!=null)
//		{
//			Employee employee = mapper.readValue(response.getBody(), Employee.class) ;
//			
//			model.addAttribute(employee);
//		}
//		else
//		{
//			//TO DO
//		}
		Client client = Client.create();
        WebResource resource = client.resource(uri+"/show/"+id);
        ClientResponse response = resource.accept("application/json").get(ClientResponse.class);
		
		if(response.getStatus()!= HttpStatus.OK.value())
	    {
	        model.addAttribute("errors",response.getStatus()+" "+response.getEntity(String.class));
	        return "errors";
	    }
		Employee employee = mapper.readValue(response.getEntity(String.class), Employee.class) ;
		model.addAttribute(employee);
		return "showResume";
	}
	
	@GetMapping("/add")
	public String showCreateResumeForm(Model model)
	{
		return "createResume";
	}
	
	@PostMapping("/add")
	public String addEmployee( @ModelAttribute("employeeDTO") EmployeeDTO employeeDTO,Model model) throws JsonProcessingException
	{
//		String employeeDTOJson = mapper.writeValueAsString(employeeDTO);
////		System.out.println("JSON");
////		System.out.println(employeeDTOJson);
//		HttpHeaders headers=new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		
//		HttpEntity<String> entity = new HttpEntity<String>(employeeDTOJson ,headers);
//		String response;
//		try {
//			response = restTemplate.postForObject(uri+"/add", entity, String.class);
//			System.out.println(response);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
		Client client = Client.create();
        WebResource webResource = client.resource(uri+"/add");
        String employeeDTOJson = mapper.writeValueAsString(employeeDTO);
        ClientResponse response = webResource.type("application/json")
        		.post(ClientResponse.class, employeeDTOJson);
        
        if(response.getStatus()!= HttpStatus.CREATED.value())
	    {
        	Map<String,List<String>> map = mapper.readValue(response.getEntity(String.class), new TypeReference<Map<String,List<String>>>() {}) ;
        	List<String> errors= map.get("errors") ;
	        model.addAttribute("errors",errors);
	        model.addAttribute("employeeDTO", employeeDTO);
			model.addAttribute("employee", Converter.employeeDTOToEmployee(employeeDTO) );
	        return "createResume";
	    }
        
		return "redirect:/employees";
	}
	
	@PostMapping("/edit")
	public String showEditEmployeeForm(@RequestParam("id") int id,Model model) throws JsonMappingException, JsonProcessingException
	{
//		System.out.println("Edit form");
//		ResponseEntity<String>response = restTemplate.getForEntity(uri+"/edit/"+id,String.class);
		Client client = Client.create();
		WebResource webResource = client.resource(uri+"/edit/"+id);
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		
		if(response.getStatus()!= HttpStatus.OK.value())
		{
			model.addAttribute("errors",response.getStatus()+" "+response.getEntity(String.class));
        	return "errors";
		}
		
		Employee employee = mapper.readValue(response.getEntity(String.class), Employee.class) ;
		
		model.addAttribute("employeeDTO",Converter.employeeToEmployeeDTO( employee));
		model.addAttribute("employee", employee);
		return "editResume";
	}
	
	@PostMapping("/update")
	public String saveEditEmployee(@ModelAttribute("employeeDTO") EmployeeDTO employeeDTO, @RequestParam("oldEmployeeId") int oldEmployeeId,Model model) throws JsonProcessingException {
			

//		employeeDTO.setId(oldEmployeeId);
//		
//		String employeeDTOJson = mapper.writeValueAsString(employeeDTO);
//		HttpHeaders headers=new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<String> entity = new HttpEntity<String>(employeeDTOJson ,headers);
//		
//		ResponseEntity<String> response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
//		try {
//			//response = restTemplate.exchange(uri+"/edit/save",HttpMethod.PUT, entity, String.class);
//			//System.out.println(restTemplate.postForEntity(uri+"/add", entity, String.class));
//			response = restTemplate.exchange(uri+"/update", HttpMethod.PUT, entity, String.class);
//			System.out.println(response.getStatusCode());
//			if(response.getStatusCode()==HttpStatus.ACCEPTED)
//			{
//				model.addAttribute("errors",mapper.readValue(response.getBody(), new TypeReference<List<String>>() {}));
//				model.addAttribute("employeeDTO", employeeDTO);
//				model.addAttribute("employee", Converter.employeeDTOToEmployee(employeeDTO) );
//				return "editResume";
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//			System.out.println(response.getStatusCode());
//		}
		
		employeeDTO.setId(oldEmployeeId);
		String employeeDTOJson = mapper.writeValueAsString(employeeDTO);
		
		Client client = Client.create();
		WebResource resource = client.resource(uri+"/update");
		
		ClientResponse response = resource.type("application/json")
        		.put(ClientResponse.class, employeeDTOJson);
		if(response.getStatus()!= HttpStatus.CREATED.value())
	    {
        	Map<String,List<String>> map = mapper.readValue(response.getEntity(String.class), new TypeReference<Map<String,List<String>>>() {}) ;
        	List<String> errors= map.get("errors") ;
	        model.addAttribute("errors",errors);
	        model.addAttribute("employeeDTO", employeeDTO);
			model.addAttribute("employee", Converter.employeeDTOToEmployee(employeeDTO) );
	        return "editResume";
	    }
		
		return "redirect:/employees";
	}
	@PostMapping("/delete")
	public String deleteEmployee(@RequestParam("id") int id, Model model)
	{
//		employeeService.deleteEmployee(employeeService.getEmployeeById(id));
//		ResponseEntity<String>response = restTemplate.exchange(uri+"/delete/"+id,HttpMethod.DELETE,null,String.class);
		Client client = Client.create();
		WebResource resource = client.resource(uri+"/delete/"+id);
		ClientResponse response = resource.type("application/json")
        		.delete(ClientResponse.class);
		if(response.getStatus()!= HttpStatus.OK.value())
		{
			model.addAttribute("errors",response.getStatus()+" "+response.getEntity(String.class));
        	return "errors";
		}
		return "redirect:/employees";
	}
	
}
