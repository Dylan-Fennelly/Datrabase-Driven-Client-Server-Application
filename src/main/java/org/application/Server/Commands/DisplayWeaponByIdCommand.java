package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.Weapon.MySqlWeaponDAO;
import org.application.exceptions.DAOException;

public class DisplayWeaponByIdCommand implements Command
{
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.DISPLAY_WEAPON_BY_ID);
        response.append(ServerDetails.BREAKING_CHARACTERS);
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
            return response.append(weaponDAO.findGunByIdJSON(Integer.parseInt(components[1]))).toString();
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
