package com.example.HelloWorld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class HelloWorldApplication extends SpringBootServletInitializer
{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(HelloWorldApplication.class);
	}

	public static void main(String[] args)
	{
		SpringApplication.run(HelloWorldApplication.class, args);
	}

	@RequestMapping(value = "/hello")
	public String helloWorld(@RequestParam String username,@RequestParam String url)
	{
		
		 RestTemplate restTemplate = new RestTemplate();
		    //String result = restTemplate.getForObject(dto.getUrl(), String.class);
		    System.out.println("USER DTo :"+username);
		    MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
		    parametersMap.add("username", username);
		    
		   // ResponseEntity<String> result = restTemplate.exchange(dto.getUrl(), HttpMethod.POST, entity, String.class);
		    
		    String result = restTemplate.postForObject(url, parametersMap, String.class);
		     
		    System.out.println(result);
		   
		
		return username;
	}
}
