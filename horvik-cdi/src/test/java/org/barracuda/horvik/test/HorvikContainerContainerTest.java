package org.barracuda.horvik.test;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.junit.Test;

public class HorvikContainerContainerTest {

	@Test
	public void test_session_scope() {
		// HorvikContainer container = Horvik.getContainer();
		// Session session = new Session();
		// Bean<SessionScopedTest> bean = container.getBean(SessionScopedTest.class);
		
		SessionScopedTest test_1 = null; // bean.create(session);
		SessionScopedTest test_2 = null; // bean.create(session);

		System.out.println("test_1: " + test_1);
		System.out.println("test_2: " + test_2);
	}
	
	@SessionScoped
	private class SessionScopedTest {
		
		@Inject
		private RandomContainer container;
		
		@Override
		public String toString() {
			return "container - " + container;
		}
		
	}
	
	@SessionScoped
	private class RandomContainer {
		
		/**
		 * The randomly generated value
		 */
		private double value = Math.random();
		
		@Override
		public String toString() {
			return "value: " + value;
		}
		
	}

}
