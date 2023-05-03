package org.application.Client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.application.Core.Colours;
import org.application.Core.Commands;
import org.application.Core.ServerDetails;
import org.application.dao.Attachment.AttachmentComparator;
import org.application.dao.CustomWeapon.CustomWeaponComparator;
import org.application.dao.Weapon.WeaponComparator;
import org.application.dto.Attachment;
import org.application.dto.CustomWeapon;
import org.application.dto.Weapon;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Client
{
    public static void main(String[] args)
    {
        try
        (
            Socket dataSocket = new Socket(ServerDetails.HOST, ServerDetails.SERVER_PORT)
        )
        {
            OutputStream out = dataSocket.getOutputStream();
            PrintWriter output = new PrintWriter(out, true);

            InputStream in = dataSocket.getInputStream();
            Scanner input = new Scanner(new InputStreamReader(in));

            Scanner keyboard = new Scanner(System.in);
            String message = "";

            while(!message.equals(Commands.EXIT_COMMAND))
            {
                displayMenu();
                int choice = getNumberInt(keyboard);
                String response = "";
                switch(choice)
                {
                    case 0:
                        message = Commands.EXIT_COMMAND;
                        output.println(message);

                        dataSocket.shutdownOutput();
                        response = input.nextLine();
                        if(response.equals(Commands.SESSION_TERMINATED))
                        {
                            System.out.println("Session ended");
                        }
                        break;
                    case 1:
                        displayAllWeapons(getAllWeapons(output, input));
                        break;
                    case 2:
                        displayAllAttachments(getAllAttachments(output, input));
                        break;
                    case 3:
                        displayAllCustomWeapons(getAllCustomWeapons(output, input));
                        break;
                    case 4:
                        getWeaponByID(output, input);
                        break;
                    case 5:
                        getAttachmentByID(output, input);
                        break;
                    case 6:
                        getCustomWeaponByID(output, input);
                        break;
                    case 7:
                        String weapon = EnterWeapon();
                        sendWeapon(weapon, output, input);
                        break;
                    case 8:
                        String attachment = EnterAttachment();
                        sendAttachment(attachment, output, input);
                        break;
                    case 9:
                        String customWeapon = EnterCustomWeapon(getAllWeapons(output,input), getAllAttachments(output, input));
                        sendCustomWeapon(customWeapon, output, input);
                        break;
                    case 10:
                        deleteWeaponById(output, input);
                        break;
                    case 11:
                        deleteAttachmentById(output, input);
                        break;
                    case 12:
                        deleteCustomWeaponById(output, input);
                        break;


                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void sendCustomWeapon(String customWeapon, PrintWriter output, Scanner input)
    {
        if(customWeapon.equals(""))
        {
            System.out.println(Colours.YELLOW + "Custom weapon not added" + Colours.RESET);
            return;
        }
        String message = Commands.ADD_CUSTOM_WEAPON + ServerDetails.BREAKING_CHARACTERS + customWeapon;
        output.println(message);

        String response = input.nextLine();
        if(response.equals(Commands.SUCCESS))
        {
            System.out.println(Colours.GREEN +"Custom weapon added"+Colours.RESET);
        }
        else if(response.equals(Commands.ERROR))
        {
            System.out.println(Colours.RED +"Custom weapon not added"+Colours.RESET);
        }
    }

    private static void sendAttachment(String attachment, PrintWriter output, Scanner input)
    {
        String message = Commands.ADD_ATTACHMENT + ServerDetails.BREAKING_CHARACTERS + attachment;
        output.println(message);

        String response = input.nextLine();
        String compenents[] = response.split(ServerDetails.BREAKING_CHARACTERS);
        if(compenents[1].equals(Commands.SUCCESS))
        {
            System.out.println(Colours.GREEN +"Attachment added"+Colours.RESET);
        }
        else if(compenents[1].equals(Commands.ERROR))
        {
            System.out.println(Colours.RED +"Attachment not added"+Colours.RESET);
        }
    }

    private static void sendWeapon(String weapon, PrintWriter output, Scanner input)
    {
        String message = Commands.ADD_WEAPON+ServerDetails.BREAKING_CHARACTERS+weapon;
        output.println(message);

        String response = input.nextLine();
        String compenents[] = response.split(ServerDetails.BREAKING_CHARACTERS);

        if(compenents[1].equals(Commands.SUCCESS))
        {
            System.out.println(Colours.GREEN +"Weapon added"+Colours.RESET);
        }
        else if(compenents[1].equals(Commands.ERROR))
        {
            System.out.println(Colours.RED +"Weapon not added"+Colours.RESET);
        }
    }

    private static int getNumberInt(Scanner keyboard)
    {
        int number = -1;
        boolean valid = false;
        while(!valid)
        {
            try
            {
                number = keyboard.nextInt();
                valid = true;
            }
            catch(InputMismatchException ime)
            {
                System.out.println(Colours.RED +"Please enter a number"+Colours.RESET);
                keyboard.nextLine();
            }
        }
        return number;
    }
    private static float getNumberFloat(Scanner keyboard)
    {
        float number = -1;
        boolean valid = false;
        while(!valid)
        {
            try
            {
                number = keyboard.nextFloat();
                valid = true;
            }
            catch(InputMismatchException ime)
            {
                System.out.println(Colours.RED +"Please enter a number"+Colours.RESET);
                keyboard.nextLine();
            }
        }
        return number;
    }
    //Gets the clients input as a string for text fields
    private static String getInput(Scanner Keyboard)
    {
        //Check if the input contains ' or " and if it does then replace it with a '' or "" so that it can be stored in the database
        String input = Keyboard.nextLine();
        if(input.contains("'"))
        {
            input = input.replace("'", "''");
        }
        if(input.contains("\""))
        {
            input = input.replace("\"", "\"\"");
        }
        return input;
    }

    private static void displayMenu()
    {
        System.out.println("0.Exit");
        System.out.println("1.Display all Weapons");
        System.out.println("2.Display all Attachments");
        System.out.println("3.Display all Custom Weapons");
        System.out.println("4.Search for Weapon by ID");
        System.out.println("5.Search for Attachment by ID");
        System.out.println("6.Search for Custom Weapon by ID");
        System.out.println("7.Add Weapon");
        System.out.println("8.Add Attachment");
        System.out.println("9.Add Custom Weapon");
        System.out.println("10.Delete Weapon");
        System.out.println("11.Delete Attachment");
        System.out.println("12.Delete Custom Weapon");
    }
    private static void displayAllFilterMenu()
    {
        System.out.println("0.No Filter");
        System.out.println("1.Filter by Cost");
        System.out.println("2.Filter by Damage");
        System.out.println("3.Filter by weight");

    }
    private static String EnterWeapon()
    {
        System.out.println("Enter Weapon Name");
        String name = getInput(new Scanner(System.in));
        System.out.println("Enter Weapon Type");
        String type = getInput(new Scanner(System.in));
        System.out.println("Enter Weapon Ammo Capacity");
        int ammoCapacity = getNumberInt(new Scanner(System.in));
        System.out.println("Enter Weapon Damage");
        int damage = getNumberInt(new Scanner(System.in));
        System.out.println("Enter Weapon Rate of Fire");
        int rateOfFire = getNumberInt(new Scanner(System.in));
        System.out.println("Enter Weapon Reload Time");
        float reloadTime = getNumberFloat(new Scanner(System.in));
        System.out.println("Enter Weapon Reload time");
        float weight = getNumberFloat(new Scanner(System.in));
        System.out.println("Enter Weapon Accuracy");
        float accuracy = getNumberFloat(new Scanner(System.in));
        System.out.println("Enter Weapon Range");
        int range = getNumberInt(new Scanner(System.in));
        System.out.println("Enter Weapon Recoil");
        float recoil = getNumberFloat(new Scanner(System.in));
        System.out.println("Enter Weapon Attachment Slots");
        int attachmentSlots = getNumberInt(new Scanner(System.in));
        System.out.println("Enter Weapon Cost");
        int cost = getNumberInt(new Scanner(System.in));
        Weapon weapon = new Weapon(name,type,ammoCapacity,damage,rateOfFire,reloadTime,weight,accuracy,range,recoil,attachmentSlots,cost);
        Gson gson = new Gson();
        return gson.toJson(weapon);
    }
    private static String EnterAttachment()
    {
        System.out.println("Enter Attachment Name");
        String name = getInput(new Scanner(System.in));
        System.out.println("Enter Attachment Type");
        String type = getInput(new Scanner(System.in));
        System.out.println("Enter Attachment Weight");
        float weight = getNumberFloat(new Scanner(System.in));
        System.out.println("Enter Attachment AccuracyBonus");
        float accuracyBonus = getNumberFloat(new Scanner(System.in));
        System.out.println("Enter Attachment RecoilBonus");
        float recoilBonus = getNumberFloat(new Scanner(System.in));
        System.out.println("Enter Attachment DamageBonus");
        float damageBonus = getNumberFloat(new Scanner(System.in));
        System.out.println("Enter Attachment RateOfFireBonus");
        float rateOfFireBonus = getNumberFloat(new Scanner(System.in));
        System.out.println("Enter Attachment ReloadTimeBonus");
        float reloadTimeBonus = getNumberFloat(new Scanner(System.in));
        System.out.println("Enter Attachment Cost");
        int cost = getNumberInt(new Scanner(System.in));
        Attachment attachment = new Attachment(name,type,weight,accuracyBonus,recoilBonus,damageBonus,rateOfFireBonus,reloadTimeBonus,cost);
        Gson gson = new Gson();
        return gson.toJson(attachment);
    }
    private static String EnterCustomWeapon(List<Weapon> allWeapons,List<Attachment> allAttachments)
    {
        CustomWeapon customWeapon;
        Weapon weaponFound;
        weaponFound = null;
        System.out.println("Enter Custom Weapon Nickname");
        String nickname = getInput(new Scanner(System.in));
        boolean found = false;
        while(!found)
        {
            System.out.println("Enter the ID of the Original Weapon");
            int weaponID = getNumberInt(new Scanner(System.in));
            for(Weapon weapon : allWeapons)
            {
                if(weapon.getId() == weaponID)
                {
                    System.out.println("Weapon Found");
                    weaponFound = weapon;
                    found = true;
                }
            }
            if(!found)
            {
                System.out.println("Weapon not found");
                System.out.println("Would you like to try again? ("+Colours.GREEN+"Y"+Colours.RESET+"/"+Colours.RED+"N"+Colours.RESET+")");
                String input = getInput(new Scanner(System.in));
                if(input.equalsIgnoreCase("Y")||input.equalsIgnoreCase("Yes"))
                {
                    found = false;
                }
                else
                {
                    return "";
                }
            }
        }
        List<Integer> attachmentIDs = new ArrayList<>();
        boolean done = false;
        boolean anyAttachments = false;
        System.out.println("Would you like to add attachments? ("+Colours.GREEN+"Y"+Colours.RESET+"/"+Colours.RED+"N"+Colours.RESET+")");
        String userInput = getInput(new Scanner(System.in));
        if(userInput.equalsIgnoreCase("Y")||userInput.equalsIgnoreCase("Yes"))
        {
            done = false;
        }
        else
        {
            done = true;
        }
        while(!done)
        {
            System.out.println("Enter the ID of the Attachment");
            int attachmentID = getNumberInt(new Scanner(System.in));
            attachmentIDs.add(attachmentID);
            System.out.println("Are you done adding attachments? ("+Colours.GREEN+"Y"+Colours.RESET+"/"+Colours.RED+"N"+Colours.RESET+")");
            String input = getInput(new Scanner(System.in));
            if(input.equalsIgnoreCase("Y")||input.equalsIgnoreCase("Yes"))
            {
                done = true;
            }
        }
        if(attachmentIDs.size() == 0)
        {
            customWeapon = new CustomWeapon(nickname,weaponFound);
        }
        else
        {
            List<Attachment> attachmentsToBeAdded = new ArrayList<>();
            for(int attachmentID : attachmentIDs)
            {
                for(Attachment attachment : allAttachments)
                {
                    if(attachment.getId() == attachmentID)
                    {
                        attachmentsToBeAdded.add(attachment);
                    }
                }
            }
            customWeapon = new CustomWeapon(nickname,weaponFound,attachmentsToBeAdded);
        }
        Gson gson = new Gson();
        return gson.toJson(customWeapon);
    }
    public static List<Weapon> getAllWeapons(PrintWriter output, Scanner input)
    {
        String message = Commands.DISPLAY_ALL_WEAPONS;
        output.println(message);

        String response = input.nextLine();
        String components[] = response.split(ServerDetails.BREAKING_CHARACTERS);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Weapon>>(){}.getType();
        List<Weapon> weapons = gson.fromJson(components[1],listType);
        return weapons;
    }
    public static List<Attachment> getAllAttachments(PrintWriter output, Scanner input)
    {
        String message = Commands.DISPLAY_ALL_ATTACHMENTS;
        output.println(message);

        String response = input.nextLine();
        String components[] = response.split(ServerDetails.BREAKING_CHARACTERS);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Attachment>>(){}.getType();
        List<Attachment> attachments = gson.fromJson(components[1],listType);
        return attachments;
    }
    public static List<CustomWeapon> getAllCustomWeapons(PrintWriter output, Scanner input)
    {
        String message = Commands.DISPLAY_ALL_CUSTOM_WEAPONS;
        output.println(message);

        String response = input.nextLine();
        String components[] = response.split(ServerDetails.BREAKING_CHARACTERS);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<CustomWeapon>>(){}.getType();
        List<CustomWeapon> customWeapons = gson.fromJson(components[1],listType);
        return customWeapons;
    }
    public static void getWeaponByID(PrintWriter output, Scanner input)
    {
        String message = Commands.DISPLAY_WEAPON_BY_ID;
        message += ServerDetails.BREAKING_CHARACTERS;
        System.out.println("Enter Weapon ID");
        int id = getNumberInt(new Scanner(System.in));
        message += id;
        output.println(message);

        String response = input.nextLine();
        String components[] = response.split(ServerDetails.BREAKING_CHARACTERS);
        if(components[1].equals("null"))
        {
            System.out.println(Colours.RED+"Weapon not found"+Colours.RESET);
            return;
        }
        Gson gson = new Gson();
        Weapon weapon = gson.fromJson(components[1],Weapon.class);
        System.out.println(weapon.toString());
    }
    public static void getAttachmentByID(PrintWriter output, Scanner input)
    {
        String message = Commands.DISPLAY_ATTACHMENT_BY_ID;
        message += ServerDetails.BREAKING_CHARACTERS;
        System.out.println("Enter Attachment ID");
        int id = getNumberInt(new Scanner(System.in));
        message += id;
        output.println(message);

        String response = input.nextLine();
        String components[] = response.split(ServerDetails.BREAKING_CHARACTERS);
        if(components[1].equals("null"))
        {
            System.out.println(Colours.RED+"Attachment not found"+Colours.RESET);
            return;
        }
        Gson gson = new Gson();
        Attachment attachment = gson.fromJson(components[1],Attachment.class);
        System.out.println(attachment.toString());
    }
    public static void getCustomWeaponByID(PrintWriter output, Scanner input)
    {
        String message = Commands.DISPLAY_CUSTOM_WEAPON_BY_ID;
        message += ServerDetails.BREAKING_CHARACTERS;
        System.out.println("Enter Custom Weapon ID");
        int id = getNumberInt(new Scanner(System.in));
        message += id;
        output.println(message);

        String response = input.nextLine();
        String components[] = response.split(ServerDetails.BREAKING_CHARACTERS);
        if(components[1].equals("null"))
        {
            System.out.println(Colours.RED+"Custom Weapon not found"+Colours.RESET);
            return;
        }
        Gson gson = new Gson();
        CustomWeapon customWeapon = gson.fromJson(components[1],CustomWeapon.class);
        System.out.println(customWeapon.toString());
    }
    public static void deleteWeaponById(PrintWriter output, Scanner input)
    {
        String message = Commands.DELETE_WEAPON;
        message += ServerDetails.BREAKING_CHARACTERS;
        System.out.println("Enter Weapon ID");
        int id = getNumberInt(new Scanner(System.in));
        message += id;
        output.println(message);

        String response = input.nextLine();
        String components[] = response.split(ServerDetails.BREAKING_CHARACTERS);
        System.out.println(components[1]);
    }
    public static void deleteAttachmentById(PrintWriter output, Scanner input)
    {
        String message = Commands.DELETE_ATTACHMENT;
        message += ServerDetails.BREAKING_CHARACTERS;
        System.out.println("Enter Attachment ID");
        int id = getNumberInt(new Scanner(System.in));
        message += id;
        output.println(message);

        String response = input.nextLine();
        String components[] = response.split(ServerDetails.BREAKING_CHARACTERS);
        System.out.println(components[1]);
    }

    public static void deleteCustomWeaponById(PrintWriter output, Scanner input)
    {
        String message = Commands.DELETE_CUSTOM_WEAPON;
        message += ServerDetails.BREAKING_CHARACTERS;
        System.out.println("Enter Custom Weapon ID");
        int id = getNumberInt(new Scanner(System.in));
        message += id;
        output.println(message);

        String response = input.nextLine();
        String components[] = response.split(ServerDetails.BREAKING_CHARACTERS);
        System.out.println(components[1]);
    }
    public static void displayAllWeapons(List<Weapon> weapons)
    {
        displayAllFilterMenu();
        int choice = getNumberInt(new Scanner(System.in));
        switch (choice)
        {
            case 1:
                Collections.sort(weapons, WeaponComparator.PRICE_COMPARATOR);
                for(Weapon weapon : weapons)
                {
                    System.out.println(weapon.toString());
                }
                break;
            case 2:
                Collections.sort(weapons, WeaponComparator.DAMAGE_COMPARATOR);
                for(Weapon weapon : weapons)
                {
                    System.out.println(weapon.toString());
                }
                break;
            case 3:
                Collections.sort(weapons, WeaponComparator.WEIGHT_COMPARATOR);
                for(Weapon weapon : weapons)
                {
                    System.out.println(weapon.toString());
                }
                break;
            default:
                for(Weapon weapon : weapons)
                {
                    System.out.println(weapon.toString());
                }
                break;
        }
    }
    public static void displayAllAttachments(List<Attachment> attachments)
    {
        displayAllFilterMenu();
        int choice = getNumberInt(new Scanner(System.in));
        switch (choice)
        {
            case 1:
                Collections.sort(attachments, AttachmentComparator.PRICE_COMPARATOR);
                for(Attachment attachment : attachments)
                {
                    System.out.println(attachment.toString());
                }
                break;
            case 2:
                Collections.sort(attachments, AttachmentComparator.DAMAGE_COMPARATOR);
                for(Attachment attachment : attachments)
                {
                    System.out.println(attachment.toString());
                }
                break;
            case 3:
                Collections.sort(attachments, AttachmentComparator.WEIGHT_COMPARATOR);
                for(Attachment attachment : attachments)
                {
                    System.out.println(attachment.toString());
                }
                break;
            default:
                for(Attachment attachment : attachments)
                {
                    System.out.println(attachment.toString());
                }
                break;
        }
    }
    public static void displayAllCustomWeapons(List<CustomWeapon> customWeapons)
    {
        displayAllFilterMenu();
        int choice = getNumberInt(new Scanner(System.in));
        switch (choice)
        {
            case 1:
                Collections.sort(customWeapons, CustomWeaponComparator.PRICE_COMPARATOR);
                for(CustomWeapon customWeapon : customWeapons)
                {
                    System.out.println(customWeapon.toString());
                }
                break;
            case 2:
                Collections.sort(customWeapons, CustomWeaponComparator.DAMAGE_COMPARATOR);
                for(CustomWeapon customWeapon : customWeapons)
                {
                    System.out.println(customWeapon.toString());
                }
                break;
            case 3:
                Collections.sort(customWeapons, CustomWeaponComparator.WEIGHT_COMPARATOR);
                for(CustomWeapon customWeapon : customWeapons)
                {
                    System.out.println(customWeapon.toString());
                }
                break;
            default:
                for(CustomWeapon customWeapon : customWeapons)
                {
                    System.out.println(customWeapon.toString());
                }
                break;
        }
    }



}
