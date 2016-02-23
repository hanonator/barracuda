package org.barracuda.model.actor.sync;

import java.util.Deque;
import java.util.LinkedList;

import org.barracuda.model.actor.Actor;
import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.event.RegionEntered;
import org.barracuda.model.location.Location;
import org.barracuda.model.location.Region;

/**
 * 
 * @author brock
 *
 */
public class DefaultWaypointVector implements WaypointVector {

	/**
	 * The maximum size of the queue. If there are more points than this size,
	 * they are discarded.
	 */
	public static final int MAXIMUM_SIZE = 50;

	/**
	 * dunno
	 */
	static final byte[] DIRECTION_DELTA_X = new byte[] { -1, 0, 1, -1,
			1, -1, 0, 1 };

	/**
	 * duno
	 */
	static final byte[] DIRECTION_DELTA_Y = new byte[] { 1, 1, 1, 0, 0,
			-1, -1, -1 };

	/**
	 * The entity this walking queue belongs to
	 */
	private final Actor character;

	/**
	 * The queue of waypoints.
	 */
	private final Deque<Waypoint> waypoints = new LinkedList<>();

	/**
	 * Run toggle (button in client).
	 */
	private boolean runToggled = false;

	/**
	 * Run for this queue (CTRL-CLICK) toggle.
	 */
	private boolean runQueue = false;

	/**
	 * Constructor
	 * 
	 * @param character
	 */
	public DefaultWaypointVector(Actor character) {
		this.character = character;
	}

	/**
	 * Adds a single step to the walking queue, filling in the points to the
	 * previous point in the queue if necessary.
	 * 
	 * @param x
	 *            The local x coordinate.
	 * @param y
	 *            The local y coordinate.
	 */
	@Override
	public WaypointVector add(int x, int y) {
		/*
		 * The RuneScape client will not send all the points in the queue. It
		 * just sends places where the direction changes.
		 * 
		 * For instance, walking from a route like this:
		 * 
		 * <code> ***** * * ***** </code>
		 * 
		 * Only the places marked with X will be sent:
		 * 
		 * <code> X***X * * X***X </code>
		 * 
		 * This code will 'fill in' these points and then add them to the queue.
		 */

		/*
		 * We need to know the previous point to fill in the path.
		 */
		if (waypoints.size() == 0) {
			/*
			 * There is no last point, reset the queue to add the player's
			 * current location.
			 */
			clear();
		}

		/*
		 * We retrieve the previous point here.
		 */
		Waypoint last = waypoints.peekLast();

		/*
		 * We now work out the difference between the points.
		 */
		int diffX = x - last.getX();
		int diffY = y - last.getY();

		/*
		 * And calculate the number of steps there is between the points.
		 */
		int max = Math.max(Math.abs(diffX), Math.abs(diffY));
		for (int i = 0; i < max; i++) {
			/*
			 * Keep lowering the differences until they reach 0 - when our route
			 * will be complete.
			 */
			if (diffX < 0) {
				diffX++;
			} else if (diffX > 0) {
				diffX--;
			}
			if (diffY < 0) {
				diffY++;
			} else if (diffY > 0) {
				diffY--;
			}

			/*
			 * Add this next step to the queue.
			 */
			addStepInternal(x - diffX, y - diffY);
		}
		return this;
	}

	/**
	 * Adds a single step to the queue internally without counting gaps. This
	 * method is unsafe if used incorrectly so it is private to protect the
	 * queue.
	 * 
	 * @param x
	 *            The x coordinate of the step.
	 * @param y
	 *            The y coordinate of the step.
	 */
	private void addStepInternal(int x, int y) {
		/*
		 * Check if we are going to violate capacity restrictions.
		 */
		if (waypoints.size() >= MAXIMUM_SIZE) {
			/*
			 * If we are we'll just skip the point. The player won't get a
			 * complete route by large routes are not probable and are more
			 * likely sent by bots to crash servers.
			 */
			return;
		}

		/*
		 * We retrieve the previous point (this is to calculate the direction to
		 * move in).
		 */
		Waypoint last = waypoints.peekLast();

		/*
		 * Now we work out the difference between these steps.
		 */
		int diffX = x - last.getX();
		int diffY = y - last.getY();

		/*
		 * And calculate the direction between them.
		 */
		int dir = DirectionUtil.direction(diffX, diffY);

		/*
		 * Check if we actually move anywhere.
		 */
		if (dir > -1) {
			/*
			 * We now have the information to add a point to the queue! We
			 * create the actual point object and add it.
			 */
			waypoints.add(new Waypoint(x, y, Direction.values()[dir]));

		}
	}

