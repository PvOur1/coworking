package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ListaReservaServlet", urlPatterns = {"/ListaReservaServlet"})
public class ListaReservaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<List<String>> reservas = (List<List<String>>) session.getAttribute("reservas");

        // si esta vacia inicializa la lista 
        if (reservas == null) {
            reservas = new ArrayList<>();
            session.setAttribute("reservas", reservas);
        }

        request.setAttribute("reservas", reservas);
        request.getRequestDispatcher("reservas.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Muestra la lista de reservas";
    }
}


