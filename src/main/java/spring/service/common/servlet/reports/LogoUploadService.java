package spring.service.common.servlet.reports;

import us.mn.state.health.lims.image.valueholder.Image;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

public interface LogoUploadService {

	void removeImage(Image image, SiteInformation logoInformation);

	void saveImage(Image image, boolean newImage, String imageId, SiteInformation logoInformation);

}
