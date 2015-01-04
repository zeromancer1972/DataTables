package org.openntf.github.xsp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openntf.github.model.GitHubProfile;
import org.openntf.github.model.GitHubRepos;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GitHubController implements Serializable {
	private static final long serialVersionUID = -5848833372412383265L;

	public GitHubProfile profile(final String profileName) {
		GitHubProfile profile = null;
		CloseableHttpResponse response;
		CloseableHttpClient httpclient;
		try {
			RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
			HttpClients.custom().setDefaultRequestConfig(config);
			httpclient = HttpClients.custom().build();
			HttpGet get = new HttpGet("https://api.github.com/users/" + profileName);
			response = httpclient.execute(get);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			ObjectMapper mapper = new ObjectMapper();
			profile = mapper.readValue(rd, GitHubProfile.class);

			response.close();
			httpclient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return profile;

	}

	public GitHubRepos repos(final String profileName) {
		GitHubRepos repos = null;

		CloseableHttpResponse response;
		CloseableHttpClient httpclient;
		try {
			RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(5000).build();
			HttpClients.custom().setDefaultRequestConfig(config);
			httpclient = HttpClients.custom().build();
			HttpGet get = new HttpGet("https://api.github.com/users/" + profileName + "/repos");
			response = httpclient.execute(get);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));

			ObjectMapper mapper = new ObjectMapper();
			repos = mapper.readValue(rd, GitHubRepos.class);

			response.close();
			httpclient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return repos;
	}
}
