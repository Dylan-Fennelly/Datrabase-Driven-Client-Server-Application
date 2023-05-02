package org.application.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.application.dao.Weapon.MySqlWeaponDAO;
import org.application.dto.Weapon;
import org.application.exceptions.DAOException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlWeaponDAOTest
{
    private MySqlWeaponDAO dao;

    @BeforeEach
    void setUp() throws DAOException
    {
         dao = new MySqlWeaponDAO();
    }

    @AfterEach
    void tearDown()
    {
        dao = null;
    }

    @Test
    void findAllGuns() throws DAOException
    {
        List<Weapon> guns = dao.findAllGuns();
        assertEquals(10, guns.size());
    }
    @Test
    void findGunById() throws DAOException
    {
        Weapon gun = dao.findGunById(2);
        assertEquals("M4A1", gun.getName());
    }
    @Test
    void deleteGunById() throws DAOException
    {
        dao.deleteGunById(3);
        List<Weapon> guns = dao.findAllGuns();
        assertEquals(10, guns.size());
    }
    @Test
    void insertGun() throws DAOException
    {
        Weapon m4 = new Weapon( "M4", "Assault Rifle", 30, 30, 800, 7.5f, 2.5f, 0.5f, 500, 0.5f, 3, 1500);
        dao.insertGun(m4);
        List<Weapon> guns = dao.findAllGuns();
        assertEquals(11, guns.size());
    }
    @Test
    void findAllGunsJSON() throws DAOException
    {
        String guns = dao.findAllGunsJSON();
        assertTrue(guns.contains("Bowie"));
    }
    @Test
    void findGunByIdJSON() throws DAOException
    {
        String gun = dao.findGunByIdJSON(2);
        assertTrue(gun.contains("M4A1"));
    }

}