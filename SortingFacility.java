package Component.Project;
import java.util.Random;
import GenCol.*;
import Component.SISO.*;
import model.modeling.content;
import model.modeling.message;
import view.modeling.ViewableAtomic;
public class SortingFacility extends ViewableAtomic{
	protected Queue<Pair<String, String>> packageQ;
	protected Pair<String, String> packagesSF;
	private static final String FULL = "full";
	protected String sfName;
	protected String compName;
	
	public SortingFacility() {
		this("SortingFacility");
	}
	public SortingFacility(String name) {
		super(name);
		
		sfName = name;
		packageQ = new Queue<Pair<String, String>>();
		addInport("in");
		addOutport("out");
		addOutport("back");
		
				
	}
	

	public void initialize() {
		super.initialize();
		sigma = INFINITY;	
		phase = "passive";
		
	}
	
	public void deltext(double e, message x) {
		Continue(e);
		packagesSF = (Pair<String, String>) x.getValOnPort("in",0);
		
		if(packagesSF.value.contains("Phoenix")|| packagesSF.value.contains("SFO") )
		{
			compName = "Chicago";
		}
		
		else if(packagesSF.value.contains("LA")|| packagesSF.value.contains("Seattle") )
		{
			compName = "Memphis";
		}
		
		else if(packagesSF.value.contains("LV") || packagesSF.value.contains("Portland") )
		{
			compName = "Tampa";
		}
		
		
		if (phaseIs("passive") && sfName == compName) {
			
			
				if (packagesSF.key.contains("package") ) {
					packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));	
					System.out.println("queue after adding to "+ sfName+ " : " + packageQ.size());
					holdIn("active", 20);
				}
		}
		else if (phaseIs("active") && sfName == compName && !packagesSF.value.contains("Delay"))
		{
			if (packagesSF.key.contains("package") ) {
				if(packageQ.size()<5)
				{
					
					packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));	
					System.out.println("queue after adding to "+ sfName+" : " + packageQ.size());
					//holdIn("active", sigma);
				}
				else if(packageQ.size()==5)
				{
					holdIn(FULL, 0);
				}
			}
			
		
			
		}
	 if(sfName != compName || packagesSF.value.contains("Delay"))
		{
			if(compName == "Chicago" && sfName == "Memphis" && packagesSF.value.contains("Chicago Delay"))
			{
				packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));
			}
			if(compName == "Tampa" && sfName == "Chicago" && packagesSF.value.contains("Tampa Delay"))
			{
				packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));
			}
			if(compName == "Memphis" && sfName == "Tampa" && packagesSF.value.contains("Memphis Delay"))
			{
				packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));
			}
			
			if(compName == "Memphis" && sfName == "Memphis" && packagesSF.value.contains("Chicago Delay"))
			{
				packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));
			}
		
			if(compName == "Memphis" && sfName == "Memphis" && packagesSF.value.contains("Tampa Delay"))
			{
				packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));
			}
			
			if(compName == "Chicago" && sfName == "Chicago" && packagesSF.value.contains("Memphis Delay"))
			{
				packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));
			}
			
			if(compName == "Chicago" && sfName == "Chicago" && packagesSF.value.contains("Tampa Delay"))
			{
				packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));
			}
			
			if(compName == "Tampa" && sfName == "Tampa" && packagesSF.value.contains("Chicago Delay"))
			{
				packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));
			}
			
			if(compName == "Tampa" && sfName == "Tampa" && packagesSF.value.contains("Memphis Delay"))
			{
				packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));
			}
		}
		
	}
	
	public void deltint() {	
			if (phaseIs("active")) {
				packageQ.remove();
				holdIn("active", 20);
				System.out.println("queue: after removing " + packageQ.size());
			 }
			if (packageQ.isEmpty()) {
				holdIn("passive", INFINITY);
			}
			if (phaseIs(FULL)) {
				holdIn("active", sigma);
			}
		
	} 
	
	public message out() {
		message m = new message();
		
		if (phaseIs("active")) 
		{
			m.add(makeContent("out", packageQ.first()));	
		}
		else if(phaseIs(FULL))
		{
			m.add(makeContent("back", new entity(sfName+" full")));

			return m;
		}
		return m;
	}
	
	public void showState() {
		super.showState();
		System.out.println("queue: " + packageQ.size());
	}

	public String getTooltipText() {
		return super.getTooltipText() + "\n" + " int_arr_time: hello "  + "\n" + " count: " ;
	}

}
