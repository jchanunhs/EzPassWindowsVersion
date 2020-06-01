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
                    <h1 align ="center">Customer Profile</h1>


                    <form>
                        <label for="Name">Customer Name:</label>
                        <input type="text" name="Name" value="<%=(String) session.getAttribute("Name")%>" readonly><br>
                        <label for="Street">Street:</label>
                        <input type="text" name="Street" value="<%=(String) session.getAttribute("Street")%>" readonly><br>
                        <label for="City">City:</label>
                        <input type="text" name="City" value="<%=(String) session.getAttribute("City")%>" readonly><br>
                        <label for="State">State:</label>
                        <input type="text" name="State" value="<%=(String) session.getAttribute("State")%>" readonly><br>                                  
                        <label for="Zip">Zip:</label>
                        <input type="text" name="Zip" value="<%=(String) session.getAttribute("Zip")%>" readonly><br>
                        <label for="Phone">Phone:</label>
                        <input type="text" name="Phone" value="<%=(String) session.getAttribute("Phone")%>" readonly><br>
                        <label for="Email">Email:</label>
                        <input type="text" name="Email" value="<%=(String) session.getAttribute("Email")%>" readonly><br>
                        <label for="Balance">Balance:</label>
                        <input type="text" name="Balance" value="<%=String.valueOf(session.getAttribute("Balance"))%>" readonly><br>        
                    </form>
                    <div align="center">
                        <a href='./ChangePassword.jsp'>Change Password</a>
                        <a href='./Recharge.jsp'>Recharge Account</a>
                        <a href="./LogOutControl">Log Off</a>
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



