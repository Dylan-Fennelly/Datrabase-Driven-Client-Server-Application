package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.Attachment.MySqlAttachmentDao;

public class DisplayAllAttachmentsCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.DISPLAY_ALL_ATTACHMENTS);
        response.append(ServerDetails.BREAKING_CHARACTERS);
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
            return response.append(attachmentDao.getAllAttachmentsJSON()).toString();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
