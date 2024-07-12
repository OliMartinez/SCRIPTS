package com.castores.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.castores.dao.UsuarioDAO;
import com.castores.models.Usuario;

@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO;

    public Login() {
        this.usuarioDAO = new UsuarioDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String contrasena = request.getParameter("contrasena");

        Usuario usuario = usuarioDAO.selectUser(nombre, contrasena);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("idUsuario", usuario.getIdUsuario());
            session.setAttribute("nombre", usuario.getNombre());
            session.setAttribute("idRol", usuario.getIdRol());

            if (usuario.getIdRol() == 1) {
                response.sendRedirect("admin.jsp");
            } else if (usuario.getIdRol() == 2) {
                response.sendRedirect("almacenista.jsp");
            }
        } else {
            response.sendRedirect("index.jsp?error=1");
        }
    }
}