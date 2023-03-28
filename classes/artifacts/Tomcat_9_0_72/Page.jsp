<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String pageTitle = "Page Carte"; %>

<html>
<jsp:include page="Header.jsp" flush="true">
    <jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
    <body>
    <jsp:include page="Navbar.jsp"/>
        <iframe width="1280" height="760" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://www.openstreetmap.org/export/embed.html?bbox=1.0792779922485354%2C49.3770642379392%2C1.1360979080200198%2C49.396591284851276&amp;layer=mapnik" style="border: 1px solid black">
        </iframe>
        <br/>

    </body>
</html>