package org.application.Server.Commands;

import org.application.Server.Command;
import org.application.dao.Attachment.MySqlAttachmentDao;

public class DeleteAttachmentCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        MySqlAttachmentDao attachmentDao = null;
        try
        {
            attachmentDao = new MySqlAttachmentDao();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        try
        {
            if(attachmentDao.deleteAttachmentById(Integer.parseInt(components[1])))
            {
                return "Attachment deleted";
            }
            else
            {
                return "Attachment not deleted";
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
