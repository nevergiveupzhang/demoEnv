package com.example.demo.service;

import java.security.GeneralSecurityException;
import java.util.Map;

import javax.security.auth.login.FailedLoginException;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.AnyAuthenticationPolicy;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PolicyBasedAuthenticationManager;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.RequiredHandlerAuthenticationPolicy;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

public class TestQueryAndEncodeDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler{
	@Override
	protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential transformedCredential)
			throws GeneralSecurityException, PreventedException {
		final String username = getPrincipalNameTransformer().transform(transformedCredential.getUsername());
        final String encodedPsw = this.getPasswordEncoder().encode(transformedCredential.getPassword());
        final Map<String, Object> values = getJdbcTemplate().queryForMap(this.sql, username, encodedPsw);
        if(values==null||values.isEmpty()) {
        	 throw new FailedLoginException("Password does not match value on record.");
        }
        return createHandlerResult(transformedCredential,
                this.principalFactory.createPrincipal(username), null);
	}
	
	  @Autowired
	    public void setSql(final String sql) {
	        this.sql = sql;
	    }
	  @NotNull
	    private String sql;
	  
	  @Autowired(required=false)
	    @Override
	    public void setDataSource(final DataSource dataSource) {
	        super.setDataSource(dataSource);
	    }
}
