import java.util.*;

public class Objective {
	int tier;
	String name;
	ArrayList<String> tags;
	
	public Objective()
	{
		tier = 0;
		name = "None";
		tags = new ArrayList<String>();
	}
	
	public Objective(int tier, String name, ArrayList<String> tags)
	{
		this.tier = tier;
		this.name = name;
		this.tags = tags;
	}
	
	public int getTier()
	{
		return tier;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getTags()
	{
		if(tags.isEmpty())
			return "[]";
		return "[\"" + String.join("\",\"", tags) + "\"]";
	}
	
	public String toString()
	{
		return "{\"name\": \"" + getName() + "\", \"types\": " + getTags() + "}";
	}
}
