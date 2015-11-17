/**
 * 
 */
package utilities;

import java.util.List;

/**
 * @author b
 *
 */
public interface Repository<E> {
	abstract void add(E obj);
	abstract void delete(E obj);
	abstract void update(E obj);
	abstract List<E> returnAll();
	
}
