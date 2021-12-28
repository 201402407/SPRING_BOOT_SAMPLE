package com.example.test;

import org.hamcrest.core.IsNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApplicationTests {

	private String api = "";

	@Autowired
	private MockMvc mockMvc;

	public TestApplicationTests(String url) {
		this.api = url;
	}

	public ResultActions failure(ResultActions res) throws Exception {
		return res
				.andExpect(status().is(res.andReturn().getResponse().getStatus()))
				.andExpect(jsonPath("$.success", is(false)))
				.andExpect(jsonPath("$.response").value(IsNull.nullValue()))
				.andExpect(jsonPath("$.error").exists())
				.andExpect(jsonPath("$.error.status", is(res.andReturn().getResponse().getStatus())));
	}

	public ResultActions success(ResultActions res) throws Exception {
		return res
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.success", is(true)))
				.andExpect(jsonPath("$.response").exists())
				.andExpect(jsonPath("$.error").value(IsNull.nullValue()));
	}

	public String getString(JSONObject json, String prop) throws JSONException {
		return json.getString(prop.toUpperCase());
	}

	public String apiUrl(String url) {
		return this.api + "/" + url;
	}

	public ResultActions reqGet(String url) throws Exception {
		return mockMvc.perform(get(apiUrl(url)));
	}

	public ResultActions reqGet(String url, MultiValueMap<String, String> queryMap) throws Exception {
		return mockMvc.perform(get(apiUrl(url)).queryParams(queryMap));
	}

	public ResultActions reqDel(String url) throws Exception {
		return mockMvc.perform(delete(apiUrl(url)));
	}

	public ResultActions reqPost(String url) throws Exception {
		return mockMvc.perform(post(apiUrl(url)));
	}

	public ResultActions reqPost(String url, JSONObject json) throws Exception {
		return mockMvc.perform(post(apiUrl(url))
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()));
	}

	public ResultActions reqPut(String url) throws Exception {
		return mockMvc.perform(put(apiUrl(url)));
	}

	public ResultActions reqPut(String url, JSONObject json) throws Exception {
		return mockMvc.perform(put(apiUrl(url))
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()));
	}

	public ResultActions reqPatch(String url) throws Exception {
		return mockMvc.perform(patch(apiUrl(url)));
	}

	public ResultActions reqPatch(String url, JSONObject json) throws Exception {
		return mockMvc.perform(patch(apiUrl(url))
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.toString()));
	}
}
