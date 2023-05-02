package org.application.Server.Commands;

import org.application.Server.Command;
import org.application.dao.Attachment.MySqlAttachmentDao;

public class DisplayAllAttachmentsCommand implements Command
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
            return attachmentDao.getAllAttachmentsJSON();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
