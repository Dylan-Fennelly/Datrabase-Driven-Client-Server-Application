package org.application.dao;

import org.application.dto.Gun;
import org.application.exceptions.DAOException;

import java.util.List;

public interface IGunDAOInterface
{
    public List<Gun> findAllGuns() throws DAOException;
//    public Gun findGunById(int id);
//    public Gun deleteGunById(int id);
//    public Gun insertGun(Gun gun);
//    //list the entites using a filter
//    public List<Gun> findGunsByFilter(String filter);


}
