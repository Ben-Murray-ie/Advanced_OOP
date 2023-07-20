package ie.atu.sw;

/**
 * Parsator - Acts as template or contract for inheriting subtypes. If a class
 * (abstract/concrete) wants to be a Parsator, it must parse().
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public interface Parsator {

	/**
	 * Implementations of parse() include private process() method, so they are
	 * declared in their respective abstract or concrete classes.
	 */
	public abstract void parse();

}
