package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ReservaServlet", urlPatterns = {"/ReservaServlet"})
public class ReservaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html"); // Redirige al formulario de reservas
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Obtener los datos del form
        String NombreUsuario = request.getParameter("nombre");
        String FechaReserva = request.getParameter("fecha");
        String EspacioTrabajo = request.getParameter("espacio");
        String duracionStr = request.getParameter("duracion");

        // Validar que los campos no esten vacios
        if (NombreUsuario.isEmpty() || FechaReserva.isEmpty() || EspacioTrabajo.isEmpty() || duracionStr.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("index.html").forward(request, response);
            return;
        }
        
        //Validar que la fecha que se ingresa sea mayor a 0+
        int duracion;
        try {
            duracion = Integer.parseInt(duracionStr);
            if (duracion <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "La duración debe ser un número mayor a 0.");
            request.getRequestDispatcher("index.html").forward(request, response);
            return;
        }

        // Crear la reserva como lista de Strings
        List<String> reserva = new ArrayList<>();
        reserva.add(NombreUsuario);
        reserva.add(FechaReserva);
        reserva.add(EspacioTrabajo);
        reserva.add(String.valueOf(duracion));

        // Obtener la lista de reservas desde la sesión
        HttpSession session = request.getSession();
        List<List<String>> reservas = (List<List<String>>) session.getAttribute("reservas");
        //verifica que la lista no este vacio
        if (reservas == null) {
            reservas = new ArrayList<>();
            session.setAttribute("reservas", reservas);
        }

        reservas.add(reserva);

        // Redirigir a la lista de reservas
        response.sendRedirect("ListaReservaServlet");
    }

}
