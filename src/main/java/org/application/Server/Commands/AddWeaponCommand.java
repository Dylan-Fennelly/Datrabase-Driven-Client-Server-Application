package org.application.Server.Commands;

import com.google.gson.Gson;
import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.Weapon.MySqlWeaponDAO;
import org.application.dto.Weapon;

public class AddWeaponCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.ADD_WEAPON);
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
        //The command will parse a Json string and create a weapon object from it
        //The weapon object will be passed to the DAO to be added to the database
        Gson gson = new Gson();
        Weapon weapon = gson.fromJson(components[1], Weapon.class);
        try
        {
            if(weaponDAO.insertGun(weapon))
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
