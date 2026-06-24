package com.example.bfhlapi;

import com.example.bfhlapi.dto.RequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class BfhlApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testBfhlEndpoint_ExampleA() throws Exception {
		RequestDto request = new RequestDto();
		request.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

		ObjectMapper mapper = new ObjectMapper();
		String jsonRequest = mapper.writeValueAsString(request);

		mockMvc.perform(post("/bfhl")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.is_success").value(true))
				.andExpect(jsonPath("$.sum").value("339"))
				.andExpect(jsonPath("$.concat_string").value("Ra"))
				.andExpect(jsonPath("$.odd_numbers[0]").value("1"))
				.andExpect(jsonPath("$.even_numbers[0]").value("334"))
				.andExpect(jsonPath("$.alphabets[0]").value("A"))
				.andExpect(jsonPath("$.special_characters[0]").value("$"));
	}
}