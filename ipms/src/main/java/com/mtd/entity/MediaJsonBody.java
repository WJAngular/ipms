package com.mtd.entity;

public class MediaJsonBody {

	private String url;
	private String mediaID;

	public MediaJsonBody() {

	}

	public MediaJsonBody(String url) {
		this.url = url;
	}

	public MediaJsonBody(String url, String mediaID) {
		this.url = url;
		this.mediaID = mediaID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMediaID() {
		return mediaID;
	}

	public void setMediaID(String mediaID) {
		this.mediaID = mediaID;
	}

}
