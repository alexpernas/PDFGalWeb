package org.pdfgal.pdfgalweb.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ZipUtils {

	/**
	 * Saves the files located at URIs set on the urisList into a new ZIP file,
	 * named fileName.
	 * @param urisList
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void zipFiles(List<String> urisList, String fileName) throws FileNotFoundException, IOException;

}
