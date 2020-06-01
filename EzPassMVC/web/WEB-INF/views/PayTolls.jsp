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
                            <a href='./Vehicle.jsp'>Vehicle</a>
                            <a href='./EzTag.jsp'>EzTags</a>
                            <a href='./PayTolls.jsp'class = "active-link">Pay Tolls</a>
                            <a href='./Transactions.jsp'>Transactions</a>
                        </div>
                    </aside>
                </div>

                <div id = "space"> </div>

                <main> 
                    <h1 align ="center">Pay Toll</h1>

                    <form name="PayToll" action="./PayTollControl" method="post">
                        <label for="CustomerID">Customer ID:</label>
                        <input type="text" name="CustomerID" value="<%=(String)session.getAttribute("CID")%>"readonly><br>
                        <label for="TagCode">Tag Code:</label>
                        <input type="text" name="TagCode"><br>
                        <label for="TollPlaza">Toll Plaza:</label>
                        <input type="text" name="TollPlaza"><br>
                        <label for="TollLane">Toll Lane:</label>
                        <input type="text" name="TollLane"><br>
                        <label for="TollAmount">Toll Amount:</label>
                        <input type="text" name="TollAmount"><br>


                        <input type="button" value="Pay Toll" onClick="checkInputs()">
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
                TagCode = document.PayToll.TagCode.value;
                TollPlaza = document.PayToll.TollPlaza.value;
                TollLane = document.PayToll.TollLane.value;
                TollAmount = document.PayToll.TollAmount.value;
                if (isNaN(TollLane) || isNaN(TollAmount)) {
                    window.alert("Toll Lane and Toll Amount must be a number!");
                } else if (TagCode == "" || TollPlaza == "" || TollLane == "" || TollAmount == "") {
                    window.alert("One or more fields are empty! Please fill out all information!");
                } else {
                    document.PayToll.submit();
                }
            }

        </script>

        <script>
            var d = new Date();
            document.getElementById("date").innerHTML = d;
        </script>
    </body> 
</html>

