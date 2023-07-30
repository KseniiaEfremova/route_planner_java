package com.example.route_planner;

import com.example.route_planner.controllers.AcceleratorController;
import com.example.route_planner.services.AcceleratorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiApplicationTests {

	@Autowired
	private AcceleratorController controller;

	@Autowired
	private AcceleratorService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test() throws Exception {
		this.mockMvc.perform(get("/accelerators"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("[{\"id\":\"SOL\",\"name\":\"Sol\",\"connections\":" +
						"[{\"id\":\"SIR\",\"hu\":\"200\"},{\"id\":\"PRO\",\"hu\":\"120\"}]}]")));

		this.mockMvc.perform(get("/accelerators/SOL"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"id\":\"SOL\",\"name\":\"Sol\",\"connections\":" +
						"[{\"id\":\"SIR\",\"hu\":\"200\"},{\"id\":\"PRO\",\"hu\":\"120\"}]}")));

		this.mockMvc.perform(get("/accelerators/QWE/to/SOL"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("0")));

		this.mockMvc.perform(get("/transport/111"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("0")));


	}

}


