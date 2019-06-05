package spring.service.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import spring.service.common.BaseObjectService;
import us.mn.state.health.lims.image.valueholder.Image;

public interface ImageService extends BaseObjectService<Image, String> {
	ByteArrayOutputStream retrieveImageOutputStream(String id);

	ByteArrayInputStream retrieveImageInputStream(String id);

	void deleteImage(Image image);

	Image getImage(String imageId);

	String saveImage(Image image);
}
