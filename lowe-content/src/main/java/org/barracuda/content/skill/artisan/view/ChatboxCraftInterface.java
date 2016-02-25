package org.barracuda.content.skill.artisan.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.barracuda.content.skill.artisan.ProductDefinition;
import org.barracuda.core.game.contract.ui.ChatboxInterface;
import org.barracuda.core.game.contract.ui.Label;
import org.barracuda.core.game.contract.ui.ModelSprite;
import org.barracuda.core.net.Channel;

public class ChatboxCraftInterface extends AbstractCraftInterface {
	
	/**
	 * The default model zoom factor. Currently using 200 as standard across all items.
	 */
	private static final int MODEL_ZOOM = 200;

	/**
	 * Collection of templates
	 */
	private static final Map<Integer, Template> templates = new HashMap<>();

	/**
	 * The products shown on the interface
	 */
	private final ProductDefinition definition;

	/**
	 * Constructor
	 * 
	 * @param definition
	 */
	public ChatboxCraftInterface(ProductDefinition definition) {
		this.definition = definition;
	}

	@Override
	public CraftInterface open(Channel channel) {
		Template template = templates.get(definition.getProducts().length);
		channel.write(new ChatboxInterface(template.interfaceId));
		for (int i = 0; i < definition.getProducts().length; i++) {
			CraftInterfaceElement element = template.elements[i];
			
			channel.write(new Label("name_of_item", element.getLabelId()));
			channel.write(new ModelSprite(definition.getProducts()[i].getId(), MODEL_ZOOM, element.getModelId()));
		}
		return this;
	}
	
	/**
	 * Contains information for a chatbox interface
	 * 
	 * @author brock
	 *
	 */
	public static class Template {
		
		/**
		 * Interface id
		 */
		private int interfaceId;
		
		/**
		 * Collection of elements
		 */
		private CraftInterfaceElement[] elements;

		/**
		 * @return the interfaceId
		 */
		public int getInterfaceId() {
			return interfaceId;
		}

		/**
		 * @param interfaceId the interfaceId to set
		 */
		public void setInterfaceId(int interfaceId) {
			this.interfaceId = interfaceId;
		}

		/**
		 * @return the elements
		 */
		public CraftInterfaceElement[] getElements() {
			return Arrays.copyOf(elements, elements.length);
		}

		/**
		 * @param elements the elements to set
		 */
		public void setElements(CraftInterfaceElement[] elements) {
			this.elements = Arrays.copyOf(elements, elements.length);
		}
		
	}

}
