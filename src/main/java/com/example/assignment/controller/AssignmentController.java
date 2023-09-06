package com.example.assignment.controller;

import com.example.assignment.model.Customer;
import com.example.assignment.service.CustomerService;
import com.example.assignment.util.UtilManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AssignmentController {

    final CustomerService service;
    @ExceptionHandler(Exception.class)
    public ErrorResponse exceptionHandler( Exception exception )
    {
        return ErrorResponse.create(exception.fillInStackTrace(), HttpStatusCode.valueOf(500),exception.getLocalizedMessage());
    }
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request,
            Model model) throws JSONException, JsonProcessingException {
        var res=service.login(username,password);
        JsonNode root = UtilManager.getJsonNode(res);

        UtilManager.setAuthToken(request, root);

        return "redirect:/table";

    }

    @GetMapping("/table")
    public String table(HttpServletRequest request, Model model) throws JsonProcessingException {
        Object token = UtilManager.getToken(request);
        if(token==null) return "redirect:/login";
        model.addAttribute("tableData", service.fetchCustomerData(token.toString()));
        return "table";
    }

    @GetMapping("/add-customer")
    String addCustomer(HttpServletRequest request) {
        Object token = UtilManager.getToken(request);
        if(token==null) return "redirect:/login";
        return "addcustomer";
    }

    @PostMapping("/add-customer")
    public String addCustomer(Customer customer, HttpServletRequest request,
            Model model) throws JSONException {
        Object token = UtilManager.getToken(request);
        if(token==null) return "redirect:/login";

        service.addCustomer(customer, token);

        return "redirect:/table";
    }



    @GetMapping("/edit-customer")
    public String editCustomer(@RequestParam String uuid, HttpServletRequest request, Model model)
            throws JsonProcessingException {

        Object token = UtilManager.getToken(request);
        if(token==null) return "redirect:/login";

        Customer customer = service.editCustomer(uuid, token);

        model.addAttribute("action", "edit");
        model.addAttribute("customer", customer);
        return "addcustomer";
    }



    @PostMapping("/edit-customer")
    public String editCustomer(Customer customer, @RequestParam String uuid,
            HttpServletRequest request, Model model)
            throws JsonProcessingException {
        Object token = UtilManager.getToken(request);
        if(token==null) return "redirect:/login";
        service.editCustomer(customer, token);

        return "redirect:/table";
    }



    @GetMapping("/delete-customer")
    public String deleteCustomer(@RequestParam String uuid, HttpServletRequest request, Model model)
            throws JsonProcessingException {
        Object token = UtilManager.getToken(request);
        if(token==null) return "redirect:/login";
        service.deleteCustomer(uuid, token);

        return "redirect:/table";
    }


}
