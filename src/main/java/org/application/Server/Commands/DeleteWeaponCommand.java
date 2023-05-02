package org.application.Server.Commands;

import org.application.Server.Command;
import org.application.dao.Weapon.MySqlWeaponDAO;

public class DeleteWeaponCommand implements Command
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
            if(weaponDAO.deleteGunById(Integer.parseInt(components[1])))
            {
                return "Weapon deleted";
            }
            else
            {
                return "Weapon not deleted";
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
