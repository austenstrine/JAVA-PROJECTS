package insps;

import java.util.*;
import javax.swing.*;

public class Inspections 
{
	private static ArrayList<Integer> factoryDays = new ArrayList<Integer>(52);
	
	public static void main(String[] args)
	{
		int i = 51;
		int loc = 0;
		while(i >= 0)
		{
			assign(randomize(loc));
			--i;
		}
		ArrayList<Integer> factoryIters = new ArrayList<Integer>(4);
		for(int in = 4; in > 0; --in)
		{
			factoryIters.add(0);
		}
		for(int bob = 0; bob <= 51; ++bob)
		{
			JOptionPane.showMessageDialog(null, "Day "+(bob+1)+": Factory " + factoryDays.get(bob) +" will be inspected.");
		}
		for(int gob = 0; gob < factoryDays.size(); ++gob)
		{
			if(factoryDays.get(gob) == 1)
				
				factoryIters.set(0, factoryIters.get(0)+1);
			else if(factoryDays.get(gob) == 2)
				factoryIters.set(1, factoryIters.get(1)+1);
			else if(factoryDays.get(gob) == 3)
				factoryIters.set(2, factoryIters.get(2)+1);
			else if(factoryDays.get(gob) == 4)
				factoryIters.set(3, factoryIters.get(3)+1);
		}
		JOptionPane.showMessageDialog(null, "Factory 1 is to be inspected "+factoryIters.get(0)+" times. \nFactory 2 is to be inspected "+factoryIters.get(1)+" times. \nFactory 3 is to be inspected "+factoryIters.get(2)+" times. \nFactory 4 is to be inspected "+factoryIters.get(3)+" times.");

	}
	
	public static int randomize(int loc)
	{
		loc = 1 + (int) (Math.random() * 4);
		return loc;
	}
	
	public static void assign(int fac)
	{
		factoryDays.add(fac);
	}
}
