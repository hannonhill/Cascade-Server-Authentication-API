### Getting Started


If you're using Cascade 6.7 or later, please clone or [fork](http://help.github.com/fork-a-repo/) a copy this repository if using Git and GitHub or [download a copy](https://github.com/hannonhill/Cascade-Server-Authentication-API/zipball/master). For older versions of Cascade, browse the [branches](https://github.com/hannonhill/Cascade-Server-Authentication-API/branches) and select one that corresponds to your version of Cascade.

The project is configured as an Eclipse Project and can be imported directly into Eclipse by doing: **File > Import... > Existing Projects into Workspace** and selecting the downloaded project directory.

If you're not using Eclipse, you can add the `tomcat-6.0.32-servlet-api.jar` and `dist/authentication-6.10.jar` to your own project.

### Project Structure

The structure of the project is as follows:

- [lib/com/hannonhill/cascade/model/security/authentication](https://github.com/hannonhill/Cascade-Server-Authentication-API/tree/master/lib/com/hannonhill/cascade/model/security/authentication) - package of types for Cascade's authentication API
- [lib/com/hannonhill/cascade/model/security/authentication/Authenticator](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/lib/com/hannonhill/cascade/model/security/authentication/Authenticator.java) - Interface that custom auth modules must implement
- [lib/com/hannonhill/cascade/model/security/authentication/AuthenticationPhase](https://github.com/hannonhill/Cascade-Server-Authentication-API/blob/master/lib/com/hannonhill/cascade/model/security/authentication/AuthenticationPhase.java) - Enum describing the phases of Authentication that any implementation of Authenticator should handle
- tomcat-6.0.32-servlet-api.jar - JAR containing the classes from the [Servlet API](http://docs.oracle.com/javaee/6/api/javax/servlet/package-summary.html) used by Cascade's Authenticator interface

