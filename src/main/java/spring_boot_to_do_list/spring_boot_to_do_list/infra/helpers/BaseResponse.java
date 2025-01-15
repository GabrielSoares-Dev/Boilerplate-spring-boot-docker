package spring_boot_to_do_list.spring_boot_to_do_list.infra.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BaseResponse {

    public static ResponseEntity<Map<String, Object>> success(String message, HttpStatus statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", statusCode.value());
        response.put("message", message);
        return new ResponseEntity<>(response, statusCode);
    }

    public static ResponseEntity<Map<String, Object>> successWithContent(String message, HttpStatus statusCode,
            Object content) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", statusCode.value());
        response.put("message", message);
        response.put("content", content);
        return new ResponseEntity<>(response, statusCode);
    }

    public static ResponseEntity<Map<String, Object>> error(String message, HttpStatus statusCode) {
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", statusCode.value());
        response.put("message", message);
        return new ResponseEntity<>(response, statusCode);
    }
}