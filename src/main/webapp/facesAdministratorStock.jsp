<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Admin Stock</title>
</head>
<body body bgcolor="Silver">

<f:view>
    <h:form id="formOperatii">
        <font color="red">
            <h2 align="center">
                PAGINA DE ADMINISTRARE A STOCULUI
            </h2>
        </font>

        <h2 align="center">Bine ai venit administrator, <h:outputText value="#{LoginInSistem.userInfo.firstName} "/>
            <h:outputText value="#{LoginInSistem.userInfo.lastName}"/> !
        </h2>

        <h:commandButton action="#{StockOps.goToUsers}" id="stock" value="Vezi utilizatorii"/>
        <br/><br/>
        <center>
        <h:dataTable border="2" bgcolor="green" value="#{StockOps.stock}" var="myList">
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Cod produs"/>
                </f:facet>
                <h:outputLabel for="myList" value="#{myList.id}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Denumire"/>
                </f:facet>
                <h:outputLabel for="myList" value="#{myList.name}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Cantitate"/>
                </f:facet>
                <h:outputLabel for="myList" value="#{myList.quantity}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Unitate"/>
                </f:facet>
                <h:outputLabel for="myList" value="#{myList.measurement}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Locatie"/>
                </f:facet>
                <h:outputLabel for="myList" value="#{myList.location}"/>
            </h:column>
            <h:column>
                    <f:facet name="header">
                        <h:outputText value="Pret unitar fara TVA"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{myList.price}"/>
            </h:column>
            <h:column>
                    <f:facet name="header">
                        <h:outputText value="Pret unitar cu TVA"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{myList.priceWithTva}"/>
                </h:column>
            <h:column>
                    <f:facet name="header">
                        <h:outputText value="Pret total cu TVA"/>
                    </f:facet>
                    <h:outputLabel for="myList" value="#{myList.totalPrice}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Stergere"/>
                </f:facet>
                <h:commandLink onclick="return confirm ('delete  #{myList.name} ?')"
                               value="Sterge produs" action="#{StockOps.deleteStock}">
                    <f:param id="idStockDeSters" name="idStockDeSters" value="#{myList.id}"/>
                </h:commandLink>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Editare"/>
                </f:facet>
                <h:commandLink value="Editeaza produs" action="#{StockOps.setEditFields}">
                    <f:param id="idStockDeEditat" name="idStockDeEditat" value="#{myList.id}"/>
                </h:commandLink>
            </h:column>
        </h:dataTable>
    </h:form>

    <h:form id="addStock">
        <br/>
        <div id='a1' style="display:block;">
            <h4>ADAUGA SAU EDITEAZA PRODUS:</h4>
            <table align="center">
                <tr>
                    <td>Cod produs</td>
                    <td><h:inputText id="idAdd" value="#{StockOps.idToEdit}" required="true"/>
                        <h:message for="idAdd" style="color:red"/>
                    </td>
                </tr>
                <tr>
                    <td>Denumire</td>
                    <td><h:inputText id="nameAdd" value="#{StockOps.nameAdd}"
                                     required="true"/>
                        <h:message for="nameAdd" style="color:red"/>
                    </td>
                </tr>
                <tr>
                    <td>Cantitate</td>
                    <td>
                        <h:inputText id="quantityAdd" value="#{StockOps.quantityAdd}" required="true"/>
                        <h:message for="quantityAdd" style="color:red"/>
                    </td>
                </tr>
                <tr>
                    <td>Unitate</td>
                    <td>
                        <h:inputText id="measurementAdd" value="#{StockOps.measurementAdd}" required="true"/>
                        <h:message for="measurementAdd" style="color:red"/>
                    </td>
                </tr>
                <tr>
                    <td>Locatie</td>
                    <td>
                        <h:inputText id="locationAdd" value="#{StockOps.locationAdd}" required="true"/>
                        <h:message for="locationAdd" style="color:red"/>
                    </td>
                </tr>
                <tr>
                        <td>Pret unitar</td>
                        <td>
                            <h:inputText id="priceAdd" value="#{StockOps.priceAdd}" required="true"/>
                            <h:message for="priceAdd" style="color:red"/>
                        </td>
                 </tr>
            </table>
            <tr>
                <td colspan="2" align="center">
                    <h:commandButton action="#{StockOps.addStock}" id="addStock" value="Adauga produs nou"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <h:commandButton action="#{StockOps.updateStockInfo}" id="editStock"
                                     value="Editeaza produs existent"/>
                </td>
            </tr>
        </div>
    </h:form>

    <br/>
    <h:outputLink value="faces/facesLogin.jsp">
        <h:outputText value="Log Out"/>
    </h:outputLink>
    </center>

</f:view>
</body>
</html>
