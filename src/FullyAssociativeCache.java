import java.util.HashMap;

public class FullyAssociativeCache implements Cache
{
	private int numberOfBlocks, blocksUsed;
	private HashMap<Integer, Integer> content;
	private String previous = ",";
	
	public FullyAssociativeCache(int blocks)
	{
		numberOfBlocks = blocks;
		content = new HashMap<>();
	}
	
	@Override
	public void printCache()
	{
		System.out.println(content);
	}
	
	@Override
	public boolean memoryAccess(int reference)
	{
		if (!content.containsValue(reference))
		{
			if (blocksUsed == numberOfBlocks)// No Hit, need to find LRU to replace\
			{
				int posLRU = -1;
				int valLRU = Integer.MAX_VALUE;
				for (int o = 0; o < numberOfBlocks; o++)
				{
					int val = previous.lastIndexOf("," + content.get(o) + ",");
					if (val < valLRU)
					{
						posLRU = o;
						valLRU = val;
					}
				}
				content.put(posLRU, reference);
			} else
			{
				content.put(blocksUsed++, reference);
			}
			
			previous += reference + ","; // Used in LRU replacement
			return false;
		}
		previous += reference + ","; // Used in LRU replacement
		return true;
	}
	
}
