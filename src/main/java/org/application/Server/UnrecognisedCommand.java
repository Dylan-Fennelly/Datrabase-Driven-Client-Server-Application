package org.application.Server;

import org.example.Question2.Core.Details;

public class UnrecognisedCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        return Details.UNRECOGNISED;
    }
}
