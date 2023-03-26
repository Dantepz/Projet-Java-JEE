package fr.esigelec.jee.servlet;

import fr.esigelec.jee.dao.MairieDAO;
import fr.esigelec.jee.models.Mairie;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Pattern;

@WebServlet(name = "RechercheMairie", value = "/RechercheMairie")
public class RechercheMairie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(true);

        session.removeAttribute("mairies");

        long start = System.currentTimeMillis();
        String zipcode = request.getParameter("zipcode");
        String mairieString = request.getParameter("mairie");

        out = response.getWriter();

        out.println("<h1>Your Zip code is : "+ zipcode+"</h1>");

        MairieDAO mdao = new MairieDAO();


        ArrayList<Mairie> mairies = new ArrayList<>();

        mairies.addAll(mdao.getMairiesByZipCode(zipcode,0,1000));

        Collections.sort(mairies);
        session.setAttribute("mairies",mairies);
        out.println("Run time "+ (System.currentTimeMillis() - start));

        response.sendRedirect("Index.jsp?mairie="+mairieString);
    }
}
