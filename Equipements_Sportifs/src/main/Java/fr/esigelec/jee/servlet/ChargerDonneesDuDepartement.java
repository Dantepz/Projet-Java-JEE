package fr.esigelec.jee.servlet;

import fr.esigelec.jee.dao.EquipementDAO;
import fr.esigelec.jee.dao.MairieDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ChargerDonneesDuDepartement", value = "/ChargerDonneesDuDepartement")
public class ChargerDonneesDuDepartement extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String zipcode = request.getParameter("zipcode");
        String equipementString  = request.getParameter("equipement");

        if(!zipcode.trim().matches("^[1-9][0-9]?.*")){
            response.sendRedirect("Index.jsp");
        }else{
            request.getSession().setAttribute("equipement",equipementString);
            String depcode = (String) request.getSession().getAttribute("depcode");

            if(zipcode.substring(0,2).equals(depcode)){
                response.sendRedirect("RechercheEquipements");
            }else {

                request.getSession().setAttribute("depcode", zipcode.substring(0, 2));

                request.getSession().removeAttribute("mairies");
                request.getSession().removeAttribute("equipements");

                MairieDAO mdao = new MairieDAO();
                EquipementDAO edao = new EquipementDAO();

                request.getSession().setAttribute("mairies", mdao.getMairiesByZipCode(zipcode, 0, 1000));
                request.getSession().setAttribute("equipements", edao.getEquipByZipCode(zipcode, 0, 1000));

                response.sendRedirect("RechercheEquipements");
            }
        }
    }
}
