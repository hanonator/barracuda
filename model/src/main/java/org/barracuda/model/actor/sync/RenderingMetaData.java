package org.barracuda.model.actor.sync;

import java.util.HashMap;
import java.util.Map;

import org.barracuda.model.actor.sync.attribute.Attribute;

class RenderingMetaData {

	/**
	 * The rendering order
	 */
	private final RenderingOrder renderingOrder;
	
	/**
	 * The collection of renderers
	 */
	private final Map<Class<? extends Attribute>, Renderer<? extends Attribute>> renderers;

	/**
	 * Constructor
	 * 
	 * @param renderingOrder
	 * @param renderers
	 */
	public RenderingMetaData(RenderingOrder renderingOrder) {
		this.renderingOrder = renderingOrder;
		this.renderers = new HashMap<>();
	}

	/**
	 * @return the renderingOrder
	 */
	public RenderingOrder getRenderingOrder() {
		return renderingOrder;
	}

	/**
	 * @return the renderer for the given class
	 */
	public Renderer<? extends Attribute> getRenderer(Class<? extends Attribute> attribute_type) {
		return renderers.get(attribute_type);
	}
	
	/**
	 * Submits a renderer
	 * @param attribute_type
	 * @param renderer
	 */
	public void submit(Class<? extends Attribute> attribute_type, Renderer<? extends Attribute> renderer) {
		renderers.put(attribute_type, renderer);
	}

}
