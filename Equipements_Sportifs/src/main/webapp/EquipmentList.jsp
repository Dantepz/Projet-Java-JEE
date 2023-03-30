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
%>
    <div class="col-6">
<%
        for(Equipement eq : equipements){
%>
        <div class> <%=eq.getId()%> <%=eq.getNom()%> <%=eqsMs.get(eq).getNom()%></div>
<%
    }

    }else{%>
        <div class="col-6 mx-auto">
            <form action="ChargerDonneesDuDepartement" method="POST">
                <div class="form-floating">
                    <input class="form-control me-2" type="text" id="equipement" name="equipement" placeholder="football" value="football" required>
                    <label for="equipement">Entrer le nom de l'équipement : </label>
                </div>
                <div class="form-floating">
                    <input class="form-control" type="text" id="zipcode" name="zipcode" placeholder="76000" value="76000" required>
                    <label for="zipcode">Entrer le code Postal</label>
                </div>
                <button class="btn btn-success" role="submit">Lancer la recherche</button>
            </form>
        </div>
    <%}%>
