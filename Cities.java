package Component.Project;
//import java.util.Random;
import GenCol.*;
import Component.SISO.*;
import model.modeling.message;
import view.modeling.ViewableAtomic;

public class Cities extends ViewableAtomic{
	protected Pair<String, String> packages;
	protected String cityName;
	protected Pair<String, String> myPackage;
	public Cities() {
		this("cityPhoenix");
	}
	public Cities(String name) {
		super(name);
		packages = new Pair<String, String>();
		cityName = name;
		addInport("in");
		//addInport("return");
		addOutport("out");	
		
	}
	
	public void initialize() {
		super.initialize();
		sigma = INFINITY;
		phase = "passive";
	
	}
	
	public void deltext(double e, message x) {
		Continue(e);
		for(int i=0; i<x.getLength(); i++)
		{
		packages = (Pair<String, String>) x.getValOnPort("in",i);
		if (phaseIs("passive")) {
			
			
				if (packages.key.contains("package") && packages.value.contains(cityName)) {
					myPackage = packages;	
					holdIn("active", 0);
				}
		}
//		if (phaseIs("active"))
//		{
//				if (input.eq("stop"))
//					phase = "passive";
//		}
		}
	}
	
public void deltint() {
		
		
			passivate();
	}

public message out() {
	message m = new message();
	
	if (phaseIs("active")) {
	
		m.add(makeContent("out", myPackage));
		
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
