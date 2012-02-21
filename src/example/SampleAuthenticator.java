/*
 * Created Mar 29, 2006
 */
package example;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hannonhill.cascade.model.security.authentication.AuthenticationPhase;
import com.hannonhill.cascade.model.security.authentication.Authenticator;

/**
 * Sample authenticator that mimicks a SSO webapp.
 * 
 * @author Collin VanDyck
 * @since 4.0
 */
public class SampleAuthenticator implements Authenticator
{

    public boolean redirect(HttpServletRequest request, HttpServletResponse response, AuthenticationPhase phase) throws IOException
    {
        if (phase == AuthenticationPhase.LOGIN)
        {
            // mimick that the user has already authenticated, and redirect back to the custom authentication
            // servlet
            response.sendRedirect("http://localhost:8080" + AUTHENTICATION_URI);
            return true;
        }
        else if (phase == AuthenticationPhase.LOGOUT)
        {
            // we don't have a external auth webapp, so we'll just redirect here to the web site.
            response.sendRedirect("http://www.hannonhill.com");
            return true;
        }

        return false;
    }

    public String authenticate(HttpServletRequest request, HttpServletResponse response)
    {
        return "admin2";
    }
}
