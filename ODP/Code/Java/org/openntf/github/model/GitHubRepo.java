package org.openntf.github.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepo implements Serializable {

	private static final long serialVersionUID = 2420278232445213119L;

	private String name;
	private String description;
	private String html_url;
	private String updated_at;

	public GitHubRepo() {

	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(final String html_url) {
		this.html_url = html_url;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(final String updated_at) {
		this.updated_at = updated_at;
	}

}
