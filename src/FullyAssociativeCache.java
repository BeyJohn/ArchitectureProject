import java.util.HashMap;

public class FullyAssociativeCache implements Cache
{
	private int numberOfBlocks;
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
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean memoryAccess(int reference)
	{
		if (!content.containsValue(reference))
		{
			// No Hit, need to find LRU to replace
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
			previous += reference + ","; // Used in LRU replacement
			return false;
		}
		previous += reference + ","; // Used in LRU replacement
		return true;
	}
	
}
