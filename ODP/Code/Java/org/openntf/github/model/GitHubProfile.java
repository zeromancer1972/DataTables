package org.openntf.github.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubProfile implements Serializable {

	private static final long serialVersionUID = 3578007780249316980L;

	private String login;
	private int id;
	private String avatar_url;
	private String gravatar_id;
	private String url;
	private String html_url;
	private String followers_url;
	private String following_url;
	private String gists_url;
	private String starred_url;
	private String subscriptions_url;
	private String organizations_url;
	private String repos_url;
	private String events_url;
	private String received_events_url;
	private String type;
	private boolean site_admin;
	private int public_repos;
	private int public_gists;
	private int followers;
	private int following;
	private String created_at;
	private String updated_at;
	@SuppressWarnings("unused")
	private String idAndLogin;

	public GitHubProfile() {

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(final String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getGravatar_id() {
		return gravatar_id;
	}

	public void setGravatar_id(final String gravatar_id) {
		this.gravatar_id = gravatar_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(final String html_url) {
		this.html_url = html_url;
	}

	public String getGists_url() {
		return gists_url;
	}

	public void setGists_url(final String gists_url) {
		this.gists_url = gists_url;
	}

	public String getStarred_url() {
		return starred_url;
	}

	public void setStarred_url(final String starred_url) {
		this.starred_url = starred_url;
	}

	public String getSubscriptions_url() {
		return subscriptions_url;
	}

	public void setSubscriptions_url(final String subscriptions_url) {
		this.subscriptions_url = subscriptions_url;
	}

	public String getOrganizations_url() {
		return organizations_url;
	}

	public void setOrganizations_url(final String organizations_url) {
		this.organizations_url = organizations_url;
	}

	public String getRepos_url() {
		return repos_url;
	}

	public void setRepos_url(final String repos_url) {
		this.repos_url = repos_url;
	}

	public String getEvents_url() {
		return events_url;
	}

	public void setEvents_url(final String events_url) {
		this.events_url = events_url;
	}

	public String getReceived_events_url() {
		return received_events_url;
	}

	public void setReceived_events_url(final String received_events_url) {
		this.received_events_url = received_events_url;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public boolean isSite_admin() {
		return site_admin;
	}

	public void setSite_admin(final boolean site_admin) {
		this.site_admin = site_admin;
	}

	public int getPublic_repos() {
		return public_repos;
	}

	public void setPublic_repos(final int public_repos) {
		this.public_repos = public_repos;
	}

	public int getPublic_gists() {
		return public_gists;
	}

	public void setPublic_gists(final int public_gists) {
		this.public_gists = public_gists;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(final String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(final String updated_at) {
		this.updated_at = updated_at;
	}

	public String getFollowers_url() {
		return followers_url;
	}

	public void setFollowers_url(final String followers_url) {
		this.followers_url = followers_url;
	}

	public String getFollowing_url() {
		return following_url;
	}

	public void setFollowing_url(final String following_url) {
		this.following_url = following_url;
	}

	public int getFollowers() {
		return followers;
	}

	public void setFollowers(final int followers) {
		this.followers = followers;
	}

	public int getFollowing() {
		return following;
	}

	public void setFollowing(final int following) {
		this.following = following;
	}

	public synchronized String getIdAndLogin() {
		return id + " ### " + login;
	}

	public synchronized void setIdAndLogin(final String idAndLogin) {
		this.idAndLogin = idAndLogin;
	}

}
