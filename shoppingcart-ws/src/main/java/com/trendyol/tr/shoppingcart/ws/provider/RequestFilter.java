package com.trendyol.tr.shoppingcart.ws.provider;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class RequestFilter implements ContainerRequestFilter {

	private static final String ENCODING_UTF_8 = "UTF-8";
	private static final String METHOD_PUT = "PUT";
	private static final String METHOD_POST = "POST";
	private static final String APPLICATION_JSON = "application/json";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestFilter.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {

		LOGGER.info(requestContext.getUriInfo().getPath() + " is called.");
		
		if (hasInputData(requestContext) && isJson(requestContext)) {
            try {
            	String json = IOUtils.toString(requestContext.getEntityStream(), ENCODING_UTF_8);
                // do whatever you need with json
            	LOGGER.debug("Received JSON data is:\n {}", json);
            	// replace input stream for Jersey as we've already read it
                InputStream in = IOUtils.toInputStream(json);
                requestContext.setEntityStream(in);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
	}

	private boolean isJson(ContainerRequestContext request) {
        return request.getMediaType() != null
        		? request.getMediaType().toString().contains(APPLICATION_JSON)
        		: false; 
    }

	private boolean hasInputData(ContainerRequestContext requestContext){
		return requestContext.getMethod().equalsIgnoreCase(METHOD_POST) || 
			   requestContext.getMethod().equalsIgnoreCase(METHOD_PUT);
   }
}
