package org.application.dao.Weapon;

import org.application.dto.Weapon;
import org.application.exceptions.DAOException;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

public interface IWeaponDAOInterface
{
    public List<Weapon> findAllGuns() throws DAOException;
    public Weapon findGunById(int id) throws DAOException;
    public boolean deleteGunById(int id) throws DAOException;
    public boolean insertGun(Weapon gun) throws DAOException;
    public void updateIDCache(Connection Con) throws DAOException;
    public String findAllGunsJSON() throws DAOException;

    String findGunByIdJSON(int id) throws DAOException;

    public List<Weapon> findGunsByFilter(Comparator comparator) throws DAOException;


}
