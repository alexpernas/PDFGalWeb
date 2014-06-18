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
import org.pdfgal.pdfgalweb.utils.ZipUtils;
import org.springframework.stereotype.Component;

@Component
public class ZipUtilsImpl implements ZipUtils {

	@Override
	public void zipFiles(final List<String> urisList, final String fileName)
			throws FileNotFoundException, IOException {

		if (CollectionUtils.isNotEmpty(urisList) && StringUtils.isNotEmpty(fileName)) {

			final FileOutputStream fos = new FileOutputStream(fileName + ".zip");
			final ZipOutputStream zos = new ZipOutputStream(fos);

			for (final String uri : urisList) {
				if (StringUtils.isNotEmpty(uri)) {
					addToZipFile(uri, zos);
				}
			}

			zos.close();
			fos.close();
		}

	}

	/**
	 * Adds a new file to the {@link ZipOutputStream}.
	 * @param fileName
	 * @param zos
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void addToZipFile(final String fileName, final ZipOutputStream zos)
			throws FileNotFoundException, IOException {

		final File file = new File(fileName);
		final FileInputStream fis = new FileInputStream(file);
		final ZipEntry zipEntry = new ZipEntry(fileName);
		zos.putNextEntry(zipEntry);

		final byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}

		zos.closeEntry();
		fis.close();
	}

}
