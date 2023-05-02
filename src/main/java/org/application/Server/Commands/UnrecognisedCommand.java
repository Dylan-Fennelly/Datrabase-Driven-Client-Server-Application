package org.application.Server.Commands;

import org.application.Core.Commands;
import org.application.Server.Command;

public class UnrecognisedCommand implements Command
{

    @Override
    public String createResponse(String[] components)
    {
        return Commands.UNRECOGNISED;
    }
}
