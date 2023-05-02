package org.application.dao;

import org.application.dto.Weapon;
import org.application.exceptions.DAOException;

import java.util.List;

public interface IWeaponDAOInterface
{
    public List<Weapon> findAllGuns() throws DAOException;
    public Weapon findGunById(int id) throws DAOException;
    public boolean deleteGunById(int id) throws DAOException;
//    public Gun insertGun(Gun gun);
//    //list the entites using a filter
//    public List<Gun> findGunsByFilter(String filter);


}
