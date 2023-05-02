package org.application.Server.Commands;

import com.google.gson.Gson;
import org.application.Server.Command;
import org.application.dao.CustomWeapon.MySqlCustomWeaponDAO;
import org.application.dto.CustomWeapon;
import org.application.exceptions.DAOException;

public class AddCustomWeaponCommand implements Command
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
        Gson gson = new Gson();
        CustomWeapon weapon = gson.fromJson(components[1], CustomWeapon.class);
        try
        {
            if(dao.insertCustomWeapon(weapon))
            {
                return "Weapon added";
            }
            else
            {
                return "Weapon not added";
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
}
