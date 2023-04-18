package org.application.dao;

import org.application.dto.Weapon;
import org.application.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlWeaponDAO extends MySqlDAO implements IWeaponDAOInterface
{
    @Override
    public List<Weapon> findAllGuns() throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Weapon> weapons = new ArrayList<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM weapon";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("weapon_id");
                String name = rs.getString("weapon_name");
                String type = rs.getString("weapon_type");
                int ammoCapacity = rs.getInt("ammo_capacity");
                int damage = rs.getInt("damage");
                int rateOfFire = rs.getInt("rate_of_fire");
                float weight = rs.getFloat("weight");
                float reloadTime = rs.getFloat("reload_time");
                float accuracy = rs.getFloat("accuracy");
                int range = rs.getInt("range_of_effectiveness");
                float recoil = rs.getFloat("recoil");
                int attachmentSlots = rs.getInt("attachment_slots");
                int price = rs.getInt("price");
                Weapon g = new Weapon(id, name, type, ammoCapacity, damage, rateOfFire, weight, reloadTime, accuracy, range,recoil, attachmentSlots, price);
                weapons.add(g);
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
        return weapons;
    }

    @Override
    public Weapon findGunById(int id) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Weapon g = null;

        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM weapon WHERE weapon_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next())
            {
                String name = rs.getString("weapon_name");
                String type = rs.getString("weapon_type");
                int ammoCapacity = rs.getInt("ammo_capacity");
                int damage = rs.getInt("damage");
                int rateOfFire = rs.getInt("rate_of_fire");
                float weight = rs.getFloat("weight");
                float reloadTime = rs.getFloat("reload_time");
                float accuracy = rs.getFloat("accuracy");
                int range = rs.getInt("range_of_effectiveness");
                float recoil = rs.getFloat("recoil");
                int attachmentSlots = rs.getInt("attachment_slots");
                int price = rs.getInt("price");
                g = new Weapon(id, name, type, ammoCapacity, damage, rateOfFire, weight, reloadTime, accuracy, range,recoil, attachmentSlots, price);

            }

        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            this.freeConnection(con);
        }
        return g;
    }
}
