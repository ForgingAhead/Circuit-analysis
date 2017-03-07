/**
 * The Circuit class is an abstract class that generally describes what a circuit contains.
 * In the class it has two components with the type Circuit itself. And it contains the name
 * , the resistance, the voltage and the current of the circuit.
 *  In addition, this class provides several methods for manipulating the circuit.
 *  Many of the methods are abstract.
 *
 */
public abstract class Circuit {
protected Circuit c1;
protected Circuit c2;
protected String name;
protected float resistance;
protected float voltage;
protected float current;
/**
 * Constructor for a new object that set values of the two components and its name.
 * @param comp1 One of the two components of the circuit with a type of Circuit.
 * @param comp2 Another component of the circuit with type Circuit.
 * @param n A string which is the name of the circuit.
 */
public Circuit(Circuit comp1, Circuit comp2, String n)
{
	name = n;
	c1 = comp1;
	c2 = comp2;
	resistance  = 0;
	voltage = 0;
	current = 0;
}
/**
 *  Default constructor to the class.
 */
public Circuit()
{
	name = "";
	c1 = null;
	c2 = null;
	resistance = 0;
	voltage = 0;
	current = 0;
}
/**
 * Copy constructor
 * @param c
 */
public Circuit(Circuit c)
{
	name = c.name;
	c1 = c.c1;
	c2 = c.c2;
	resistance = c.resistance;
	voltage = c.voltage;
	current = c.current;
}
/**
 *
 * @return the name of the whole circuit
 */
public String getAllName(){
	String sum = "";

	Circuit c1, c2;
	c1 = this.c1;
	c2 = this.c2;

	sum = sum + " " + this.name;

	if(c1 != null)
		sum = sum + " " + get(c1);
	if(c2 != null)
		sum = sum + " " + get(c2);

	return sum;

}
/**
 *
 * @param c the very circuit that want to know its name
 * @return the name of Circuit c
 */
public String get(Circuit c){
	String sum = "";

	if(c.c1 != null && c.c2 != null){
		sum = sum + " " + c.name;
	}

	if(c.c1 != null)
		sum += c.get(c.c1);
	if(c.c2 != null)
		sum += c.get(c.c2);

	return sum;
}
/**
 * Find the circuit with the name of the string argument in the argument circuit.
 * @param c the circuit in which want to search.
 * @param n the name of the circuit that want to find
 * @return a circuit object in the Circuit C that has the name of the string argument n.
 */
public Circuit find(Circuit c, String n){
	Circuit result;
	result = null;
	if(c != null){
		if(c.name.equals(n))
			return(c);

		else
			{
				if(c.c1 != null){
					result = find(c.c1, n);
				}

				 if(this.c2 != null && result == null){
					result = find(c.c2, n);
				}
			}
	}

	return result;
}
/**
 * Set the voltage of the whole circuit to the float argument.
 * @param v
 */
public abstract void setAllVoltage(float v);
/**
 * Get the resistance of the whole circuit
 * @return the resistance of the whole circuit
 */
public abstract float getAllResistance();
/**
 *
 * @return the resistance of the first component of the circuit
 */
public abstract float getResistance1();
/**
 *
 * @return the resistance of the second component of the circuit
 */
public abstract float getResistance2();
/**
 *
 * @return the voltage of the whole circuit
 */
public abstract float getAllVoltage();
/**
 * 
 * @return the current of the whole circuit
 */
public abstract float getAllCurrent();
/**
 * 
 * @return the voltage for the first component of the circuit
 */
public abstract float getVoltage1();
/**
 * 
 * @return the voltage for the second component of the circuit
 */
public abstract float getVoltage2();
/**
 * 
 * @return the current for the first component of the circuit
 */
public abstract float getCurrent1();
/**
 * 
 * @return the voltage for the second component of the circuit
 */
public abstract float getCurrent2();
/**
 * 
 * @return the name of the circuit
 */
public abstract String getName();
/**
 * Set the name of the circuit to the parameter string
 * @param s 
 */
public abstract void setName(String s);
/**
 * return a circuit object with the name of the parameter string
 * @param name
 * @return a circuit object with the name of the parameter string
 */
public abstract Circuit getSubcircuit(String name);
/**
 * represent the circuit to string which is its whole name in string form
 */
public abstract String toString();
/**
 * 
 * @return the circuit name
 */
public abstract String getStringForm();

}

