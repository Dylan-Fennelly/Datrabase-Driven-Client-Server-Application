package org.application.dao.Attachment;

import com.google.gson.Gson;
import org.application.dao.MySqlDAO;
import org.application.dto.Attachment;
import org.application.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySqlAttachmentDao extends MySqlDAO implements IAttachmentDAOInterface
{
    private HashSet<Integer> idCache = new HashSet<>();

public MySqlAttachmentDao() throws DAOException
{
        super();
        Connection con = null;
        try
        {
            con = this.getConnection();
            this.updateIDCache(con);
        }
        catch(SQLException e)
        {
            throw new DAOException("MySqlAttachmentDao() " + e.getMessage());
        }
        finally
        {
            this.freeConnection(con);
        }
    }
    @Override
    public List<Attachment> getAllAttachments() throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Attachment> attachments = new ArrayList<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM attachment";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("attachment_id");
                String name = rs.getString("attachment_name");
                String type = rs.getString("attachment_type");
                float weight = rs.getFloat("weight");
                float accuracyBonus = rs.getFloat("accuracy_bonus");
                float recoilBonus = rs.getFloat("recoilBonus");
                float damageBonus = rs.getFloat("damage_bonus");
                float rateOfFireBonus = rs.getFloat("rate_of_fire_bonus");
                float reloadTimeBonus = rs.getFloat("reload_time_bonus");
                int price = rs.getInt("price");
                Attachment attachment = new Attachment(id, name, type, weight, accuracyBonus, recoilBonus, damageBonus,
                        rateOfFireBonus, reloadTimeBonus, price);
                attachments.add(attachment);
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
        return attachments;
    }

    @Override
    public Attachment getAttachmentById(int id) throws DAOException
    {
        if(!idCache.contains(id))
        {
            System.out.println("Attachment with id " + id + " does not exist");
            return null;
        }
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Attachment attachment = null;

        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM attachment WHERE attachment_id = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if(rs.next())
            {
                String name = rs.getString("attachment_name");
                String type = rs.getString("attachment_type");
                float weight = rs.getFloat("weight");
                float accuracyBonus = rs.getFloat("accuracy_bonus");
                float recoilBonus = rs.getFloat("recoilBonus");
                float damageBonus = rs.getFloat("damage_bonus");
                float rateOfFireBonus = rs.getFloat("rate_of_fire_bonus");
                float reloadTimeBonus = rs.getFloat("reload_time_bonus");
                int price = rs.getInt("price");
                attachment = new Attachment(id, name, type, weight, accuracyBonus, recoilBonus, damageBonus,
                        rateOfFireBonus, reloadTimeBonus, price);
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
        return attachment;
    }

    @Override
    public boolean deleteAttachmentById(int id) throws DAOException
    {
        if(!idCache.contains(id))
        {
            System.out.println("Attachment with id " + id + " does not exist");
            return false;
        }
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean deleted = false;

        try
        {
            con = this.getConnection();
            String query = "DELETE FROM attachment WHERE attachment_id = ?";
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

    @Override
    public boolean insertAttachment(Attachment attachment) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean inserted = false;

        try
        {
            con = this.getConnection();
            String query = "INSERT INTO attachment (attachment_name, attachment_type, weight, accuracy_bonus, recoilBonus, damage_bonus, rate_of_fire_bonus, reload_time_bonus, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(query);
            ps.setString(1, attachment.getName());
            ps.setString(2, attachment.getType());
            ps.setFloat(3, attachment.getWeight());
            ps.setFloat(4, attachment.getAccuracyBonus());
            ps.setFloat(5, attachment.getRecoilBonus());
            ps.setFloat(6, attachment.getDamageBonus());
            ps.setFloat(7, attachment.getRateOfFireBonus());
            ps.setFloat(8, attachment.getReloadTimeBonus());
            ps.setInt(9, attachment.getPrice());
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
            String query = "SELECT attachment_id FROM attachment";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next())
            {
                int id = rs.getInt("attachment_id");
                idCache.add(id);
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getAllAttachmentsJSON() throws DAOException
    {
        List<Attachment> attachments = getAllAttachments();
        Gson gson = new Gson();
        return gson.toJson(attachments);
    }

    @Override
    public String getAttachmentByIdJSON(int id) throws DAOException
    {
        Attachment attachment = getAttachmentById(id);
        Gson gson = new Gson();
        return gson.toJson(attachment);
    }

    @Override
    public List<Attachment> getAttachmentsByFilter(Comparator comparator) throws DAOException
    {
        List<Attachment> attachments = getAllAttachments();
        Collections.sort(attachments, comparator);
        return attachments;
    }
}
