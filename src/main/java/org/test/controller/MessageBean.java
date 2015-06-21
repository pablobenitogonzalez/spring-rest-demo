package org.test.controller;

@SuppressWarnings(ApiController.UNUSED)
public class MessageBean {
	public String message;
	public String developerMessage;

	public MessageBean() {}

	public MessageBean(String message, String developerMessage) {
		this.message = message;
		this.developerMessage = developerMessage;
	}

}
