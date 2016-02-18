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
	public ActionExceptionHandler getExceptionHandler() {
		return exceptionHandler;
	}

	/**
	 * @param exceptionHandler the exceptionHandler to set
	 */
	public void error(ActionExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	/**
	 * @return the successHandler
	 */
	public ActionSuccessHandler getSuccessHandler() {
		return successHandler;
	}

	/**
	 * @param successHandler the successHandler to set
	 */
	public void success(ActionSuccessHandler successHandler) {
		this.successHandler = successHandler;
	}

}
