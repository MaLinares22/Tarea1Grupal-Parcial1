package hn.uth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class SevletNumeral
 */
@WebServlet("/ServletBinario")
public class ServletBinario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletBinario() {
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

        String operacion = request.getParameter("operacion");
        String numero = request.getParameter("numero");
        String resultado = "Error en la conversión";
        String opNombre = "Operación desconocida";

        try{
            if("ADecimal".equals(operacion)) {
                // Binario a Decimal
                int decimal = Integer.parseInt(numero.trim(), 2);
                resultado = String.valueOf(decimal);
                opNombre = "Conversión de número binario a decimal";
            }else if("ABinario".equals(operacion)) {
                // Decimal a Binario
                int decimal = Integer.parseInt(numero.trim());
                resultado = Integer.toBinaryString(decimal);
                opNombre = "Conversión de número decimal a binario";
            }
        }catch (NumberFormatException e) {
            resultado = "Entrada inválida. Asegúrate de ingresar:";
            if("ADecimal".equals(operacion)) {
                resultado += " un número binario válido (solo 0 y 1)";
            }else if("ABinario".equals(operacion)) {
                resultado += " un número entero válido";
            }
        }catch(Exception e) {
            resultado = "Error inesperado: " + e.getMessage();
        }

        //HTML
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Resultado - Conversión Binaria</title>");
        out.println("<link rel='stylesheet' href='respuesta.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet Tarea 1 - Conversión Binaria/Decimal</h1>");
        out.println("<div class='info'>");
        out.println("<h2>Integrantes del Grupo:</h2>");
        out.println("<ul>");
        out.println("<li>Manuel Alejandro Linares - 202410011073</li>");
        out.println("<li>Jenson Geovany Arita Reyes - 201230010015</li>");
        out.println("<li>Michael Javier Turcios - 202020010114</li>");
        out.println("</ul>");
        out.println("</div>");
        
       
        out.println("<h3>Operación Realizada: " + opNombre + "</h3>");
        out.println("<table>");
        out.println("<tr><th>Entrada</th><th>Respuesta</th></tr>");
        out.println("<tr><td>" + numero + "</td><td>" + resultado + "</td></tr>");
        out.println("</table>");
       
        out.println("<br><a href='" + request.getContextPath() + 
                   "' style='color: #3498db; text-decoration: none; font-weight: bold;'>← Volver al menú principal</a>");
        
        out.println("</body>");
        out.println("</html>");
    }

}
