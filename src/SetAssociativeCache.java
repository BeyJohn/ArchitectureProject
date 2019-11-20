import java.util.HashMap;

public class SetAssociativeCache implements Cache
{
	private int numberOfBlocks;
	private int numberOfSets;
	private int[] usedInSet, blocksInSet;
	private HashMap<Integer, HashMap<Integer, Integer>> content;
	private boolean twoWay;
	private String previous = ",";
	
	public SetAssociativeCache(int blocks, boolean twoWay)
	{
		numberOfBlocks = blocks;
		this.twoWay = twoWay;
		numberOfSets = numberOfBlocks / (twoWay ? 2 : 4);
		content = new HashMap<>();
		usedInSet = new int[numberOfSets];
		blocksInSet = new int[numberOfSets];
		int blocksLeft = blocks;
		for (int o = 0; o < numberOfSets; o++)// Need to create HashMaps for each set so there won't be a
												// NullPointerException later.
		{
			blocksInSet[o] = Math.min(twoWay ? 2 : 4, blocksLeft);
			blocksLeft -= blocksInSet[o];
			content.put(o, new HashMap<>());
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
		int set = reference % numberOfSets;
		
		if (!content.get(set).containsValue(reference))
		{
			if (usedInSet[set] == blocksInSet[set])
			{
				// No Hit, need to find LRU to replace
				int posLRU = -1;
				int valLRU = Integer.MAX_VALUE;
				for (int o = 0; o < (twoWay ? 2 : 4); o++)
				{
					int val = previous.lastIndexOf("," + content.get(set).get(o) + ",");
					if (val < valLRU)
					{
						posLRU = o;
						valLRU = val;
					}
				}
				
				content.get(set).put(posLRU, reference);
			} else
			{
				content.get(set).put(usedInSet[set]++, reference);
			}
			previous += reference + ","; // Used in LRU replacement
			return false;
		}
		previous += reference + ","; // Used in LRU replacement
		return true;
	}
	
}
