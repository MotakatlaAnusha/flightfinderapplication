package com.flightfinder.application.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Flightfinder {

	@GetMapping("/getFlightCount/{input}")
	public ResponseEntity<Object> getFlightCount(@PathVariable(value = "input") String input) {

		if (input.matches(".*[A-Z].*")) {
			 return ResponseEntity.badRequest()
			            .body("Input string should not contain upper case letters");		
			 }

		if (input.length() > 100) {
			 return ResponseEntity.badRequest()
			            .body("Input string should not be greater than 100 characters");
		}

		String findstr = "flight";
		List<Character> inputstr = input.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		List<Character> findstrchar = findstr.chars().mapToObj(c -> (char) c).collect(Collectors.toList());

		boolean checkstr = false;
		int output = 0;

		while (!inputstr.isEmpty() && inputstr.size() >= findstrchar.size()) {
			for (int i = 0; i < findstrchar.size(); i++) {
				if (inputstr.contains(findstrchar.get(i))) {
					int index = inputstr.indexOf(findstrchar.get(i));
					inputstr.remove(index);
				} else {
					 return ResponseEntity.status(HttpStatus.OK)
						        .body(output);
				}
			}
			output = output + 1;

		}
		 return ResponseEntity.status(HttpStatus.OK)
			        .body(output);	}

}