/**
 * The class Single extending Circuit is the situation that the circuit just has a single component
 * which contains one Resistor object. 
 * In addition, this class also implement all the methods that apply for single circuit. 
 *
 */
class Single extends Circuit{
	private Resistor r;
/**
 * Default constructor
 */
	public Single(){
		super();
		r = null;
	}
/**
 * Copy constructor
 * @param r
 */
	public Single(Resistor r){
		super(null,null,r.getName());
		resistance = r.getResistance();
		this.r = r;
	}
/**
 * Set the voltage for the circuit
 * @param v
 */
	public void setVoltage(float v){
		r.setVoltage(v);
		voltage = v;
		current = r.getCurrent();
	}
/**
 * 
 * @return the voltage for the circuit
 */
	public float getVoltage(){
		return voltage;
	}
/**
 * 
 * @return the current of the circuit
 */
	public float getCurrent(){
		return current;
	}
/**
 * Set the voltage of the circuit to the float parameter
 * @param v
 */
	public void setAllVoltage(float v){
		setVoltage(v);
	}
/**
 * get the resistance of the whole circuit
 */
	public float getAllResistance(){
		return resistance;
	}
/**
 * get the resistance of the first component circuit
 */
	public float getResistance1(){
		return resistance;
	}
/**
 * get the resistance of the second component circuit
 */
	public float getResistance2(){
		return resistance;
	}
/**
 * get the voltage of the whole circuit
 */
	public float getAllVoltage(){
		return voltage;
	}
/**
 * get the current of the whole circuit
 */
	public float getAllCurrent(){
		return current;
	}
/**
 * get the voltage of the first component circuit
 */
	public float getVoltage1(){
		return voltage;
	}
/**
 * get the voltage of the second component circuit
 */
	public float getVoltage2(){
		return voltage;
	}
/**
 * get the current of the first component circuit
 */
	public float getCurrent1(){
		return voltage;
	}
/**
 * get the current of the second component circuit
 */
	public float getCurrent2(){
		return voltage;
	}
/**
 * Set the name of the circuit
 * @param s
 */
	public void setName(String s){
		this.name = s;
	}
/**
 * get the name of the whole circuit
 */
	public String getName(){
		return r.getName();
	}
/**
 * @return a Circuit object with the name of the parameter string in the circuit
 */
	public Circuit getSubcircuit(String name){
		return null;
	}
/**
 * represent the circuit to string which is its whole name in string form
 */
	public String toString(){
		return name;
	}

/**
 * @return the name of the circuit
 */
	public String getStringForm(){
		return null;
	}

}

/**
 * The class Series extending Circuit describes the circuit connected in series
 *  It has two components where each component is of the base class type.
 *In addition, it also implement those methods suitable for its situation.
 */
class Series extends Circuit{
	public Series(Circuit comp1, Circuit comp2, String name){
		super(comp1,comp2,name);
		resistance = comp1.getAllResistance() + comp2.getAllResistance();
		current = (comp1.getAllVoltage() + comp2.getAllVoltage()) / resistance;
	}
/**
 * Default constructor
 */
	public Series(){
		super(null,null,"");
	}
/**
 * Set the voltage for the whole circuit
 * @param v
 */
	public void setAllVoltage(float v){
		voltage = v;
		float vol1 = c1.getAllResistance() / resistance;
		c1.setAllVoltage(vol1 * voltage);
		c2.setAllVoltage((1 - vol1) * voltage);
		current = voltage / resistance;
	}
/**
 * get the resistance of the whole circuit
 */
	public float getAllResistance(){
		return resistance;
	}
/**
 * get the resistance of the first component circuit
 */
	public float getResistance1(){
		return c1.getAllResistance();
	}
/**
 * get the resistance of the second component circuit
 */
	public float getResistance2(){
		return c2.getAllResistance();
	}
/**
 * 
 * @return the voltage for the circuit
 */
	public float getAllVoltage(){
		return voltage;
	}
/**
 * @return the current for the whole circuit
 */
	public float getAllCurrent(){
		return current;
	}
/**
 * @return the voltage for the first component of the circuit
 */
	public float getVoltage1(){
		return c1.getAllVoltage();
	}
/**
 * @return the voltage for the second component of the circuit
 */
	public float getVoltage2(){
		return c2.getAllVoltage();
	}

/**
 * @return the current for the first component of the circuit
 */
	public float getCurrent1(){
		return current;
	}
/**
 * @return the current for the second component of the circuit
 */
	public float getCurrent2(){
		return current;
	}
/**
 * @return the name of the whole circuit
 */
	public String getName(){
		return name;
	}
	/**
	 * Set the name of the circuit
	 * @param s
	 */
	public void setName(String s){
		name = s;
	}
/**
 * @return a Circuit object with the name of the parameter string in the circuit
 */
	public Circuit getSubcircuit(String name){
		if(name == c1.getName())
			return c1;
		else if(name == c2.getName())
			return c2;
		else return null;
	}
	/**
	 * @return the name of the circuit
	 */	
	public String getStringForm(){
		return c1.toString() + "-" + c2.toString();
	}
	/**
	 * represent the circuit to string which is its whole name in string form
	 */	
	public String toString(){
		if(c1 == null && c2 == null)
			return "";
		else if(c1 == null && c2 != null)
			return "(" + c2.toString() + ")";
		else if(c1 != null && c2 == null)
			return "(" + c1.toString() + ")";
		else
			return "(" + c1.toString() + "-" + c2.toString() + ")";
	}


}
/**
 * The class Parallel extending Circuit describes the circuit that connected in parallel.
 * It has two component, where each component is of the base class type.
 * It implements all the methods that are needed for them.
 */
