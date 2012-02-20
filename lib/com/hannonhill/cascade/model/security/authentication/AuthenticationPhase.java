/*
 * Created Mar 28, 2006
 */
package com.hannonhill.cascade.model.security.authentication;

/**
 * Enumerates the different phases at which the custom authenticator will be allowed
 * to redirect the client browser if necessary.
 * 
 * @author Collin VanDyck
 * @since 4.0
 */
public enum AuthenticationPhase
{
    LOGIN
    {
        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString()
        {
            return "Login: when the user is a custom auth user, the module can supply a redirect here";
        }
    },
    LOGOUT
    {
        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @Override
        public String toString()
        {
            return "Logout: this phase occurs when the clients logs out of the system";
        }
    }
}
