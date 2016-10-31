package com.padana;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.padana.domain.Document;
import com.padana.domain.Watermark;
import com.padana.service.GenericService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@WebAppConfiguration
public class TestApi {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	private Document doc = new Document();
	private Watermark wm = new Watermark();

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype());

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();

		doc.setDocAuthor("Test Author");
		doc.setDocTitle("Test Title");
	}

	@Test
	public void addDocuments() throws Exception {
		String str = "{\"docTitle\":\"testTtitle\",\"docAuthor\": \"testAuthor\",\"docTopic\": \"science\"}";
		mockMvc.perform(post("/addDocuments").contentType(contentType).content(str)).andExpect(status().isCreated());
	}

	@Test
	public void addBulkDocuments() throws Exception {
		String str = "[{\"docTitle\":\"testTtitle\",\"docAuthor\": \"testAuthor\",\"docTopic\": \"science\"},{\"docTitle\":\"testTtitle12\",\"docAuthor\": \"testAuthor12\"}]";
		mockMvc.perform(post("/addBulkDocuments").contentType(contentType).content(str))
				.andExpect(status().isCreated());
	}

	@Test
	public void getDocument() throws Exception {
		String titleEncoded = URLEncoder.encode("testTtitle", "UTF-8");
		String authorEncoded = URLEncoder.encode("testAuthor", "UTF-8");
		String url = "/checkDocument/" + titleEncoded + "/" + authorEncoded;
		mockMvc.perform(get(url).contentType(contentType)).andExpect(status().isOk())
				.andExpect(content().contentType(contentType));
	}
}
