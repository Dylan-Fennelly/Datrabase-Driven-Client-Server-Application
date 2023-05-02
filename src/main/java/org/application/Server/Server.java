package org.application.Server;

import org.example.Question2.Core.Details;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        try {
            //Step 1: Listen for incoming connections and build communications link
            ServerSocket listeningSocket = new ServerSocket(Details.SERVER_PORT);

            ThreadGroup clientThreads = new ThreadGroup("Client Threads");
            clientThreads.setMaxPriority(Thread.currentThread().getPriority() - 1);
            boolean continueRunning = true;
            int numberClients = 0;

            while (continueRunning) {
                System.out.println("Server is up and listening for connections....");
                //Wait for an incoming connection and build a communications link
                Socket dataSocket = listeningSocket.accept();

                numberClients++;

                System.out.println("Server has now accepted " + numberClients + " clients");

                //Build the thread to handle the client
                //Thread needs:
                //1. A group to be stored in
                //Build the thread to handle the client
                //Thread needs:
                //1. A group to be stored in
                //2. A name to listed under
                //3. A socket to communicate through
                //4. Maybe other stuff
                ServiceThread newClient = new ServiceThread(clientThreads, dataSocket.getInetAddress() + "", dataSocket, numberClients);
                Thread clientThread = new Thread(clientThreads, newClient);
                clientThread.start();
            }
            listeningSocket.close();

        }
        catch (IOException e)
        {
            System.out.println("We have a problem: " + e.getMessage());
        }
    }
}
