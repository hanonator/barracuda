package org.barracuda.core.game.util;

/**
 * Contains details about the client's version
 * 
 * Format is {major}.{minor} (e.g. the most used client is of version 317.0)
 * 
 * @author brock
 *
 */
public class VersionMetaData {

	/**
	 * The client's release cycle. This can mean a small hotfix still on the
	 * same client version id
	 */
	private final int release;

	/**
	 * The major client version.
	 */
	private final int version;

	/**
	 * Constructor
	 * 
	 * @param release
	 * @param version
	 */
	public VersionMetaData(int release, int version) {
		this.release = release;
		this.version = version;
	}

	/**
	 * @return the release
	 */
	public int getRelease() {
		return release;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}
}