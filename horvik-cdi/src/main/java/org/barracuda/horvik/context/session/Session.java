package org.barracuda.horvik.context.session;

import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.bean.BeanRepository;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.Contextual;
import org.barracuda.horvik.util.ReflectionUtil;

/**
 * Represents a session
 * 
 * @author brock
 *
 */
@SessionScoped
@Discoverable
public class Session implements Contextual {

	/**
	 * The repository containing the instances of the beans for this session
	 */
	private final BeanRepository repository = new BeanRepository();

	/**
	 * The id of the session
	 */
	private final String id;

	/**
	 * Constructor
	 * 
	 * @param id
	 */
	public Session(String id) {
		this.id = id;
	}

	/**
	 * Gets the instance for a given bean for this session. This will not create a new
	 * instance but will only supply an existing one
	 * 
	 * @param type
	 * @return
	 */
	public <T> T retrieve(Bean<T> type) {
		return ReflectionUtil.cast(repository.get(type));
	}

	/**
	 * This session's id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Associates the instance of a bean with its bean class so it can be injected
	 * in future references
	 * 
	 * @param type
	 * @param instance
	 */
	public <T> void associate(Bean<T> type, T instance) {
		repository.put(type, instance);
	}

	@Override
	public String toString() {
		return "Session [repository=" + repository + ", id=" + id + "]";
	}

}
