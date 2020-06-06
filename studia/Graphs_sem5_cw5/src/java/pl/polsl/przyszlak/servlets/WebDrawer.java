package pl.polsl.przyszlak.servlets;

import java.io.PrintWriter;

/**
 *
 * @author Frogi
 */
public class WebDrawer {

    /**
     * Print HTML head section
     *
     * @param out to print to website
     */
    protected void headSection(PrintWriter out) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Graphs / Floyd-Warshall</title>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<meta name=\"author\" content=\"Sebastian Przyszlak\">");
        out.println("<meta name=\"description\" content=\"Graphs representation and Floyd-Warshall shortest path algorithm\"/>\n");
        out.println("<link rel=\"stylesheet\" href=\"style.css\">");
        out.println("</head>");
    }

    /**
     * Print start of body section
     *
     * @param out to print to website
     */
    protected void startBodySection(PrintWriter out) {
        out.println("<body>");
        out.println("<main>");
        out.println("<header>");
        out.println("<h1 class=\"logo\">Graphs / Floyd-Warshall</h1>");
        out.println("<nav id=\"topnav\">");
        out.println("<ul class=\"menu\">");
        out.println("<li><a href=\"/Graphs\">Home</a></li>");
        out.println("<li><a href=\"/Graphs/Graphs\">FW application</a></li>");
        out.println("<li><a href=\"/Graphs/LogoutServlet\">Logout</a></li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</header>");
        out.println("<article>");
        out.println("<section>");
    }

    /**
     * Print start of body section
     *
     * @param out to print to website
     */
    protected void startBodySectionForLogin(PrintWriter out) {
        out.println("<body>");
        out.println("<main>");
        out.println("<header>");
        out.println("<h1 class=\"logo\">Graphs / Floyd-Warshall</h1>");
        out.println("<nav id=\"topnav\">");
        out.println("<ul class=\"menu\">");
        out.println("<li><a href=\"/Graphs\">Home</a></li>");
        out.println("<li><a href=\"/Graphs/Graphs\">FWI application</a></li>");
        out.println("</ul>");
        out.println("</nav>");
        out.println("</header>");
        out.println("<article>");
        out.println("<section>");
    }

    /**
     * Print form section
     *
     * @param out to print to website
     */
    protected void formSection(PrintWriter out) {
        out.println("<h2>Add vertex</h2>");
        out.println("<form action=AddVertex method=POST>");
        out.println("<input type=text size=20 placeholder=\"Vertex label\" name=vertexLabel><br>");
        out.println("<input type=submit value=Add />");
        out.println("</form>");

        out.println("<h2>Add edge</h2>");
        out.println("<form action=AddEdge method=POST>");
        out.println("<input type=text size=20 placeholder=\"Start vertex label\" name=startVertexLabel><br>");
        out.println("<input type=text size=20 placeholder=\"End vertex label\" name=endVertexLabel><br>");
        out.println("<input type=text size=20 placeholder=\"Weight\" name=weight><br>");
        out.println("<input type=submit value=Add />");
        out.println("</form>");

        out.println("<h2>Graph</h2>");
        out.println("<form action=ShowGraph method=POST>");
        out.println("<input type=submit value=Show />");
        out.println("</form>");
        
        out.println("<h2>Database</h2>");
        out.println("<form action=ShowDatabase method=POST>");
        out.println("<input type=submit value=Show />");
        out.println("</form>");

        out.println("<h2>Floyd Warshall</h2>");
        out.println("<form action=FloydWarshall method=POST>");
        out.println("<input type=submit value=Calculate />");
        out.println("</form>");
    }

    /**
     * Print exception message
     *
     * @param out to print to website
     * @param msg exception message
     */
    protected void exception(PrintWriter out, String msg) {
        out.println("<div class=Exception>");
        out.println("Exception: " + msg);
        out.println("</div>");
    }

    /**
     * Print end of body section
     *
     * @param out to print to website
     */
    protected void endBodySection(PrintWriter out) {
        out.println("</section>");
        out.println("</article>");
        out.println("<footer>");
        out.println("<section>");
        out.println("author: Sebastian Przyszlak<br>");
        out.println("Project: Graphs / Floyd-Warshall algorithm<br>");
        out.println("<a href=\"https://github.com/przyszlak\">GitHub</a>");
        out.println("</section>");
        out.println("</footer>");
        out.println("</main>");
        out.println("</body>");
        out.println("</html>");
    }

}
