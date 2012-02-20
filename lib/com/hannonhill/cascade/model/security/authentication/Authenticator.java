/*
 * Created Mar 28, 2006
 */
package com.hannonhill.cascade.model.security.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Basic interface for all Cascade custom authentication types. 
 * 
 * @author Collin VanDyck
 * @since 4.0
 */
public interface Authenticator
{
    /** The authentication servlet that calls Authenticator.authenticate() lives here */
    public static final String AUTHENTICATION_URI = "/customauth";
    
    /**
     * If the authenticator needs to redirect at any one of the two authentication phases, it may do
     * so here.  If the authenticator decides to redirect the client, then it must signal this back to
     * Cascade Server by returning true.  
     * 
     * For Phase LOGIN
     * 
     * If the user has already been authenticated, then the module may choose to redirect to
     * http://[host]:[port] + AUTHENTICATION_URI .  This points to a servlet that will reinstantiate 
     * the module and call authenticate().  At this point, if the module returns a valid username, then
     * the user will be logged into Cascade Server.
     * 
     * For Phase LOGOUT
     * 
     * The redirect here will likely be back to the external authentication webapp.  By the time this
     * redirect callback is received, the Cascade user session will already have been invalidated.
     * 
     * @param request
     * @param response
     * @param phase
     * @throws IOException
     */
    public boolean redirect(HttpServletRequest request, HttpServletResponse response, AuthenticationPhase phase) throws IOException;

    /**
     * Allows the module to report back whether or not the user has been authenticated.
     * 
     * @param request
     * @param response
     * @return the username if the user successfully authenticated
     */
    public String authenticate(HttpServletRequest request, HttpServletResponse response);
}
