package org.application.Server.Commands;

import com.google.gson.Gson;
import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.Server.Command;
import org.application.dao.Attachment.MySqlAttachmentDao;
import org.application.dto.Attachment;

public class AddAttachmentCommand implements Command
{

    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Commands.ADD_ATTACHMENT);
        response.append(ServerDetails.BREAKING_CHARACTERS);
        MySqlAttachmentDao attachmentDao = null;
        try
        {
            attachmentDao = new MySqlAttachmentDao();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Attachment attachment = gson.fromJson(components[1], Attachment.class);
        try
        {
            if(attachmentDao.insertAttachment(attachment))
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
