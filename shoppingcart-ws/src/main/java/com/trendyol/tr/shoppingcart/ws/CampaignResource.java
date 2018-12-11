package com.trendyol.tr.shoppingcart.ws;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trendyol.tr.shoppingcart.schema.Campaign;
import com.trendyol.tr.shoppingcart.service.CampaignService;
import com.trendyol.tr.shoppingcart.ws.util.ExceptionMapper;


@Path(value = "/campaign")
@Stateless
public class CampaignResource extends BaseResource {

	private static final Logger logger = LoggerFactory.getLogger(CampaignResource.class);

	@EJB
	private CampaignService productService;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	@Path(value = "")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCampaignList(@Context HttpServletRequest httpServletRequest) {
		try {
			List<Campaign> output = productService.getCampaignList();
			return sendResponse(output);
		} catch (Exception e) {
			return ExceptionMapper.handleException(e);
		}
	}

}