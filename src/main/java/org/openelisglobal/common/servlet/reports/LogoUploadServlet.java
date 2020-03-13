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
package org.openelisglobal.common.servlet.reports;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.validator.GenericValidator;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.log.LogEvent;
import org.openelisglobal.common.service.servlet.reports.LogoUploadService;
import org.openelisglobal.image.service.ImageService;
import org.openelisglobal.image.valueholder.Image;
import org.openelisglobal.internationalization.MessageUtil;
import org.openelisglobal.login.dao.UserModuleService;
import org.openelisglobal.siteinformation.service.SiteInformationService;
import org.openelisglobal.siteinformation.valueholder.SiteInformation;
import org.openelisglobal.spring.util.SpringContext;

public class LogoUploadServlet extends HttpServlet {

    static final long serialVersionUID = 1L;

    private ImageService imageService = SpringContext.getBean(ImageService.class);
    private SiteInformationService siteInformationService = SpringContext.getBean(SiteInformationService.class);
    private UserModuleService userModuleService = SpringContext.getBean(UserModuleService.class);
    private LogoUploadService logoUploadService = SpringContext.getBean(LogoUploadService.class);
    private static final String PREVIEW_FILE_PATH = File.separator + "static" + File.separator + "images"
            + File.separator;
    private String FULL_PREVIEW_FILE_PATH;

    @Override
    public void init() throws ServletException {
        super.init();
        FULL_PREVIEW_FILE_PATH = getServletContext().getRealPath("") + PREVIEW_FILE_PATH;
    }

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

        if (removeImage) {
            removeImage(whichLogo);
        } else {
            updateImage(request, whichLogo);
        }

        response.sendRedirect(getServletContext().getContextPath() + "/PrintedReportsConfigurationMenu.do");
    }

    private void removeImage(String logoName) {
        File previewFile = new File(
                FULL_PREVIEW_FILE_PATH + (logoName.equals("headerLeftImage") ? "leftLabLogo.jpg" : "rightLabLogo.jpg"));

        boolean deleteSuccess = previewFile.delete();
        if (!deleteSuccess) {
            LogEvent.logError(this.getClass().getName(), "removeImage", "could not delete preview file");
        }

        SiteInformation logoInformation = siteInformationService.getSiteInformationByName(logoName);

        if (logoInformation == null) {
            return;
        }

        String imageId = logoInformation.getValue();

        if (!GenericValidator.isBlankOrNull(imageId)) {
            Image image = imageService.get(imageId);

            try {
                logoUploadService.removeImage(image, logoInformation);
            } catch (LIMSRuntimeException e) {
                LogEvent.logErrorStack(e);
            }

        }

    }

    private void updateImage(HttpServletRequest request, String whichLogo) throws ServletException {
        DiskFileItemFactory factory = new DiskFileItemFactory();

        factory.setSizeThreshold(Image.MAX_MEMORY_SIZE);

        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        upload.setSizeMax(Image.MAX_MEMORY_SIZE);

        try {
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {

                if (validToWrite(item)) {

                    File previewFile = new File(FULL_PREVIEW_FILE_PATH
                            + (whichLogo.equals("headerLeftImage") ? "leftLabLogo.jpg" : "rightLabLogo.jpg"));

                    item.write(previewFile);

                    writeFileImageToDatabase(previewFile, whichLogo);

                    break;
                }
            }

        } catch (FileUploadException e) {
            throw new ServletException(e);
        } catch (RuntimeException e) {
            throw new ServletException(e);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void writeFileImageToDatabase(File file, String logoName) {
        if (!fileInImageDirectory(file)) {
            return;
        }
        SiteInformation logoInformation = siteInformationService.getSiteInformationByName(logoName);

        if (logoInformation == null) {
            return;
        }

        String imageId = logoInformation.getValue();

        boolean newImage = GenericValidator.isBlankOrNull(imageId);

        long fileSize = file.length();
        byte[] imageData = new byte[(int) fileSize];

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            int bytesRead = fileInputStream.read(imageData);
            if (bytesRead != fileSize) {
                throw new IOException("file size changed between array allocation and file read, suspected attack");
            }
        } catch (IOException e) {
            LogEvent.logError(e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    LogEvent.logError(e.getMessage(), e);
                }
            }
        }

        Image image = new Image();
        image.setImage(imageData);
        image.setDescription(logoName);

        try {
            logoUploadService.saveImage(image, newImage, imageId, logoInformation);
        } catch (LIMSRuntimeException e) {
            LogEvent.logErrorStack(e);
        }
    }

    private boolean fileInImageDirectory(File file) {
        String filePath;
        try {
            filePath = file.getCanonicalPath();
            return filePath.startsWith((new File(FULL_PREVIEW_FILE_PATH).getCanonicalPath()));
        } catch (IOException e) {
            LogEvent.logErrorStack(e);
            return false;
        }
    }

    private boolean validToWrite(FileItem item) {
        boolean valid = !item.isFormField() && item.getSize() > 0 && !GenericValidator.isBlankOrNull(item.getName())
                && (item.getName().contains("jpg") || item.getName().contains("png") || item.getName().contains("gif"));

        try (InputStream input = item.getInputStream()) {
            ImageIO.read(input);
        } catch (IOException e) {
            valid = false;
        }
        return valid;
    }

}
