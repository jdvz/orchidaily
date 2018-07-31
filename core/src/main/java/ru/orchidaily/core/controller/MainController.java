package ru.orchidaily.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: zera
 * @date: 6/15/18 9:37 PM
 */
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@Api(value = "Main Controller")
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "test passed")
    })
    public ResponseEntity<Map<String, Object>> index() {
        return ResponseEntity.ok(Collections.singletonMap("test", "passed"));
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "test passed")
    })
    public ResponseEntity<Map<String, Object>> test() {
        return ResponseEntity.ok(Collections.singletonMap("test", "passed2"));
    }
}