class Parallel extends Circuit{
/**
 * Construct a new Parallel object with two components of the Circuit patameter
 * and the name of the string parameter.
 * @param comp1
 * @param comp2
 * @param name
 */
	public Parallel(Circuit comp1, Circuit comp2, String name){
		super(comp1,comp2,name);
		resistance = (comp1.getAllResistance() * comp2.getAllResistance()) / (comp1.getAllResistance() + comp2.getAllResistance());
	}
/**
 * Default constructor
 */
	public Parallel(){
		super(null,null,"");
	}
	/**
	 * Set the voltage of the circuit to the float parameter
	 * @param v
	 */
	public void setAllVoltage(float v){
		voltage = v;
		c1.setAllVoltage(voltage);
		c2.setAllVoltage(voltage);
		current = voltage / resistance;
	}
	/**
	 * @return the resistance of the whole circuit
	 */
	public float getAllResistance(){
		return resistance;
	}
	/**
	 * get the resistance of the first component circuit
	 */
	public float getResistance1(){
		return c1.getAllResistance();
	}
	/**
	 * get the resistance of the second component circuit
	 */
	public float getResistance2(){
		return c2.getAllResistance();
	}
	/**
	 * get the voltage of the whole circuit
	 */
	public float getAllVoltage(){
		return voltage;
	}
	/**
	 * get the current of the whole circuit
	 */
	public float getAllCurrent(){
		return current;
	}
	/**
	 * get the voltage of the first component circuit
	 */
	public float getVoltage1(){
		return c1.getAllVoltage();
	}
	/**
	 * get the voltage of the second component circuit
	 */
	public float getVoltage2(){
		return c2.getAllVoltage();
	}
	/**
	 * get the current of the first component circuit
	 */
	public float getCurrent1(){
		return c1.getAllCurrent();
	}
	/**
	 * get the current of the second component circuit
	 */
	public float getCurrent2(){
		return c2.getAllCurrent();
	}
	/**
	 * get the name of the whole circuit
	 */
	public String getName(){
		return name;
	}
	/**
	 * Set the name of the circuit
	 * @param s
	 */
	public void setName(String s){
		name = s;
	}
	/**
	 * @return a Circuit object with the name of the parameter string in the circuit
	 */
	public Circuit getSubcircuit(String name){
		if(name == c1.getName())
			return c1;
		else if(name == c2.getName())
			return c2;
		else return null;
	}
	/**
	 * @return the name of the circuit
	 */
	public String getStringForm(){
		return c1.toString() + "|" + c2.toString();
	}
	/**
	 * represent the circuit to string which is its whole name in string form
	 * @return the name of the circuit
	 */
	public String toString(){
		if(c1 == null && c2 == null)
			return "";
		else if(c1 == null && c2 != null)
			return "(" + c2.toString() + ")";
		else if(c1 != null && c2 == null)
			return "(" + c1.toString() + ")";
		else
			return "(" + c1.toString() + "|" + c2.toString() + ")";
	}


}
