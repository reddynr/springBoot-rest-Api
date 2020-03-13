package com.mkyong;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookController {

	
	@PostMapping("/token")
    public String retrieveToken(@RequestBody TableauTicketRequestDTO dto) {
	     
	    RestTemplate restTemplate = new RestTemplate();
	    //String result = restTemplate.getForObject(dto.getUrl(), String.class);
	    
	    MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
	    parametersMap.add("username", dto.getUserName());
	    
	   // ResponseEntity<String> result = restTemplate.exchange(dto.getUrl(), HttpMethod.POST, entity, String.class);
	    
	    String result = restTemplate.postForObject(dto.getUrl(), parametersMap, String.class);
	     
	    System.out.println(result);
	    return result;
	}

    
}
