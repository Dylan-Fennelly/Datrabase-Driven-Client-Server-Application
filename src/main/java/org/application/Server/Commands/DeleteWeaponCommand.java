package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.Weapon.MySqlWeaponDAO;

public class DeleteWeaponCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.DELETE_WEAPON);
        response.append(ServerDetails.BREAKING_CHARACTERS);
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
                return response.append(Commands.SUCCESS).toString();
            }
            else
            {
                return response.append(Commands.ERROR).toString();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
