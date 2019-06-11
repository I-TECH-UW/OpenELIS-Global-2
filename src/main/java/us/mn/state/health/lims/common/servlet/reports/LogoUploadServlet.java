/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
*
* The Original Code is OpenELIS code.
*
* Copyright (C) ITECH, University of Washington, Seattle WA.  All Rights Reserved.
*
*/
package us.mn.state.health.lims.common.servlet.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.validator.GenericValidator;

import spring.mine.internationalization.MessageUtil;
import spring.service.common.servlet.reports.LogoUploadService;
import spring.service.image.ImageService;
import spring.service.siteinformation.SiteInformationService;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.image.valueholder.Image;
import us.mn.state.health.lims.login.dao.UserModuleService;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public class LogoUploadServlet extends HttpServlet {

	static final long serialVersionUID = 1L;

	private ImageService imageService = SpringContext.getBean(ImageService.class);
	private SiteInformationService siteInformationService = SpringContext.getBean(SiteInformationService.class);
	private UserModuleService userModuleService = SpringContext.getBean(UserModuleService.class);
	private LogoUploadService logoUploadService = SpringContext.getBean(LogoUploadService.class);
	private static final String PREVIEW_FILE_PATH = File.separator + "images" + File.separator;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// check for authentication
		if (userModuleService.isSessionExpired(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().println(MessageUtil.getMessage("message.error.unauthorized"));
			return;
		}

		String whichLogo = request.getParameter("logo");
		boolean removeImage = "true".equals(request.getParameter("removeImage"));
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

		if (!isMultipart) {
			return;
		}

		String uploadPreviewPath = getServletContext().getRealPath("") + PREVIEW_FILE_PATH
				+ (whichLogo.equals("headerLeftImage") ? "leftLabLogo.jpg" : "rightLabLogo.jpg");

		if (removeImage) {
			removeImage(whichLogo, uploadPreviewPath);
		} else {
			updateImage(request, whichLogo, uploadPreviewPath);
		}

		getServletContext().getRequestDispatcher("/PrintedReportsConfigurationMenu.do").forward(request, response);
	}

	private void removeImage(String logoName, String uploadPreviewPath) {
		File previewFile = new File(uploadPreviewPath);
		previewFile.delete();

		SiteInformation logoInformation = siteInformationService.getSiteInformationByName(logoName);

		if (logoInformation == null) {
			return;
		}

		String imageId = logoInformation.getValue();

		if (!GenericValidator.isBlankOrNull(imageId)) {
			Image image = imageService.get(imageId);

			try {
				logoUploadService.removeImage(image, logoInformation);
			} catch (LIMSRuntimeException lre) {
				LogEvent.logErrorStack(this.getClass().getSimpleName(), "removeImage()", lre);
			}

		}

	}

	private void updateImage(HttpServletRequest request, String whichLogo, String uploadPreviewPath)
			throws ServletException {
		DiskFileItemFactory factory = new DiskFileItemFactory();

		factory.setSizeThreshold(Image.MAX_MEMORY_SIZE);

		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		upload.setSizeMax(Image.MAX_MEMORY_SIZE);

		try {
			@SuppressWarnings("unchecked")
			List<FileItem> items = upload.parseRequest(request);

			for (FileItem item : items) {

				if (validToWrite(item)) {

					File previewFile = new File(uploadPreviewPath);

					item.write(previewFile);

					writeToDatabase(previewFile, whichLogo);

					break;
				}
			}

		} catch (FileUploadException ex) {
			throw new ServletException(ex);
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void writeToDatabase(File file, String logoName) {
		SiteInformation logoInformation = siteInformationService.getSiteInformationByName(logoName);

		if (logoInformation == null) {
			return;
		}

		String imageId = logoInformation.getValue();

		boolean newImage = GenericValidator.isBlankOrNull(imageId);

		byte[] imageData = new byte[(int) file.length()];

		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(imageData);
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Image image = new Image();
		image.setImage(imageData);
		image.setDescription(logoName);

		try {
			logoUploadService.saveImage(image, newImage, imageId, logoInformation);
		} catch (LIMSRuntimeException lre) {
			LogEvent.logErrorStack(this.getClass().getSimpleName(), "writeToDatabase()", lre);
		}
	}

	private boolean validToWrite(FileItem item) {
		return !item.isFormField() && item.getSize() > 0 && !GenericValidator.isBlankOrNull(item.getName())
				&& (item.getName().contains("jpg") || item.getName().contains("png") || item.getName().contains("gif"));
	}

}
