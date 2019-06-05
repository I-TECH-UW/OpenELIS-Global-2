package spring.service.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.service.common.BaseObjectServiceImpl;
import us.mn.state.health.lims.image.dao.ImageDAO;
import us.mn.state.health.lims.image.valueholder.Image;

@Service
public class ImageServiceImpl extends BaseObjectServiceImpl<Image, String> implements ImageService {
	@Autowired
	protected ImageDAO baseObjectDAO;

	ImageServiceImpl() {
		super(Image.class);
	}

	@Override
	protected ImageDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

	@Override
	public ByteArrayOutputStream retrieveImageOutputStream(String id) {
        return getBaseObjectDAO().retrieveImageOutputStream(id);
	}

	@Override
	public ByteArrayInputStream retrieveImageInputStream(String id) {
        return getBaseObjectDAO().retrieveImageInputStream(id);
	}

	@Override
	public void deleteImage(Image image) {
        getBaseObjectDAO().deleteImage(image);

	}

	@Override
	public Image getImage(String imageId) {
        return getBaseObjectDAO().getImage(imageId);
	}

	@Override
	public String saveImage(Image image) {
        return getBaseObjectDAO().saveImage(image);
	}
}
