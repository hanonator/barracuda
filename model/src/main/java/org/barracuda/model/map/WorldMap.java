package org.barracuda.model.map;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.barracuda.cache.Cache;
import org.barracuda.cache.index.impl.MapIndex;
import org.barracuda.cache.util.BufferUtil;
import org.barracuda.cache.util.ZipUtils;
import org.barracuda.horvik.bean.Discoverable;
import org.barracuda.horvik.context.application.ApplicationScoped;
import org.barracuda.horvik.environment.ContainerInitialized;
import org.barracuda.horvik.event.Observes;
import org.barracuda.model.fixed.RSObject;
import org.barracuda.model.location.Location;

/**
 * Worldmap
 * 
 * @author koga
 *
 */
@ApplicationScoped
@Discoverable
public class WorldMap {
	
	/**
	 * The static logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(WorldMap.class);

	/**
	 * The collection of landscape objects
	 */
	private static final Map<Integer, Landscape> landscapes = new HashMap<>();

	/**
	 * Gets the landscape based on the absolute coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static final Landscape get(int x, int y) {
		return landscapes.get(((x / 64) << 8) | (y / 64));
	}

	/**
	 * Gets the landscape based on the absolute coordinates
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static final Landscape get(Location location) {
		return WorldMap.get(location.getX(), location.getY());
	}
	
	/**
	 * The container initialized event
	 * 
	 * @param event
	 * @param cache
	 * @throws IOException
	 */
	public static void loadRegions(@Observes ContainerInitialized event, Cache cache) throws IOException {
		for (MapIndex index : cache.getIndexTable().getMapIndices()) {
			int x = (index.getIdentifier() >> 8) * 64;
			int y = (index.getIdentifier() & 0xff) * 64;
			Set<RSObject> objects = read_objects(ZipUtils.unzip(cache.getFile(4, index.getLandscapeFile())), new HashSet<>(), x, y);
			Set<Tile> tiles = read_tiles(ZipUtils.unzip(cache.getFile(4, index.getMapFile())), new HashSet<>());
			landscapes.put(index.getIdentifier(), new Landscape(objects, tiles));
			logger.debug("Loaded landscape ({}, {}) with {} objects and {} tiles", x, y, objects.size(), tiles.size());
		}
	}
	
	/**
	 * Parses the tiles in the landscape file
	 * 
	 * @param buffer
	 * @param tiles
	 * @return
	 */
	private static Set<Tile> read_tiles(ByteBuffer buffer, Set<Tile> tiles) {
		return tiles;
	}
	
	/**
	 * Parses the objects in the landscape file
	 * 
	 * @param buffer
	 * @return
	 */
	private static Set<RSObject> read_objects(ByteBuffer buffer, Set<RSObject> collection, int x_offset, int y_offset) {
		for (int index_offset = -1; buffer.get(buffer.position()) != 0; buffer.get()) {
			index_offset += BufferUtil.readUnsignedMedium(buffer);
			for (int test_index = 0; buffer.get(buffer.position()) != 0; ) {
				test_index += BufferUtil.readUnsignedMedium(buffer) - 1;
				int y = test_index & 0x3f;
				int x = test_index >> 6 & 0x3f;
				
				int metadata = buffer.get() & 0xff;
				int type = metadata >> 2;
				int orientation = metadata & 3;
				collection.add(new RSObject(index_offset, x + x_offset, y + y_offset, type, orientation));
			}
		}
		return collection;
	}

}
