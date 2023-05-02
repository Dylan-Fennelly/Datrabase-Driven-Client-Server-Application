package org.application.Server.Commands;

import org.application.Server.Command;
import org.application.dao.CustomWeapon.MySqlCustomWeaponDAO;
import org.application.exceptions.DAOException;

public class DisplayCustomWeaponByIdCommand implements Command
{

    @Override
    public String createResponse(String[] components)
    {
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
            return dao.getCustomWeaponById(Integer.parseInt(components[1])).toString();
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
