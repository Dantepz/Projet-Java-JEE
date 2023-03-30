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
    else{
        response.sendRedirect("Index.jsp");
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
                    <h3 id="equip_title" class="text-center text-light mt-auto" >Nothing for the moment</h3>
                </div>
            </div>
            <div class ="row align-items-center text-body">
                <form>

                    <!--Equipement information-->

                    <div class="mt-2 border rounded-3 bg-primary" data-bs-theme="blue">
                        <div class="col">
                            <br><h6 class="text-center text-light mb-3">Informations liées à l'infrastructure</h6>
                            <div class="mt-2 form-floating">
                                <input id="nom-eq" readonly class="form-control-plaintext"  placeholder="---" value="---">
                                <label for="nom-eq" class="text-light"> Nom de l'équipement :</label>
                            </div>
                            <div class="mt-2 form-floating">
                                <input class="form-control-plaintext" placeholder="nothing" readonly id="id-eq" value="---">
                                <label for="id-eq" class="text-light">Id de l'équipement :</label>
                            </div>
                            <div class="mt-2 form-floating">
                                <input readonly class="form-control-plaintext"  placeholder="---" id="sup-eq"value="---">
                                <label for="sup-eq" class="text-light">superficie :</label>
                            </div>
                            <div class="my-2  form-floating">
                                <input readonly class="form-control-plaintext"  placeholder="---" id="type-eq" value="---">
                                <label for="type-eq" class="text-light">Type d'équipement :</label>
                            </div>
                        </div>
                    </div>

                        <!--Informations mairies -->

                        <div class="mt-2 border rounded-3  bg-dark" data-bs-theme="dark">
                            <br><h6 class="text-center text-light mb-3">Informations liées à la mairie</h6><button id="mairie-location" type="button" onclick=""><i class="fa-solid fa-location-arrow" style="color: #ffffff;" ></i></button>
                            <div class="form-floating">
                                <input readonly class="form-control-plaintext"  placeholder="---" id="mairie-eq" value="---">
                                <label for="mairie-eq" class="text-light">Mairie responsable : </label>
                            </div>
                            <div class="form-floating">
                                <input readonly class="form-control-plaintext"  placeholder="---" id="mairie-adresse" value="---">
                                <label for="mairie-adresse" class="text-light">Adresse : </label>
                            </div>
                            <div class="form-floating">
                                <input readonly class="form-control-plaintext"  placeholder="---" id="mairie-tel" value="---">
                                <label for="mairie-tel" class="text-light">Téléphone : </label>
                            </div>
                            <div class="form-floating">
                                <input readonly class="form-control-plaintext"  placeholder="---" id="mairie-mail" value="---">
                                <label for="mairie-mail" class="text-light">Mail : </label>
                            </div>
                            <!--<div class="form-floating">
                                <input readonly class="form-control-plaintext"  placeholder="---" id="mairie-ouv1" value="---">
                                <label for="mairie-ouv1" class="text-light">Ouvertures : </label>
                            </div>-->
                        </div>

                </form>
            </div>
        </div>

        <script src="https://unpkg.com/leaflet@1.3.1/dist/leaflet.js" integrity="sha512-/Nsx9X4HebavoBvEBuyp3I7od5tA0UzAxs+j83KgC8PU0kgB4XiK4Lfe4y4cgBtaRJQEIFCW+oC506aPT2L1zw==" crossorigin=""></script>
        <script type="text/javascript">
            // On initialise la latitude et la longitude de Paris (centre de la carte)
            var lat = 41.983417511;
            var lon = 8.79639816284;
            var macarte = null;
            var zoom = 17;
            var dezoom=15;
            // Fonction d'initialisation de la carte

            function initMap() {
                //macarte = L.map('map').setView(lat,lon);
                // Créer l'objet "macarte" et l'insèrer dans l'élément HTML qui a l'ID "map"
                <% if(eqsMs.size() != 0)
                {%>
                macarte = L.map('map').setView([<%=eqsMs.get(equipements.get(0)).getAdresse().getLatitude()%>, <%=eqsMs.get(equipements.get(0)).getAdresse().getLongitude()%>], zoom);
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

                <%-- mairie informations --%>
                var ma;
                <%
                        for(int m = 0;m < equipements.size();m++){ %>
                ma =  L.marker([<%=eqsMs.get(equipements.get(m)).getAdresse().getLatitude()%>, <%=eqsMs.get(equipements.get(m)).getAdresse().getLongitude()%>], {icon : mairieIcon}).addTo(macarte)<%--.bindPopup(<%=mairies.get(m).getNom()%>)--%>;
                ma.bindPopup("<%=eqsMs.get(equipements.get(m)).getNom()%>");
                ma.on('click', function (){
                    macarte.flyTo([<%=eqsMs.get(equipements.get(m)).getAdresse().getLatitude()%>, <%=eqsMs.get(equipements.get(m)).getAdresse().getLongitude()%>], zoom);
                    document.getElementById("equip_title").innerHTML="<%=eqsMs.get(equipements.get(m)).getNom()%>";
                    document.getElementById("id-eq").value="---";
                    document.getElementById("nom-eq").value="---";
                    document.getElementById("sup-eq").value="---";
                    document.getElementById("type-eq").value="---";

                    //Mairie informations
                    document.getElementById("mairie-eq").value="<%=eqsMs.get(equipements.get(m)).getNom()%>";
                    document.getElementById("mairie-adresse").value="<%=eqsMs.get(equipements.get(m)).getAdresse().getLigne()%>, <%=eqsMs.get(equipements.get(m)).getAdresse().getCodePostal()%>";
                    document.getElementById("mairie-tel").value="<%=eqsMs.get(equipements.get(m)).getCoordonnees().getTelephone()%>";
                    document.getElementById("mairie-mail").value="<%=eqsMs.get(equipements.get(m)).getCoordonnees().getMail()%>";
                    document.getElementById("mairie-location").onclick="L.map('map').flyTo([<%=eqsMs.get(equipements.get(m)).getAdresse().getLatitude()%>, <%=eqsMs.get(equipements.get(m)).getAdresse().getLongitude()%>], dezoom);"
                });
                <%}%>
            <%-- Equipment informations --%>
                <%
                   for(Equipement eq : equipements){
                %>
                ma = L.marker([<%=eq.getEquipGpsy()%>,<%=eq.getEquipGpsx()%>], {incon : equipIcon}).addTo(macarte);
                ma.bindPopup("<%=eq.getNom()%>");

                ma.on('click', function (){
                    macarte.flyTo([<%=eq.getEquipGpsy()%>,<%=eq.getEquipGpsx()%>],zoom);
                    document.getElementById("equip_title").innerHTML="<%=eq.getNom()%>";
                    document.getElementById("id-eq").value="<%=eq.getId()%>";
                    document.getElementById("nom-eq").value="<%=eq.getNom()%>";
                    document.getElementById("sup-eq").value="<%=eq.getEquSurfEvol()%>";
                    document.getElementById("type-eq").value="<%=eq.getEquipTypeCodeId().getEquipTypeLib()%>";
                    <%--document.getElementById("mairie-location").onclick="L.map('map').flyTo([<%=eqsMs.get(eq).getAdresse().getLatitude()%>,<%=eqsMs.get(eq).getAdresse().getLongitude()%>],dezoom)"
                    --%>

                    var bt = document.getElementById("mairie-location");
                    bt.addEventListener('click', function(e){
                        e.preventDefault();
                        macarte.flyTo([<%=eqsMs.get(eq).getAdresse().getLatitude()%>,<%=eqsMs.get(eq).getAdresse().getLongitude()%>],dezoom);

                        L.marker([<%=eqsMs.get(eq).getAdresse().getLatitude()%>,<%=eqsMs.get(eq).getAdresse().getLongitude()%>], {icon : mairieIcon}).bindPopup("<%=eqsMs.get(eq).getNom()%>").openOn(macarte);
                    });
                     //Mairie informations
                    document.getElementById("mairie-eq").value="<%=eqsMs.get(eq).getNom()%>";
                    document.getElementById("mairie-adresse").value="<%=eqsMs.get(eq).getAdresse().getLigne()%>, <%=eqsMs.get(eq).getAdresse().getCodePostal()%>";
                    document.getElementById("mairie-tel").value="<%=eqsMs.get(eq).getCoordonnees().getTelephone()%>";
                    document.getElementById("mairie-mail").value="<%=eqsMs.get(eq).getCoordonnees().getMail()%>";

                });
                <%
                }%>
            };

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
            };




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