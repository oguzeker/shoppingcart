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

import com.trendyol.tr.shoppingcart.schema.Coupon;
import com.trendyol.tr.shoppingcart.service.CouponService;
import com.trendyol.tr.shoppingcart.ws.util.ExceptionMapper;


@Path(value = "/coupon")
@Stateless
public class CouponResource extends BaseResource {

	private static final Logger logger = LoggerFactory.getLogger(CouponResource.class);

	@EJB
	private CouponService productService;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	@Path(value = "")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCouponList(@Context HttpServletRequest httpServletRequest) {
		try {
			List<Coupon> output = productService.getCouponList();
			return sendResponse(output);
		} catch (Exception e) {
			return ExceptionMapper.handleException(e);
		}
	}

}