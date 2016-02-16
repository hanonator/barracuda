package org.barracuda.core.game.v317.sync;

import org.barracuda.model.actor.Player;
import org.barracuda.model.actor.sync.Renderer;
import org.barracuda.model.actor.sync.Renders;
import org.barracuda.model.actor.sync.attribute.Animation;

import io.netty.buffer.ByteBuf;

@Renders(attribute=Animation.class, entity=Player.class)
public class AnimationRenderer implements Renderer<Animation> {

	@Override
	public void serialize(Animation attribute, ByteBuf buffer) {
		buffer.writeShort(attribute.getId());
		buffer.writeInt(attribute.getDelay());
	}

	@Override
	public int getIdentifier() {
		return 0;
	}

}
