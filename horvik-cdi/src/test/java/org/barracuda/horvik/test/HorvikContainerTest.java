package org.barracuda.horvik.test;

import java.util.UUID;

import org.barracuda.horvik.Horvik;
import org.barracuda.horvik.HorvikException;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.request.RequestScoped;
import org.barracuda.horvik.context.session.Session;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.inject.Inject;
import org.junit.Test;

public class HorvikContainerTest {

	@Test
	public void test_session_scope() throws HorvikException {
		Horvik horvik = new Horvik();
		horvik.initializeContainer();

		Session session_1 = new Session(UUID.randomUUID().toString());		
		Bean<SessionScopedTest> bean = horvik.getContainer().getBean(SessionScopedTest.class);
		horvik.getContainer().getInjectedReference(bean, session_1);
		
		long delta = System.currentTimeMillis();
		for (int i = 0; i < 1_000_000; i++) {
			horvik.getContainer().getInjectedReference(bean, session_1);
		}
		
		System.out.println(System.currentTimeMillis() - delta + "ms delay");
	}
	
	@Discoverable
	@SessionScoped
	public static class SessionScopedTest {
		
		@Inject
		private RandomContainer container;
		
		@Inject
		private RequestSomething something;
		
		@Override
		public String toString() {
			return container + " " + something;
		}
		
	}
	
	@Discoverable
	@SessionScoped
	public static class RandomContainer {
		
		/**
		 * The randomly generated value
		 */
		private double value = Math.random();
		
		@Override
		public String toString() {
			return "value: " + value + " hashcode: " + this.hashCode();
		}
		
	}
	
	@Discoverable
	@RequestScoped
	public static class RequestSomething {
		
		/**
		 * The randomly generated uuid
		 */
		private String uuid = UUID.randomUUID().toString();
		
		@Override
		public String toString() {
			return "uuid: " + uuid;
		}
		
	}

}