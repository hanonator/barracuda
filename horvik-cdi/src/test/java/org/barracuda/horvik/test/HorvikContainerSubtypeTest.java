package org.barracuda.horvik.test;

import java.util.UUID;

import org.barracuda.horvik.Horvik;
import org.barracuda.horvik.bean.Bean;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.session.Session;
import org.barracuda.horvik.context.session.SessionScoped;
import org.barracuda.horvik.inject.Inject;
import org.junit.Test;

public class HorvikContainerSubtypeTest {

	@Test
	public void test_subtype() {
		Horvik horvik = new Horvik();
		horvik.initializeContainer();

		Session session = new Session(UUID.randomUUID().toString());		
		Bean<SubType> bean = horvik.getContainer().getBean(SubType.class);
		SubType subtype = horvik.getContainer().getInjectedReference(bean, session);

		System.out.println(subtype.sub_container);
		System.out.println(subtype.super_container);
	}

	@Discoverable
	@SessionScoped
	public static class SuperType {
		
		@Inject
		protected RandomContainer super_container;
		
	}

	@Discoverable
	@SessionScoped
	public static class SubType extends SuperType {
		
		@Inject
		private RandomContainer sub_container;
		
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

}
