package org.barracuda.model.realm.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.barracuda.model.EntityList;
import org.barracuda.model.actor.Player;
import org.junit.Test;

/**
 * Standard implementation of the Realm interface that stores the 2
 * main types of entities into an entity list
 * 
 * @author brock
 *
 */
public class EntityListTest {
	
	/**
	 * The player the tests will be run on
	 */
	private final Player player = new Player(null);

	/**
	 * A regular player list
	 */
	private final EntityList<Player> list = new EntityList<>(100, Player.class).register(player);

	@Test
	public void testPlayerId() {
		assertSame("player expected to have index 1", player.getIndex(), 1);
	}
	
	@Test
	public void testContains() {
		assertTrue("player expected to be in the list of players", list.contains(player));
	}

	@Test
	public void testConstraints() {
		assertFalse(list.full());
		assertSame(1, list.size());
		assertSame(player, list.get(1));
		assertSame(100, list.capacity());
	}

}
