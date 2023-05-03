package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.Attachment.MySqlAttachmentDao;
import org.application.exceptions.DAOException;

public class DisplayAttachmentByIdCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.DISPLAY_ATTACHMENT_BY_ID);
        response.append(ServerDetails.BREAKING_CHARACTERS);
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
            return response.append(attachmentDao.getAttachmentById(Integer.parseInt(components[1]))).toString();
        }
        catch (DAOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
