package org.application.dao.Attachment;

import org.application.dto.Attachment;
import org.application.exceptions.DAOException;

import java.sql.Connection;
import java.util.Comparator;
import java.util.List;

public interface IAttachmentDAOInterface
{
    public List<Attachment> getAllAttachments() throws DAOException;
    public Attachment getAttachmentById(int id) throws DAOException;
    public boolean deleteAttachmentById(int id) throws DAOException;
    public boolean insertAttachment(Attachment attachment) throws DAOException;
    public void updateIDCache(Connection Con) throws DAOException;
    public String getAllAttachmentsJSON() throws DAOException;
    public String getAttachmentByIdJSON(int id) throws DAOException;
    public List<Attachment> getAttachmentsByFilter(Comparator comparator) throws DAOException;
}
