<% if ((Integer)session.getAttribute("idRol")==1){ %>
<%-- 
    Document   : historialMovimientos
    Created on : 5/07/2024, 08:24:15 PM
    Author     : olixe
--%>

<%@page import="java.util.List"%>
<%@page import="com.castores.models.Historico"%>
<%@page import="com.castores.dao.HistoricoDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Historial de Movimientos</title>
    </head>
    <body>
        <h2>Historial de Movimientos</h2>
        Filtrar por tipo de movimiento:
        <select id="tipo" name="tipo">
            <option value="">Todos</option>
            <option value="entrada">Entrada</option>
            <option value="salida">Salida</option>
        </select>
        <table border="1" id="movimientosTable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ID Producto</th>
                    <th>Usuario</th>
                    <th>Tipo</th>
                    <th>Cantidad</th>
                    <th>Fecha</th>
                </tr>
            </thead>
            <tbody>
            <%
                HistoricoDAO dao = new HistoricoDAO();
                List<Historico> movimientos;
                movimientos = dao.selectAllMovimientos();

                for (Historico movimiento : movimientos) {
            %>
            <tr>
                <td><%= movimiento.getIdHist()%></td>
                <td><%= movimiento.getIdProducto()%></td>
                <td><%= movimiento.getNombreUsuario()%></td>
                <td><%= movimiento.getMovimiento()%></td>
                <td><%= movimiento.getCantidad()%></td>
                <td><%= movimiento.getFecha()%></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/historial.js"></script>
    </body>
</html>
<% } %>