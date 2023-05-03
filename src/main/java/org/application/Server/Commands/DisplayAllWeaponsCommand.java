package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.Weapon.MySqlWeaponDAO;
import org.application.exceptions.DAOException;

public class DisplayAllWeaponsCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.DISPLAY_ALL_WEAPONS);
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
            return response.append(weaponDAO.findAllGunsJSON()).toString();
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
