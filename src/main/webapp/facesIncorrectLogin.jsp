<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Incorrect Login Info</title>
</head>

<body bgcolor="Silver">
<f:view>
    <center>
    <h:form>
        <h1><h:outputText value="Date gresite de login!"/></h1>
        <h:outputLink value="#{faces.facesLogin}">
            <h:outputText value="back"/>
        </h:outputLink>
    </h:form>
    </center>
</f:view>
</body>
</html>
