package org.pdfgal.pdfgalweb.utils.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.pdfgal.pdfgalweb.utils.FileUtils;
import org.pdfgal.pdfgalweb.utils.ZipUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ZipUtilsImpl implements ZipUtils {

	@Autowired
	FileUtils fileUtils;

	@Override
	public String zipFiles(final List<String> urisList, final String fileName)
			throws FileNotFoundException, IOException {

		String generatedFileName = null;

		if (CollectionUtils.isNotEmpty(urisList)
				&& StringUtils.isNotEmpty(fileName)) {

			// Autogeneration of file name
			generatedFileName = this.fileUtils.getAutogeneratedName(fileName
					+ ".zip");

			// ZIP file is created.
			final FileOutputStream fos = new FileOutputStream(generatedFileName);
			final ZipOutputStream zos = new ZipOutputStream(fos);

			// Files are added to ZIP.
			for (final String uri : urisList) {
				if (StringUtils.isNotEmpty(uri)) {
					this.addToZipFile(uri, zos, fileName);
				}
			}

			zos.close();
			fos.close();
		}

		return generatedFileName;
	}

	/**
	 * Adds a new file to the {@link ZipOutputStream}.
	 * 
	 * @param fileName
	 * @param zos
	 * @param originalFileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void addToZipFile(final String fileName, final ZipOutputStream zos,
			final String originalFileName) throws IOException {

		// File is opened.
		final File file = new File(fileName);

		// File is renamed.
		final String newName = fileName.substring(
				fileName.indexOf(originalFileName), fileName.length());
		final File renamedFile = new File(newName);
		file.renameTo(renamedFile);

		// File is included into ZIP.
		final FileInputStream fis = new FileInputStream(renamedFile);
		final ZipEntry zipEntry = new ZipEntry(newName);
		zos.putNextEntry(zipEntry);

		final byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		// Closing elements.
		zos.closeEntry();
		fis.close();

		// File is deleted
		this.fileUtils.delete(newName);
	}
}
