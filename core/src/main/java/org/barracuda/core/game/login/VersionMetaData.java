package org.barracuda.core.game.login;

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

	public VersionMetaData(int release, int version) {
		this.release = release;
		this.version = version;
	}

	public int getRelease() {
		return release;
	}

	public int getVersion() {
		return version;
	}

}