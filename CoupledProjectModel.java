
package Component.Project;

import java.awt.*;

import GenCol.*;


import model.modeling.*;
import model.simulation.*;

import view.modeling.ViewableAtomic;
import view.modeling.ViewableComponent;
import view.modeling.ViewableDigraph;
import view.simView.*;


public class CoupledProjectModel extends ViewableDigraph{

	protected int count;

	
public CoupledProjectModel(){
    super("couplingProject");
 
    
    
    ViewableAtomic g = new GeneratorProject("GeneratorProject", 5);
    ViewableAtomic hq = new HQ("HQ");
    ViewableAtomic trans = new TransducerProject("TransducerProject", 1000);
    ViewableAtomic chi = new SortingFacility("Chicago");
    ViewableAtomic mem = new SortingFacility("Memphis");
    ViewableAtomic tam = new SortingFacility("Tampa");
    ViewableAtomic phx = new Cities("Phoenix");
    ViewableAtomic sfo = new Cities("SFO");
    ViewableAtomic la = new Cities("LA");
    ViewableAtomic sea = new Cities("Seattle");
    ViewableAtomic lv = new Cities("LV");
    ViewableAtomic por = new Cities("Portland");
   // ViewableAtomic t = new transducerhw4("transducerhw4",200);
   // count++;
     add(g);
     add(hq);
     add(chi);
     add(mem);
     add(tam);
     add(phx);
     add(sfo);
     add(la);
     add(sea);
     add(lv);
     add(por);
     add(trans);
     
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
     addCoupling(chi,"out",phx,"in");
     addCoupling(chi,"out",sfo,"in");
     addCoupling(chi,"out",la,"in");
     addCoupling(chi,"out",sea,"in");
     addCoupling(chi,"out",lv,"in");
     addCoupling(chi,"out",por,"in");
     
     addCoupling(hq,"out",mem,"in");
     addCoupling(mem,"back",hq,"return");
     addCoupling(mem,"out",phx,"in");
     addCoupling(mem,"out",sfo,"in");
     addCoupling(mem,"out",la,"in");
     addCoupling(mem,"out",sea,"in");
     addCoupling(mem,"out",lv,"in");
     addCoupling(mem,"out",por,"in");
     
     addCoupling(hq,"out",tam,"in");
     addCoupling(tam,"back",hq,"return");
     addCoupling(tam,"out",phx,"in");
     addCoupling(tam,"out",sfo,"in");
     addCoupling(tam,"out",la,"in");
     addCoupling(tam,"out",sea,"in");
     addCoupling(tam,"out",lv,"in");
     addCoupling(tam,"out",por,"in");
     
     addCoupling(trans,"out",this,"result");
     addCoupling(g,"out",trans,"ariv");
     
     addCoupling(phx,"out", trans, "solved");
     addCoupling(sfo,"out", trans, "solved");
     addCoupling(la,"out", trans, "solved");
     addCoupling(sea,"out", trans, "solved");
     addCoupling(lv,"out", trans, "solved");
     addCoupling(por,"out", trans, "solved");
     
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
        preferredSize = new Dimension(1300, 600);
        ((ViewableComponent)withName("GeneratorProject")).setPreferredLocation(new Point(20, 300));
        
        ((ViewableComponent)withName("HQ")).setPreferredLocation(new Point(270, 300));
        
        ((ViewableComponent)withName("Chicago")).setPreferredLocation(new Point(520, 150));
        ((ViewableComponent)withName("Memphis")).setPreferredLocation(new Point(520, 300));
        ((ViewableComponent)withName("Tampa")).setPreferredLocation(new Point(520, 450));

        ((ViewableComponent)withName("Phoenix")).setPreferredLocation(new Point(770, 20));
        ((ViewableComponent)withName("SFO")).setPreferredLocation(new Point(770, 120));
        ((ViewableComponent)withName("LA")).setPreferredLocation(new Point(770, 220));
        ((ViewableComponent)withName("Seattle")).setPreferredLocation(new Point(770, 320));
        ((ViewableComponent)withName("LV")).setPreferredLocation(new Point(770, 420));
        ((ViewableComponent)withName("Portland")).setPreferredLocation(new Point(770, 520));
        
        ((ViewableComponent)withName("TransducerProject")).setPreferredLocation(new Point(970, 300));

       // ((ViewableComponent)withName("generatorhw4")).setPreferredLocation(new Point(50, 49));
    }
}
