package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.CustomWeapon.MySqlCustomWeaponDAO;

public class DeleteCustomWeaponCommand implements Command
{

    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.DELETE_CUSTOM_WEAPON);
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
            if(dao.deleteCustomWeaponById(Integer.parseInt(components[1])))
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
