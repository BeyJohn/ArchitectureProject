import java.util.HashMap;

public class SetAssociativeCache implements Cache
{
	private int numberOfBlocks;
	private HashMap<Integer, HashMap<Integer, Integer>> content;
	private boolean twoWay;
	
	public SetAssociativeCache(int blocks, boolean twoWay)
	{
		numberOfBlocks = blocks;
		this.twoWay = twoWay;
		content = new HashMap<>();
		for (int o = 0; o < (twoWay ? 2 : 4); o++)// Need to create HashMaps for each set so there won't be a NullPointerException later.
		{
			content.put(o, new HashMap<>());
		}
	}
	
	@Override
	public void printCache()
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean memoryAccess(int reference)
	{
		if (!content.get(reference % (twoWay ? 2 : 4)).containsValue(reference))
		{
			
			return false;
		}
		return true;
	}
	
}
