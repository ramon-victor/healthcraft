package br.univille.apidacs2022;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.univille.apidacs2022.api.MedicoControllerAPI;
import br.univille.apidacs2022.api.PacienteControllerAPI;

@SpringBootTest
@AutoConfigureMockMvc
class Apidacs2022ApplicationTests {

	@Autowired
	private PacienteControllerAPI pacienteControllerAPI;
	
	@Autowired
	private MedicoControllerAPI medicoControllerAPI;


	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(pacienteControllerAPI).isNotNull();
		assertThat(medicoControllerAPI).isNotNull();
	}


	@Test
	void pacienteControllerAPIPOSTGETTest() throws Exception{
		MvcResult result = 
		mockMvc.perform(post("/api/v1/pacientes")
		.content("{\"nome\":\"Zezinho\",\"sexo\":\"Masculino\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);
		
		mockMvc.perform(get("/api/v1/pacientes/" + objJson.getString("id")))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nome", is("Zezinho")))
			.andExpect(jsonPath("$.sexo", is("Masculino")));
	}


	@Test
	void medicoControllerAPIPOSTGETTest() throws Exception{
		MvcResult result = 
		mockMvc.perform(post("/api/v1/medicos")
		.content("{\"nome\":\"Gedso\",\"crm\":12345}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(get("/api/v1/medicos/" + objJson.getString("id")))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nome", is("Gedso")))
			.andExpect(jsonPath("$.crm", is("12345")));
	}

	@Test
	void cidadeControllerAPIPOSTGETTest() throws Exception {
		MvcResult result = 
		mockMvc.perform(post("/api/v1/cidades")
		.content("{\"nome\":\"São Paulo\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(get("/api/v1/cidades/" + objJson.getString("id")))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nome", is("São Paulo")));
	}

	@Test
	void planosControllerAPIPOSTGETTest() throws Exception {
		MvcResult result = 
		mockMvc.perform(post("/api/v1/planos")
		.content("{\"nome\":\"Herolife\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(get("/api/v1/planos/" + objJson.getString("id")))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.nome", is("Herolife")));
	}
}
