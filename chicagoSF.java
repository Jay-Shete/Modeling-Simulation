package Component.Project;
import java.util.Random;
import GenCol.*;
import Component.SISO.*;
import model.modeling.message;
import view.modeling.ViewableAtomic;
public class chicagoSF extends ViewableAtomic{
	protected Queue<Pair<String, String>> packageQ;
	protected Pair<String, String> packagesSF;
	private static final String FULL = "full";
	public chicagoSF() {
		this("chicagoSF");
	}
	public chicagoSF(String name) {
		super(name);
		
	
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
		if (phaseIs("passive")) {
			
			
				if (packagesSF.key.contains("package")) {
					packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));	
					System.out.println("queue: after adding" + packageQ.size());
					holdIn("active", 20);
				}
		}
		else if (phaseIs("active"))
		{
			if (packagesSF.key.contains("package")) {
				if(packageQ.size()<5)
				{
					
					packageQ.add(new Pair<String, String>(packagesSF.key, packagesSF.value));	
					System.out.println("queue: after adding" + packageQ.size());
					holdIn("active", 20);
				}
				else if(packageQ.size()==5)
				{
					holdIn(FULL, 20);
				}
			}
			
		}
		
	}
	
	public void deltint() {	
			 if (phaseIs("active")) {
			packageQ.remove();
			System.out.println("queue: after removing" + packageQ.size());
			 }
			if (packageQ.isEmpty()) {
				holdIn("passive", INFINITY);
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
			m.add(makeContent("back", new entity("Bhar gaya")));
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
