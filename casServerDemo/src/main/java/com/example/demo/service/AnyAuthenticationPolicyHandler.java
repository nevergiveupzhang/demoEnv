package com.example.demo.service;

import java.security.GeneralSecurityException;

import javax.security.auth.login.FailedLoginException;

import org.jasig.cas.authentication.Credential;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.handler.support.AbstractPreAndPostProcessingAuthenticationHandler;

public class AnyAuthenticationPolicyHandler extends AbstractPreAndPostProcessingAuthenticationHandler{

	@Override
	public boolean supports(Credential credential) {
		return true;
	}

	@Override
	protected HandlerResult doAuthentication(Credential credential)
			throws GeneralSecurityException, PreventedException {
		throw new FailedLoginException("Password does not match value on record.");
	}

}
