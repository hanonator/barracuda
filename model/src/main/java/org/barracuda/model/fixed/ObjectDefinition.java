package org.barracuda.model.fixed;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.cache.Archive;
import org.barracuda.cache.Cache;
import org.barracuda.core.net.ByteBufferUtil;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;

public class ObjectDefinition {
	
	/**
	 * The static logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(ObjectDefinition.class);

	/**
	 * The collection of definitions
	 */
	private static ObjectDefinition[] definitions;
	
	/**
	 * The id of the object
	 */
	private int id;
	
	/**
	 * The width of the object in tiles
	 */
	private int width = 1;
	
	/**
	 * The length of the object in tiles
	 */
	private int height = 1;

	/**
	 * The object's name
	 */
	private String name;
	
	/**
	 * The object's description
	 */
	private String description;
	
	/**
	 * The object's options
	 */
	private String[] options;
	
	/**
	 * Indicates you can interact with the given object (through clicking)
	 */
	private boolean interactable;

	/**
	 * Initializes all of the definitions
	 * @param initialize
	 * @throws Exception 
	 */
	public static void initialize(@Observes ContainerInitialized initialize, Cache cache) throws IOException {
		Archive archive = new Archive(cache.getFile(0, 2));
		ByteBuffer data_file = archive.getFileAsByteBuffer("loc.dat");
		ByteBuffer index_file = archive.getFileAsByteBuffer("loc.idx");
		ObjectDefinition.definitions = new ObjectDefinition[index_file.getShort() & 0xffff];
		int buffer_offset = 2;
		for (int index = 0; index < ObjectDefinition.definitions.length; index++) {
			ObjectDefinition.definitions[index] = from_buffer((ByteBuffer) data_file.position(buffer_offset));
			buffer_offset += index_file.getShort();
		}
		logger.info("{} object definitions loaded", ObjectDefinition.definitions.length);
	}

	/**
	 * Gets the definition for the given id
	 * @param id
	 * @return
	 */
	public static ObjectDefinition get(int id) {
		return definitions[id];
	}

	/**
	 * Reads the next objectdefinition from the given buffer
	 * 
	 * @param buffer
	 * @return
	 */
	private static ObjectDefinition from_buffer(ByteBuffer buffer) {
		ObjectDefinition definition = new ObjectDefinition();
		for (int opcode = buffer.get(); opcode != 0 && opcode != 77; opcode = buffer.get()) {
			if (opcode == 1) {
				buffer.position(buffer.position() + buffer.get() * 3 + 1);
			}
			else if (opcode == 2) {
				definition.setName(ByteBufferUtil.readString(buffer));
			}
			else if (opcode == 3) {
				definition.setDescription(ByteBufferUtil.readString(buffer));
			}
			else if (opcode == 5) {
				int length = buffer.get();
				if (length > 0)
					buffer.position(buffer.position() + length * 2);
			}
			else if (opcode == 14) {
				definition.setWidth(buffer.get());
			}
			else if (opcode == 15) {
				definition.setHeight(buffer.get());
			}
			else if (opcode == 28 || opcode == 29 || opcode == 39 || opcode == 69 || opcode == 75) {
				buffer.position(buffer.position() + 1);
			}
			else if (opcode == 19) {
				definition.setInteractable(buffer.get() == 1);
			}
			else if (opcode >= 30 && opcode < 39) {
				if (definition.options == null) {
					definition.options = new String[5];
				}
				definition.options[opcode - 30] = ByteBufferUtil.readString(buffer);
			}
			else if(opcode == 40){
				buffer.position(buffer.position() + buffer.get() * 4 + 1);
			}
			else if (opcode == 24 || opcode == 60 || (opcode >= 65 && opcode <= 68) || (opcode >= 70 && opcode <= 72)) {
				buffer.position(buffer.position() + 2);
			}
		}
		return definition;
	}

	@Override
	public String toString() {
		return "ObjectDefinition [id=" + id + ", width=" + width + ", height=" + height + ", name=" + name
				+ ", description=" + description + ", options=" + Arrays.toString(options) + ", interactable="
				+ interactable + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isInteractable() {
		return interactable;
	}

	public void setInteractable(boolean interactable) {
		this.interactable = interactable;
	}

}