package org.openelisglobal.image.service;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.openelisglobal.common.service.BaseObjectServiceImpl;
import org.openelisglobal.image.dao.ImageDAO;
import org.openelisglobal.image.valueholder.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl extends BaseObjectServiceImpl<Image, String> implements ImageService {
    @Autowired
    protected ImageDAO baseObjectDAO;
    @Autowired
    private ServletContext servletContext;

    public static final String PREVIEW_FILE_PATH = File.separator + "static" + File.separator + "images"
            + File.separator;
    public String FULL_PREVIEW_FILE_PATH;

    ImageServiceImpl() {
        super(Image.class);
        disableLogging();
    }

    @PostConstruct
    public void init() {
        FULL_PREVIEW_FILE_PATH = servletContext.getRealPath("") + PREVIEW_FILE_PATH;
    }

    @Override
    protected ImageDAO getBaseObjectDAO() {
        return baseObjectDAO;
    }

    @Override
    public String getFullPreviewPath() {
        return FULL_PREVIEW_FILE_PATH;
    }

    @Override
    public String getImageNameFilePath(String imageName) {
        switch (imageName) {
        case "headerLeftImage":
            return "leftLabLogo.jpg";
        case "labDirectorSignature":
            return "labDirectorSignature.jpg";
        default:
            return "rightLabLogo.jpg";
        }
    }

}
