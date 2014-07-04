package org.pdfgal.pdfgalweb.services;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.encryption.BadSecurityHandlerException;
import org.springframework.web.multipart.MultipartFile;

public interface ProtectService {

	public void protect(MultipartFile file, String password,
			String repeatedPassword, HttpServletResponse response)
			throws COSVisitorException, IOException,
			BadSecurityHandlerException;

}
