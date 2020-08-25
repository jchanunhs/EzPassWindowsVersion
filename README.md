# Ez Pass Application (Windows Version)

Created an EzPass simulation using MVC design patterns and Java 8.

- Microsoft SQL Server was used to store customer information.
- GUI was created using Java Swing (JTables, JList, JLabels, JTextFields/PasswordFields, Verticle Box Panels, JPanels, JFrame).
- Control objects handles user input submissions.
- Data access object and entity object work together to create and make changes to the customer information inside our database.

There are 5 tabs in the MainWindows

Profile
- Shows customer information (Name, address, balance on account, etc.).
- They are able to change their password, recharge their account using credit card information, logout.
- Transactions related to account recharge is displayed in a JTable.

Vehicle
- License plate is displayed in JList.
- Users add a vehicle to their account.
- User can select the license plate from the JList and click remove vehicle button to delete the vehicle from their account.

EZ Tags
- Tag code is displayed in JList.
- Users can add ez tag or remove it.
- JComboBox used for customer to select their tag type.
- Tag code can only be removed if it's not linked to a vehicle or never used to pay a toll.
- When user adds a vehicle, they also have to specify the ez tag associated with their vehicle.

Pay Toll
- User can pay toll by entering their license plate, tag code, toll plaza, lane number and amount charged.

Transaction
- Transactions are displayed in a JTable.
- Users can also enter date intervals to view transactions on specific dates.