	/**
	 * Processes the next player's movement.
	 */
	@Override
	public LocationMetaData update() {
		/*
		 * The points which we are walking to.
		 */
		Waypoint walkPoint = null, runPoint = null;
		Direction primaryDirection = null, secondaryDirection = null;
		Location teleportTarget = null;
		Region before = character.getLocation() == null ? new Region(0, 0) : character.getLocation().localize();
		
		/*
		 * Check to see if the player is teleporting or not
		 */
		if(character.getTeleportRequest() != null) {
			/*
			 * After a teleport, the walking queue resets
			 */
			clear();
			
			/*
			 * change the player's location to that of the teleport target
			 */
			character.setLocation(character.getTeleportRequest());
			teleportTarget = character.getTeleportRequest();
			character.setTeleportRequest(null);
		} else {
			/*
			 * If the player isn't teleporting, they are walking (or standing
			 * still). We get the next direction of movement here.
			 */
			walkPoint = getNextPoint();

			/*
			 * Technically we should check for running here.
			 */
			if (runToggled || runQueue) {
				runPoint = getNextPoint();
			}

			/*
			 * 
			 */
			primaryDirection = walkPoint == null ? null : walkPoint.getDirection();
			secondaryDirection = runPoint == null ? null : runPoint.getDirection();
		}

		/*
		 * Calculate the distance between the two tiles
		 */
		int diff_x = character.getLocation().getX() - before.getSmallCoordinate(character.getLocation()).getX() * 8;
		int diff_y = character.getLocation().getY() - before.getSmallCoordinate(character.getLocation()).getY() * 8;
		
		/*
		 * Set the map region changed flag
		 */
		boolean regionUpdateRequired = diff_x < -32 || diff_x >= 40 || diff_y < -32 || diff_y >= 40;
		
		/*
		 * If the character is a player and he changes region we need to fix this
		 */
		if (regionUpdateRequired && character instanceof Player) {
			((Player) character).notify(new RegionEntered(before, character.getLocation().localize()));
		}
		return new LocationMetaData(teleportTarget, primaryDirection, secondaryDirection, regionUpdateRequired);
	}

	/**
	 * Gets the next point of movement.
	 * 
	 * @return The next point.
	 */
	private Waypoint getNextPoint() {
		/*
		 * Take the next point from the queue.
		 */
		Waypoint p = waypoints.poll();

		/*
		 * Checks if there are no more points.
		 */
		if (p == null || p.getDirection() == null) {
			/*
			 * Return <code>null</code> indicating no movement happened.
			 */
			return null;
		} else {
			/*
			 * Set the player's new location.
			 */
			int diffX = DIRECTION_DELTA_X[p.getDirection().ordinal()];
			int diffY = DIRECTION_DELTA_Y[p.getDirection().ordinal()];
			
			/*
			 * Transform the player's location
			 */
			character.getLocation().translate(diffX, diffY, 0);

			/*
			 * And return the direction.
			 */
			return p;
		}
	}

	/**
	 * Resets the walking queue so it contains no more steps.
	 */
	@Override
	public WaypointVector clear() {
		runQueue = false;
		waypoints.clear();
		waypoints.add(new Waypoint(character.getLocation().getX(), character.getLocation().getY(), null));
		return this;
	}

	/**
	 * Checks if the queue is empty.
	 * 
	 * @return <code>true</code> if so, <code>false</code> if not.
	 */
	public boolean isEmpty() {
		return waypoints.isEmpty();
	}

	/**
	 * Removes the first waypoint which is only used for calculating directions.
	 * This means walking begins at the correct time.
	 */
	@Override
	public WaypointVector finish() {
		waypoints.removeFirst();
		return this;
	}

	/**
	 * Sets the run toggled flag.
	 * 
	 * @param runToggled
	 *            The run toggled flag.
	 */
	public void setRunningToggled(boolean runToggled) {
		this.runToggled = runToggled;
	}

	/**
	 * Sets the run queue flag.
	 * 
	 * @param runQueue
	 *            The run queue flag.
	 */
	public void setRunningQueue(boolean runQueue) {
		this.runQueue = runQueue;
	}

	/**
	 * Gets the run toggled flag.
	 * 
	 * @return The run toggled flag.
	 */
	public boolean isRunningToggled() {
		return runToggled;
	}

	/**
	 * Gets the running queue flag.
	 * 
	 * @return The running queue flag.
	 */
	public boolean isRunningQueue() {
		return runQueue;
	}

	/**
	 * Checks if any running flag is set.
	 * 
	 * @return <code>true</code. if so, <code>false</code> if not.
	 */
	public boolean isRunning() {
		return runToggled || runQueue;
	}

}
