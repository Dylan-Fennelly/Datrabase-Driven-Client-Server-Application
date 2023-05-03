package org.application.Server;

import org.application.Core.Commands;
import org.application.Server.Commands.*;

public class CommandFactory
{
    public static Command createCommand(String command)
    {
        switch (command)
        {
            case Commands.DISPLAY_WEAPON_BY_ID:
                return new DisplayWeaponByIdCommand();
            case Commands.DISPLAY_ATTACHMENT_BY_ID:
                return new DisplayAttachmentByIdCommand();
            case Commands.DISPLAY_CUSTOM_WEAPON_BY_ID:
                return new DisplayCustomWeaponByIdCommand();
            case Commands.DISPLAY_ALL_WEAPONS:
                return new DisplayAllWeaponsCommand();
            case Commands.DISPLAY_ALL_ATTACHMENTS:
                return new DisplayAllAttachmentsCommand();
            case Commands.DISPLAY_ALL_CUSTOM_WEAPONS:
                return new DisplayAllCustomWeaponsCommand();
            case Commands.ADD_WEAPON:
                return new AddWeaponCommand();
            case Commands.ADD_ATTACHMENT:
                return new AddAttachmentCommand();
            case Commands.ADD_CUSTOM_WEAPON:
                return new AddCustomWeaponCommand();
            case Commands.DELETE_WEAPON:
                return new DeleteWeaponCommand();
            case Commands.DELETE_ATTACHMENT:
                return new DeleteAttachmentCommand();
            case Commands.DELETE_CUSTOM_WEAPON:
                return new DeleteCustomWeaponCommand();
            case Commands.EXIT_COMMAND:
                return new ExitCommand();
            default:
                return new UnrecognisedCommand();
        }

    }
}
