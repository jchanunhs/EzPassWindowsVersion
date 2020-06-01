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
                    <h1 align ="center">Add Vehicle</h1>

                    <form name="AddVehicle" action="./AddVehicleControl" method="post">
                        <label for="LicensePlateNumber">License Plate Number:</label>
                        <input type="text" name="LicensePlateNumber"><br>
                        <label for="Make">Make:</label>
                        <input type="text" name="Make"><br>
                        <label for="Model">Model:</label>
                        <input type="text" name="Model"><br>
                        <label for="Year">Year:</label>
                        <input type="text" name="Year"><br>                                  
                        <label for="Color">Color:</label>
                        <input type="text" name="Color"><br>
                        <label for="TagCode">Tag Code:</label>
                        <input type="text" name="TagCode"><br>

                        <input type="button" value="Add Vehicle" onClick="checkInputs()">
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
                LicensePlateNumber = document.AddVehicle.LicensePlateNumber.value;
                Make = document.AddVehicle.Make.value;
                Model = document.AddVehicle.Model.value;
                Year = document.AddVehicle.Year.value;
                Color = document.AddVehicle.Color.value;
                TagCode = document.AddVehicle.TagCode.value;

                if (LicensePlateNumber == "" || Make == "" || Model == "" || Year == "" || Color == "" || TagCode == "") {
                    window.alert("One or more fields are empty! Please fill out all information!");
                } else {
                    document.AddVehicle.submit();
                }
            }

        </script>

        <script>
            var d = new Date();
            document.getElementById("date").innerHTML = d;
        </script>

    </body> 
</html>

