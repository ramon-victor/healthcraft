package br.univille.apidacs2022;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import br.univille.apidacs2022.api.CidadeControllerAPI;
import br.univille.apidacs2022.api.MedicoControllerAPI;
import br.univille.apidacs2022.api.PacienteControllerAPI;
import br.univille.apidacs2022.api.PlanoDeSaudeControllerAPI;
import br.univille.apidacs2022.api.ProcedimentoControllerAPI;

@SpringBootTest
@AutoConfigureMockMvc
class Apidacs2022ApplicationTests {

	@Autowired
	private PacienteControllerAPI pacienteControllerAPI;

	@Autowired
	private MedicoControllerAPI medicoControllerAPI;

	@Autowired
	private CidadeControllerAPI cidadeoControllerAPI;

	@Autowired
	private PlanoDeSaudeControllerAPI planoDeSaudeControllerAPI;

	@Autowired
	private ProcedimentoControllerAPI procedimentoControllerAPI;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(pacienteControllerAPI).isNotNull();
		assertThat(medicoControllerAPI).isNotNull();
		assertThat(cidadeoControllerAPI).isNotNull();
		assertThat(planoDeSaudeControllerAPI).isNotNull();
		assertThat(procedimentoControllerAPI).isNotNull();
	}

	// Teste API [POST] [GET] do Paciente
	@Test
	void pacienteControllerAPIPOSTGETTest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/pacientes")
				.content("{\"nome\":\"Zezinho\",\"sexo\":\"Masculino\"}")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(get("/api/v1/pacientes/" + objJson.getString("id"))
				.header("Authorization", "Bearer " + jwtToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is("Zezinho")))
				.andExpect(jsonPath("$.sexo", is("Masculino")));
	}

	// Teste API [PUT] do Paciente
	@Test
	void pacienteControllerAPIPUTTest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/pacientes")
				.content("{\"nome\":\"Zezinho\",\"sexo\":\"Masculino\"}")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(put("/api/v1/pacientes/" + objJson.getString("id"))
				.content("{\"nome\":\"Roberta\",\"sexo\":\"Feminino\"}")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is("Roberta")))
				.andExpect(jsonPath("$.sexo", is("Feminino")));
	}

	// Teste API [DELETE] do Paciente
	@Test
	void pacienteControllerAPIDELETETest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/pacientes")
				.content("{\"nome\":\"Zezinho\",\"sexo\":\"Masculino\"}")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(delete("/api/v1/pacientes/" + objJson.getString("id"))
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	// Teste API [POST] [GET] do Medico
	@Test
	void medicoControllerAPIPOSTGETTest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/medicos")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"nome\":\"Gedso\",\"crm\":12345}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(get("/api/v1/medicos/" + objJson.getString("id"))
				.header("Authorization", "Bearer " + jwtToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is("Gedso")))
				.andExpect(jsonPath("$.crm", is("12345")));
	}

	// Teste API [PUT] do Medico
	@Test
	void medicoControllerAPIPUTTest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/medicos")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"nome\":\"Gedso\",\"crm\":12345}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(put("/api/v1/medicos/" + objJson.getString("id"))
				.content("{\"nome\":\"Gedson\",\"crm\":\"121214\"}")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is("Gedson")))
				.andExpect(jsonPath("$.crm", is("121214")));
	}

	// Teste API [DELETE] do Medico
	@Test
	void medicoControllerAPIDELETETest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/medicos")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"nome\":\"Gedso\",\"crm\":12345}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(delete("/api/v1/medicos/" + objJson.getString("id"))
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	// Teste API [POST] [GET] da Cidade
	@Test
	void cidadeControllerAPIPOSTGETTest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/cidades")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"nome\":\"São Paulo\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(get("/api/v1/cidades/" + objJson.getString("id"))
				.header("Authorization", "Bearer " + jwtToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is("São Paulo")));
	}

	// Teste API [PUT] da Cidade
	@Test
	void cidadeControllerAPIPUTTest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/cidades")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"nome\":\"Sã Paulo\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(put("/api/v1/cidades/" + objJson.getString("id"))
				.content("{\"nome\":\"São Paulo\"}")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is("São Paulo")));
	}

	// Teste API [DELETE] da Cidade
	@Test
	void cidadeControllerAPIDELETETest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/cidades")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"nome\":\"São Paulo\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(delete("/api/v1/cidades/" + objJson.getString("id"))
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	// Teste API [POST] [GET] dos Planos de Saude
	@Test
	void planosControllerAPIPOSTGETTest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/planos")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"nome\":\"Herolife\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(get("/api/v1/planos/" + objJson.getString("id"))
				.header("Authorization", "Bearer " + jwtToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is("Herolife")));
	}

	// Teste API [PUT] dos Planos de Saude
	@Test
	void planosControllerAPIPUTTest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/planos")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"nome\":\"HeroLifer\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(put("/api/v1/planos/" + objJson.getString("id"))
				.content("{\"nome\":\"Herolife\"}")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is("Herolife")));
	}

	// Teste API [DELETE] dos Planos de Saude
	@Test
	void planosControllerAPIDELETETest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/planos")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"nome\":\"HeroLifer\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(delete("/api/v1/planos/" + objJson.getString("id"))
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	// Teste API [POST] [GET] dos Procedimentos
	@Test
	void procedimentosControllerAPIPOSTGETTest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/procedimentos")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"descricao\":\"Cura Veneno\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(get("/api/v1/procedimentos/" + objJson.getString("id"))
				.header("Authorization", "Bearer " + jwtToken))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.descricao", is("Cura Veneno")));
	}

	// Teste API [PUT] dos Procedimentos
	@Test
	void procedimentosControllerAPIPUTTest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/procedimentos")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"descricao\":\"Cura Venenos\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(put("/api/v1/procedimentos/" + objJson.getString("id"))
				.content("{\"descricao\":\"Cura Veneno\"}")
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.descricao", is("Cura Veneno")));
	}

	// Teste API [DELETE] dos Procedimentos
	@Test
	void procedimentosControllerAPIDELETETest() throws Exception {
		MvcResult resultAuth = mockMvc.perform(post("/api/v1/auth/signin")
				.content("{\"usuario\":\"admin\",\"senha\":\"admin\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn();
		String jwtToken = resultAuth.getResponse().getContentAsString();

		MvcResult result = mockMvc.perform(post("/api/v1/procedimentos")
				.header("Authorization", "Bearer " + jwtToken)
				.content("{\"descricao\":\"Cura Venenos\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		String resultString = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultString);

		mockMvc.perform(delete("/api/v1/procedimentos/" + objJson.getString("id"))
				.header("Authorization", "Bearer " + jwtToken)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
