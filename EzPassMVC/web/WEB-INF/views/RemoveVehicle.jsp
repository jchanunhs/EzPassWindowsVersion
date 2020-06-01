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
                    <h1 align ="center">Remove Vehicle</h1>

                    <form name="RemoveVehicle" action="./RemoveVehicleControl" method="post">
                        <label for="LicensePlateNumber">License Plate Number:</label>
                        <input type="text" name="LicensePlateNumber"><br>

                        <input type="button" value="Remove Vehicle" onClick="checkInputs()">
                        <input type="reset" value="Reset">

                    </form>
                    <% if(request.getParameter("message")!=null){%>
                    <div id="message"><%=request.getParameter("message")%></div>    
                    <%}%>
                    <div id = "date"> </div>

                </main>

            </div>

            <footer><small><em>
                        <br>Copyright © 2020 EzPassWebApplication</br>
                        <a href = "mailto:jchanunh@student.fdu.edu">jchanunh@student.fdu.edu</a>
                    </em></small></footer>

        </div>



        <script language="JavaScript">

            function checkInputs()
            {
                var Prompts = "";
                LicensePlateNumber = document.RemoveVehicle.LicensePlateNumber.value;
                if (LicensePlateNumber == "") {
                    window.alert("Please enter the license plate number for the vehicle you wish to remove!");
                } else {
                    document.RemoveVehicle.submit();
                }
            }

        </script>

        <script>
            var d = new Date();
            document.getElementById("date").innerHTML = d;
        </script>
        
    </body> 
</html>
