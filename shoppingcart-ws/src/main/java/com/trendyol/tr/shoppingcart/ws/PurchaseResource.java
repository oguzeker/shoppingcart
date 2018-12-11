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

import com.trendyol.tr.shoppingcart.schema.Purchase;
import com.trendyol.tr.shoppingcart.service.PurchaseService;
import com.trendyol.tr.shoppingcart.ws.util.ExceptionMapper;


@Path(value = "/purchase")
@Stateless
public class PurchaseResource extends BaseResource {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseResource.class);

	@EJB
	private PurchaseService productService;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	@Path(value = "")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPurchaseList(@Context HttpServletRequest httpServletRequest) {
		try {
			List<Purchase> output = productService.getPurchaseList();
			return sendResponse(output);
		} catch (Exception e) {
			return ExceptionMapper.handleException(e);
		}
	}

}