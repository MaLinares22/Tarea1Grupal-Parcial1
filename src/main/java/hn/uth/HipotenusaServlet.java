package hn.uth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class HipotenusaServlet
 */
@WebServlet("/HipotenusaServlet")
public class HipotenusaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HipotenusaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String aCateto= request.getParameter("a");
        String bCateto= request.getParameter("b");
        String resultado= "";
        String formula= "";
        String detalle= "";
        
        boolean error = false;
        try {
            double a= Double.parseDouble(aCateto.trim());
            double b= Double.parseDouble(bCateto.trim());
            double c= Math.sqrt(a * a + b * b); //Formula
            formula= "c = √(a² + b²)";
            
            //Verificar el resultado de la formula
            if (Double.isNaN(c)) {
                resultado = "No existe solución real (a² < b²)";
                error = true;
                detalle += "<br><span style='color: red;'>⚠ Advertencia: a debe ser mayor que b</span>";
            } else {
                resultado = String.format("%.6f", c);
            }
        //Errores       
        }catch (NumberFormatException e) {
            resultado = "Error: Los valores deben ser números válidos";
            error = true;
        } catch (Exception e) {
            resultado = "Error inesperado: " + e.getMessage();
            error = true;
        }

        //HTML de Respuesta
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Resultado - Cálculo de Hipotenusa</title>");
        out.println("<link rel='stylesheet' href='respuesta.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet Tarea 1 - Cálculo de Hipotenusa</h1>");
        out.println("<div class='info'>");
        out.println("<h2>Integrantes del Grupo:</h2>");
        out.println("<ul>");
        out.println("<li>Manuel Alejandro Linares - 202410011073</li>");
        out.println("<li>Jenson Geovany Arita Reyes - 201230010015</li>");
        out.println("<li>Michael Javier Turcios - 202020010114</li>");
        out.println("</ul>");
        out.println("</div>");
        
        out.println("<h3>Operación Realizada</h3>");
        out.println("<table>");
        out.println("<tr><th>Cateto a</th><th>Cateto b</th><th>Resultado</th></tr>");
        out.println("<tr><td>" + aCateto + "</td><td>" + bCateto + "</td><td>" + 
                   (error ? "<span style='color: red;'>" + resultado + "</span>" : resultado) + 
                   "</td></tr>");
        out.println("</table>");
        
        if (!error) {
            out.println("<div style='margin-top: 20px;'>");
            out.println("<h4>Verificación:</h4>");
            out.println("<ul>");
            out.println("<li>a² = " + String.format("%.2f", Double.parseDouble(aCateto) * Double.parseDouble(aCateto)) + "</li>");
            out.println("<li>b² = " + String.format("%.2f", Double.parseDouble(bCateto) * Double.parseDouble(bCateto)) + "</li>");
            out.println("<li>a² + b² = " + String.format("%.2f", (Double.parseDouble(aCateto) * Double.parseDouble(aCateto) + Double.parseDouble(bCateto) * Double.parseDouble(bCateto))) + "</li>");
            out.println("</ul>");
            out.println("</div>");
        }
        out.println("<br><a href='" + request.getContextPath() + 
                   "' style='color: #3498db; text-decoration: none; font-weight: bold;'>← Volver al menú principal</a>");
        out.println("</body>");
        out.println("</html>");
    }

}
