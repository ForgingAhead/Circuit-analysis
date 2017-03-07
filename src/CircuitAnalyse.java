import javax.swing.JOptionPane;
import java.util.*;
/**
 * The CircuitAnalyse class analyses circuit as a resistor networks with three basic construction rule:
 * A single resistor is a resistor network
 * A  resistor network in series with a resistor is a resistor network
 * A resistor network in parallel with a resistor is a resistor network
 * In this class, it contains a method to create resistor networks and can analyse two fixed circuits or 
 *  circuit that user provided.
 *
 */

public class CircuitAnalyse{
	private Circuit circuit;
	static final int N = 20;
	/**
	 * Inner class Stack stores objects with the type ResistorNetwork. It implements the basic functions of a stack by using
	 * LinkedList.
	 *
	 */
	private class Stack {

		// use composition to provide linked list features
		private LinkedList<ResistorNetwork> storage;
		/**
		 * Default constructor
		 */
		public Stack() {
			storage = new LinkedList<ResistorNetwork>();
		}		
		/**
		 * pushes (adds) a tree node to the stack
		 * 
		 * @param node - tree node object to be added to the stack
		 */
		public void push(ResistorNetwork node) {
			storage.addFirst(node);
		}
		/**
		 * pops (removes) a tree node from the stack
		 * 
		 * @return Last tree node that has been added to stack. if stack is empty
		 * 	null is returned.
		 */
		public ResistorNetwork pop() {
			ResistorNetwork  node = null;
			if (storage.size() > 0) {
				node = storage.removeFirst();
			}
			return node;
		}

}	
	/**
	 * Inner class ResistorNetwork is the resistor network that the CircuitAnalyse need to analyse.
	 * It contains another ResistorNetwork and a Resistor and both the two together create the Circuit, while the 
	 * ResistorNetwork component or the Resistor can be null as resistor networks has three basic construction rule.
	 * @author Administrator
	 *
	 */
	private class ResistorNetwork{
		private ResistorNetwork network;
		private Resistor resist;
		private Circuit cir; 
		private int type = 0;           //type = 0: only one    type = 1: series   type = 2 : parallel
		/*
		 * Default constructor, a new empty ResistorNetwork will be created.
		 */
		public ResistorNetwork(){
			resist = null;
			setNetwork(null);
			cir = null;
		}
		
		/**
		 * 
		 *Construct a new ResistorNetwork that only has a single resistor which is the argument Resistor.
		 * @param rr
		 */
		public ResistorNetwork(Resistor rr){
			resist = rr;
			setNetwork(null);
			Circuit a;
			a = new Single(rr);
			cir = a;
		}
		
		/**
		 * Construct a new ResistorNetwork in parallel or series according to its type. 
		 * @param net one ResistorNetwork component of the new ResistorNetwork
		 * @param rr a single resistor component of the new ResistorNetwork
		 * @param t the type of the resistor network, series or parallel
		 */
		public ResistorNetwork(ResistorNetwork net,Resistor rr, int t){
			setNetwork(net);
			resist = rr;
			Circuit a;
			a = new Single(rr);// create a single circuit for the single resistor
			/* create a circuit cir taking net.getCircuit() and a in series */
			if(t == 1){
				cir = new Series(net.getCircuit(),a, "");
				
			}
			/* create a circuit cir taking net.getCircuit() and a in parallel */
			else if(t == 2){
				cir = new Parallel(net.getCircuit(),a, "");
			}
			this.setType(t);
		}
		/**
		 * Construct a new ResistorNetwork in parallel or series according to its type with the given circuit name
		 * @param net one ResistorNetwork component of the new ResistorNetwork
		 * @param rr a single resistor component of the new ResistorNetwork
		 * @param t the type of the resistor network, series or parallel
		 * @param circuitName the name of the creating circuit
		 */
		public ResistorNetwork(ResistorNetwork net,Resistor rr, int t, String circuitName){
			setNetwork(net);
			resist = rr;
			Circuit a;
			a = new Single(rr);
			if(t == 1){
				cir = new Series(net.getCircuit(),a, circuitName);
			}
			else if(t == 2){
				cir = new Parallel(net.getCircuit(),a, circuitName);
			}
			type = t;
		}
		
