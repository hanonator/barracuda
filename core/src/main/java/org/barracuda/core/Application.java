package org.barracuda.core;

import org.barracuda.cdi.Horvik;
import org.barracuda.cdi.HorvikContainer;

public class Application {

	public static void main(String[] args) throws InterruptedException {
		HorvikContainer container = new HorvikContainer();
		Horvik horvik = new Horvik();
		
		System.out.println(horvik);
		System.out.println(container);
	}

}
