package fr.esigelec.jee.servlet;

import fr.esigelec.jee.dao.EquipementDAO;
import fr.esigelec.jee.models.Equipement;
import fr.esigelec.jee.models.EquipementType;
import fr.esigelec.jee.models.Mairie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "RechercheEquipements", value = "/RechercheEquipements")
public class RechercheEquipements extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Equipement> equipements = (ArrayList<Equipement>)request.getSession().getAttribute("equipements");
        ArrayList<Mairie> mairies = (ArrayList<Mairie>)request.getSession().getAttribute("mairies");

        HashMap<Equipement,Mairie> eqsMs = new HashMap<>();

        String search = (String) request.getSession().getAttribute("equipement");
        search = search.toLowerCase();
        /*

         */
        for(Equipement eq: equipements ) {
                for(Mairie ma : mairies){
                    String equNom = eq.getNom().toLowerCase();
                    if(equNom.contains(search) && eq.getMairie().equals(ma.getInsee())){
                        eqsMs.put(eq,ma);
                }
            }
        }

        request.getSession().setAttribute("eqsMs",eqsMs);
        response.sendRedirect("Index.jsp");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }
}
