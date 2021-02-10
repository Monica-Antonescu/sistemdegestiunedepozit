<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Front End - Login</title>
    </head>

    <body bgcolor="Silver">

        <f:view>

            <h:form>
                <h2 align ="center" > SISTEM DE GESTIUNE DEPOZIT </h2>
                <table align="center">
                    <tr>
                        <td>Username: </td>
                        <td> <h:inputText  id="username" value="#{LoginInSistem.username}" required="true"/>
                            <h:message for="username" style="color:red"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Parola: </td>
                        <td>
                            <h:inputSecret id="password" value="#{LoginInSistem.password}" required="true"/>
                            <h:message for="password" style="color:red"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <h:commandButton action="#{LoginInSistem.validUser}" id="logare" value="Log in" />
                        </td>
                    </tr>
                </table>
            </h:form>
        </f:view>
    </body>
</html>
