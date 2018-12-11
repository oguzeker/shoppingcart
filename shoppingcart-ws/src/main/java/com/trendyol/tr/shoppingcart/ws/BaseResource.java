package com.trendyol.tr.shoppingcart.ws;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trendyol.tr.shoppingcart.common.util.ResponseUtils;
import com.trendyol.tr.shoppingcart.exception.ErrorCode;
import com.trendyol.tr.shoppingcart.ws.util.ExceptionMapper;

public abstract class BaseResource {
	
	private static final String TRIPLE_DOTS = "...";
	private static final String QUESTION_MARK = "?";
	private static final String UTF_8 = "UTF-8";

	protected abstract Logger getLogger();
	
	protected Response sendResponse(Object output) {
    	ObjectMapper restMapper = new ObjectMapper();
    	try {
    		if(output == null || ResponseUtils.isOutputBlank(restMapper.writeValueAsString(output))) {
	    		return Response.status(HttpStatus.SC_OK)
	    				.entity(ResponseUtils.EMPTY_RESPONSE)
	    				.type(MediaType.APPLICATION_JSON + ResponseUtils.CHARSET_UTF_8)
	    				.build();
	    	}
    	} catch (Exception e) {
			return ExceptionMapper.handleException(e);
		}
    	
    	return Response.status(HttpStatus.SC_OK)
				.entity(output)
				.type(MediaType.APPLICATION_JSON + ResponseUtils.CHARSET_UTF_8)
				.build();
    }
	
	protected Response sendResponse(int httpStatus, ErrorCode code) {
    	return ExceptionMapper.prepareResponse(httpStatus, code.getCode(), null);
    }
	
	protected void logInfoHttpRequest(String methodName, @Context HttpServletRequest httpServletRequest) {
		Logger logger = getLogger();
		if (logger.isInfoEnabled()) {

			StringBuilder sb = new StringBuilder(methodName).append(TRIPLE_DOTS);
			sb.append(httpServletRequest.getRequestURL());
			if (org.apache.commons.lang.StringUtils.isNotBlank(httpServletRequest.getQueryString())) {
				try {
					sb.append(QUESTION_MARK).append(URLDecoder.decode(httpServletRequest.getQueryString(), UTF_8));
				} catch (UnsupportedEncodingException e) {
					// Do nothing since it is logging incoming request
				}
			}
			logger.info(sb.toString());
		}
	}
	
}