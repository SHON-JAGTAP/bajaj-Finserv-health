package com.bajaj.qualifier;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebhookRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        // user details
        String name = "Shon jagtap";
        String regNo = "ADT23SOCB1048";
        String email = "jagtapshon@gmail.com";

        System.out.println("Starting application...");
        
        RestTemplate template = new RestTemplate();
        String url1 = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("regNo", regNo);
        data.put("email", email);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request1 = new HttpEntity<>(data, headers);
        
        System.out.println("Calling first API...");
        ResponseEntity<String> res1 = template.postForEntity(url1, request1, String.class);
        System.out.println("Got response: " + res1.getStatusCode());

        // parse json to get tokens
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(res1.getBody());
        
        String url2 = node.get("webhook").asText();
        String token = node.get("accessToken").asText();

        System.out.println("Webhook url is: " + url2);

        // my reg no is even so question 2, but the query is same for this problem
        String query = "SELECT e.EMP_ID, e.FIRST_NAME, e.LAST_NAME, d.DEPARTMENT_NAME, " +
                "COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT " +
                "FROM EMPLOYEE e " +
                "JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID " +
                "LEFT JOIN EMPLOYEE e2 ON e2.DEPARTMENT = e.DEPARTMENT AND e2.DOB > e.DOB " +
                "GROUP BY e.EMP_ID, e.FIRST_NAME, e.LAST_NAME, d.DEPARTMENT_NAME " +
                "ORDER BY e.EMP_ID DESC";

        // second api call
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON);
        headers2.set("Authorization", token);
        
        Map<String, String> submitData = new HashMap<>();
        submitData.put("finalQuery", query);
        
        HttpEntity<Map<String, String>> request2 = new HttpEntity<>(submitData, headers2);
        
        System.out.println("Sending SQL query...");
        try {
            ResponseEntity<String> res2 = template.postForEntity(url2, request2, String.class);
            System.out.println("Final response: " + res2.getBody());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

