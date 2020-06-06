<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>
<html>
    <head>
        <title>Graphs / Floyd-Warshall</title> 
        <meta charset=UTF-8> 
        <meta name=viewport content="width=device-width, initial-scale=1.0"> 
        <meta name=author content="Sebastian Przyszlak"> 
        <meta name=description content="Graphs representation and Floyd-Warshall shortest path algorithm"/>
        <link rel=stylesheet href=style.css>
    </head>

    <body>
        <main>
            <header>
                <h1 class=logo>Graphs / Floyd-Warshall</h1>
                <nav id=topnav>
                    <ul class=menu>
                        <li><a href="/Graphs">Home</a></li>
                        <li><a href="/Graphs/Graphs">FW application</a></li>
                    </ul>
                </nav>
            </header>

            <article>
                <section>
                    <section class="table">
                        <h1>List of Vertex currently in Database</h1>
                        <table>
                            <tr>
                                <th bgcolor=>ID</th>
                                <th bgcolor=>LABEL</th>
                            </tr>
                            <c:forEach var="vertex" begin="0" items="${requestScope.vertexList}">
                                <tr>
                                    <td>${vertex.id}&nbsp;&nbsp;</td> 
                                    <td>${vertex.label}&nbsp;&nbsp;</td> 
                                </tr> 
                            </c:forEach>
                        </table>
                    </section>

                    <section class="table">
                        <h1>List of Edges currently in Database</h1>
                        <table>
                            <tr>
                                <th bgcolor=>ID</th>
                                <th bgcolor=>START VERTEX</th>
                                <th bgcolor=>END VERTEX</th>
                                <th bgcolor=>WEIGHT</th>
                            </tr>
                            <c:forEach var="edge" begin="0" items="${requestScope.edgeList}">
                                <tr>
                                    <td>${edge.id}&nbsp;&nbsp;</td> 
                                    <td>${edge.startVertex.id}&nbsp;&nbsp;</td> 
                                    <td>${edge.endVertex.id}&nbsp;&nbsp;</td> 
                                    <td>${edge.weight}&nbsp;&nbsp;</td> 
                                </tr> 
                            </c:forEach>
                        </table>
                    </section>
                </section>
            </article>

            <footer>
                <section>
                    author: Sebastian Przyszlak<br>
                    Project: Graphs / Floyd-Warshall algorithm<br>
                    <a href="https://github.com/przyszlak">GitHub</a>
                </section>
            </footer>
        </main>
    </body>

</html>
