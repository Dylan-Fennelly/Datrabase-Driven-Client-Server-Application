package org.application.Server;

import org.example.Question2.Core.Details;

public class CommandFactory
{
    public static Command getCommand(String command)
    {
        if (command.equals(Details.GET_WEATHER))
        {
            return new GetWeatherCommand();
        }
        else if (command.equals(Details.EXIT_COMMAND))
        {
            return new ExitCommand();
        }
        else
        {
            return new UnrecognisedCommand();
        }
    }
}
