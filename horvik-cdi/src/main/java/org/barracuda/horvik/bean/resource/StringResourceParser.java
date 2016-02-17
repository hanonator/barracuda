package org.barracuda.horvik.bean.resource;

public class StringResourceParser implements ResourceParser<String> {

	@Override
	public String parse(String input) {
		return input;
	}

}
