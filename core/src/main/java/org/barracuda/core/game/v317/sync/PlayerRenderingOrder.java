package org.barracuda.core.game.v317.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.barracuda.model.actor.Actor;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.RenderingOrder;
import org.barracuda.model.actor.sync.attribute.Animation;
import org.barracuda.model.actor.sync.attribute.Appearance;
import org.barracuda.model.actor.sync.attribute.Attribute;

public class PlayerRenderingOrder implements RenderingOrder {

	/**
	 * 
	 */
	private static final Class<?>[] order = {
			Appearance.class,
			Animation.class,
	};

	@Override
	public Queue<Attribute> order(Collection<Attribute> attributes) {
		List<Attribute> list = new ArrayList<>(attributes);
		Collections.sort(list, (o1, o2) -> {
			return Integer.compare(indexOf(order, o1.getClass()), indexOf(order, o2.getClass()));
		});
		return new LinkedList<Attribute>(list);
	}

	private <T> int indexOf(T[] array, T element) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element)
				return i;
		}
		throw new NullPointerException("element not found");
	}

	@Override
	public Class<? extends Actor> getRenderedType() {
		return Player.class;
	}

}
