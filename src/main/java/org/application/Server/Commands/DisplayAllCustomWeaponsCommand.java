package org.application.Server.Commands;

import org.application.Server.Command;
import org.application.dao.CustomWeapon.MySqlCustomWeaponDAO;

public class DisplayAllCustomWeaponsCommand implements Command
{

    @Override
    public String createResponse(String[] components)
    {
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
            return dao.getAllCustomWeaponsJSON();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
