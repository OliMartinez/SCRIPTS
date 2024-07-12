<% if ((Integer)session.getAttribute("idRol")==2){ %>
<%-- 
    Document   : salidaProductos
    Created on : 5/07/2024, 08:23:25 PM
    Author     : olixe
--%>

<%@page import="java.util.List"%>
<%@page import="com.castores.models.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Salida de Productos</title>
    </head>
    <body>
        <h2>Salida de Productos</h2>
        <form action="Inventario" method="post">
            <input type="hidden" name="action" value="salida">
            ID del Producto: <input type="number" name="idProducto" id="idProducto" required><br>
            Cantidad: <input type="number" name="cantidad" id="cantidad" required><br>
            <input type="hidden" name="nameUser" value="<%=session.getAttribute("nombre")%>">
            <input type="submit" value="Sacar">
            <div id="error-message" style="color: red; display: none;">El valor debe ser menor o igual que la cantidad total en el inventario</div>
        </form>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
            </tr>
            <%
                List<Producto> tablaProductos = (List<Producto>) request.getAttribute("listaProductos");
                for (Producto producto : tablaProductos) {
                    if (producto.getEstatus() == 1) {
            %>
            <tr>
                <td><%= producto.getIdProducto()%></td>
                <td><%= producto.getNombre()%></td>
                <td><%= producto.getPrecio()%></td>
                <td><%= producto.getCantidad()%></td>
            </tr>
            <%
                    }
                }
            %>    
        </table>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/salida.js"></script>
    </body>
</html>
<% } %>