import java.io.*;
import java.util.*;

public class main {
	private static ArrayList<Objective> unsorted = new ArrayList<Objective>();
	private static ArrayList<ArrayList<Objective>> sorted = new ArrayList<ArrayList<Objective>>(); 
	
	private static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("Input file: ");
		String file = input.nextLine();
		if(file.startsWith("\"")) {
			file = file.substring(1,file.length()-1);
		}
		
		try {
			Scanner in = new Scanner(new File(file));
			in.nextLine();
			while(in.hasNextLine()) {
				String value = in.nextLine();
				String[] values = value.split("\t");
				if(values.length >=2) {
					int tier = Integer.parseInt(values[0]);
					String name = values[1];
					ArrayList<String> tags = new ArrayList<String>(); // separate tags
					for(int i=2; i<values.length;i++) {
						tags.add(values[i]);
					}
					
					Objective obj = new Objective(tier, name, tags);
					unsorted.add(obj);
				}
			}
			
			// create tiers
			for(int i=0; i<25; i++) {
				sorted.add(new ArrayList<Objective>());
			}
			
			// sort objectives into tiers
			for(Objective obj: unsorted) {
				sorted.get(obj.tier-1).add(obj);
			}
			
			String srl = generateSRL(sorted);
			
			PrintWriter out = new PrintWriter(new FileOutputStream(new File("SRL.json")));
			out.print(srl);
			out.close();
			
			System.out.print("SRL.json generated.\nPress [ENTER] to close.");
			input.nextLine();
			
		}
		catch(IOException e)
		{
			System.out.println(e);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	private static String generateSRL(ArrayList<ArrayList<Objective>> objectives) {
		String output = "[\n";
		for(ArrayList<Objective> objtier: objectives) {
			output += "\t[\n";
			for(Objective obj: objtier) {
				output += "\t\t" + obj.toString() + ",\n";
			}
			output = output.substring(0, output.length()-2) + "\n";
			output += "\t],\n";
		}
		output = output.substring(0, output.length()-2) + "\n";
		output += "]";
		
		return output;
	}
}
