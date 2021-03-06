package ru.spbstu.telematics.java;

public class Car extends Thread
{
    private TrafficLigth tLigth;
    int number;
    public String name;
    Thread t;
    private boolean updateState;

    private Direction direction;

    public void Unfreeze()
    {
        updateState = true;
    }

    public Car(char from, char to, TrafficLigth tl, int n)
    {
        direction = new Direction(from, to);
        tLigth = tl;
        number = n;
        isAsleep = true;

        System.out.println("Created car number " + n + " with tl " + tl);
    }

    public void SetTrafficLigth(TrafficLigth tl)
    {
        tLigth = tl;
    }

    public void SetNumber(int n)
    {
        number = n;

        System.out.println("Number " + number + " given");
    }

    public void SetName(String n)
    {
        name = n;
    }

    boolean statePrinted;
    boolean callbackPrinted;
    boolean initPrinted;

    public void run()
    {
        boolean isFree = false;
        Direction[] curDir;

        while (!isFree)
        {
            if(!isAsleep)
            {
                curDir = tLigth.GetState();

                if(curDir == null)
                    continue;

                System.out.println("Car number " + number + "succesfully got state");

                for(int i = 0; i < curDir.length; i++)
                    if( (curDir[i].from == direction.from) && (curDir[i].to == direction.to) )
                    {
                        isFree = true;
                        System.out.println("Car number " + number + " released");
                        break;
                    }

                if(!isFree)
                {
                    System.out.println("Car number " + number + " staying on crossroad");
                }

                isAsleep = true;
                tLigth.GiveCallback(number, isFree);
            }

            try {
                Thread.sleep(300);
            }
            catch (InterruptedException exc) {
                System.out.println(exc);
            }
        }
    }

    public boolean isAsleep;

    public void activate()
    {
        Runnable task = () -> {
            this.run();
        };

        t = new Thread(task);
        t.start();
    }
}
//*/