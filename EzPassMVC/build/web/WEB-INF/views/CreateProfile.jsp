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


                        </div>
                    </aside>
                </div>

                <div id = "space"> </div>


                <main> 
                    <h1 align ="center">Customer Profile</h1>


                    <form name="CreateProfile" action="./CreateProfileControl" method="post">
                        <label for="Name">Customer Name:</label>
                        <input type="text" name="Name" value="<%=(String) session.getAttribute("Name")%>"readonly><br>
                        <label for="Street">Street:</label>
                        <input type="text" name="Street"><br>
                        <label for="City">City:</label>
                        <input type="text" name="City"><br>
                        <label for="State">State:</label>
                        <input type="text" name="State"><br>                                  
                        <label for="Zip">Zip:</label>
                        <input type="text" name="Zip"><br>
                        <label for="Phone">Phone:</label>
                        <input type="text" name="Phone"><br>
                        <label for="Email">Email:</label>
                        <input type="text" name="Email"><br>
                        <label for="Balance">Balance:</label>
                        <input type="text" name="Balance"><br>   
                        <input type="button" value="Create Profile" onClick="checkInputs()">
                        <input type="reset" value="Reset">

                    </form>                              
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
                Name = document.CreateProfile.Name.value;
                Street = document.CreateProfile.Street.value;
                City = document.CreateProfile.City.value;
                State = document.CreateProfile.State.value;
                Zip = document.CreateProfile.Zip.value;
                Phone = document.CreateProfile.Phone.value;
                Email = document.CreateProfile.Email.value;
                Balance = document.CreateProfile.Balance.value;
                if (Name == "" || Street == "" || City == "" || State == "" || Zip == "" || Phone == "" || Email == "" || Balance == "") {
                    window.alert("One or more fields are empty! Please fill out all information!");
                } else {
                    document.CreateProfile.submit();
                }
            }

        </script>

        <script>
            var d = new Date();
            document.getElementById("date").innerHTML = d;
        </script>

    </body> 
</html>

