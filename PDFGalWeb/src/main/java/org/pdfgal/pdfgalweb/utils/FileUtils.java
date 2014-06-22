package org.pdfgal.pdfgalweb.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public interface FileUtils {

	/**
	 * Deletes the file existing in the path described as URI.
	 * 
	 * @param uri
	 * @return True in case file could be delete. False if file could not be
	 *         deleted.
	 */
	boolean delete(String uri);

	/**
	 * Deletes the files existing in the paths included in the parameter's list.
	 * 
	 * @param urisList
	 * @return
	 */
	boolean delete(List<String> urisList);

	/**
	 * Returns the original name received as parameter with 20 auto generated
	 * characters before the original name.
	 * 
	 * @param originalName
	 * @return
	 */
	String getAutogeneratedName(String originalName);

	/**
	 * Prepares the {@link HttpServletResponse} for downloading the file whose
	 * URI is the received parameter.
	 * 
	 * @param response
	 *            The HttpServletResponse.
	 * @param uri
	 *            The URI of the file to download.
	 * @param fileName
	 *            The file name of the file to download.
	 * @throws IOException
	 */
	void prepareFileDownload(HttpServletResponse response, String uri,
			String fileName) throws IOException;

	/**
	 * 
	 * @param response
	 *            The HttpServletResponse.
	 * @param urisList
	 *            The URIs of the files to download.
	 * @param fileName
	 *            The file name of the files to download.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	void prepareFileDownload(HttpServletResponse response,
			List<String> urisList, String fileName)
			throws FileNotFoundException, IOException;

	/**
	 * Stores the {@link MultipartFile} to the system. Returns the path where
	 * the file was stored, or null in case it could not be stored.
	 * 
	 * @param file
	 *            File to save.
	 * @return Path where file was stored, or null in case file could not be
	 *         stored.
	 */
	String saveFile(MultipartFile file);

	/**
	 * Stores each one of the {@link MultipartFile} to the system. Returns the
	 * paths where the files were stored, or null in case it could not be
	 * stored.
	 * 
	 * @param file
	 *            File to save.
	 * @return Path where file was stored, or null in case file could not be
	 *         stored.
	 */
	List<String> saveFile(List<MultipartFile> files);

}
