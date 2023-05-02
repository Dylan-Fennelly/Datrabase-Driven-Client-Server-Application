package org.application.Server.Commands;

import org.application.Server.Command;
import org.application.dao.Weapon.MySqlWeaponDAO;
import org.application.exceptions.DAOException;

public class DisplayWeaponByIdCommand implements Command
{
    public String createResponse(String[] components)
    {
        MySqlWeaponDAO weaponDAO = null;
        try
        {
            weaponDAO = new MySqlWeaponDAO();
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            return weaponDAO.findGunByIdJSON(Integer.parseInt(components[1]));
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
