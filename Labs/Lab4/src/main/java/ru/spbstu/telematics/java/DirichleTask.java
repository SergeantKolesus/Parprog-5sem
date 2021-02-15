package ru.spbstu.telematics.java;
import java.io.Console;
import java.util.function.Function;

import static java.lang.Math.sin;

public class DirichleTask {
    //Poisson equasion - /laplace/ u = f
    //Laplace equasion - /laplaca/ u = 0
    //Dirichle task - /laplace/ u = 0 with
    //               |
    //Border cond - u|       = g
    //               |dOmega

    public void PrintMatrix(double res[][])
    {
        for(int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++)
                System.out.print(res[i][j] + "   ");
            System.out.println();
        }
    }

    public void PrintMatrix(Coordinate res[][])
    {
        for(int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(res[i][j].x + "   ");
            System.out.println();
        }
    }


    private double xMin;
    private double xMax;
    private double yMin;
    private double yMax;

    private double U(double x, double y)
    {
        //return x * sin(x);
        return x * y;
    }

    private double U(Coordinate c)
    {
        double x = c.x;
        //return x * sin(x);
        return x * c.y;
    }

    private double G(Coordinate c)
    {
        double x = c.x;

        //return x * sin(x);
        return x * c.y;
    }

    private double LaplacedU(double x, double y)
    {
        return 2 * Math.cos(x) - x * Math.sin(x);
    }

    public DirichleTask(double xMin, double xMax, double yMin, double yMax)
    {
        this.xMin = xMin;
        this.xMax = xMax;
        this.yMin = yMin;
        this.yMax = yMax;
    }

    double[][] solution = null;
    Coordinate[][] gridNodes = null;

    private void _generateGrid(int xSections, int ySections)
    {
        double xStep = (xMax - xMin) / xSections;
        double yStep = (yMax - yMax) / ySections;

        gridNodes = new Coordinate[xSections + 1][ySections + 1];

        for(int x = 0; x <= xSections; x++)
            for(int y = 0; y <= ySections; y++)
                gridNodes[x][y] = new Coordinate(x * xStep + xMin, y * yStep + yMin);
    }

    private void _initBorders(int xSections, int ySections)
    {
        for(int x = 0; x < solution.length; x++)
        {
            solution[x][0] = G(gridNodes[x][0]);
            solution[x][ySections] = G(gridNodes[x][ySections]);
        }

        for(int y = 1; y < solution.length - 1; y++)
        {
            solution[0][y] = G(gridNodes[0][y]);
            solution[xSections][y] = G(gridNodes[xSections][y]);
        }
    }

    private int topRow;
    private int secondTopRow;
    private int bottomRow;
    private double epsilon = 0.1;
    boolean firstInRule;

    private double _calculateFromTop()
    {
        boolean errorOutOfLimits = false;
        double t = 0;
        double err = 0;

        if(topRow >= bottomRow)
            return 0;

        for(; topRow < bottomRow; topRow++)
        {
            while( !firstInRule && (secondTopRow <= topRow) )
                try {
                    Thread.sleep(10);
                }
                catch (Exception exc) {}

            for(int x = 1; x < solution.length - 1; x++)
            {
                solution[topRow][x] = (solution[topRow - 1][x] + solution[topRow + 1][x] + solution[topRow][x - 1] + solution[topRow][x + 1]) / 4;

                if(!errorOutOfLimits)
                {
                    t = Math.abs(solution[topRow][x] - U(gridNodes[topRow][x]));

                    if(t > err) {
                        err = t;
                        if(err > epsilon)
                            errorOutOfLimits = true;
                    }
                }
            }
        }

        firstInRule = false;

        return err;
    }

    private double _calculateSecondWave(int row)
    {
        boolean errorOutOfLimits = false;
        double t = 0;
        double err = 0;
        int secondTopRow = row;

        if(secondTopRow >= bottomRow)
            return 0;

        for(; secondTopRow < bottomRow - 1; secondTopRow++)
        {
            while( firstInRule && (secondTopRow >= topRow) )
                try {
                    Thread.sleep(10);
                }
                catch (Exception exc) {}

            for(int x = 1; x < solution.length - 1; x++)
            {
                solution[secondTopRow][x] = (solution[secondTopRow - 1][x] + solution[secondTopRow + 1][x] + solution[secondTopRow][x - 1] + solution[secondTopRow][x + 1]) / 4;

                if(!errorOutOfLimits)
                {
                    t = Math.abs(solution[secondTopRow][x] - U(gridNodes[secondTopRow][x]));

                    if(t > err) {
                        err = t;
                        if(err > epsilon)
                            errorOutOfLimits = true;
                    }
                }
            }
        }

        for(int x = 1; x < solution.length - 1; x++)
        {
            solution[secondTopRow][x] = (solution[secondTopRow - 1][x] + solution[secondTopRow + 1][x] + solution[secondTopRow][x - 1] + solution[secondTopRow][x + 1]) / 4;

            if(!errorOutOfLimits)
            {
                t = Math.abs(solution[secondTopRow][x] - U(gridNodes[secondTopRow][x]));

                if(t > err) {
                    err = t;
                    if(err > epsilon)
                        errorOutOfLimits = true;
                }
            }
        }

        firstInRule = true;

        return err;
    }

    private double _launchThread()
    {
        final double[] err = new double[1];

        firstInRule = true;
        topRow = 1;
        bottomRow = solution[0].length - 1;
        err[0] = epsilon + 0.1;

        Thread t1 = new Thread()
        {
            public void run()
            {
                double t;
                err[0] = _calculateFromTop();
                //t = _calculateFromTop();
                //System.out.println(err[0]);
                //err[0] = t;
            }
        };

        t1.start();

        try {
            t1.join();
        }
        catch (Exception exc)
        {

        }

        return err[0];
    }

    private double _launchTwoThreads()
    {
        double actualErr = epsilon + 0.1;
        Thread t1 = null;
        Thread t2 = null;

        while(actualErr < epsilon) {
            final double[] err = new double[2];

            topRow = 1;
            secondTopRow = 1;
            bottomRow = solution[0].length - 1;
            err[0] = epsilon + 0.1;
            err[1] = epsilon + 0.1;

            if((t1 == null) || (!t1.isAlive()) ) {
                if(actualErr > err[0])
                    actualErr = err[0];

                t1 = new Thread() {
                    public void run() {
                        err[0] = _calculateFromTop();
                    }
                };
            }

            if((t2 == null) || (!t2.isAlive()) ) {
                if(actualErr > err[1])
                    actualErr = err[1];

                t2 = new Thread() {
                    public void run() {
                        err[1] = _calculateSecondWave(1);
                    }
                };
            }

            firstInRule = true;

            t1.start();
            t2.start();

            try{
                Thread.sleep(10);
            }
            catch (Exception exc){}
        }

        try {
            t1.join();
            firstInRule = false;
            t2.join();
        } catch (Exception exc) {

        }

        return actualErr;
    }

    public double[][] UnthreadedSolve(int xSections, int ySections, double eps)
    {
        if(xSections < 1 || ySections < 1)
            return null;

        epsilon = eps;
        double iterationErr = eps + 0.01;

        solution = new double[xSections + 1][ySections + 1];

        _generateGrid(xSections, ySections);

        _initBorders(xSections, ySections);

        for(int i = 0; (i < 10000) && (iterationErr > eps); i++)
        {
            iterationErr = _launchThread();
            //System.out.println(iterationErr);
            //System.out.println(i);
            //PrintMatrix(solution);
            //System.out.println();
            //System.out.println();
        }

        return solution;
    }

    public double[][] Solve(int xSections, int ySections, double eps)
    {
        if(xSections < 1 || ySections < 1)
            return null;

        double err = eps + 1;

        epsilon = eps;
        solution = new double[xSections + 1][ySections + 1];

        _generateGrid(xSections, ySections);

        _initBorders(xSections, ySections);
        int iterations = 0;

        /*
        while( (err > epsilon) && (iterations < 10000))
        {


            err = _launchTwoThreads();
            iterations++;
            //System.out.println(err);
            //System.out.println(iterations);
        }

         */
        _launchTwoThreads();

        //System.out.println(iterations);

        return solution;
    }

    /*
    double[] grid;
    double step;
    double[] solution;
    double[][] threelinearSystem;
    int bottomIndex;
    int topIndex;

    /*
    private void _buildGrid(int gridSections)
    {
        step = leftBorder - rightBorder;

        grid[0] = leftBorder;
        grid[gridSections] = rightBorder;

        for(int i = 1; i < gridSections; i++)
            grid[i] = grid[i - 1] + step;
    }

    private void _calculateFromTop()
    {
        if(topIndex <= bottomIndex)
            solution[1] = 2 * solution[0];

        for(topIndex++; topIndex <= bottomIndex; topIndex++)
        {
            System.out.print(topIndex + " : ");

            solution[topIndex] = 2 * solution[topIndex - 1] - solution[topIndex - 2];

            System.out.println(solution[topIndex]);
        }

    }

    private void _calculateFromBottom()
    {
        if(bottomIndex >= topIndex)
            solution[bottomIndex] = 2 * solution[bottomIndex + 1];

        for(bottomIndex--; topIndex <= bottomIndex; bottomIndex--)
        {
            System.out.print(bottomIndex + " : ");

            solution[bottomIndex] = 2 * solution[bottomIndex + 1] - solution[bottomIndex + 2];

            System.out.println(solution[bottomIndex]);
        }
    }

    public double[] Solve(int gridSections) throws Exception
    {
        if(gridSections < 1)
            throw  new Exception("Not enough points");

        grid = new double[gridSections + 1];
        solution = new double[gridSections + 1];

        _buildGrid(gridSections);

        topIndex = 1;
        bottomIndex = gridSections - 1;

        solution[0] = U(leftBorder);
        solution[gridSections] = U(rightBorder);

        if(gridSections == 1)
            return solution;

        Thread t1 = new Thread()
        {
            public void run()
            {
                _calculateFromTop();
            }
        };

        Thread t2 = new Thread()
        {
            public void run()
            {
                _calculateFromBottom();
            }
        };

        t1.start();
        t2.start();

        while(t1.isAlive() || t2.isAlive())
            try {
                wait(100);
            }
        catch (Exception exc)
        {

        }

        return solution;
    }

    public double[] Errors()
    {
        if(solution == null)
            return null;

        double[] errors = new double[solution.length];

        for(int i = 0; i < errors.length; i++)
            errors[i] = Math.abs(U(grid[i]) - solution[i]);

        return errors;
    }

     */
}
