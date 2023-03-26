<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.esigelec.jee.dao.EquipementTypeDao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% String pageTitle = "Rechercher"; %>

<html>
  <jsp:include page="Header.jsp" flush="true">
    <jsp:param name="pageTitle" value="<%=pageTitle%>" />
  </jsp:include>
  <body>
    <jsp:include page="Navbar.jsp"/>
    <div class="container">
      <form action="Rechercher">
        <%-- Nom de l'équipement --%>
        <div class="mb-3">
          <label for="InputNomEquipment" class="form-label">Nom de l'équipement</label>
          <input type="text" class="form-control" id="InputNomEquipment" placeholder="Saisir le nom de l'équipement...">
        </div>
        <%-- référence de l'équipement --%>
          <div class="mb-3">
            <label for="InputReferenceEquipment" class="form-label">Référence de l'équipement</label>
            <input type="text" class="form-control" id="InputReferenceEquipment" placeholder="Saisir la référence de l'équipement...">
          </div>
        <%-- Nom du village --%>
          <div class="mb-3">
            <label for="InputNomVillage" class="form-label">Nom du village de l'équipement</label>
            <input type="text" class="form-control" id="InputNomVillage" placeholder="Saisir le nom du village...">
          </div>
        <%-- Code Postal --%>
        <div class="mb-3">
          <label for="InputCodePostal" class="form-label">Code Postal</label>
          <input type="password" class="form-control" id="InputCodePostal" placeholder="Saisir le code postal du village...">
        </div>
        <%-- Datalist Type d'équipement --%>
        <div class="mb-3">
          <label for="equipmentListDataList" class="form-label">Datalist example</label>
          <input class="form-control" list="datalistOptions" id="equipmentListDataList" placeholder="Écrire pour rechercher...">
          <datalist id="datalistOptions">
            <%
              ArrayList<String> list;
              EquipementTypeDao dao = new EquipementTypeDao();
              list = dao.getEquipementTypesFamille();
              for(String equipementType : list) {
            %>
                <option value="<%= equipementType %>">
            <%
              }
            %>
          </datalist>
        </div>
        <%-- Button rechercher --%>
        <div class="text-center">
          <button type="submit" class="btn btn-primary">Rechercher</button>
        </div>
      </form>
    </div>
    <jsp:include page="Footer.jsp"/>
  </body>
</html>