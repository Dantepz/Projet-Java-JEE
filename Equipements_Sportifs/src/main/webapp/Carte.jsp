<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.esigelec.jee.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.esigelec.jee.models.Mairie" %>

<%
    ArrayList<Mairie> mairies = new ArrayList<>();
    if(request.getSession(true).getAttribute("mairies") != null) {
        mairies = (ArrayList<Mairie>) request.getSession(true).getAttribute("mairies");
    }
%>

<% String pageTitle = "Page Carte"; %>

<html>
<jsp:include page="Header.jsp" flush="true">
    <jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
    <body>
    <jsp:include page="Navbar.jsp"/>
    <div class="container">
        <div id="map">
            <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==" crossorigin=""></script>
            <script type="text/javascript">
                // On initialise la latitude et la longitude de Paris (centre de la carte)
                var lat = 41.983417511;
                var lon = 8.79639816284;
                var macarte = null;
                // Fonction d'initialisation de la carte
                function initMap() {
                    // Créer l'objet "macarte" et l'insèrer dans l'élément HTML qui a l'ID "map"
                    macarte = L.map('map').setView([<%=mairies.get(mairies.size() - 1).getAdresse().getLatitude()%>, <%=mairies.get(mairies.size() - 1).getAdresse().getLongitude()%>], 17);
                    // Leaflet ne récupère pas les cartes (tiles) sur un serveur par défaut. Nous devons lui préciser où nous souhaitons les récupérer. Ici, openstreetmap.fr
                    L.tileLayer('https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png', {
                        // Il est toujours bien de laisser le lien vers la source des données
                        attribution: 'Données © <a href="//osm.org/copyright">OpenStreetMap</a>/ODbL - rendu <a href="//openstreetmap.fr">OSM France</a>',
                        minZoom: 1,
                        maxZoom: 20
                    }).addTo(macarte);
                    addPoint(lat, lon);

                    var leavesMarker = L.layerGroup([
                        <% for(int m = 0;m <mairies.size() - 1;m++){ %>
                            new L.marker([<%=mairies.get(m).getAdresse().getLatitude()%>, <%=mairies.get(m).getAdresse().getLongitude()%>], {icon : mairieIcon}),
                        <%}%>
                        new L.marker([<%=mairies.get(mairies.size() - 1).getAdresse().getLatitude()%>, <%=mairies.get(mairies.size() - 1).getAdresse().getLongitude()%>], {icon : mairieIcon})
                    ]);



                    leavesMarker.addTo(macarte);

                    <%--if(mairies.get(m).getAdresse().getLatitude() == 0.00000000 || mairies.get(m).getAdresse().getLongitude()==0.00000000){
                           continue;}--%>




                }

                var mairieIcon = L.icon({iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
                        shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
                        iconSize: [25, 41],iconAnchor: [12, 41],popupAnchor: [1, -34],shadowSize: [41, 41]
                });

                function addPoint(lat, lon) {
                    var marker = L.marker([lat, lon]).addTo(macarte);
                }

                function addPointMairie(lat, lon) {
                    var marker = L.marker([lat, lon], {icon : mairieIcon}).addTo(macarte);
                    marker.bindPopUp("centre");
                }
                window.onload = function(){
                    // Fonction d'initialisation qui s'exécute lorsque le DOM est chargé
                    initMap();
                };
            </script>
        </div>
    </div>
    <jsp:include page="Footer.jsp"/>
    </body>
</html>