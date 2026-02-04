package hn.uth;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Servlet implementation class ServletNumeral
 */
@WebServlet("/ServletNumeral")
public class ServletNumeral extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNumeral() {
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

        String operacion= request.getParameter("operacion");
        String resultado= "Error";
        String opName= "Operación desconocida";
        String inputDisplay= "";
        List<String> detalles= new ArrayList<>();  // Lista Para informacion adicional

        try{
            if("maximo".equals(operacion) || "minimo".equals(operacion)) {
                int n1 = Integer.parseInt(request.getParameter("num1"));
                int n2 = Integer.parseInt(request.getParameter("num2"));
                int n3 = Integer.parseInt(request.getParameter("num3"));
                inputDisplay = n1 + ", " + n2 + ", " + n3;

                if("maximo".equals(operacion)) {
                    int max = Math.max(n1, Math.max(n2, n3));
                    resultado = String.valueOf(max);
                    opName = "Encontrar el mayor de 3 números enteros";
                    detalles.add("Números analizados: " + inputDisplay);
                }else{
                    int min = Math.min(n1, Math.min(n2, n3));
                    resultado = String.valueOf(min);
                    opName = "Encontrar el menor de 3 números enteros";
                    detalles.add("Números analizados: " + inputDisplay);
                }
                
            }else if("repetido".equals(operacion)) {
             
                String numbersStr = request.getParameter("numbers");
                if(numbersStr == null || numbersStr.trim().isEmpty()) {
                    throw new IllegalArgumentException("La lista de números está vacía");
                }
                
                inputDisplay = numbersStr;
                
                String[] parts = numbersStr.split(",");
                List<Integer> nums = new ArrayList<>();
                for(String part : parts) {
                    part = part.trim();
                    if (!part.isEmpty()) {
                        nums.add(Integer.parseInt(part));
                    }
                }
                
                if (nums.isEmpty()) {
                    throw new IllegalArgumentException("No se proporcionaron números válidos");
                }
                
                Map<Integer, Integer> freq = new HashMap<>();
                for (int num : nums) {
                    freq.put(num, freq.getOrDefault(num, 0) + 1);
                }
                
                int maxCount = Collections.max(freq.values());
          
                List<Integer> modas = new ArrayList<>();
                for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                    if (entry.getValue() == maxCount) {
                        modas.add(entry.getKey());
                    }
                }
                
                if(modas.size() == 1) {
                    resultado = String.valueOf(modas.get(0));
                    detalles.add("Frecuencia: " + maxCount + " veces");
                }else{
                    resultado = String.join(", ", modas.stream()
                                                     .map(String::valueOf)
                                                     .toArray(String[]::new));
                    detalles.add("Hay " + modas.size() + " valores que se repiten " + 
                                maxCount + " veces cada uno");
                }
                opName = "Encontrar el valor que más se repite";
                detalles.add("Total de números analizados: " + nums.size());
                detalles.add("Números únicos encontrados: " + freq.size());
            }else{
                resultado = "Operación no reconocida";
            }
            
        }catch(NumberFormatException e) {
            resultado = "Error: Uno o más valores no son números enteros válidos";
            detalles.add("Asegúrate de ingresar solo números enteros");
        }catch(IllegalArgumentException e) {
            resultado = "Error: " + e.getMessage();
        }catch(Exception e) {
            resultado = "Error inesperado: " + e.getMessage();
        }

        //HTML de Respuesta
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Resultado - Operaciones con Números</title>");
        out.println("<link rel='stylesheet' href='respuesta.css'>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet Tarea 1 - Operaciones con Números</h1>");
        out.println("<div class='info'>");
        out.println("<h2>Integrantes del Grupo:</h2>");
        out.println("<ul>");
        out.println("<li>Manuel Alejandro Linares - 202410011073</li>");
        out.println("<li>Jenson Geovany Arita Reyes - 201230010015</li>");        
        out.println("<li>Michael Javier Turcios - 202020010114</li>");
        out.println("</ul>");
        out.println("</div>");
        out.println("<h3>Operación Realizada: " + opName + "</h3>");
        out.println("<table>");
        out.println("<tr><th>Entrada</th><th>Respuesta</th></tr>");
        out.println("<tr><td>" + inputDisplay + "</td><td>" + resultado + "</td></tr>");
        out.println("</table>");
        
        if (!detalles.isEmpty()) {
            out.println("<div class='detalle'>");
            out.println("<h4>Detalles:</h4>");
            out.println("<ul>");
            for (String detalle : detalles) {
                out.println("<li>" + detalle + "</li>");
            }
            out.println("</ul>");
            out.println("</div>");
        }
        out.println("<br><a href='" + request.getContextPath() + 
                   "' style='color: #3498db; text-decoration: none; font-weight: bold;'>← Volver al menú principal</a>");
        out.println("</body>");
        out.println("</html>");
    }

}
