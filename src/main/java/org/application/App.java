package org.application;

import org.application.dao.Attachment.AttachmentComparator;
import org.application.dao.Attachment.IAttachmentDAOInterface;
import org.application.dao.Attachment.MySqlAttachmentDao;
import org.application.dao.Weapon.IWeaponDAOInterface;
import org.application.dao.Weapon.MySqlWeaponDAO;
import org.application.dao.Weapon.WeaponComparator;
import org.application.dto.Attachment;
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
        Weapon m4 = new Weapon( "M4", "Assault Rifle", 30, 30, 800, 7.5f, 2.5f, 0.5f, 500, 0.5f, 3, 1500);
        if(gunDAO.insertGun(m4))
        {
            System.out.println("M4 inserted successfully");
        }
        System.out.println(gunDAO.findAllGuns());
        System.out.println(gunDAO.findAllGunsJSON());
        System.out.println(gunDAO.findGunByIdJSON(1));

        System.out.println(gunDAO.findGunsByFilter(WeaponComparator.PRICE_COMPARATOR));
        System.out.println(gunDAO.findGunsByFilter(WeaponComparator.DAMAGE_COMPARATOR));

        IAttachmentDAOInterface attachmentDAO = new MySqlAttachmentDao();
        System.out.println(attachmentDAO.getAllAttachments());
        System.out.println(attachmentDAO.getAttachmentById(3));
        attachmentDAO.deleteAttachmentById(3);
        Attachment miniDot = new Attachment("Mini Dot", "Sight",0f,5f,
                0f,0,0,0,50);
        if(attachmentDAO.insertAttachment(miniDot))
        {
            System.out.println("Mini Dot inserted successfully");
        }
        System.out.println(attachmentDAO.getAttachmentsByFilter(AttachmentComparator.PRICE_COMPARATOR));
    }

}
