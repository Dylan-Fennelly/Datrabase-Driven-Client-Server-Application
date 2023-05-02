package org.application.dao.CustomWeapon;

import org.application.dto.CustomWeapon;
import org.application.exceptions.DAOException;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

public interface ICustomWeaponDAO
{
    public List<CustomWeapon> getAllCustomWeapons() throws DAOException;
    public CustomWeapon getCustomWeaponById(int id) throws DAOException;
    public boolean deleteCustomWeaponById(int id) throws DAOException;
    public boolean insertCustomWeapon(CustomWeapon customWeapon) throws DAOException;

    void updateIDCache(Connection con) throws DAOException;

    public String getAllCustomWeaponsJSON() throws DAOException;
    public String getCustomWeaponByIdJSON(int id) throws DAOException;
    public List<CustomWeapon> getCustomWeaponsByFilter(Comparator comparator) throws DAOException;

}
