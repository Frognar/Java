<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

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
                    <h2>Floyd-Warshall shortest path algorithm</h2>
                    <p>
                        <i>In computer science, the Floyd–Warshall algorithm (also known as Floyd's algorithm, the Roy–Warshall algorithm,<br>
                        the Roy–Floyd algorithm, or the WFI algorithm) is an algorithm for finding shortest paths in a weighted graph with<br>
                        positive or negative edge weights (but with no negative cycles). A single execution of the algorithm will find the<br>
                        lengths (summed weights) of shortest paths between all pairs of vertices. Although it does not return details of the<br>
                        paths themselves, it is possible to reconstruct the paths with simple modifications to the algorithm. Versions of<br>
                        the algorithm can also be used for finding the transitive closure of a relation {\displaystyle R}R, or (in connection<br>
                        with the Schulze voting system) widest paths between all pairs of vertices in a weighted graph.</i><br>
                        <br>
                        -- en.wikipedia.org/wiki/Floyd–Warshall_algorithm
                    </p>
                    
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