		/**
		 * @return the total resistance of this circuit ( this resistor network)
		 */
		public float getResistance(){
			return cir.getAllResistance();
		}
		/**
		 * 
		 * @return the actual circuit of this resistor network
		 */
		public Circuit getCircuit(){
			return cir;
		}
		/**
		 * Set the ResistorNetwork component of the resistor network to the argument network
		 * @param network
		 */
		public void setNetwork(ResistorNetwork network) {
			this.network = network;
		}
		/**
		 * 
		 * @return the network of the ResistorNetwork component
		 */
		public ResistorNetwork getNetwork() {
			return network;
		}
		/**
		 * Set the type of the resistor network, single, parallel or series
		 * @param type
		 */
		public void setType(int type) {
			this.type = type;
		}
		/**
		 * 
		 * @return the type of the resistor network
		 */
		public int getType() {
			return type;
		}
		/**
		 * represent the ResistorNetwork in a string form
		 * @return the string representation of the resistor network in a string form
		 */
		public String toString(){
			return cir.getStringForm();
		}

	}

	/**
	 * Create resistor network from a list of resistors.
	 * This method will find the resistor network that is closest in overall resistance to the argument target resistance
	 * @param target the target resistance that the created resistor network should be close to.
	 * @param r a list of Resistors to create the resistor network
	 * @param n number of Resistors
	 * @return the resistor network that is closest in overall resistance to the argument target resistance
	 */
	public ResistorNetwork getTarget(float target, Resistor r[], int n){
		ResistorNetwork  net1, result;
		Stack link, link1;
        link = new Stack();
		
		float best = Math.abs(r[0].getResistance() - target);
		
		result = new ResistorNetwork(r[0]); //the very resistor network that will be returned.
		net1 = new ResistorNetwork(r[0]); // temporary resistor network we need to try on to find the best. 
		link.push(net1);
		/* use a stack to construct the best resistor network */
		for(int i = 1; i < n; i++){
			link1 = new Stack();
			
			ResistorNetwork aa;
			aa = link.pop();
			
			while(aa != null){
				int j = aa.resist.getNumber();// use the number to tell how many resistors have been used so far.
				/* use a stack to try every construction of the rest resistors with the component resistor network aa to find the best one */
				for(int k = j+1; k<n; k++){
					/* try to create a resistor network in series taking aa and r[k] */
					ResistorNetwork rn;
					rn = new ResistorNetwork(aa, r[k], 1);
					/* push it into stack for further usage to create other resistor network */
					link1.push(rn);
					/* if this new resistor network can satisfy the condition, change the result network to this network */
					if(Math.abs(rn.getResistance() - target) < best){
						best = Math.abs(rn.getResistance() - target);
						result = new ResistorNetwork(aa, r[k], 1);
					}
					/* try to create a reistor network in parallel taking aa and r[k] */					
					ResistorNetwork rnp;
					rnp = new ResistorNetwork(aa, r[k], 2);
					/* push it into stack for further usage to create other resistor network */
					link1.push(rnp);
					/* if this new resistor network can satisfy the condition, change the result network to this network */
					if(Math.abs(rnp.getResistance() - target) < best){
						best = Math.abs(rnp.getResistance() - target);
						result = new ResistorNetwork(aa, r[k], 2);
					}
				}			
				aa = link.pop();
			}// end while
			
			link = link1;
		}
		//end for
		return result;	
	}
	/**
	 * 
	 * @return the name of the whole circuit
	 */
	public String getNames(){
		return circuit.getAllName();
	}
	/**
	 * 
	 * @param c
	 * @return the name of the argument circuit in a string form
	 */
	public String getComponent(Circuit c){
		return circuit.getStringForm();
	}
	/**
	 * Set voltage of the circuit to the argument v.
	 * @param v
	 */
	public void setVoltage(float v){
		circuit.setAllVoltage(v);
	}
	/**
	 * 
	 * @param c
	 * @return the resistance of the circuit c
	 */
	public float getResistanceByName(Circuit c){
		Circuit a;
		a = c.find(c, c.getName());
		return a.getAllResistance();
	}
	/**
	 * 
	 * @param c
	 * @return the voltage of the argument circuit
	 */
	public float getVoltageByName(Circuit c){
		Circuit a;
		a = c.find(c, c.getName());
		return a.getAllVoltage();
	}	
	/**
	 * 
	 * @param c
	 * @return the current of the argument circuit
	 */
	public float getCurrentByName(Circuit c){
		Circuit a;
		a = c.find(c, c.getName());
		return a.getAllCurrent();
	}
	/**
	 * Create the fixed two circuits with given names and values
	 * @param t the first one or second one in the fixed circuits
	 * @return the circuit that created
	 */
	public Circuit createCircuit(int t){
		/* creating the first fixed circuit*/
		if(t == 1){
			Circuit c1, c2, c3, c4, c5, c6;
			
			Resistor[] R;
			R = new Resistor[8];
			Circuit[] c;
			c = new Circuit[8];
			
			R[1] = new Resistor(50, "R1", 1);
			R[2] = new Resistor(25, "R2", 2);
			R[3] = new Resistor(30, "R3", 3);
			R[4] = new Resistor(150, "R4", 4);
			R[5] = new Resistor(75, "R5", 5);
			R[6] = new Resistor(100, "R6", 6);
			R[7] = new Resistor(40, "R7", 7);
			
			for(int i = 1; i < 8; i++){
				c[i] = new Single(R[i]);
			}
			
			
			c1 = new Parallel(c[1], c[2], "c1");
			c2 = new Series(c1, c[3], "c2");
			c3 = new Parallel(c[4], c[5], "c3");
			c4 = new Parallel(c3, c[6], "c4");
			c5 = new Series(c4, c[7], "c5");
			c6 = new Series(c2, c5, "c6");
			return c6;
		}
		/* creating the second fixed circuit*/
		else if(t == 2){
			Circuit c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13;
			
			Resistor[] R;
			R = new Resistor[15];
			Circuit[] c;
			c = new Circuit[15];
			
			R[1] = new Resistor(50, "R1", 1);
			R[2] = new Resistor(25, "R2", 2);
			R[3] = new Resistor(30, "R3", 3);
			R[4] = new Resistor(150, "R4", 4);
			R[5] = new Resistor(75, "R5", 5);
			R[6] = new Resistor(100, "R6", 6);
			R[7] = new Resistor(40, "R7", 7);
			R[8] = new Resistor(60, "R8", 8);
			R[9] = new Resistor(50, "R9", 9);
			R[10] = new Resistor(20, "R10", 10);
			R[11] = new Resistor(40, "R11", 11);
			R[12] = new Resistor(30, "R12", 12);
			R[13] = new Resistor(70, "R13", 13);
			R[14] = new Resistor(80, "R14", 14);
			
			
			for(int i = 1; i < 15; i++){
				c[i] = new Single(R[i]);
			}
			
			
			c1 = new Parallel(c[3], c[4], "c1");
			c2 = new Parallel(c1, c[5], "c2");
			c3 = new Series(c[1], c2, "c3");
			c4 = new Parallel(c3, c[2], "c4");
			
			c5 = new Parallel(c[6], c[7], "c5");
			c6 = new Parallel(c[8], c[9], "c6");
			c7 = new Parallel(c5, c6, "c7");
			
			c8 = new Series(c4, c7, "c8");
			
			c9 = new Series(c[10], c[11], "c9");
			c10 = new Series(c[12], c[13], "c10");
			c11 = new Parallel(c9, c10, "c11");
			
			c12 = new Parallel(c11, c[14], "c12");
			
			c13 = new Series(c8, c12, "c13");
			
			return c13;
		}
		return null;
	
	}
	/**
	 * Change the argument string in the JOptionPane.showInputDialog into integers.
	 * @param s
	 * @return
	 */
	public int changeToInt(String s){
		String[] possibleValues = { " Get a list of component labels\n", " Get a string representing of it\n", 
	    		" apply an overall voltage to the circuit\n", " get resistance of any component by name\n",
	    		" get voltage of any component by name\n", " get current of any component by name\n",
	    		"quit the Fixed circuit\n"};
		
		for(int i=0; i<7; i++){
			if(s == possibleValues[i]){
				return (i+1);
			}
		}
		return 0;
	}
	/**
	 * set up the user interface of the CircuitAnalysis 
	 */
	public void UI(){
		String input;				// for user input using JOptionPane
		int number;
		boolean flag1;  // used to jump out of the while loop if it is true
		while(true){
			input = JOptionPane.showInputDialog("Please enter a number to choose the function\n" 
					+ "1. Fixed circuit\n" 
					+ "2. Analyse circuit");
			number = Integer.parseInt(input);
			
			flag1 = false;
			if(number == 1){
				int num, type;
				Circuit re;
				float v;
	
				input = JOptionPane.showInputDialog("Please choose which circuit you want:\n" 
											+ "1. the first one\n"
											+ "2. the second one");
				num = Integer.parseInt(input);
				
				re = createCircuit(num);
			/* Menu option */
				Object[] possibleValues = { " Get a list of component labels\n", " Get a string representing of it\n", 
				    		" apply an overall voltage to the circuit\n", " get resistance of any component by name\n",
				    		" get voltage of any component by name\n", " get current of any component by name\n",
				    		"quit the Fixed circuit\n"};
				
				while(true){
					//try{
						String selectedValue = (String) JOptionPane.showInputDialog(null,			
						                "Please enter a number to choose the function\n", "Input",		
						                JOptionPane.INFORMATION_MESSAGE, null,			
						                possibleValues, possibleValues[0]);
						
						type = changeToInt(selectedValue);
						Circuit aa;
						switch(type){
						case 1: //Get a list of component labels
							JOptionPane.showMessageDialog(null, re.getAllName());
							break;
						case 2:// Get a string representing of it
							JOptionPane.showMessageDialog(null, re.getStringForm());
							break;
						case 3: // apply an overall voltage to the circuit
							input = JOptionPane.showInputDialog("Please input the voltage you want to apply to the circuit:");
							v = Float.parseFloat(input);
							re.setAllVoltage(v);
							break;
						case 4:// get resistance of any component by name
							
							input = JOptionPane.showInputDialog("Please input the name of component:");
							
							aa = re.find(re, input);

							if(aa != null)
								JOptionPane.showMessageDialog(null, aa.getAllResistance() + "¦¸");
							else
								JOptionPane.showMessageDialog(null, "could not find it");
							
							break;
						case 5:// get voltage of any component by name
							input = JOptionPane.showInputDialog("Please input the name of component:");
							
							aa = re.find(re, input);
							if(aa != null)
							JOptionPane.showMessageDialog(null, aa.getAllVoltage() + "V");
							else
								JOptionPane.showMessageDialog(null, "could not find it");
							break;
						case 6: // get current of any component by name
							input = JOptionPane.showInputDialog("Please input the name of component:");
							
							aa = re.find(re, input);
							if(aa != null)
							JOptionPane.showMessageDialog(null, aa.getAllCurrent() + "A");
							else
								JOptionPane.showMessageDialog(null, "could not find it");
							break;
						case 7:// quit the Fixed circuit
							flag1 = true;
						}
						//end switch
					//}
					//catch(Exception e){
					//	flag1 = true;
					//}
						if(flag1)
							break;
					}
					//end while
			}
			/* choose to analyse circuit */
			else if(number == 2){
				int sum; // resistor number
				float target;
				
				input = JOptionPane.showInputDialog("Please enter the number of resistor you will use:");
			    sum = Integer.parseInt(input);
			    
			    float[] r;
				r = new float[sum];
			    
			    for(int i = 0; i < sum; i++){
			    	input = JOptionPane.showInputDialog("Please enter the " + i + " resistor's resistance( it's name is R" + i + "):");
				    r[i] = Float.parseFloat(input);
			    }
			    /* input target resistance in order to create the closest circuit */
			    input = JOptionPane.showInputDialog("Please enter the target resistance you want:");
			    target = Float.parseFloat(input);
			    
			    Resistor[] res;
			    res = new Resistor[sum];
			    for(int i = 0; i < sum; i++){
			    	res[i] = new Resistor(r[i], "R" + i, i);
			    }
			    
			    ResistorNetwork netw;
			    netw = getTarget(target, res, sum);
			    if(netw != null)
			    	JOptionPane.showMessageDialog(null, "The required ResistorNetwork is showed as follows: \n"
			    	+ netw.toString());
			}
			
			boolean flag = true;;
			
			while(flag){
				flag = false;
				input = JOptionPane.showInputDialog("quit this program?  1.quit  2. continue");
				number = Integer.parseInt(input);
				if(number == 1)
					return;
				else if(number != 2){
					JOptionPane.showMessageDialog(null, "your input is wrong, please try again");
					flag = true;
				}
			}
		}	
	}
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args){
		CircuitAnalyse a;
		a = new CircuitAnalyse();
		a.UI();
		
	}
}
