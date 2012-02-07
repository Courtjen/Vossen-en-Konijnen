package vk.main;
import static org.junit.Assert.*;

import org.junit.Test;
import vk.runner.*;

@SuppressWarnings("unused")
public class JUnitTest {

	@SuppressWarnings("static-method")
	@Test
	public void test00() {
		String[] test = new String[] {};
		 Runner.main(test);
	}

	@SuppressWarnings("static-method")
	@Test
	public void test50() {
		String[] test = new String[] {"50","50"};
		Runner.main(test);
	}

	@SuppressWarnings("static-method")
	@Test
	public void test100() {
		String[] test = new String[] {"100","100"};
		Runner.main(test);
	}

	@SuppressWarnings("static-method")
	@Test
	public void test150() {
		String[] test = new String[] {"150","150"};
		Runner.main(test);
	}
}
