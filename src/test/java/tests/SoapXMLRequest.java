package tests;

import org.testng.annotations.Test;
import org.apache.commons.io.IOUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class SoapXMLRequest {
	
	
	@Test
	public void validateSoapXML() throws IOException {
	
	        File file = new File("./SOAPRequest/Add.xml");
	        
	        if(file.exists())
	        	System.out.println(" >> File Exists ");
	        
	        FileInputStream fileInputStream = new FileInputStream(file);
	          String requestBody = IOUtils.toString(fileInputStream,"UTF-8");
	        		 
	            
	         baseURI = "http://currencyconverter.kowabunga.net";
	         
	         
	         given().contentType("text/xml").
	                accept(ContentType.XML).
	                body(requestBody).
	         when().
	            post("/calculator.asmx")
	         .then()
	                .statusCode(200).log().all().
	                and().body("//*:AddResult.text()",equalTo("5"));
	         
	        
	}


			
			
		
}			
	


