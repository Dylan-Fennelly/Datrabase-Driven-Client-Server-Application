package org.application.dao;

import org.application.Core.Colours;
import org.application.exceptions.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDAO
{
public Connection getConnection() throws DAOException
    {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/gamefirearmdb";
        String username = "Dylan";
        String password = "Blacklockbox99!";
        Connection con = null;
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(Colours.RED+ "Class not found " + e.getMessage() + Colours.RESET);
            System.exit(1);
        }
        catch (SQLException e)
        {
            System.out.println("Connection failed " + e.getMessage());
        }
        System.out.println(Colours.GREEN + "Connection successful" + Colours.RESET);
        return con;
    }
    public void freeConnection(Connection con) throws DAOException
    {
        try
        {
            if(con != null)
            {
                con.close();
                con = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println(Colours.RED +"Failed to free the connection " + e.getMessage() + Colours.RESET);
            System.exit(1);
        }
    }
}
