package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Server.Command;

public class ExitCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        return Commands.SESSION_TERMINATED;
    }
}
