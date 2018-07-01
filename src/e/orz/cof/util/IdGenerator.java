package e.orz.cof.util;

import java.io.File;
import java.math.BigInteger;
import java.util.Random;

public class IdGenerator {
	public static String genericPath(String filename, String storeDirectory) {
		int hashCode = filename.hashCode();
		int dir1 = hashCode & 0xf;
		int dir2 = (hashCode & 0xf0) >> 4;

		String dir = "/" + dir1 + "/" + dir2;

		File file = new File(storeDirectory, dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return dir;
	}

	public static String generateGUID() {
		return new BigInteger(165, new Random()).toString(36).toUpperCase();
	}
}
