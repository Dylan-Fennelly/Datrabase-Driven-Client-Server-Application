package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.Attachment.MySqlAttachmentDao;

public class DeleteAttachmentCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.DELETE_ATTACHMENT);
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
            if(attachmentDao.deleteAttachmentById(Integer.parseInt(components[1])))
            {
                return response.append(Commands.SUCCESS).toString();
            }
            else
            {
                return response.append(Commands.ERROR).toString();
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
