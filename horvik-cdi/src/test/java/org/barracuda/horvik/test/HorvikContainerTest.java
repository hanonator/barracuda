package org.barracuda.horvik.test;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import org.horvik.Horvik;
import org.horvik.HorvikException;
import org.junit.Test;

public class HorvikContainerTest {

	@Test
	public void test_session_scope() throws HorvikException {
		Horvik horvik = new Horvik();
		horvik.initializeContainer();
		
		SessionScopedTest test = new SessionScopedTest();
		
		horvik.getContainer().getManager().inject(horvik.getContainer().getManager().getBean(SessionScopedTest.class), test);
		// Session session = new Session();
		// Bean<SessionScopedTest> bean = container.getBean(SessionScopedTest.class);
		
//		SessionScopedTest test_1 = null; // bean.create(session);
//		SessionScopedTest test_2 = null; // bean.create(session);
//
//		System.out.println("test_1: " + test_1);
//		System.out.println("test_2: " + test_2);
	}
	
	@SessionScoped
	public class SessionScopedTest {
		
		@Inject
		private RandomContainer container;
		
		@Inject
		@Override
		public String toString() {
			return "container - " + container;
		}
		
	}
	
	@SessionScoped
	public class RandomContainer {
		
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
