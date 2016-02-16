package org.barracuda.core.game.v317.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.barracuda.core.game.v317.sync.PlayerRenderingOrder;
import org.barracuda.model.actor.sync.attribute.Animation;
import org.barracuda.model.actor.sync.attribute.Appearance;
import org.barracuda.model.actor.sync.attribute.Attribute;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerRenderingOrderTest {

	@Test
	public void testOrder() {
		List<Attribute> attributes = new ArrayList<>();

		attributes.add(new Appearance());
		attributes.add(new Animation(1));
		
		PlayerRenderingOrder order = new PlayerRenderingOrder();
		Queue<Attribute> queue = order.order(attributes);

		assertSame(queue.poll().getClass(), Appearance.class);
		assertSame(queue.poll().getClass(), Animation.class);
	}

}
