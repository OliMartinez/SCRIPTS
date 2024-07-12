<%-- 
    Document   : verInventario
    Created on : 5/07/2024, 08:22:02 PM
    Author     : olixe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.castores.models.Producto"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inventario</title>
    </head>
    <body>
        <h2>Inventario de Productos</h2>
        <%
            Integer idRol = (Integer) session.getAttribute("idRol");
            if (idRol != null && idRol == 1) { // 1 es el rol de administrador
        %>
        <h3>Agregar Nuevo Producto</h3>
        <form action="Inventario" method="post">
            <input type="hidden" name="action" value="agregar">
            Nombre del Producto: <input type="text" name="nombre" required><br>
            Precio: <input type="number" name="precio" required><br>
            <input type="submit" value="Agregar">
        </form>
        <h3>Entrada de Productos</h3>
        <form action="Inventario" method="post">
            <input type="hidden" name="action" value="entrada">
            <input type="hidden" name="nameUser" value="<%=session.getAttribute("nombre")%>">
            Producto: <select name="idProducto" required>
                <option>Elige una opción</option>
                <%
                    List<Producto> listaProductos = (List<Producto>) request.getAttribute("listaProductos");
                    for (Producto producto : listaProductos) {
                %>
                <option value="<%= producto.getIdProducto()%>"><%= producto.getNombre()%></option>
                <%
                    }
                %>
            </select><br>
            Cantidad: <input type="number" name="cantidad" id="cantidad" required><br>
            <input type="submit" value="Aumentar">
        </form>
        <div id="error-message" style="color: red; display: none;">El valor debe ser mayor que 0</div>
        <%
            }
        %>
        <br>
        <table border="1" id="inv">
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Estatus</th>
                <%if (idRol != null && idRol == 1) { %>
                <th>Acciones</th>
                <% } %>
            </tr>
            <%
                List<Producto> tablaProductos = (List<Producto>) request.getAttribute("listaProductos");
                for (Producto producto : tablaProductos) {
            %>
            <tr>
                <td><%= producto.getIdProducto()%></td>
                <td><%= producto.getNombre()%></td>
                <td><%= producto.getPrecio()%></td>
                <td><%= producto.getCantidad()%></td>
                <td class="estatus"><%= producto.getEstatus() == 1 ? "Activo" : "Inactivo"%></td>
                <%if (idRol != null && idRol == 1) { %>
                <td> <form action="Inventario" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="cambiarEstatus">
                        <input type="hidden" name="idProducto" value="<%= producto.getIdProducto()%>">
                        <input type="hidden" name="nuevoEstatus" value="<%= producto.getEstatus() == 1 ? 0 : 1%>">
                        <button type="submit" class="nuevoestatus">
                            <%= producto.getEstatus() == 1 ? "Baja" : "Alta"%>
                        </button>
                    </form>
                </td>
                <% } %>
            </tr>
            <%
                }
            %>    
        </table>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/inventario.js"></script>
    </body>
</html>
