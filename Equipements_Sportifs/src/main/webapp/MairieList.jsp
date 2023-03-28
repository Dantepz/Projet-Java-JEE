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
<%@ page import="java.util.HashMap" %>

<%
    HashMap<Equipement,Mairie> eqsMs = new HashMap<>();
    ArrayList<Equipement> equipements = new ArrayList<>();
    if(request.getSession().getAttribute("eqsMs") != null) {
        eqsMs = (HashMap<Equipement, Mairie>) request.getSession().getAttribute("eqsMs");
        equipements = new ArrayList<>(eqsMs.keySet());
    }


for(Equipement eq : equipements){
%>
            <div> <%=eq.getId()%> <%=eq.getNom()%> <%=eqsMs.get(eq).getNom()%></div><br>
<%
    }
%>
