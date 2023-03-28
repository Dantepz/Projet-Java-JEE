<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="fr.esigelec.jee.*"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="fr.esigelec.jee.models.Mairie" %>
<%@ page import="fr.esigelec.jee.models.Equipement" %>
<%@ page import="java.util.HashMap" %>

<%

    HashMap<Equipement,Mairie> eqsMs = new HashMap<>();
    ArrayList<Equipement> equipements = new ArrayList<>();
    if(request.getSession().getAttribute("eqsMs") != null) {
        eqsMs = (HashMap<Equipement, Mairie>) request.getSession().getAttribute("eqsMs");
        equipements = new ArrayList<>(eqsMs.keySet());
    }
%>

<% String pageTitle = "Page Carte"; %>

<html>
<jsp:include page="Header.jsp" flush="true">
    <jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
<body>
<jsp:include page="Navbar.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div id="map" class="col-md-7">
        </div>
        <div id="equip_info" class="col-md-5" >
            <div class ="row align-items-center bg-dark">
                <div class="col my-3">
                    <h3 id="equip_title" class="text-center text-light-emphasis mt-auto" >Nothing for the moment</h3>
                </div>
            </div>
            <div class ="row align-items-center text-body" style="background-color:#4527A0">
                <div class="col gap-1">
                    <h6 class="text-center text-light-emphasis my-2">Informations liées à l'infrastructure</h6>
                    <div class="mt-2">
                        <span id="id-eq"></span>
                        <lable for="id-eq">Id de l'équipement :</lable>
                    </div>
                    <div class="mt-2">
                        <span id="nom-eq"></span>
                        <label for="nom-eq"> Nom de l'équipement :</label>
                    </div>
                    <div class="mt-2">
                        <span id="sup-eq"></span>
                        <label for="sup-eq">superficie :</label>
                    </div>
                    <div class="mt-2">
                        <span id="mairie-eq"></span>
                        <label for="mairie-eq">Mairie responsable : </label>
                    </div>
                    <div class="my-2">
                        <span id="type-eq"></span>
                        <label for="type-eq">Type d'équipement :</label>
                    </div>
                </div>
            </div>
            <div class ="row align-items-center text-body" style="background-color:#2E7D32">
                <div class="col">
                    <h6 class="text-center text-light-emphasis my-2">Informations liées à la mairie</h6>
                </div>
            </div>
        </div>

        <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==" crossorigin=""></script>
        <script type="text/javascript">
            // On initialise la latitude et la longitude de Paris (centre de la carte)
            var lat = 41.983417511;
            var lon = 8.79639816284;
            var macarte = null;
            // Fonction d'initialisation de la carte
            function initMap() {
                // Créer l'objet "macarte" et l'insèrer dans l'élément HTML qui a l'ID "map"
                <% if(eqsMs.size() != 0)
                {%>
                macarte = L.map('map').setView([<%=eqsMs.get(equipements.get(0)).getAdresse().getLatitude()%>, <%=eqsMs.get(equipements.get(0)).getAdresse().getLongitude()%>], 17);
                <%
                }else {%>
                macarte = L.map('map').setView(lat,lon,17);
                <%
                }
                %>// Leaflet ne récupère pas les cartes (tiles) sur un serveur par défaut. Nous devons lui préciser où nous souhaitons les récupérer. Ici, openstreetmap.fr
                L.tileLayer('https://{s}.tile.openstreetmap.fr/osmfr/{z}/{x}/{y}.png', {
                    // Il est toujours bien de laisser le lien vers la source des données
                    attribution: 'Données © <a href="//osm.org/copyright">OpenStreetMap</a>/ODbL - rendu <a href="//openstreetmap.fr">OSM France</a>',
                    minZoom: 1,
                    maxZoom: 20
                }).addTo(macarte);
                addPoint(lat, lon);

                var ma;
                <%
                        for(int m = 0;m < equipements.size();m++){ %>
                ma =  L.marker([<%=eqsMs.get(equipements.get(m)).getAdresse().getLatitude()%>, <%=eqsMs.get(equipements.get(m)).getAdresse().getLongitude()%>], {icon : mairieIcon}).addTo(macarte)<%--.bindPopup(<%=mairies.get(m).getNom()%>)--%>;
                ma.bindPopup("<%=eqsMs.get(equipements.get(m)).getNom()%>");
                ma.on('click', function (){
                    console.log("clicked");
                    document.getElementById("equip_title").innerHTML="<%=eqsMs.get(equipements.get(m)).getNom()%>";
                });
                <%--ma.on('click', function (event){
                     document.getElementById("")
                 });--%>
                <%}%>

                <%
                   for(Equipement eq : equipements){
                %>
                ma = L.marker([<%=eq.getEquipGpsy()%>,<%=eq.getEquipGpsx()%>], {incon : equipIcon}).addTo(macarte);
                ma.bindPopup("<%=eq.getNom()%>");
                <%--ma.on('click', function (event){
                    map.panTo(<%=eq.getEquipGpsy()%>, <%=eq.getEquipGpsx()%>);
                });--%>
                ma.on('click', function (){
                    console.log("clicked");
                    document.getElementById("equip_title").innerHTML="<%=eq.getNom()%>";
                    document.getElementById("id-eq").innerHTML="<%=eq.getId()%>";
                    document.getElementById("nom-eq").innerHTML="<%=eq.getNom()%>";
                    document.getElementById("sup-eq").innerHTML="<%=eq.getEquSurfEvol()%>";
                    document.getElementById("mairie-eq").innerHTML="<%=eqsMs.get(eq).getNom()%>";
                    document.getElementById("type-eq").innerHTML="<%=eq.getEquipTypeCodeId().getEquipTypeLib()%>";
                    macarte.
                });
                <%
                }%>
            }

            var mairieIcon = L.icon({iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
                shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
                iconSize: [25, 41],iconAnchor: [12, 41],popupAnchor: [1, -34],shadowSize: [41, 41]
            });

            var equipIcon = L.icon({iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png',
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

            /*document.getElementById("button").addEventListener("click",function (){
                document.getElementById("equip_title").innerHTML="New tiltle";
            });*/
        </script>
    </div>
</div>
<jsp:include page="Footer.jsp"/>
</body>
</html>