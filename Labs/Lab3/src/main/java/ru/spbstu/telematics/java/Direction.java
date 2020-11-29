package ru.spbstu.telematics.java;

public class Direction
{
    public int from;
    public int to;

    public Direction(char start, char finish)
    {

        switch (start)
        {
            case 'u':
                from = 8;
                break;
            case 'r':
                from = 2;
                break;
            case 'd':
                from = 4;
                break;
            case 'l':
                from = 6;
                break;
            default:
                break;
        }

        switch (finish)
        {
            case 'u':
                to = 1;
                break;
            case 'r':
                to = 3;
                break;
            case 'd':
                to = 5;
                break;
            case 'l':
                to = 7;
                break;
            default:
                break;
        }
    }
}