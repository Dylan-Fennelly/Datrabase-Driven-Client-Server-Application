package org.application.Server.Commands;

import com.google.gson.Gson;
import org.application.Server.Command;
import org.application.dao.Attachment.MySqlAttachmentDao;
import org.application.dto.Attachment;

public class AddAttachmentCommand implements Command
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
            e.printStackTrace();
        }
        Gson gson = new Gson();
        Attachment attachment = gson.fromJson(components[1], Attachment.class);
        try
        {
            if(attachmentDao.insertAttachment(attachment))
            {
                return "Attachment added";
            }
            else
            {
                return "Attachment not added";
            }
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }
}
