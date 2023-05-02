package org.application.Server;

import com.github.javafaker.Faker;
import org.example.Question2.Core.Details;

public class GetWeatherCommand implements Command
{
    @Override
    public String createResponse(String[] components)
    {
        StringBuffer response = new StringBuffer();
        response.append(Details.RETURN_WEATHER);
        response.append(Details.BREAKING_CHARACTERS);
        //The client will request weather for the next n days
        Faker faker = new Faker();
        int numberOfDays = Integer.parseInt(components[1]);
        for(int i =0; i < numberOfDays; i++)
        {
            if(i == numberOfDays -1)
            {
                response.append(faker.weather().description());
            }
            else
            {
                response.append(faker.weather().description());
                response.append(Details.BREAKING_CHARACTERS);
            }
        }
        return response.toString();
    }
}
