package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.CustomWeapon.MySqlCustomWeaponDAO;
import org.application.exceptions.DAOException;

public class DisplayCustomWeaponByIdCommand implements Command
{

    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.DISPLAY_CUSTOM_WEAPON_BY_ID);
        response.append(ServerDetails.BREAKING_CHARACTERS);
        MySqlCustomWeaponDAO dao = null;
        try
        {
            dao = new MySqlCustomWeaponDAO();
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            return response.append(dao.getCustomWeaponByIdJSON(Integer.parseInt(components[1]))).toString();
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
