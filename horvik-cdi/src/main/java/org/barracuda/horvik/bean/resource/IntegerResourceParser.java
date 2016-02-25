package org.barracuda.horvik.bean.resource;

public class IntegerResourceParser implements ResourceParser<Integer> {

	@Override
	public Integer parse(String input) {
		return Integer.parseInt(input);
	}

}
