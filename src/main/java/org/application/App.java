package org.application;

import org.application.dao.IWeaponDAOInterface;
import org.application.dao.MySqlWeaponDAO;
import org.application.exceptions.DAOException;

public class App
{
    public static void main(String[] args) throws DAOException
    {
        IWeaponDAOInterface gunDAO = new MySqlWeaponDAO();
        System.out.println(gunDAO.findAllGuns());
        System.out.println(gunDAO.findGunById(3));
    }
}
