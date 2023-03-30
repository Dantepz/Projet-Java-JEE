<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<ul class="navbar-nav mr-auto">
        <li class="nav-item">
            <a class="nav-link" href="Index.jsp">
                <i class="fa-solid fa-house-chimney fa-xl"></i>
                Accueil
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="Carte.jsp">
                <i class="fa-solid fa-xl fa-map-location-dot"></i>
                Carte
            </a>
        </li>
    </ul>
    <ul class="navbar-nav ms-auto">
        <li class="nav-item">
            <form action="ChargerDonneesDuDepartement" method="POST" class = "d-flex mt-3">
                <input class="form-control me-2" type="text" id="equipement" name="equipement" placeholder="Entrer le nom de l'équipement">
                <input class="form-control" type="text" id="zipcode" name="zipcode" placeholder="Entrer le code Postal" required>
                <button class="btn btn-success" role="submit">Go</button>
            </form>
        </li>
    </ul>


</nav>