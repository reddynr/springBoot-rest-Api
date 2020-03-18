package com.example.HelloWorld;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	 private static final Logger logger = LogManager.getLogger(HelloWorldApplication.class);
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
	public ResponseEntity<String> helloWorld(@RequestParam String username,@RequestParam String url,@RequestParam String site,@RequestParam String client_Ip)
	{
		
		 RestTemplate restTemplate = new RestTemplate();
		    //String result = restTemplate.getForObject(dto.getUrl(), String.class);
		    System.out.println("USER DTo :"+username);
		    logger.info("Service Inputs :userName"+username,"url="+url,"site="+site);
		    MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
		    parametersMap.add("username", username);
		    parametersMap.add("target_site",site);
		    parametersMap.add("client_Ip",client_Ip);
		    
		   // ResponseEntity<String> result = restTemplate.exchange(dto.getUrl(), HttpMethod.POST, entity, String.class);
		    
		    String result = restTemplate.postForObject(url, parametersMap, String.class);
		     
		    System.out.println(result);
		    logger.info("Token :",result);
		    String urlToBeRedirected = null;
		    if(null != result & !("-1".equals(result))) {
		    	
		    	//http://tabserver/trusted/<ticket>/views/<workbook>/<view> 
		    	 urlToBeRedirected = url + "/" + result +  "/t/ELTScorecard/views/" + "ELTScorecard" + "/" + "Home";
		    	System.out.println("url to be redirected " + urlToBeRedirected);

		    	
		    	
		    }
		    return new ResponseEntity<String>(urlToBeRedirected, HttpStatus.OK);
		//return result;
	}
}
