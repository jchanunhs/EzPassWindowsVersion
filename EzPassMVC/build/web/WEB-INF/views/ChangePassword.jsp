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
                            <a href='./Main.jsp'class = "active-link">Profile</a>
                            <a href='./Vehicle.jsp'>Vehicle</a>
                            <a href='./EzTag.jsp'>EzTags</a>
                            <a href='./PayTolls.jsp'>Pay Tolls</a>
                            <a href='./Transactions.jsp'>Transactions</a>
                        </div>
                    </aside>
                </div>

                <div id = "space"> </div>

                <main> 
                    <h1 align ="center">Change Password</h1>
                    <form name="ChangePassword" action="./ChangePasswordControl" method="post"> 
                        <label for="Username">Username: </label>
                        <input type="text" name="Username" value="<%=(String) session.getAttribute("Username")%>" readonly><br>
                        <label for="Old">Old Password: </label>
                        <input type="password" name="Old"><br>
                        <label for="New">New Password: </label>
                        <input type="password" name="New"><br>
                        <label for="New1">New Password(re-enter): </label>
                        <input type="password" name="New1"><br>

                        <input type="button" value="Change Password" onClick="checkInputs()">
                        <input type="reset" value="Reset">

                    </form>
                    <% if (request.getParameter("message") != null) {%>
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
                Old = document.ChangePassword.Old.value;
                New = document.ChangePassword.New.value;
                New1 = document.ChangePassword.New1.value;
                if (Old == "" || New == "" || New1 == "") {
                    window.alert("One or more fields are empty! Please fill out all information!");
                } else if (New != New1) {
                    window.alert("Your new passwords are not same. Please enter the same one!");
                } else {
                    document.ChangePassword.submit();
                }
            }

        </script>

        <script>
            var d = new Date();
            document.getElementById("date").innerHTML = d;
        </script>


    </body> 
</html>


