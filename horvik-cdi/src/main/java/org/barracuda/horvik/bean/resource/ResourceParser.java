package org.barracuda.horvik.bean.resource;

public interface ResourceParser<T> {

	/**
	 * 
	 * @param input
	 * @param type
	 * @return
	 */
	T parse(String input);

}
