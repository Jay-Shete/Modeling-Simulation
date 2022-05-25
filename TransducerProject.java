
package Component.Project;


import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import GenCol.Pair;
import GenCol.doubleEnt;
import GenCol.entity;


import model.modeling.content;
import model.modeling.message;

import view.modeling.ViewableAtomic;



public class TransducerProject extends ViewableAtomic{
 protected Map arrived, solved;
 protected double clock,total_ta,observation_time;
 public Double count=0.00;
 protected String packageNum;

 public TransducerProject(String  name,double Observation_time){
  super(name);
   //addInport("in");
   addOutport("out");
  // addOutport("TA");
 //  addOutport("Thru");
  addInport("ariv");
  addInport("solved");
  //addOutport("out");
  arrived = new HashMap();
  solved = new HashMap();
  observation_time = Observation_time;
  addTestInput("ariv",new entity("val"));
  addTestInput("solved",new entity("val"));
  initialize();
  
  setBackgroundColor(Color.red);
 }

 public TransducerProject() {this("TransducerProject", 1000);}

 public void initialize(){
  phase = "active";
  sigma = observation_time;
  clock = 0;
  total_ta = 0;
  super.initialize();
 }

 public void showState(){
  super.showState();

 }

 public void  deltext(double e,message  x){

  clock = clock + e;
  Continue(e);
  Pair<String, Double>  val;
  for(int i=0; i< x.size();i++){
    if(messageOnPort(x,"ariv",i)){
    	val = (Pair<String, Double>) x.getValOnPort("ariv",i);
           arrived.put(val.key,new doubleEnt(clock));
    }
    if(messageOnPort(x,"solved",i)){
    	val = (Pair<String, Double>) x.getValOnPort("solved",i);
       count++;
       total_ta = 0.0;
      	packageNum = val.key;
      	
       if(arrived.containsKey(val.key)){
         entity  ent = (entity)arrived.get(val.key);

         doubleEnt  num = (doubleEnt)ent;
         double arrival_time = num.getv();

         double turn_around_time = clock - arrival_time;
         total_ta = total_ta + turn_around_time;
         System.out.println(val.key + " total time to be delivered "+ total_ta);
         solved.put(val.getName(), new doubleEnt(clock));
       }
    }
  }
show_state();
 }

 public void  deltint(){
  clock = clock + sigma;
  passivate();
  show_state();
 }

 public  message    out() {
  message  m = new message();

  content  con2 = makeContent("out",new entity());
  
  m.add(con2);
 
  return m;
 }

 public double compute_TA(){
  double avg_ta_time = 0;
  if(!solved.isEmpty())
    avg_ta_time = ((double)total_ta)/solved.size();
  return avg_ta_time;
 }

 
 public double compute_Thru(){
  double thruput = 0;
  if(clock > 0)
    thruput = solved.size()/(double)clock;
  return thruput;
 }

 public void  show_state(){

 if (arrived != null && solved != null){

  }
 }
}
