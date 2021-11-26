package Component.Project;
import java.util.Random;
import GenCol.*;
import Component.SISO.*;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class HQ extends ViewableAtomic{
	protected Pair<String, String> packages;
	public HQ() {
		this("HQ");
	}
	public HQ(String name) {
		super(name);
		packages = new Pair<String, String>();
		addInport("in");
		addInport("return");
		addOutport("out");	
		
	}
	
	public void initialize() {
		super.initialize();
		sigma = INFINITY;
		phase = "passive";
	
	}
	
	public void deltext(double e, message x) {
		Continue(e);
		packages = (Pair<String, String>) x.getValOnPort("in",0);
		if (phaseIs("passive")) {
			
			
				if (packages.key.contains("package")) {
						
					holdIn("active", 1);
				}
		}
//		if (phaseIs("active"))
//		{
//				if (input.eq("stop"))
//					phase = "passive";
//		}
		
	}
	
public void deltint() {
		
		
			passivate();
	}

public message out() {
	message m = new message();
	
	if (phaseIs("active")) {
		m.add(makeContent("out", packages));
		
	}

	return m;
}
public void showState() {
	super.showState();
	//System.out.println("int_arr_t: " + int_arr_time);
}

public String getTooltipText() {
	return super.getTooltipText() + "\n" + " int_arr_time: hello "  + "\n" + " count: " ;
}


}
