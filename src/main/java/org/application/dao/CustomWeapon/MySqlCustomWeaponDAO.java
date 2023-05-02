package org.application.dao.CustomWeapon;

import com.google.gson.Gson;
import org.application.dao.Attachment.MySqlAttachmentDao;
import org.application.dao.MySqlDAO;
import org.application.dao.Weapon.MySqlWeaponDAO;
import org.application.dto.Attachment;
import org.application.dto.CustomWeapon;
import org.application.dto.Weapon;
import org.application.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySqlCustomWeaponDAO extends MySqlDAO implements ICustomWeaponDAO
{
    private MySqlWeaponDAO weaponDAO;
    private MySqlAttachmentDao attachmentDAO;
    private HashSet<Integer> idCache = new HashSet<>();

    public MySqlCustomWeaponDAO() throws DAOException
    {
        super();
        this.weaponDAO = new MySqlWeaponDAO();
        this.attachmentDAO = new MySqlAttachmentDao();
        Connection con = null;
        try
        {
            con = this.getConnection();
            this.updateIDCache(con);
        }
        catch(SQLException e)
        {
            throw new DAOException("CustomWeaponDAO() " + e.getMessage());
        }
        finally
        {
            this.freeConnection(con);
        }
    }

    @Override
    public List<CustomWeapon> getAllCustomWeapons() throws DAOException
    {
        List<Weapon> weapons = weaponDAO.findAllGuns();
        List<Attachment> attachments = attachmentDAO.getAllAttachments();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CustomWeapon> customWeapons = new ArrayList<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT c.custom_weapon_id, c.custom_weapon_name, c.weapon_id, ca.attachment_id " +
                    "FROM custom_weapon c LEFT JOIN custom_weapon_attachments ca ON c.custom_weapon_id = ca.custom_weapon_id";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            Map<Integer, CustomWeapon> customWeaponMap = new HashMap<>();
            while (rs.next())
            {
                int customWeaponId = rs.getInt("custom_weapon_id");
                String customWeaponName = rs.getString("custom_weapon_name");
                int weaponId = rs.getInt("weapon_id");
                Weapon weapon = null;
                for (Weapon w : weapons)
                {
                    if (w.getId() == weaponId)
                    {
                        weapon = w;
                        break;
                    }
                }
                if (!customWeaponMap.containsKey(customWeaponId))
                {
                    CustomWeapon customWeapon = new CustomWeapon(customWeaponId, customWeaponName, weapon);
                    customWeaponMap.put(customWeaponId, customWeapon);
                    customWeapons.add(customWeapon);
                }
                int attachmentId = rs.getInt("attachment_id");
                Attachment attachment = null;
                for (Attachment a : attachments)
                {
                    if (a.getId() == attachmentId)
                    {
                        attachment = a;
                        break;
                    }
                }
                if (attachment != null)
                {
                    customWeaponMap.get(customWeaponId).addAttachment(attachment);
                }
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
        return customWeapons;
    }




    @Override
    public CustomWeapon getCustomWeaponById(int customWeaponId) throws DAOException
    {
        List<Weapon> weapons = weaponDAO.findAllGuns();
        List<Attachment> attachments = attachmentDAO.getAllAttachments();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CustomWeapon customWeapon = null;
        try
        {
            con = this.getConnection();
            String query = "SELECT c.custom_weapon_name, c.weapon_id, ca.attachment_id " +
                    "FROM custom_weapon c, custom_weapon_attachments ca " +
                    "WHERE c.custom_weapon_id = ? AND c.custom_weapon_id = ca.custom_weapon_id";
            ps = con.prepareStatement(query);
            ps.setInt(1, customWeaponId);
            rs = ps.executeQuery();
            List<Attachment> customAttachments = new ArrayList<>();
            String customWeaponName = null;
            Weapon weapon = null;
            while (rs.next())
            {
                customWeaponName = rs.getString("custom_weapon_name");
                int weaponId = rs.getInt("weapon_id");
                int attachmentId = rs.getInt("attachment_id");
                if (weapon == null)
                {
                    for (Weapon w : weapons)
                    {
                        if (w.getId() == weaponId)
                        {
                            weapon = w;
                            break;
                        }
                    }
                }
                for (Attachment a : attachments)
                {
                    if (a.getId() == attachmentId)
                    {
                        customAttachments.add(a);
                        break;
                    }
                }
            }
            customWeapon = new CustomWeapon(customWeaponId, customWeaponName, weapon, customAttachments);
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        } finally
        {
            this.freeConnection(con);
        }
        return customWeapon;
    }


    @Override
    public boolean deleteCustomWeaponById(int id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsDeleted = 0;
        try
        {
            con = this.getConnection();
            con.setAutoCommit(false);
            String deleteAttachmentsQuery = "DELETE FROM custom_weapon_attachments WHERE custom_weapon_id = ?";
            ps = con.prepareStatement(deleteAttachmentsQuery);
            ps.setInt(1, id);
            ps.executeUpdate();
            String deleteCustomWeaponQuery = "DELETE FROM custom_weapon WHERE custom_weapon_id = ?";
            ps = con.prepareStatement(deleteCustomWeaponQuery);
            ps.setInt(1, id);
            rowsDeleted = ps.executeUpdate();
            con.commit();
        }
        catch (SQLException e)
        {
            try
            {
                if (con != null) con.rollback();
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally
        {
            updateIDCache(con);
            this.freeConnection(con);
        }
        return rowsDeleted > 0;
    }


    @Override
    public boolean insertCustomWeapon(CustomWeapon customWeapon) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        boolean success = false;
        try
        {
            con = this.getConnection();
            con.setAutoCommit(false);
            String insertCustomWeaponQuery = "INSERT INTO custom_weapon (custom_weapon_name, weapon_id) VALUES (?, ?)";
            ps = con.prepareStatement(insertCustomWeaponQuery);
            ps.setString(1, customWeapon.getNickname());
            ps.setInt(2, customWeapon.getOriginalWeapon().getId());
            ps.executeUpdate();
            String selectLastInsertIdQuery = "SELECT LAST_INSERT_ID()";
            ps = con.prepareStatement(selectLastInsertIdQuery);
            ResultSet rs = ps.executeQuery();
            int customWeaponId = 0;
            if (rs.next())
            {
                customWeaponId = rs.getInt(1);
            }
            for (Attachment a : customWeapon.getAttachments())
            {
                String insertAttachmentQuery = "INSERT INTO custom_weapon_attachments (custom_weapon_id, attachment_id) VALUES (?, ?)";
                ps = con.prepareStatement(insertAttachmentQuery);
                ps.setInt(1, customWeaponId);
                ps.setInt(2, a.getId());
                ps.executeUpdate();
            }
            con.commit();
            success = true;
        }
        catch (SQLException e)
        {
            try
            {
                if (con != null) con.rollback();
            }
            catch (SQLException ex)
            {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally
        {
            updateIDCache(con);
            this.freeConnection(con);
            try
            {
                if (ps != null) ps.close();
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e);
            }
        }
        return success;
    }

    @Override
    public void updateIDCache(Connection con) throws DAOException
    {
        if(con == null)
        {
            con = this.getConnection();
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            String query = "SELECT custom_weapon_id FROM custom_weapon";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("custom_weapon_id");
                idCache.add(id);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAllCustomWeaponsJSON() throws DAOException
    {
        List<CustomWeapon> customWeapons = getAllCustomWeapons();
        Gson gson = new Gson();
        return gson.toJson(customWeapons);
    }

    @Override
    public String getCustomWeaponByIdJSON(int id) throws DAOException
    {
        CustomWeapon customWeapon = getCustomWeaponById(id);
        Gson gson = new Gson();
        return gson.toJson(customWeapon);
    }

    @Override
    public List<CustomWeapon> getCustomWeaponsByFilter(Comparator comparator) throws DAOException
    {
        List<CustomWeapon> customWeapons = getAllCustomWeapons();
        Collections.sort(customWeapons, comparator);
        return customWeapons;
    }
}
