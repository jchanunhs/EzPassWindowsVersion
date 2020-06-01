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
                            <a href = "./index.jsp" class = "active-link">Login</a>
                            <a href = "./SignUp.jsp">Sign Up</a>
                            
                        </div>
                    </aside>
                </div>
                <div id = "space"> </div>
                <main> 
                    <h1 align ="center">Sign In</h1>
                    <form name="SignIn" action="./LoginControl" method ="get"> 
                        <label for="Username">Username: </label>
                        <input type="text" name="Username"><br>
                        <label for="Password">Password: </label>
                        <input type="password" name="Password"><br>

                        <input type="button" value="Login" onClick="checkInputs()">
                        <input type="reset" value="Reset">
                    </form>
                    <% if(request.getAttribute("message")!=null){%>
                    <div id="message"><%=request.getAttribute("message")%></div>    
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
                Username = document.SignIn.Username.value;
                Password = document.SignIn.Password.value;

                if (Username == "" || Password == "") {
                    if (Username == "")
                        Prompts += "Please enter your username!\n";
                    if (Password == "")
                        Prompts += "Please enter your password!\n";
                    if (Prompts != "")
                        window.alert(Prompts);
                } else {
                    document.SignIn.submit();
                }
            }

        </script>

        <script>
            var d = new Date();
            document.getElementById("date").innerHTML = d;
        </script>
    </body> 
</html>