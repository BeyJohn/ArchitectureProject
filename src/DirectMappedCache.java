import java.util.HashMap;

public class DirectMappedCache implements Cache
{
	private int numberOfBlocks;
	private HashMap<Integer, Integer> content;
	
	public DirectMappedCache(int blocks)
	{
		numberOfBlocks = blocks;
		content = new HashMap<>();
		for (int o = 0; o < numberOfBlocks; o++)
		{
			content.put(o, -1);
		}
	}
	
	@Override
	public void printCache()
	{
		System.out.println(content);
	}
	
	@Override
	public boolean memoryAccess(int reference)
	{
		if (!content.getOrDefault(reference % numberOfBlocks, -1).equals(reference))
		{
			content.put(reference % numberOfBlocks, reference);
			return false;
		}
		return true;
	}
	
}
