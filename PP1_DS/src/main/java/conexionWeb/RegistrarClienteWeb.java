/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexionWeb;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/RegistrarClienteWeb "})
public class RegistrarClienteWeb extends HttpServlet {

      public void doGet(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String nombre = request.getParameter("NOMBRE");
    String apellido1 = request.getParameter("APELLIDO1");
    String apellido2 = request.getParameter("APELLIDO2");
    String cedula = request.getParameter("CEDULA");
    String telefono = request.getParameter("TELEFONO");
    String correo = request.getParameter("CORREO");
    String dia = request.getParameter("DIA");
    String mes = request.getParameter("MES");
    String año = request.getParameter("AÑO");
    
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Cliente registrado</title>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
    out.println("<link href='Inicio.css' rel='stylesheet' type='text/css'/>  "  );    
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>¡Usuario registrado!</h1>");
    out.println("<hr>");
    out.println("Gracias por resgitrarse" + "  " + nombre + "  " + apellido1 + "  "+apellido2+"   cedula:"+cedula+"<br>" );
    out.println("Su correo registrado es : " + "  " + correo + "<br>");
    out.println("Su numero de telefono registrado es : " + "  " + telefono + "<br>");
    out.println("Su fecha de nacimiento registrada es : " + dia+"/" + mes +"/"+año+ "<br>");
    out.print("<hr><a href=\"index.html\">Volver a pagina principal</a>");
    out.println("</body></html>");
  }

}
