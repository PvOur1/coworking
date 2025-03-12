<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<%
    List<List<String>> reservas = (List<List<String>>) request.getAttribute("reservas");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Reservas</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100 flex justify-center items-center min-h-screen">

    <div class="w-full max-w-2xl bg-white shadow-lg rounded-lg p-6">
        <h2 class="text-2xl font-bold text-center text-gray-800 mb-4">Lista de Reservas</h2>

        <table class="w-full border-collapse border border-gray-300">
            <thead>
                <tr class="bg-gray-200">
                    <th class="border border-gray-300 px-4 py-2">Nombre</th>
                    <th class="border border-gray-300 px-4 py-2">Fecha</th>
                    <th class="border border-gray-300 px-4 py-2">Espacio</th>
                    <th class="border border-gray-300 px-4 py-2">Duración (horas)</th>
                    <th class="border border-gray-300 px-4 py-2">Eliminar</th>
                </tr>
            </thead>
            <tbody>
                <!-- revisa el estado de la lista que no este vacia-->
                <% if (reservas != null && !reservas.isEmpty()) { 
                    for (int i = 0; i < reservas.size(); i++) { 
                        List<String> reserva = reservas.get(i);
                %>
                        <tr>
                            <td class="border border-gray-300 px-4 py-2"><%= reserva.get(0) %></td>
                            <td class="border border-gray-300 px-4 py-2"><%= reserva.get(1) %></td>
                            <td class="border border-gray-300 px-4 py-2"><%= reserva.get(2) %></td>
                            <td class="border border-gray-300 px-4 py-2"><%= reserva.get(3) %></td>
                            <td class="border border-gray-300 px-4 py-2">
                                <form action="EliminarReservaServlet" method="post">
                                    <input type="hidden" name="indice" value="<%= i %>">
                                    <button type="submit" class="bg-red-500 text-white px-4 py-1 rounded">Eliminar</button>
                                </form>
                            </td>
                        </tr>
                <% }} else { %>
                    <tr><td colspan="5" class="text-center py-4">No hay reservas registradas.</td></tr>
                <% } %>
            </tbody>
        </table>

        <div class="text-center mt-4">
            <a href="index.html" class="bg-gray-600 text-white font-semibold px-4 py-2 rounded-lg hover:bg-gray-700 transition duration-200">Volver</a>
        </div>
    </div>

</body>
</html>
