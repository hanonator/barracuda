package org.barracuda.content.action;

public class ActionPromise {

	/**
	 * What happens on exception
	 */
	private ActionExceptionHandler exceptionHandler;

	/**
	 * What happens on success
	 */
	private ActionSuccessHandler successHandler;

	/**
	 * @return the exceptionHandler
	 */
	ActionExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	/**
	 * @param exceptionHandler the exceptionHandler to set
	 */
	public ActionPromise error(ActionExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
		return this;
	}

	/**
	 * @return the successHandler
	 */
	ActionSuccessHandler getSuccessHandler() {
		return successHandler;
	}

	/**
	 * @param successHandler the successHandler to set
	 */
	public ActionPromise success(ActionSuccessHandler successHandler) {
		this.successHandler = successHandler;
		return this;
	}

}
