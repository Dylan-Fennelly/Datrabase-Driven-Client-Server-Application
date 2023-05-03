package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.CustomWeapon.MySqlCustomWeaponDAO;

public class DisplayAllCustomWeaponsCommand implements Command
{

    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.DISPLAY_ALL_CUSTOM_WEAPONS);
        response.append(ServerDetails.BREAKING_CHARACTERS);
        MySqlCustomWeaponDAO dao = null;
        try
        {
            dao = new MySqlCustomWeaponDAO();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            return response.append(dao.getAllCustomWeaponsJSON()).toString();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
