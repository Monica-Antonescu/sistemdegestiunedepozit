<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Welcome Simple User</title>
</head>

<body bgcolor="Silver">
<f:view>
    <h:form>

        <font color="red">
            <h2 align="center">
                PAGINA DE VIZUALIZARE A STOCULUI
            </h2>
        </font>

        <h2 align="center">Bine ai venit,  <h:outputText value="#{LoginInSistem.userInfo.firstName} "/>
                                         <h:outputText value="#{LoginInSistem.userInfo.lastName} "/> !
        </h2>

        <div align="center">
            <h:dataTable border="2" bgcolor="green" id="stockTable" value="#{StockSistem.stock}" var="obj">
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Cod produs"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{obj.id}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Denumire"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{obj.name}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Cantitate"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{obj.quantity}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Unitate"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{obj.measurement}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Locatie"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{obj.location}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Pret unitar fara TVA"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{obj.price}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Pret unitar cu TVA"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{obj.priceWithTva}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Pret total cu TVA"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{obj.totalPrice}"/>
                </h:column>
            </h:dataTable>
        </div>

        <h4 align="center">
            <h:outputLink value="#{faces.facesLogin}">
                <h:outputText value="Log Out"/>
            </h:outputLink>
        </h4>

    </h:form>
</f:view>
</body>
</html>
