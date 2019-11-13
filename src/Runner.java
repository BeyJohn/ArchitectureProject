import java.util.InputMismatchException;
import java.util.Scanner;

public class Runner
{
	
	public static void main(String[] args)
	{
		try (Scanner read = new Scanner(System.in))
		{
			Cache cache = null;
			while (cache == null) // Loop until cache is created
			{
				System.out.print("Please choose (1) Direct Mapped, (2) Set Associative, (3) Fully Associative: ");
				
				int choice = read.nextInt(), blocks = 0;
				switch (choice)
				{
					case 1:// Finish asking questions for Direct Mapped cache and set cache
						System.out.print("How many cache blocks will there be: ");
						blocks = read.nextInt();
						if (blocks > 0)
						{
							cache = new DirectMappedCache(blocks);
						}
						break;
					case 2:
						System.out.print("How many cache blocks will there be: ");
						blocks = read.nextInt();
						if (blocks > 0)
						{
							System.out.print("Will this cache be (1) Two Way Associative, (2) Four Way Associative: ");
							choice = read.nextInt();
							if (choice == 1 || choice == 2)
							{
								cache = new SetAssociativeCache(blocks, choice == 1);
							}
						}
						break;
					case 3:
						System.out.print("How many cache blocks will there be: ");
						blocks = read.nextInt();
						if (blocks > 0)
						{
							cache = new FullyAssociativeCache(blocks);
						}
						break;
					default:
						System.out.println("There was a bad input, please try again.");
				}
			}
			// The cache has been created by this point.
			System.out.println("Please enter the set of references. When finished enter -1 to end.");
			int reference = 0;
			int referenceCount = 0;
			int referenceMisses = 0;
			while ((reference = read.nextInt()) != -1)
			{
				referenceCount++;
				if (!cache.memoryAccess(reference))
				{
					referenceMisses++;
				}
				
			}
			System.out.println("Cache miss rate: " + referenceMisses / (double) referenceCount);
			cache.printCache();
		}
		catch (InputMismatchException e)
		{
			System.out.println("There was an error due to your input, please run the program again.");
		}
	}
	
}
