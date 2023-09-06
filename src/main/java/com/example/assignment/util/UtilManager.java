package com.example.assignment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;

public class UtilManager {

    public static JsonNode getJsonNode(String res) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(res);
    }

    public static void setAuthToken(HttpServletRequest request, JsonNode root) {
        HttpSession session = request.getSession();
        session.setAttribute("token", root.get("access_token").asText());
    }

    public static Object getToken(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return  session.getAttribute("token");
    }

    public static HttpHeaders setHeader(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }
}
