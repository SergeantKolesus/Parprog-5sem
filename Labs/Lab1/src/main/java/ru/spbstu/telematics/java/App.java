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

        System.out.println(number);

        isSimple = true;
        candidate  = simples.get(number - 1) + 2;

        System.out.println(candidate);

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

	private static String _simpleDividers(int val)
	{
	    int restVal;
	    int i;
	    int deg;
        String res;

        restVal = val;

	    ArrayList<Integer> simples = new ArrayList<Integer>();

        simples.add(2);
        simples.add(3);

        res = Integer.toString(val) + " = 1";
        deg = 0;
        i = 0;

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
                    res += " * " + simples.get(i);
                    if(deg != 1)
                        res += " ^ " + deg;
                }

                deg = 0;
                i++;

                if(i == simples.size())
                    simples.add(_getNextSimple(simples));
            }
        }

        res += " * " + simples.get(i);
        if(deg != 1)
            res += " ^ " + deg;

        return res;

	}

    public static void main( String[] args )
    {
        if(args.length > 0)
        	System.out.println( _simpleDividers(Integer.parseInt(args[0]) ) );
        else
        	System.out.println( "No input params" );
    }
}
