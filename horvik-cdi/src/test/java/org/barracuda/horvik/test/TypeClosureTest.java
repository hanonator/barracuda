package org.barracuda.horvik.test;

import org.horvik.bean.inject.annotated.AnnotatedFieldImpl;
import org.horvik.util.GenericsUtil;
import org.junit.Test;

public class TypeClosureTest {

	@Test
	public void test_closure() {
		GenericsUtil.getTypeClosure(AnnotatedFieldImpl.class, AnnotatedFieldImpl.class).forEach(System.out::println);
	}

}
