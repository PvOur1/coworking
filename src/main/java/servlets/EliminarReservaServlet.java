package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EliminarReservaServlet", urlPatterns = {"/EliminarReservaServlet"})
public class EliminarReservaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener el índice de la reserva a eliminar
        String indiceStr = request.getParameter("indice");

        if (indiceStr != null) {
            try {
                int indice = Integer.parseInt(indiceStr);
                
                // Obtener la sesion y la lista de reservas
                HttpSession session = request.getSession();
                List<List<String>> reservas = (List<List<String>>) session.getAttribute("reservas");

                // Validar  la lista y el índice sean correctos
                if (reservas != null && indice >= 0 && indice < reservas.size()) {
                    reservas.remove(indice);
                    session.setAttribute("reservas", reservas); // Actualizar la sesión
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Error: Índice no válido.");
            }
        }

        // Recarga de nuevo la lista de reservas
        response.sendRedirect("ListaReservaServlet");
    }
}
