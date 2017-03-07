/**
 * The Resistor class describes basic information of a resistor in a circuit. An object
 * of Resistor contains the resistor's resistance, the voltage and current in the circuit
 * and its name. The variable number is a special identify for the resistor which is the
 * last digit in its name.
 * This class provides several methods for accessing and setting those variable.
 *
 */
public class Resistor{
	private float resistance;
	private float voltage;
	private float current;
	private String name;
	private int number;
/**
 * Constructs a new resistor object that only has the resistance.
 * @param r The resistance of the resistor
 */
	public Resistor(float r){
		if(r > 0)
			resistance = r;
		else
			resistance = 1;
		name = "";
		voltage = 0;
		current = 0;
		setNumber(0);
	}
	/**
	 * General Constructor - create an resistor
	 * 
	 * create an resistor with the given resistance and the name,
	 * and the voltage is set to 0, the current is set to 0,
	 * Make sure the resistance you give is positive. 
	 * 
	 * @param r the resistance of the resistor
	 * @param n the name of the resistor
	 */
	public Resistor(float r, String n){
		if(r > 0)
			resistance = r;
		else
			resistance = 1;
		name = n;
		voltage = 0;
		current = 0;
		setNumber(0);
	}
	/**
	 * Construct a new resistor object with its resistance, name and number known.
	 * @param r The resistance with type float.
	 * @param n the name of the resistor with the type String.
	 * @param num Its specific number for identifying the resistor. 
	 */
	public Resistor(float r, String n, int num){
		if(r > 0)
			resistance = r;
		else
			resistance = 1;
		name = n;
		voltage = 0;
		current = 0;
		setNumber(num);
	}
	
	/**
	 * Access method to set the voltage.
	 * 
	 * Access method to set the voltage for an resistor, also calculate the current.
	 * Make sure the voltage you give is positive.
	 * @param v - the new voltage 
	 */
	public void setVoltage(float v){
		if(v > 0)
			voltage = v;
		else
			voltage = 1;
		current = v / resistance;
	}
	
	/**
	 * Access method to get the voltage currently applied to the resistor.
	 * @return - the voltage currently applied to the resistor
	 */
	public float getVoltage(){
		return voltage;
	}
	
	/**
	 * Access method to get the current currently applied to the resistor.
	 * @return - the current currently applied to the resistor
	 */
	public float getCurrent(){
		return current;
	}
	
	/**
	 * Access method to get the name of the resistor.
	 * @return - the resistor's name;
	 */
	public String getName(){
		return name;
	}
	/**
	 * Access method to get the resistance.
	 * @return - the resistor's resistance;
	 */
	public float getResistance()
	{
		return resistance;
	}
	/**
	 * Set the number of the resistor.
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * Access method to get the number of the resistor.
	 * @return - the resistor's number;
	 */
	public int getNumber() {
		return number;
	}
}

