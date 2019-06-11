package spring.service.common.servlet.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.image.ImageService;
import spring.service.siteinformation.SiteInformationService;
import us.mn.state.health.lims.image.valueholder.Image;
import us.mn.state.health.lims.siteinformation.valueholder.SiteInformation;

@Service
public class LogoUploadServiceImpl implements LogoUploadService {

	@Autowired
	private ImageService imageService;
	@Autowired
	private SiteInformationService siteInformationService;

	@Override
	@Transactional
	public void removeImage(Image image, SiteInformation logoInformation) {
		imageService.delete(image);
		logoInformation.setValue("");
		logoInformation.setSysUserId("1");
		siteInformationService.updateData(logoInformation);
	}

	@Override
	@Transactional
	public void saveImage(Image image, boolean newImage, String imageId, SiteInformation logoInformation) {
		if (!newImage) {
			// The reason the old image is deleted and a new one added is because updating
			// the image doesn't work.
			imageService.delete(imageService.get(imageId));
		}
		imageService.save(image);

		logoInformation.setValue(image.getId());
		logoInformation.setSysUserId("1");
		siteInformationService.updateData(logoInformation);
	}

}
