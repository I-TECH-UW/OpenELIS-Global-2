package org.openelisglobal.image.controller;

import java.util.Optional;

import org.openelisglobal.image.service.ImageService;
import org.openelisglobal.image.valueholder.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/dbImage/siteInformation/{imageName}")
    public @ResponseBody byte[] getImage(@PathVariable String imageName) {
        Optional<Image> image = imageService.getImageBySiteInfoName(imageName);
        if (image.isEmpty()) {
            return getBlankImage();
        }
        return image.get().getImage();
    }

    byte[] getBlankImage() {
        return new byte[] {};

    }
}
