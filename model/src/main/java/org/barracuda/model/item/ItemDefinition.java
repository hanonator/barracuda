package org.barracuda.model.item;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;

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
	private int id;
	
	/**
	 * The name of the item
	 */
	private String name;
	
	/**
	 * The description of the item
	 */
	private String description;
	
	/**
	 * The flag determining if an item is tradeable
	 */
	private boolean untradeable;
	
	/**
	 * The flag determining whether an item is destroyable
	 */
	private boolean destroyable;
	
	/**
	 * The flag representing whether an item is stackable
	 */
	private boolean stackable;
	
	/**
	 * The item's value
	 */
	private int value;
	
	/**
	 * The price for special stores
	 */
	private int[] specialPrice;
	
	/**
	 * The items low alchemy value
	 */
	private int lowAlchemy;
	
	/**
	 * The item's high alchemy value
	 */
	private int highAlchemy;
	
	/**
	 * The item's weight
	 */
	private double weight;
	
	/**
	 * The flag determing if an item is noted
	 */
	private boolean noted;
	
	/**
	 * the flag representing whether the item is noteable
	 */
	private boolean noteable;
	
	/**
	 * The noted id of an item
	 */
	private int childId;
	
	/**
	 * The unnoted id of an item
	 */
	private int parentId;
	
	/**
	 * The flag representing if an item is 2 handed
	 */
	private boolean isTwoHanded;
	
	/**
	 * The flag representing the equipment type of an item
	 */
	private WieldType equipmentType;
	
	/**
	 * The flag representing if an item is a weapon
	 */
	private boolean weapon;
	
	/**
	 * A flag representing if this is a full body item
	 */
	private boolean fullBody;
	
	/**
	 * A flag representing if this is a full hat item
	 */
	private boolean fullHat;
	
	/**
	 * A flag representing if this is a full mask
	 */
	private boolean fullMask;
	
	/**
	 * The bonuses of the item
	 */
	private double[] bonus;
	
	/**
	 * The item's requirements
	 */
	private int[] requirement;
	
	/**
	 * The actions this item has
	 */
	private String[] actions;
	
	/**
	 * Initializes
	 * @param initialized
	 */
	public static void initialize(@Observes ContainerInitialized event) throws Exception{
		InputStream stream = ClassLoader.getSystemResourceAsStream("static/game/item_def.json");
		definitions = new Gson().fromJson(new InputStreamReader(stream, Charset.forName("UTF-8")), ItemDefinition[].class);
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
		return Arrays.copyOf(specialPrice, specialPrice.length);
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
		return Arrays.copyOf(bonus, bonus.length);
	}

	/**
	 *  @return - requirement
	 */
	public int[] getRequirement() {
		return Arrays.copyOf(requirement, requirement.length);
	}

	/**
	 *  @return - actions
	 */
	public String[] getActions() {
		return Arrays.copyOf(actions, actions.length);
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