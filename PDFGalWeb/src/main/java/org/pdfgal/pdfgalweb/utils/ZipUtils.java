package org.pdfgal.pdfgalweb.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Utils interface for ZIP files.
 * 
 * @author Alex
 * 
 */
public interface ZipUtils {

	/**
	 * Saves the files located at URIs set on the urisList into a new ZIP file,
	 * named fileName.
	 * 
	 * @param urisList
	 * @param fileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	String zipFiles(List<String> urisList, String fileName)
			throws FileNotFoundException, IOException;

	/**
	 * Saves the files inside the parameter's ZIP. Returns the URIs where files
	 * were saved.
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	List<String> saveFilesFromZip(InputStream inputStream) throws IOException;

}
