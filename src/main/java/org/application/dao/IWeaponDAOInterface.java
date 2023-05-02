package org.application.dao;

import org.application.dto.Weapon;
import org.application.exceptions.DAOException;

import java.sql.Connection;
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

    //This method is used to update the cache of gun ids that are in the database
    //It returns true if the cache was updated successfully, or false if it was not.
//    //list the entites using a filter
//    public List<Gun> findGunsByFilter(String filter);


}
