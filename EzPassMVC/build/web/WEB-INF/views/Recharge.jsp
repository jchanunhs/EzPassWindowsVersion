<%@page import="java.util.ArrayList"%>
<%@page import="model.CreditCard"%>
<%@page import="model.Customer"%>
<%Customer cus = new Customer((String) session.getAttribute("CID"));
    cus.setData();
    CreditCard card = new CreditCard((String) session.getAttribute("CID"));
    ArrayList<String> CreditID_list = card.getAllTransactions("CreditID");
    ArrayList<String> CN_list = card.getAllTransactions("CardNumber");
    ArrayList<String> date_list = card.getAllTransactions("Date");
    ArrayList<String> time_list = card.getAllTransactions("Time");
    ArrayList<String> cd_amt = card.getAllTransactions("CreditAmount");
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
                    <h1 align ="center">Recharge</h1>

                    <form name="Recharge" action="./RechargeControl" method="post">
                        <label for="CustomerID">Customer ID:</label>
                        <input type="text" name="CustomerID" value="<%=(String)session.getAttribute("CID")%>"readonly><br>
                        <label for="CurrentBalance">Current Balance:</label>
                        <input type="text" name="CurrentBalance" value="<%=String.valueOf(cus.getBalance())%>"readonly><br>
                        <label for="CardNumber">Card Number:</label>
                        <input type="text" name="CardNumber"><br>
                        <label for="Name">Name on card:</label>
                        <input type="text" name="Name"><br>
                        <label for="EXP">EXP:</label>
                        <input type="text" name="EXP"><br>
                        <label for="CVV">CVV:</label>
                        <input type="text" name="CVV"><br>
                        <label for="Credit">Add to balance:</label>
                        <input type="text" name="Credit"><br>

                        <input type="button" value="Recharge" onClick="checkInputs()">
                        <input type="reset" value="Reset">

                    </form>
                        <% if(request.getParameter("message")!=null){%>
                    <div id="message"><%=request.getParameter("message")%></div>    
                    <%}%>
                        <table>
                            <tr>
                                <th>Credit ID</th>
                                <th>Card Number</th>
                                <th>Date</th>
                                <th>Time</th>
                                <th>Credit Amount</th>
                            </tr>
                        <%
                            for(int i = 0; i < CreditID_list.size(); i++){                       
                        %>
                        <tr>
                            <td><%=CreditID_list.get(i)%></td>
                            <td><%=CN_list.get(i)%></td>
                            <td><%=date_list.get(i)%></td>
                            <td><%=time_list.get(i)%></td>
                            <td><%=cd_amt.get(i)%></td>
                        </tr>
                        
                        <%}%>
                        </table>
                    <div align="center">
                        <a href="javascript:window.close();">Log Off</a>
                    </div>
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
        CardNumber = document.Recharge.CardNumber.value;
        Name = document.Recharge.Name.value;
        EXP = document.Recharge.EXP.value;
        CVV = document.Recharge.CVV.value;
        Credit = document.Recharge.Credit.value;
        if(isNaN(Credit)){
            window.alert("Add to balance must be a number!");
        }
        else if (CardNumber == "" || Name == "" || EXP == "" || CVV == "" || Credit == "") {
            window.alert("One or more fields are empty! Please fill out all information!");
        }
        else {
            document.Recharge.submit();
        }
    }

</script>

<script>
    var d = new Date();
    document.getElementById("date").innerHTML = d;
</script>                        
                        
    </body> 
</html>

