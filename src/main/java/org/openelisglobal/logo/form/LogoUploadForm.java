package org.openelisglobal.logo.form;

import org.openelisglobal.common.form.BaseForm;
import org.springframework.web.multipart.MultipartFile;

public class LogoUploadForm extends BaseForm {

    private MultipartFile logoFile;

    private Boolean removeImage;

    private String logoName;

    public MultipartFile getLogoFile() {
        return logoFile;
    }

    public void setLogoFile(MultipartFile logoFile) {
        this.logoFile = logoFile;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }

    public Boolean getRemoveImage() {
        return removeImage;
    }

    public void setRemoveImage(Boolean removeImage) {
        this.removeImage = removeImage;
    }
}
