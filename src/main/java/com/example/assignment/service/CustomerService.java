package com.example.assignment.service;

import com.example.assignment.model.Customer;
import com.example.assignment.util.UtilManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    final RestTemplate restTemplate;

    public String login(String username, String password) throws JSONException {
        String apiUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("login_id", username);
        jsonObject.put("password", password);
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonObject.toString());

        return restTemplate.postForObject(apiUrl, requestEntity, String.class);
    }

    public Object fetchCustomerData(String token) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = UtilManager.setHeader(token);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        String response = restTemplate
                .exchange("https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list",
                        HttpMethod.GET, httpEntity, String.class)
                .getBody();
        JsonNode root = mapper.readTree(response);
        List<Customer> customers = new ArrayList<>();

        if (root.isArray()) {
            for (JsonNode customerNode : root) {
                Customer customer = mapper.convertValue(customerNode, Customer.class);
                customers.add(customer);
            }
        }
        return customers;
    }

    public void addCustomer(Customer customer, Object token) {
        HttpHeaders headers = UtilManager.setHeader(token.toString());

        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);

        String response = restTemplate.postForObject(
                "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create",
                requestEntity,
                String.class);
    }

    public Customer editCustomer(String uuid, Object token) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        HttpHeaders headers = UtilManager.setHeader(token.toString());
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        String response = restTemplate
                .exchange("https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list",
                        HttpMethod.GET, httpEntity, String.class)
                .getBody();
        JsonNode root = mapper.readTree(response);
        List<Customer> customers = new ArrayList<>();

        if (root.isArray()) {
            for (JsonNode customerNode : root) {
                Customer customer = mapper.convertValue(customerNode, Customer.class);
                customers.add(customer);
            }
        }

        return customers.stream().filter(item -> item.getUuid().equals(uuid)).findFirst().get();
    }

    public void editCustomer(Customer customer, Object token) {
        HttpHeaders headers = UtilManager.setHeader(token.toString());

        HttpEntity<Customer> requestEntity = new HttpEntity<>(customer, headers);
        String response = restTemplate.postForObject(
                "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=update&uuid="
                        + customer.getUuid(),
                requestEntity, String.class);
    }

    public void deleteCustomer(String uuid, Object token) {
        HttpHeaders headers =UtilManager.setHeader(token.toString());

        HttpEntity<Customer> requestEntity = new HttpEntity<>(headers);

        String response = restTemplate.postForObject(
                "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid="
                        + uuid,
                requestEntity, String.class);
    }
}
