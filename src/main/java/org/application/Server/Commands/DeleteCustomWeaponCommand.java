package org.application.Server.Commands;

import org.application.Server.Command;
import org.application.dao.CustomWeapon.MySqlCustomWeaponDAO;

public class DeleteCustomWeaponCommand implements Command
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
            if(dao.deleteCustomWeaponById(Integer.parseInt(components[1])))
            {
                return "Custom weapon deleted";
            }
            else
            {
                return "Custom weapon not deleted";
            }

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
