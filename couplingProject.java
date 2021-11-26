
package Component.Project;

import java.awt.*;

import GenCol.*;


import model.modeling.*;
import model.simulation.*;

import view.modeling.ViewableAtomic;
import view.modeling.ViewableComponent;
import view.modeling.ViewableDigraph;
import view.simView.*;


public class couplingProject extends ViewableDigraph{

	protected int count;

	
public couplingProject(){
    super("couplingProject");
 
    
    
    ViewableAtomic g = new GeneratorProject("GeneratorProject", 10);
    ViewableAtomic hq = new HQ("HQ");
    ViewableAtomic chi = new chicagoSF("chicagoSF");
   // ViewableAtomic t = new transducerhw4("transducerhw4",200);
   // count++;
     add(g);
     add(hq);
     add(chi);
    // add(t);
//
//  
//    addInport("start");
//    addInport("stop");
//    addOutport("out");
    addOutport("result");
//
//     addTestInput("start",new entity());
//     addTestInput("stop",new entity(), 5.0);

//     addCoupling(this,"start",g,"start");
//     addCoupling(this,"start",g,"start");
//     addCoupling(this,"stop",g,"finish");
     addCoupling(g,"out",hq,"in");
     addCoupling(hq,"out",chi,"in");
     addCoupling(chi,"back",hq,"return");
     addCoupling(chi,"out",this,"result");
     
//     addCoupling(p,"outgoingVoice",t,"solved"); 
//     addCoupling(t,"AVG",this,"result");
//     addCoupling(t,"MAX",this,"result");
//     addCoupling(t,"MIN",this,"result");
//     addCoupling(t,"calls",this,"result");
     


}

public void initialize() {
	
//	count = 0;
	super.initialize();
}
    
   
    public void layoutForSimView()
    {
        preferredSize = new Dimension(591, 269);
        ((ViewableComponent)withName("GeneratorProject")).setPreferredLocation(new Point(63, 178));
        ((ViewableComponent)withName("HQ")).setPreferredLocation(new Point(254, 47));
       // ((ViewableComponent)withName("generatorhw4")).setPreferredLocation(new Point(50, 49));
    }
}
