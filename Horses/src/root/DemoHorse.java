package root;

public class DemoHorse {
	
	/*************
	 * Variables *
	 *************/
	
	/****************
	 * Constructors *
	 ****************/
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other
	
	public static void main(String[] args)
	{
		{
			Horse brego;
			brego = new RaceHorse();
			System.out.println(brego.getBirthYear());
			System.out.println(brego.getClass().toString());
			brego.neigh();
			brego.setBirthYear(15);
			System.out.println(brego.getBirthYear());
			brego = new WorkHorse();
			System.out.println(brego.getClass().toString());
			brego.neigh();
			System.out.println(brego.getBirthYear());
		}

		RaceHorse brego = new RaceHorse();
		brego.setNumOfRaces(5);
		System.out.println(brego.getNumOfRaces());
		System.out.println(brego.getColor());
		brego.setColor("Mottled Maroon");
		System.out.println(brego.getColor());
		
	}

}
