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

        System.out.println("Number " + number + " given");
    }

    public void SetName(String n)
    {
        name = n;
    }

    boolean statePrinted;
    boolean callbackPrinted;
    boolean initPrinted;

    @Override
    public void run()
    {
        statePrinted = false;
        callbackPrinted = false;
        initPrinted = false;
        updateState = true;

        System.out.println( "Started car from " + direction.from + " to " + direction.to);

        boolean isFree = false;
        Direction[] curDir;

        while(!isFree)
        {
            while(!updateState)
                ;

            curDir = tLigth.GetState();



            if(!initPrinted)
            {
                System.out.println("Car " + number + " got state");
                initPrinted = true;
            }

            if(curDir != null)
            for(int i = 0; i < curDir.length; i++)
                if( (curDir[i].from == direction.from) && (curDir[i].to == direction.to) )
                {
                    isFree = true;
                    System.out.println("Car number " + number + " released");
                    break;
                }
            else
                {
                    if(!statePrinted) {
                        System.out.println("For car " + number + " state donst match");
                        statePrinted = true;
                    }
                }

            //if(!callbackPrinted)
            tLigth.GiveCallback(number, isFree);
            updateState = false;

            if(!callbackPrinted) {
                System.out.println("Callback given by car " + number + " with " + isFree);
                callbackPrinted = true;
            }
            //isFree = true;
        }

        System.out.println("Car " + number + " released");
    }

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