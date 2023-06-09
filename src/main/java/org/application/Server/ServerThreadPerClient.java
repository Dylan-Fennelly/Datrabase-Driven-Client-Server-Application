package org.application.Server;

import org.application.Core.Commands;
import org.application.Core.ServerDetails;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerThreadPerClient extends Thread
{

    private Socket dataSocket;
    private Scanner input;
    private PrintWriter output;
    private int number;

    //Constructor needs:
    //1. A socket to communicate with the client

    public ServerThreadPerClient(ThreadGroup clientThreads, String s, Socket dataSocket, int number)
    {
        super(clientThreads, s);
        try
        {
            this.dataSocket = dataSocket;
            this.number = number;

            //Create stream for reading from the client
            input = new Scanner(new InputStreamReader(this.dataSocket.getInputStream()));
            output = new PrintWriter(this.dataSocket.getOutputStream(), true);

        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void run() {
        String incomingMessage = "";
        String response = "";
        boolean shouldTerminate = false;

        try
        {
            while (!shouldTerminate)
            {
                // Wipe the response to make sure we don't send old values
                response = null;
                // Read a message from the client
                try
                {
                    incomingMessage = input.nextLine();
                }
                catch (Exception e)
                {
                    System.out.println("Client #" + number + " has disconnected");
                    break;
                }
                System.out.println("Server received: " + incomingMessage);

                // Break up the message into its components
                String[] messageParts = incomingMessage.split(ServerDetails.BREAKING_CHARACTERS);

                // Process the message
                // Use the command factory to create the command object
                CommandFactory commandFactory = new CommandFactory();
                Command command = CommandFactory.createCommand(messageParts[0]);
                // Execute the command
                response = command.createResponse(messageParts);
                // Print out the response
                System.out.println("Server sending: " + response);
                // Send the response back to the client
                output.println(response);

                // Check if the client has sent a FIN packet
                if (dataSocket.isInputShutdown())
                {
                    shouldTerminate = true;
                    System.out.println("Received FIN packet from client, sending ACK packet...");
                    dataSocket.shutdownOutput();
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(incomingMessage);
            System.out.println("An exception occurred with client number #" + number + ": " + e.getMessage());
        }
        finally
        {
            try
            {
                // Close the connection
                System.out.println("Closing the connection with client number #" + number);
                dataSocket.close();
            }
            catch (IOException e)
            {
                System.out.println("Unable to disconnect with client number #" + number);
                System.exit(1);
            }
        }
    }

}