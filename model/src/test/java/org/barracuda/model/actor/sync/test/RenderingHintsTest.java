package org.barracuda.model.actor.sync.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.RenderingHints;
import org.barracuda.model.actor.sync.attribute.Appearance;
import org.junit.Test;

public class RenderingHintsTest {

	@Test
	public void testContains() {
		RenderingHints rendering_hints = new RenderingHints(Player.class);
		
		rendering_hints.add(new Appearance());

		assertFalse("Rendering hints should not be empty", rendering_hints.isEmpty());
		assertTrue("Rendering hints should contain appearance", rendering_hints.contains(Appearance.class));
	}

	@Test
	public void testEmpty() {
		RenderingHints rendering_hints = new RenderingHints(Player.class);
		assertTrue("Rendering hints should not be empty", rendering_hints.isEmpty());
	}

}
