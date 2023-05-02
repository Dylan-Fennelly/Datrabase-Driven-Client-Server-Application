package org.application.Server.Commands;

import org.application.Server.Command;
import org.application.dao.Weapon.MySqlWeaponDAO;
import org.application.exceptions.DAOException;

public class DisplayAllWeaponsCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        MySqlWeaponDAO weaponDAO = null;
        try
        {
            weaponDAO = new MySqlWeaponDAO();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            return weaponDAO.findAllGunsJSON();
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
