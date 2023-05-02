package org.application.dao;

import com.google.gson.Gson;
import org.application.dto.Weapon;
import org.application.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySqlWeaponDAO extends MySqlDAO implements IWeaponDAOInterface
{
    //The hashset is used to store the ids of the guns that are within the database
    private HashSet<Integer> gunIds = new HashSet<>();

    public MySqlWeaponDAO() throws DAOException
    {
        super();
        Connection con = null;
        try
        {
             con = this.getConnection();
            this.updateIDCache(con);
        }
        catch (SQLException e)
        {
            throw new DAOException("MySqlWeaponDAO() " + e.getMessage());
        }
        finally
        {
            this.freeConnection(con);
        }

    }
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
        if(!gunIds.contains(id))
        {
            System.out.println("Gun with id " + id + " does not exist");
        }
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

    //The deleteGunById() method deletes a gun from the database by its id.
    //It returns true if the gun was deleted successfully, or false if it was not.

    @Override
    public boolean deleteGunById(int id) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean deleted = false;

        try
        {
            con = this.getConnection();
            String query = "DELETE FROM weapon WHERE weapon_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0)
            {
                deleted = true;
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            updateIDCache(con);
            this.freeConnection(con);
        }

        return deleted;
    }

    //The insertGun() method inserts a new gun into the database.
    //It returns true if the gun was inserted successfully, or false if it was not.
    @Override
    public boolean insertGun(Weapon gun) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean inserted = false;

        try
        {
            con = this.getConnection();
            String query = "INSERT INTO weapon (weapon_name, weapon_type, ammo_capacity, damage, rate_of_fire, weight, reload_time, accuracy, range_of_effectiveness, recoil, attachment_slots, price) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setString(1, gun.getName());
            ps.setString(2, gun.getType());
            ps.setInt(3, gun.getAmmoCapacity());
            ps.setInt(4, gun.getDamage());
            ps.setInt(5, gun.getRateOfFire());
            ps.setFloat(6, gun.getWeight());
            ps.setFloat(7, gun.getReloadTime());
            ps.setFloat(8, gun.getAccuracy());
            ps.setInt(9, gun.getRange());
            ps.setFloat(10, gun.getRecoil());
            ps.setInt(11, gun.getAttachmentSlots());
            ps.setInt(12, gun.getPrice());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected > 0)
            {
                inserted = true;
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            updateIDCache(con);
            this.freeConnection(con);
        }

        return inserted;
    }

    //This method is used to update the cache of gun ids that are in the database
    //It returns true if the cache was updated successfully, or false if it was not.
    @Override
    public void updateIDCache(Connection con) throws DAOException
    {
        PreparedStatement ps = null;
        ResultSet rs = null;

        gunIds.clear();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM weapon";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("weapon_id");
                gunIds.add(id);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    //Finds all guns in the database and returns them as a JSON string
    //Gson is used to convert the list of guns to a JSON string
    @Override
    public String findAllGunsJSON() throws DAOException
    {
        List<Weapon> weapons = findAllGuns();
        Gson gson = new Gson();
        return gson.toJson(weapons);

    }
    @Override
    public String findGunByIdJSON(int id) throws DAOException
    {
        Weapon g = findGunById(id);
        Gson gson = new Gson();
        return gson.toJson(g);
    }

    @Override
    public List<Weapon> findGunsByFilter(Comparator comparator) throws DAOException
    {
        List<Weapon> weapons = findAllGuns();
        Collections.sort(weapons, comparator);
        return weapons;
    }

}
