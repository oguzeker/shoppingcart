package com.trendyol.tr.shoppingcart.exception;


public enum ErrorCode {

	SUCCESS("200"),
	
	ITEM_CREATED("200.001"),
	ITEM_UPDATED("200.002"),
	ITEM_DELETED("200.003"),
	
	BAD_REQUEST("400"),
	CUSTOM_RESOURCE_BAD_REQUEST("400.000"),
	INVALID_LOGIN_CREDENTIALS("400.001"),
	TOKEN_EXPIRED("400.002"),
	TOKEN_NOT_VALID("400.003"),
	TOKEN_NOT_PROVIDED("400.004"),
	GALERI_INVALID_PASSWORD("400.021"),
	KULLANICI_INVALID_PASSWORD("400.022"),
	
	GALERI_NOT_FOUND("404.001"),
	KULLANICI_NOT_FOUND("404.002"),
	IHALE_MISAFIR_KODU_NOT_FOUND("404.003"),
	
	GALERI_EMAIL_ALREADY_EXISTS("422.001"),
	GALERI_CANNOT_DELETE("422.002"),
	
	INTERNAL_SERVER_ERROR("500"),
	INTERNAL_SERVER_ERROR_CONFIG_NOT_SET("500.001"),
	INTERNAL_SERVER_ERROR_COULD_NOT_DELETE_FILE("500.002"),
	INTERNAL_SERVER_ERROR_COULD_NOT_WRITE_FILE("500.003");
	
	private String code;
	
	private ErrorCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static ErrorCode fromValue(String code) {
		for (ErrorCode t : values()) {
			if (t.getCode().equals(code)) {
				return t;
			}
		}
		return INTERNAL_SERVER_ERROR;
	}
}
