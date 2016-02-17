package org.barracuda.model.item;

import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;

import com.google.gson.Gson;

/**
 * 20,0073 Item definitions, represents a single item definition which
 * is held in the array {@link #definitions}.
 * 
 * In the making of this I used a lot of the data Dustin provided, but 
 * added a lot more in.
 * 
 * TODO: This is pretty shit
 * 
 * @Author Dustin Greyfield
 * @Author Maui
 */
public class ItemDefinition {
	
	/**
	 * The logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ItemDefinition.class);
	
	/**
	 * The array of definitions for items in the game
	 */
	private static ItemDefinition[] definitions;
	
	/**
	 * The id of the item
	 */
	private final int id;
	
	/**
	 * The name of the item
	 */
	private final String name;
	
	/**
	 * The description of the item
	 */
	private final String description;
	
	/**
	 * The flag determining if an item is tradeable
	 */
	private final boolean untradeable;
	
	/**
	 * The flag determining whether an item is destroyable
	 */
	private final boolean destroyable;
	
	/**
	 * The flag representing whether an item is stackable
	 */
	private final boolean stackable;
	
	/**
	 * The item's value
	 */
	private final int value;
	
	/**
	 * The price for special stores
	 */
	private final int[] specialPrice;
	
	/**
	 * The items low alchemy value
	 */
	private final int lowAlchemy;
	
	/**
	 * The item's high alchemy value
	 */
	private final int highAlchemy;
	
	/**
	 * The item's weight
	 */
	private final double weight;
	
	/**
	 * The flag determing if an item is noted
	 */
	private final boolean noted;
	
	/**
	 * the flag representing whether the item is noteable
	 */
	private final boolean noteable;
	
	/**
	 * The noted id of an item
	 */
	private final int childId;
	
	/**
	 * The unnoted id of an item
	 */
	private final int parentId;
	
	/**
	 * The flag representing if an item is 2 handed
	 */
	private final boolean isTwoHanded;
	
	/**
	 * The flag representing the equipment type of an item
	 */
	private final WieldType equipmentType;
	
	/**
	 * The flag representing if an item is a weapon
	 */
	private final boolean weapon;
	
	/**
	 * A flag representing if this is a full body item
	 */
	private final boolean fullBody;
	
	/**
	 * A flag representing if this is a full hat item
	 */
	private final boolean fullHat;
	
	/**
	 * A flag representing if this is a full mask
	 */
	private final boolean fullMask;
	
	/**
	 * The bonuses of the item
	 */
	private final double[] bonus;
	
	/**
	 * The item's requirements
	 */
	private final int[] requirement;
	
	/**
	 * The actions this item has
	 */
	private final String[] actions;

	/**
	 * The complete Item Definition
	 */
	public ItemDefinition(final int id, final String name, final String description,
			final boolean untradeable, final boolean destroyable, final boolean stackable,
			final int value, final int[] specialPrice, final int lowAlchemy, final int highAlchemy, final double weight, 
			final boolean noted, final boolean noteable, final int childId, final int parentId,
			final boolean isTwoHanded, final WieldType equipmentType, final boolean weapon, 
			final boolean fullBody, final boolean fullHat, final boolean fullMask, final double[] bonus,
			final int[] requirement, final String[] actions) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.untradeable = untradeable;
		this.destroyable = destroyable;
		this.stackable = stackable;
		this.value = value;
		this.specialPrice = specialPrice;
		this.lowAlchemy = lowAlchemy;
		this.highAlchemy = highAlchemy;
		this.weight = weight;
		this.noted = noted;
		this.noteable = noteable;
		this.childId = childId;
		this.parentId = parentId;
		this.isTwoHanded = isTwoHanded;
		this.weapon = weapon;
		this.equipmentType = equipmentType;
		this.fullBody = fullBody;
		this.fullHat = fullHat;
		this.fullMask = fullMask;
		this.bonus = bonus;
		this.requirement = requirement;
		this.actions = actions;
	}
	
	/**
	 * Initializes
	 * @param initialized
	 */
	public static void initialize(@Observes ContainerInitialized event) throws Exception{
		InputStream stream = ClassLoader.getSystemResourceAsStream("static/game/item_def.json");
		definitions = new Gson().fromJson(new InputStreamReader(stream), ItemDefinition[].class);
		logger.info("{} item definitions loaded", definitions.length);
		stream.close();
	}
	
	/**
	 * Gets the definitions for public access
	 * 
	 * @return - definitions
	 */
	public static ItemDefinition forId(int index) {
		return definitions[index];
	}

	/**
	 *  @return - id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 *  @return - name
	 */
	public String getName() {
		return name;
	}

	/**
	 *  @return - description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 *  @return - tradeable
	 */
	public boolean isUntradeable() {
		return untradeable;
	}

	/**
	 *  @return - destroyable
	 */
	public boolean isDestroyable() {
		return destroyable;
	}

	/**
	 *  @return - stackable
	 */
	public boolean isStackable() {
		return stackable;
	}

	/**
	 *  @return - value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the specialPrice
	 */
	public int[] getSpecialPrice() {
		return specialPrice;
	}

	/**
	 *  @return - lowAlchemy
	 */
	public int getLowAlchemy() {
		return lowAlchemy;
	}

	/**
	 *  @return - highAlchemy
	 */
	public int getHighAlchemy() {
		return highAlchemy;
	}

	/**
	 *  @return - weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 *  @return - noted
	 */
	public boolean isNoted() {
		return noted;
	}

	/**
	 * @return - noteable
	 */
	public boolean isNoteable() {
		return noteable;
	}

	/**
	 * @return - childId
	 */
	public int getChildId() {
		return childId;
	}

	/**
	 * @return - parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 *  @return - isTwoHanded
	 */
	public boolean isTwoHanded() {
		return isTwoHanded;
	}

	/**
	 *  @return - equipmentType
	 */
	public WieldType getEquipmentType() {
		return equipmentType;
	}

	/**
	 *  @return - weapon
	 */
	public boolean isWeapon() {
		return weapon;
	}

	/**
	 * @return the fullBody
	 */
	public boolean isFullBody() {
		return fullBody;
	}

	/**
	 * @return the fullHat
	 */
	public boolean isFullHat() {
		return fullHat;
	}

	/**
	 * @return the fullMask
	 */
	public boolean isFullMask() {
		return fullMask;
	}

	/**
	 *  @return - bonus
	 */
	public double[] getBonus() {
		return bonus;
	}

	/**
	 *  @return - requirement
	 */
	public int[] getRequirement() {
		return requirement;
	}

	/**
	 *  @return - actions
	 */
	public String[] getActions() {
		return actions;
	}
	
	/**
	 * Checks to see if the item can be wielded
	 * @return
	 */
	public boolean isWieldable() {
		String menu = actions[1].toLowerCase();
		return (menu.equals("wear") || menu.equals("wield") || menu.equals("equip"));
	}
	
	@Override
	public String toString() {
		return "ItemDefinition [" + id + "," + name + "]";
	}
	
}