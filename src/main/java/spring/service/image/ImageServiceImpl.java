package spring.service.image;

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
		disableLogging();
	}

	@Override
	protected ImageDAO getBaseObjectDAO() {
		return baseObjectDAO;
	}

}
