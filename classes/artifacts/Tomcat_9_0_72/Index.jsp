<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String pageTitle = "Accueil"; %>

<html>
    <jsp:include page="Header.jsp" flush="true">
        <jsp:param name="pageTitle" value="<%=pageTitle%>" />
    </jsp:include>
    <body>
        <jsp:include page="Navbar.jsp"/>
        <div class="container">
            <div class=" row text-center">
                <h1>Bienvenue sur le site de gestion des salles de sport en France !</h1>
                 <jsp:include page="EquipmentList.jsp"/>
            </div>
        </div>
                <jsp:include page="Footer.jsp"/>
    </body>
</html>
