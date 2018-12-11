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

import com.trendyol.tr.shoppingcart.schema.Cart;
import com.trendyol.tr.shoppingcart.service.CartService;
import com.trendyol.tr.shoppingcart.ws.util.ExceptionMapper;


@Path(value = "/cart")
@Stateless
public class CartResource extends BaseResource {

	private static final Logger logger = LoggerFactory.getLogger(CartResource.class);

	@EJB
	private CartService productService;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	@Path(value = "")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCartList(@Context HttpServletRequest httpServletRequest) {
		try {
			List<Cart> output = productService.getCartList();
			return sendResponse(output);
		} catch (Exception e) {
			return ExceptionMapper.handleException(e);
		}
	}

}