package org.pdfgal.pdfgalweb.services;

import javax.servlet.http.HttpServletResponse;

import org.pdfgal.pdfgal.model.enumerated.WatermarkPosition;
import org.pdfgal.pdfgalweb.forms.DownloadForm;
import org.pdfgal.pdfgalweb.model.enumerated.CustomColor;
import org.springframework.web.multipart.MultipartFile;

public interface WatermarkService {

	DownloadForm putWatermark(MultipartFile file, String text,
			CustomColor customColor, Float alpha,
			WatermarkPosition watermarkPosition, String pages,
			HttpServletResponse response) throws Exception;

}
