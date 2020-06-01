<%@page import="java.util.ArrayList"%>
<%@page import="model.Vehicle"%>
<%
Vehicle vehicle = new Vehicle((String)session.getAttribute("CID"));
ArrayList<String> vehicle_list = vehicle.getVehicles(); 
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Ez Pass Web Application</title>
        <link rel="stylesheet" href = "styles/content.css">
        <link rel="shortcut icon" href="images/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="author" content="Jason Chan">
        <meta name="description" content="Web implementation of the EzPassApplication">
    </head>

    <body>
        <div id="wrapper">

            <header>Ez Pass Web Application</header>

            <div class = "flexHorizontal">

                <div>
                    <aside>
                        <div class = "flexVertical">
                            <div class = "links">Website Directories</div>
                            <a href='./Main.jsp'>Profile</a>
                            <a href='./Vehicle.jsp'class = "active-link">Vehicle</a>
                            <a href='./EzTag.jsp'>EzTags</a>
                            <a href='./PayTolls.jsp'>Pay Tolls</a>
                            <a href='./Transactions.jsp'>Transactions</a>
                        </div>
                    </aside>
                </div>

                <div id = "space"> </div>

                <main> 

                    <h1 align ="center">Vehicles</h1>
                    <table>
                        <tr><th>License Plate Number</th></tr>
                                <%for (int i = 0; i < vehicle_list.size(); i++) {
                                %>
                        <tr><td><%=vehicle_list.get(i)%></td></tr>
                        <%}%>
                    </table>
                    <div align="center">    
                        <a href='./AddVehicle.jsp'>Add Vehicle</a>
                        <a href='./RemoveVehicle.jsp'>Remove Vehicle</a>
                    </div>

                    <div id = "date"> </div>

                </main>


            </div>


            <footer><small><em>
                        <br>Copyright © 2020 EzPassWebApplication</br>
                        <a href = "mailto:jchanunh@student.fdu.edu">jchanunh@student.fdu.edu</a>
                    </em></small></footer>

        </div>
        <script>
            var d = new Date();
            document.getElementById("date").innerHTML = d;
        </script>
    </body> 
</html>

