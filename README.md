# EzPassApplication
Created an EzPass simulation in Java Language and using MVC design patterns.
- Used SQL Server JDBC Driver for application to communicate with database.
- GUI was created using Java Swing.
- Control objects process user input.
- Model object controls database access.

When user first signs up and logs in, they will see a CreateProfile panel that must be filled out to get customer information. After they fill out the form and relog, they will now see the main windows. The Main windows contains 5 tabs

Profile
- Shows customer information (Name, address, balance on account, etc.).
- They are able to change their password, recharge their account using credit card information, logout.

Vehicle
- License plate is displayed in JList.
- User can select the license plate and click remove vehicle button to delete it.
- They can also add a vehicle to their account.

EZ Tags
- Users can add ez tag or remove it.
- Tag code can only be removed if it's not linked to a vehicle or never used to pay a toll.
- When user adds a vehicle, they also have to specify the ez tag associated with their vehicle.

Pay Toll
- User can pay toll by entering their tag code, toll plaza, lane number and amount charged. 
- It will be time stamped and transaction will be saved.

Transaction
- Transactions are displayed in a JTable.
- Users can also enter date intervals to view transactions on specific dates.
