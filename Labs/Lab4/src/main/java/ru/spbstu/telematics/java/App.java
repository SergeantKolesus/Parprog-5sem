package ru.spbstu.telematics.java;

import java.sql.Time;
import java.util.Timer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void _CollectData()
    {
        double[][] res = null;
        double[] errors;
        long launchTime = 0;
        long finishTime = 0;
        long parallelTime = 0;
        long normalTime = 0;
        DirichleTask dt;

        for(int i = 20; i <= 200; i += 20) {
            dt = new DirichleTask(-2, 2, -2, 2);

            try {
                launchTime = System.nanoTime();
                res = dt.Solve(i, i, 0.1);
                finishTime = System.nanoTime();
            }
            catch (Exception exc)
            {
                finishTime = System.nanoTime();
            }

            parallelTime = finishTime - launchTime;
            System.out.print(parallelTime + " ");

            dt = new DirichleTask(-2, 2, -2, 2);

            launchTime = System.nanoTime();
            res = dt.UnthreadedSolve(i, i, 0.1);
            finishTime = System.nanoTime();

            normalTime = finishTime - launchTime;
            System.out.print(normalTime + " ");
            System.out.println(normalTime - parallelTime);
        }
    }

    private static void _Test()
    {
        DirichleTask dt = new DirichleTask(-2, 2, -2, 2);
        double[][] res = null;
        double[] errors;
        long launchTime = 0;
        long finishTime = 0;
        long parallelTime = 0;
        long normalTime = 0;

        //res = dt.UnthreadedSolve(8, 8, 0.1);

        try {
            launchTime = System.nanoTime();
            res = dt.Solve(8, 8, 0.1);
            finishTime = System.nanoTime();
        }
        catch (Exception exc)
        {
            finishTime = System.nanoTime();
        }

        parallelTime = finishTime - launchTime;
        System.out.println(parallelTime);
        dt.PrintMatrix(res);

        dt = new DirichleTask(-2, 2, -2, 2);

        launchTime = System.nanoTime();
        res = dt.UnthreadedSolve(8, 8, 0.1);
        finishTime = System.nanoTime();

        normalTime = finishTime - launchTime;
        System.out.println(normalTime);
        dt.PrintMatrix(res);
        System.out.println(normalTime - parallelTime);

    }


    public static void main( String[] args )
    {
        _CollectData();
        //_Test();
    }
}
