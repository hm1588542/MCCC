package com.mccc.mccccity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.mccc.mccccity.controller.CityController;

@SpringBootTest
@AutoConfigureMockMvc
class McccCityApplicationTests {

	@Autowired
	private CityController cityController;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		assertThat(cityController).isNotNull();
	}

	@Test
	public void connectCheckYes() throws Exception {
		this.mockMvc.perform(get("/connected?origin=Boston&destination=Newark")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("true")));
	}

	@Test
	public void connectCheckNo() throws Exception {
		this.mockMvc.perform(get("/connected?origin=Philadelphia&destination=Albany")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("false")));
	}


}
