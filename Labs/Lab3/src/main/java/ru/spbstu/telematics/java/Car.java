package ru.spbstu.telematics.java;

/**
 * Hello world!
 *
 */
public class Car extends Thread
{
    private TrafficLigth tLigth;
    int number;
    public String name;

    private Direction direction;

    public Car(char from, char to)
    {
        direction = new Direction(from, to);
        number = -1;
    }

    public Car(char from, char to, TrafficLigth tl)
    {
        direction = new Direction(from, to);
        tLigth = tl;
        number = -1;
    }

    public void SetTrafficLigth(TrafficLigth tl)
    {
        tLigth = tl;
    }

    public void SetNumber(int n)
    {
        number = n;

        System.out.println("Number " + number + "given");
    }

    public void SetName(String n)
    {
        name = n;
    }

    public void run()
    {
        System.out.println( "Started car from " + direction.from + " to " + direction.to);

        boolean isFree = false;
        Direction[] curDir;

        while(!isFree)
        {
            curDir = tLigth.GetState();

            for(int i = 0; i < curDir.length; i++)
                if(curDir[i].equals(direction))
                {
                    isFree = true;
                    System.out.println("Car number " + number + " released");
                    break;
                }

            tLigth.GiveCallback(number, isFree);

            //isFree = true;
        }
    }
}