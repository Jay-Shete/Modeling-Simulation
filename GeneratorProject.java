package Component.Project;
import java.awt.Color;
import java.lang.*;
import java.util.Random;

import GenCol.*;

import model.modeling.*;
import model.simulation.*;

import view.modeling.ViewableAtomic;
import view.simView.*;

public class GeneratorProject extends ViewableAtomic{
	protected double int_arr_time;
	protected int count;
	public String[] cities = {"Phoenix", "SFO", "LA", "Seattle", "LV", "Portland"};
	static int c = 0;

	public GeneratorProject() {
		this("GeneratorProject", 5);
	}
	
	public GeneratorProject(String name, double Int_arr_time) {
		super(name);
		addInport("in");
		addOutport("out");
		int_arr_time = Int_arr_time;

		addTestInput("in", new entity("start"));
		addTestInput("in", new entity("stop"));
		
  
	}
	
	public void initialize() {
		holdIn("passive", INFINITY);
		sigma = 0;
		count = 1;
		super.initialize();
	}
	
	public void deltext(double e, message x) {
		Continue(e);
		entity input = x.getValOnPort("in" , 0);
		if (phaseIs("passive")) {
			
			
				if (input.eq("start")) {
						
					holdIn("active", int_arr_time);
				}
		}
		if (phaseIs("active"))
		{
				if (input.eq("stop"))
					phase = "passive";
				}
	}
	
	public void deltint() {
		
		if (phaseIs("active")) {
			count = count + 1;

			holdIn("active", int_arr_time);
		} else
			passivate();
	}

	public message out() {

		message m = new message();
		content con = makeContent("out", new Pair<String, String>("package "+count, cities[new Random().nextInt(cities.length)]));
		m.add(con);

		return m;
	}
	public void showState() {
		super.showState();
		System.out.println("int_arr_t: " + int_arr_time);
	}

	public String getTooltipText() {
		return super.getTooltipText() + "\n" + " int_arr_time: " + int_arr_time + "\n" + " count: " + count;
	}

}
