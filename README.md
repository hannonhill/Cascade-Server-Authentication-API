### Getting Started


If you're using Cascade 6.7 or later, please clone or [fork](http://help.github.com/fork-a-repo/) a copy this repository if using Git and GitHub or [download a copy](https://github.com/hannonhill/Cascade-Server-Authentication-API/zipball/master). For older versions of Cascade, browse the [branches](https://github.com/hannonhill/Cascade-Server-Authentication-API/branches) and select one that corresponds to your version of Cascade.

The project is configured as an Eclipse Project and can be imported directly into Eclipse by doing: **File > Import... > Existing Projects into Workspace** and selecting the downloaded project directory.

If you're not using Eclipse, you can add the `servlet-api.jar` and `dist/authentication-8.0.jar` to your own project.

### Project Structure

The structure of the project is as follows:

- [lib/com/hannonhill/cascade/model/security/authentication](https://github.com/hannonhill/Cascade-Server-Authentication-API/tree/master/lib/com/hannonhill/cascade/model/security/authentication) - package of types for Cascade's authentication API
- [lib/com/hannonhill/cascade/model/security/authentication/Authenticator](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/lib/com/hannonhill/cascade/model/security/authentication/Authenticator.java) - Interface that custom auth modules must implement
- [lib/com/hannonhill/cascade/model/security/authentication/AuthenticationPhase](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/lib/com/hannonhill/cascade/model/security/authentication/AuthenticationPhase.java) - Enum describing the phases of Authentication that any implementation of Authenticator should handle
- servlet-api.jar - JAR containing the classes from the [Servlet API](http://docs.oracle.com/javaee/7/api/javax/servlet/package-summary.html) used by Cascade's Authenticator interface
- [src/example/SampleAuthenticator](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/src/example/SampleAuthenticator.java) - a sample implementation of Authenticator that just bypasses redirecting to a third-party site and always authenticates as a static user: admin2


### Authenticator Interface

All custom authentication modules must implement the [Authenticator](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/lib/com/hannonhill/cascade/model/security/authentication/Authenticator.java) interface.

This interface defines two primary methods: [`redirect`](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/lib/com/hannonhill/cascade/model/security/authentication/Authenticator.java#L44) and [`authenticate`](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/lib/com/hannonhill/cascade/model/security/authentication/Authenticator.java#L53)

The `redirect` method is called by Cascade authentication framework during both the [LOGIN](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/lib/com/hannonhill/cascade/model/security/authentication/AuthenticationPhase.java#L15) and [LOGOUT](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/lib/com/hannonhill/cascade/model/security/authentication/AuthenticationPhase.java#L26) phases of the authentication lifecycle.

When a user first hits the app, Cascade call's the custom authentication module's `redirect` method passing AuthenticationPhase.LOGIN parameter. At this point, the module should redirect the user's browser to an external web application to handle authentication. Afterwards, the external app should redirect back [Authenticator.AUTHENTICATION_URI](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/lib/com/hannonhill/cascade/model/security/authentication/Authenticator.java#L20) with whatever data the module needs in the request to determine if authentication was successful and which user to authenticate. The AUTHENTICATION_URI is relative to the location where Cascade is deployed.

At this point, Cascade will call the module's `authenticate` method which should determine if the user successfully authenticated and return a String containing the name of the user to authenticate. Cascade will take the username and if it matches an enabled user in the Cascade user database, will create a user session for that user and redirect the browser to the dashboard.

The other phase, LOGOUT, is called when the Log Out link is clicked from within the application. The user's Cascade session is invalidated immediately. The module can then decide where to redirect the user on logout. The typical behavior is for the custom authentication module to redirect the browser back to the external authentication web application where the originally authenticated.

It is important to remember to not store state in the authentication module using instance members. The module is re-instantiated with each call so any data required by the module must bed passed in the method parameters.

### Installing a custom authentication module

An authentication module code should be bundled into a JAR and placed in the `$CASCADE_HOME/tomcat/webapps/ROOT/WEB-INF/lib` directory. A server restart is required before the module is available to the applicaiton.

After the module is deployed, it must be configured from within Cascade. 

To configure the module in Cascade Server 7.10 or later, go to:

- **Tools > Configuration > Custom Authentication Configuration**
- 'Enable' the configuration
- Enter the package qualified classname of your module -- e.g. `com.company.auth.sso.CascadePlugin`
- Check the box to 'Intercept the login page'

or, if you want to import your configuration from a different instance, you can use the XML pane to copy and paste a complete configuration.

For previous versions:

- go to **Tools > Configuration > Custom Authentication Configuration**
- enter a snippet like the following:

```xml
	<custom-authentication-module>  
		<class-name>com.company.auth.sso.CascadePlugin</class-name>  
		<should-intercept-login-page>true</should-intercept-login-page>  
	</custom-authentication-module>  
```

where `class-name` is the package-qualified name of your module class that implements Authenticator.

### More examples

Visit our [Examples repository](https://github.com/hannonhill/Custom-Authentication-Module-Examples) to see more examples.
