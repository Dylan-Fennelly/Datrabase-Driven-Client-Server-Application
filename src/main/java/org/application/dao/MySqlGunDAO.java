package org.application.dao;

import org.application.dto.Gun;
import org.application.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlGunDAO extends MySqlDAO implements IGunDAOInterface
{
    @Override
    public List<Gun> findAllGuns() throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Gun> guns = new ArrayList<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM gun";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                int ammoCapacity = rs.getInt("ammoCapacity");
                float damage = rs.getFloat("damage");
                float rateOfFire = rs.getFloat("rateOfFire");
                float weight = rs.getFloat("weight");
                float reloadTime = rs.getFloat("reloadTime");
                float accuracy = rs.getFloat("accuracy");
                float recoil = rs.getFloat("recoil");
                int attachmentSlots = rs.getInt("attachmentSlots");
                int price = rs.getInt("price");
                Gun g = new Gun(id, name, type, ammoCapacity, damage, rateOfFire, weight, reloadTime, accuracy, recoil, attachmentSlots, price);
                guns.add(g);
            }
        }
        catch (SQLException e)
        {
            throw new DAOException("findAllGuns() " + e.getMessage());
        }
        finally
        {
            this.freeConnection(con);
        }
        return guns;
    }
}
