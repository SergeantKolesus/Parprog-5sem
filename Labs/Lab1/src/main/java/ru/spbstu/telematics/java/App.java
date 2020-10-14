package ru.spbstu.telematics.java;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    private static int _getNextSimple(ArrayList<Integer> simples)
    {
        int number = simples.size();
        int[] rest = new int[number];
        int candidate;
        boolean isSimple;
        isSimple = true;
        candidate  = simples.get(number - 1) + 2;

        for(int i = 0; i < number; i++)
        {
            rest[i] = candidate % simples.get(i);
            if(rest[i] != 0)
                isSimple = false;
        }

        while(isSimple)
        {
            candidate += 2;
            isSimple = true;

            for(int i = 0; i < number; i++)
            {
                rest[i] = (rest[i] + 2) % simples.get(i);
                if(rest[i] != 0)
                    isSimple = false;
            }
        }

        return candidate;
    }

	public static String SimpleDividers(int val)
	{
	    int restVal;
	    int i;
	    int deg;
        String res;

        restVal = val;

	    ArrayList<Integer> simples = new ArrayList<Integer>();

        simples.add(2);
        simples.add(3);

        //res = Integer.toString(val) + " = ";
        res = "";
        deg = 0;
        i = 0;

        if(val == 1)
            return "1 = 1";

        while(restVal != 1)
        {
            if( (restVal % simples.get(i)) == 0)
            {
                deg++;
                restVal /= simples.get(i);
            }
            else
            {
                if(deg != 0)
                {
                    res += simples.get(i);
                    if(deg != 1)
                        res += "^" + deg;
                    res += " * ";
                }

                deg = 0;
                i++;

                if(i == simples.size())
                    simples.add(_getNextSimple(simples));

                if(simples.get(i) == val)
                    break;
            }
        }

        if( (res.length() == 0) && (deg <= 1))
            res = "1 * ";

        res += simples.get(i);
        if(deg > 1)
            res += "^" + deg;
        //res += " * ";

        //res = res.substring(0, res.length() - 3);

        res = val + " = "+ res;

        return res;

	}

	public static ArrayList<int[]> SimpleDividersArray(int val)
    {
        int restVal;
        int i;
        int deg;
        ArrayList<int[]> res;

        restVal = val;

        ArrayList<Integer> simples = new ArrayList<Integer>();

        simples.add(2);
        simples.add(3);

        deg = 0;
        i = 0;

        if(val == 1)
            return null;

        res = new ArrayList<int[]>();

        while(restVal != 1)
        {
            if( (restVal % simples.get(i)) == 0)
            {
                deg++;
                restVal /= simples.get(i);
            }
            else
            {
                if(deg != 0)
                {
                    res.add(new int[]{simples.get(i), deg});
                }

                deg = 0;
                i++;

                if(i == simples.size())
                    simples.add(_getNextSimple(simples));

                if(simples.get(i) == val)
                    break;
            }
        }

        res.add(new int[]{simples.get(i), deg});

        return res;
    }

    public static int UnpackRepresentation(ArrayList<int[]> representation)
    {
        if(representation == null)
            return 1;

        int res = 1;

        for (int[] simple: representation)
        {
            for(int i = 1; i < simple[1]; i++)
                res *= simple[0];
        }

        return res;
    }

    public static void main( String[] args )
    {
        if(args.length > 0)
        	System.out.println( SimpleDividers(Integer.parseInt(args[0]) ) );
        else
        	System.out.println( "No input params" );
    }
}
