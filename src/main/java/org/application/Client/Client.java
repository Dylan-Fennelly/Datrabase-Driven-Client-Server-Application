package org.application.Client;

import org.example.Question2.Core.Details;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        try
        {
            Socket dateSocket = new Socket(Details.HOST, Details.SERVER_PORT);
            OutputStream out = dateSocket.getOutputStream();
            PrintWriter output = new PrintWriter(out, true);

            InputStream in = dateSocket.getInputStream();
            Scanner input = new Scanner(new InputStreamReader(in));

            Scanner keyboard = new Scanner(System.in);
            String message = "";

            while (!message.equals(Details.EXIT_COMMAND))
            {
                displayMenu();
                int choice = getNumber(keyboard);
                String response = "";
                if(choice>= 0 && choice <=2)
                {
                    switch (choice)
                    {
                        case 0:
                            message = Details.EXIT_COMMAND;
                            output.println(message);

                            response = input.nextLine();
                            if(response.equals(Details.SESSION_TERMINATED))
                            {
                                System.out.println("Session ended");
                            }
                            break;
                        case 1:
                            response = getWeatherForOneDay();

                            output.println(response);

                            response = input.nextLine();
                            displayWeather(response);
                            break;
                        case 2:
                            message = getWeatherForMultipleDays();

                            output.println(message);

                            response = input.nextLine();
                            displayWeather(response);
                            break;
                    }

                }

            }
        }
        catch (UnknownHostException e)
        {
            System.out.println("Unknown host: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("IO Exception: " + e.getMessage());
        }


    }

    private static String getWeatherForMultipleDays()
    {
        System.out.println("How many days?");
        int days = getNumber(new Scanner(System.in));
        return Details.GET_WEATHER + Details.BREAKING_CHARACTERS + days;
    }

    private static String getWeatherForOneDay()
    {
        return Details.GET_WEATHER + Details.BREAKING_CHARACTERS + "1";

    }

    private static int getNumber(Scanner keyboard)
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
                System.out.println("Please enter a number");
                keyboard.nextLine();
            }
        }
        return number;
    }

    private static void displayMenu()
    {
        System.out.println("0. Exit");
        System.out.println("1. Get Weather for One day");
        System.out.println("2.Get Weather for Multiple Days");
    }
    private static void displayWeather(String Response)
    {
        String[] weather = Response.split(Details.BREAKING_CHARACTERS);
        Boolean first = true;
        for(String day: weather)
        {
            if(first)
            {
                first = false;
                continue;
            }
            System.out.println(day);
        }
    }

}
