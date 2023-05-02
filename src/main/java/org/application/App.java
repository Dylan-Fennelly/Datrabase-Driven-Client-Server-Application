package org.application;

import org.application.dao.IWeaponDAOInterface;
import org.application.dao.MySqlWeaponDAO;
import org.application.dto.Weapon;
import org.application.exceptions.DAOException;

public class App
{
    public static void main(String[] args) throws DAOException
    {
        IWeaponDAOInterface gunDAO = new MySqlWeaponDAO();
        System.out.println(gunDAO.findAllGuns());
        System.out.println(gunDAO.findGunById(3));
        gunDAO.deleteGunById(3);
        System.out.println(gunDAO.findAllGuns());
        Weapon m4 = new Weapon(3, "M4", "Assault Rifle", 30, 30, 800, 7.5f, 2.5f, 0.5f, 500, 0.5f, 3, 1500);
        gunDAO.insertGun(m4);
        System.out.println(gunDAO.findAllGuns());
    }
}
