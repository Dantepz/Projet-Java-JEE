<%--
  Created by IntelliJ IDEA.
  User: bernardombangyandoumbe
  Date: 26/03/2023
  Time: 01:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.esigelec.jee.models.*" %>
<%@ page import="java.util.ArrayList" %>

<%
    String mairieString = request.getParameter("mairie");
    ArrayList<Mairie> mairies = new ArrayList<>();
    if(request.getSession(true).getAttribute("mairies")!=null) {
        mairies = (ArrayList<Mairie>) request.getSession(true).getAttribute("mairies");
    }

for(Mairie m : mairies){

if(m.getNom().toLowerCase().contains(mairieString.toLowerCase())){

%>
        <div> <%=m.getNom()%> <%=m.getAdresse().getCodePostal()%> <%=m.getInsee()%> <%=m.getAdresse().getLatitude()%> <%=m.getAdresse().getLongitude()%></div><br>
<%
    }
}%>
