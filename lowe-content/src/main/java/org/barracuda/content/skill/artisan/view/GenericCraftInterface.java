package org.barracuda.content.skill.artisan.view;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.barracuda.content.skill.artisan.ProductDefinition;
import org.barracuda.core.game.contract.ui.ChatboxInterface;
import org.barracuda.core.game.contract.ui.Label;
import org.barracuda.core.game.contract.ui.ModelSprite;
import org.barracuda.core.net.Channel;
import org.barracuda.model.item.ItemDefinition;

public class GenericCraftInterface extends AbstractCraftInterface {
	
	/**
	 * The default model zoom factor. Currently using 200 as standard across all items.
	 */
	private static final int MODEL_ZOOM = 200;

	/**
	 * Collection of templates
	 */
	private static final Map<Integer, Template> templates = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param definition
	 */
	public GenericCraftInterface(ProductDefinition definition) {
		Arrays.stream(definition.getProducts()).forEach(product -> super.item(product.getId()));
	}

	@Override
	public CraftInterface open(Channel channel) {
		Template template = templates.get(items.size());
		items.forEach(item -> {
			CraftInterfaceElement element = template.elements[items.indexOf(item)];
			
			channel.write(new Label(ItemDefinition.forId(item).getName(), element.getLabelId()));
			channel.write(new ModelSprite(item, MODEL_ZOOM, element.getModelId()));
		});
		channel.write(new ChatboxInterface(template.interfaceId));
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
