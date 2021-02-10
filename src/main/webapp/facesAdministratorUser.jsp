<%@page contentType="text/html" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsf/core" %>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Admin Users</title>
</head>
<body body bgcolor="Silver">

<f:view>
    <h:form id="formOperatii">
        <font color="red">
            <h2 align="center">
                PAGINA DE ADMINISTRARE A UTILIZATORILOR
            </h2>
        </font>

        <h2 align="center">Bine ai venit administrator,  <h:outputText value="#{LoginInSistem.userInfo.firstName} "/>
            <h:outputText value="#{LoginInSistem.userInfo.lastName}"/> !
        </h2>

        <h:commandButton action="#{CrudOps.goToStock}" id="stock" value="Vezi stoc"/>
        <br/><br/>
        <center>
        <h:dataTable border="2" bgcolor="green" value="#{CrudOps.users}" var="myList">
            <h:column>
                <f:facet name="header">
                    <h:outputText value="User ID"/>
                </f:facet>
                <h:outputLabel for="myList" value="#{myList.id}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Username"/>
                </f:facet>
                <h:outputLabel for="myList" value="#{myList.username}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Parola"/>
                </f:facet>
                <h:outputLabel style="color:#FF0066" for="myList" value="***********"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Prenume"/>
                </f:facet>
                <h:outputLabel for="myList" value="#{myList.firstName}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Nume"/>
                </f:facet>
                <h:outputLabel for="myList" value="#{myList.lastName}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Tip utilizator"/>
                </f:facet>
                <h:outputLabel for="myList" value="#{myList.type}"/>
            </h:column>
            <h:column>
                <f:facet name="header">
                    <h:outputText value="Operatiune stergere"/>
                </f:facet>
                <h:commandLink onclick="return confirm ('delete  #{myList.firstName} #{myList.lastName} ?')"
                               value="Sterge utilizator" action="#{CrudOps.deleteUser}">
                    <f:param id="idUserDeSters" name="idUserDeSters" value="#{myList.id}"/>
                </h:commandLink>
            </h:column>
        </h:dataTable>
    </h:form>

    <h:form id="addUser">
        <br/>
        <div id='a1' style="display:block;">
            <h3>Adauga sau editeaza un utilizator:</h3>
            <table align="center">
                <tr>
                    <td>User ID:</td>
                    <td><h:inputText id="idAdd" value="#{CrudOps.idToAdd}" required="true"/>
                        <h:message for="idAdd" style="color:red"/>
                    </td>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><h:inputText id="usernameAdd" value="#{CrudOps.usernameAdd}" required="true"/>
                        <h:message for="usernameAdd" style="color:red"/>
                    </td>
                </tr>
                <tr>
                    <td>Parola:</td>
                    <td>
                        <h:inputText id="passwordAdd" value="#{CrudOps.passwordAdd}" required="true"/>
                        <h:message for="passwordAdd" style="color:red"/>
                    </td>
                </tr>
                <tr>
                    <td>Prenume:</td>
                    <td>
                        <h:inputText id="firstNameAdd" value="#{CrudOps.firstNameAdd}" required="true"/>
                        <h:message for="firstNameAdd" style="color:red"/>
                    </td>
                </tr>
                <tr>
                    <td>Nume:</td>
                    <td>
                        <h:inputText id="lastNameAdd" value="#{CrudOps.lastNameAdd}" required="true"/>
                        <h:message for="lastNameAdd" style="color:red"/>
                    </td>
                </tr>
                <tr>
                    <td>Tip utilizator:</td>
                    <h:selectOneRadio value="#{CrudOps.typeOfUseAdd}">
                        <f:selectItems value="#{CrudOps.userTypes}"/>
                    </h:selectOneRadio>
                </tr>
            </table>
            <tr>
                <td colspan="2" align="center">
                    <h:commandButton action="#{CrudOps.addUser}" id="addUser" value="Adauga utilizator nou"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <h:commandButton action="#{CrudOps.updateUserInfo}" id="editUser"
                                     value="Editeaza utilizator existent"/>
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
