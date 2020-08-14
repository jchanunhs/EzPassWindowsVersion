package ezpassapplication.dao;

import java.util.*;
import java.sql.*;

public class DBConnection {

    private Connection connection;
    private String URL;
    private String User;
    private String Password;

    public DBConnection() {
        URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=TangClass;user=standardUser;password=standardUser";
        connection = null;
    }

    public Connection openConn() {
        try {
            connection = DriverManager.getConnection(URL);
        } catch (Exception e) {
            e.printStackTrace();
            connection = null;
        }
        return connection;
    }

    public void closeConn() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println("Can't close the database connection.");
        }
    }

    public Vector getNextRow(ResultSet rs, ResultSetMetaData rsmd) throws SQLException {
        Vector currentRow = new Vector();

        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
            switch (rsmd.getColumnType(i)) {
                case Types.VARCHAR:
                case Types.NVARCHAR:
                case Types.LONGVARCHAR:
                    currentRow.addElement(rs.getString(i));
                    break;
                case Types.INTEGER:
                    currentRow.addElement(new Long(rs.getLong(i)));
                    break;
                case Types.DOUBLE:
                    currentRow.addElement(new Double(rs.getDouble(i)));
                    break;
                case Types.FLOAT:
                    currentRow.addElement(new Float(rs.getFloat(i)));
                    break;
                default:
                    System.out.println("Type was: " + rsmd.getColumnTypeName(i));
            }
        }

        return currentRow;
    }
}