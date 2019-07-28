package com.poc.exception;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomResponseErrorHandler implements ResponseErrorHandler {

	private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

	public boolean hasError(ClientHttpResponse response) throws IOException {
		return errorHandler.hasError(response);
	}

	public void handleError(ClientHttpResponse response) throws IOException {
		String theString = IOUtils.toString(response.getBody(), StandardCharsets.UTF_8);
		checkIsJsonValid(theString);
		JSONObject json = new JSONObject(theString);
		if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
              throw new ClientException(json.get("errorCode").toString(),json.get("errorMessage").toString());
		}
		if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
			   throw new ClientException(json.get("errorCode").toString(),json.get("errorMessage").toString());
		}
	}

	

	

	private void checkIsJsonValid(String theString) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.readTree(theString);
		} catch (IOException exception) {

		}

	}
}
