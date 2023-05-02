package org.application.Server.Commands;

import org.application.Server.Command;
import org.application.dao.Attachment.MySqlAttachmentDao;
import org.application.exceptions.DAOException;

public class DisplayAttachmentByIdCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        MySqlAttachmentDao attachmentDao = null;
        try
        {
            attachmentDao = new MySqlAttachmentDao();
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            return attachmentDao.getAttachmentById(Integer.parseInt(components[1])).toString();
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
