package org.barracuda.horvik.context;

import org.barracuda.horvik.bean.Bean;

public interface Context {

	/**
	 * 
	 * @param key
	 * @return
	 */
	<T> T get(Contextual key, Bean<T> type);

	/**
	 * 
	 * @param key
	 * @return
	 */
	<T> T create(Contextual key, Bean<T> type);

}
