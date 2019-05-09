package spring.service.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.common.valueholder.BaseObject;

public abstract class BaseObjectServiceImpl<T extends BaseObject> implements BaseObjectService<T> {

	@Autowired
	AuditTrailDAO auditTrailDAO;

	private final Class<T> classType;

	protected boolean auditTrailLog = true;

	public BaseObjectServiceImpl(Class<T> clazz) {
		classType = clazz;
	}

	protected abstract BaseDAO<T> getBaseObjectDAO();

	@Override
	@Transactional
	public T get(String id) throws InstantiationException, IllegalAccessException {
		return getBaseObjectDAO().get(id).orElse(classType.newInstance());
	}

	@Override
	@Transactional
	public List<T> getAll() {
		return getBaseObjectDAO().getAll();
	}

	@Override
	@Transactional
	public List<T> getAllMatching(String propertyName, Object propertyValue) {
		return getBaseObjectDAO().getAllMatching(propertyName, propertyValue);
	}

	@Override
	@Transactional
	public List<T> getAllMatching(Map<String, Object> propertyValues) {
		return getBaseObjectDAO().getAllMatching(propertyValues);
	}

	@Override
	@Transactional
	public List<T> getAllOrdered(String orderProperty, boolean descending) {
		return getBaseObjectDAO().getAllOrdered(orderProperty, descending);
	}

	@Override
	@Transactional
	public List<T> getAllOrdered(List<String> orderProperties, boolean descending) {
		return getBaseObjectDAO().getAllOrdered(orderProperties, descending);
	}

	@Override
	@Transactional
	public List<T> getAllMatchingOrdered(String propertyName, Object propertyValue, String orderProperty,
			boolean descending) {
		return getBaseObjectDAO().getAllMatchingOrdered(propertyName, propertyValue, orderProperty, descending);
	}

	@Override
	@Transactional
	public List<T> getAllMatchingOrdered(String propertyName, Object propertyValue, List<String> orderProperties,
			boolean descending) {
		return getBaseObjectDAO().getAllMatchingOrdered(propertyName, propertyValue, orderProperties, descending);
	}

	@Override
	@Transactional
	public List<T> getAllMatchingOrdered(Map<String, Object> propertyValues, String orderProperty, boolean descending) {
		return getBaseObjectDAO().getAllMatchingOrdered(propertyValues, orderProperty, descending);
	}

	@Override
	@Transactional
	public List<T> getAllMatchingOrdered(Map<String, Object> propertyValues, List<String> orderProperties,
			boolean descending) {
		return getBaseObjectDAO().getAllMatchingOrdered(propertyValues, orderProperties, descending);
	}

	@Override
	@Transactional
	public List<T> getPage(int pageNumber) {
		return getBaseObjectDAO().getPage(pageNumber);
	}

	@Override
	@Transactional
	public List<T> getMatchingPage(String propertyName, Object propertyValue, int pageNumber) {
		return getBaseObjectDAO().getMatchingPage(propertyName, propertyValue, pageNumber);
	}

	@Override
	@Transactional
	public List<T> getMatchingPage(Map<String, Object> propertyValues, int pageNumber) {
		return getBaseObjectDAO().getMatchingPage(propertyValues, pageNumber);
	}

	@Override
	@Transactional
	public List<T> getOrderedPage(String orderProperty, boolean descending, int pageNumber) {
		return getBaseObjectDAO().getOrderedPage(orderProperty, descending, pageNumber);
	}

	@Override
	@Transactional
	public List<T> getOrderedPage(List<String> orderProperties, boolean descending, int pageNumber) {
		return getBaseObjectDAO().getOrderedPage(orderProperties, descending, pageNumber);
	}

	@Override
	@Transactional
	public List<T> getMatchingOrderedPage(String propertyName, Object propertyValue, String orderProperty,
			boolean descending, int pageNumber) {
		return getBaseObjectDAO().getMatchingOrderedPage(propertyName, propertyValue, orderProperty, descending,
				pageNumber);
	}

	@Override
	@Transactional
	public List<T> getMatchingOrderedPage(String propertyName, Object propertyValue, List<String> orderProperties,
			boolean descending, int pageNumber) {
		return getBaseObjectDAO().getMatchingOrderedPage(propertyName, propertyValue, orderProperties, descending,
				pageNumber);
	}

	@Override
	@Transactional
	public List<T> getMatchingOrderedPage(Map<String, Object> propertyValues, String orderProperty, boolean descending,
			int pageNumber) {
		return getBaseObjectDAO().getMatchingOrderedPage(propertyValues, orderProperty, descending, pageNumber);
	}

	@Override
	@Transactional
	public List<T> getMatchingOrderedPage(Map<String, Object> propertyValues, List<String> orderProperties,
			boolean descending, int pageNumber) {
		return getBaseObjectDAO().getMatchingOrderedPage(propertyValues, orderProperties, descending, pageNumber);
	}

	@Override
	@Transactional
	public String insert(T baseObject) {
		if (auditTrailLog) {
			auditTrailDAO.saveNewHistory(baseObject, baseObject.getSysUserId(), getBaseObjectDAO().getTableName());
		}
		return getBaseObjectDAO().insert(baseObject);
	}

	@Override
	@Transactional
	public List<String> insertAll(List<T> baseObjects) {
		List<String> ids = new ArrayList<>();
		for (T baseObject : baseObjects) {
			ids.add(insert(baseObject));
		}
		return ids;
	}

	@Override
	@Transactional
	public T save(T baseObject) {
		if (auditTrailLog) {
			Optional<T> oldObject = Optional.empty();
			if (!GenericValidator.isBlankOrNull(baseObject.getId())) {
				oldObject = getBaseObjectDAO().get(baseObject.getId());
			}

			if (oldObject.isPresent()) {
				auditTrailDAO.saveHistory(baseObject, oldObject.get(), baseObject.getSysUserId(),
						IActionConstants.AUDIT_TRAIL_UPDATE, getBaseObjectDAO().getTableName());
			} else {
				auditTrailDAO.saveNewHistory(baseObject, baseObject.getSysUserId(), getBaseObjectDAO().getTableName());
			}
		}

		return getBaseObjectDAO().save(baseObject);
	}

	@Override
	@Transactional
	public List<T> saveAll(List<T> baseObjects) {
		List<T> resultObjects = new ArrayList<>();
		for (T baseObject : baseObjects) {
			resultObjects.add(save(baseObject));
		}
		return resultObjects;
	}

	@Override
	@Transactional
	public T update(T baseObject) {
		T oldObject = getBaseObjectDAO().get(baseObject.getId())
				.orElseThrow(() -> new ObjectNotFoundException(baseObject.getId(), classType.getName()));
		if (auditTrailLog) {
			auditTrailDAO.saveHistory(baseObject, oldObject, baseObject.getSysUserId(),
					IActionConstants.AUDIT_TRAIL_UPDATE, getBaseObjectDAO().getTableName());
		}
		return getBaseObjectDAO().save(baseObject);

	}

	@Override
	@Transactional
	public List<T> updateAll(List<T> baseObjects) {
		List<T> resultObjects = new ArrayList<>();
		for (T baseObject : baseObjects) {
			resultObjects.add(update(baseObject));
		}
		return resultObjects;
	}

	@Override
	@Transactional
	public void delete(T baseObject) {
		T oldObject = getBaseObjectDAO().get(baseObject.getId())
				.orElseThrow(() -> new ObjectNotFoundException(baseObject.getId(), classType.getName()));
		if (auditTrailLog) {
			auditTrailDAO.saveHistory(null, oldObject, baseObject.getSysUserId(), IActionConstants.AUDIT_TRAIL_DELETE,
					getBaseObjectDAO().getTableName());
		}
		getBaseObjectDAO().delete(baseObject);
	}

	@Override
	@Transactional
	public void deleteAll(List<T> baseObjects) {
		for (T baseObject : baseObjects) {
			delete(baseObject);
		}
	}

	@Override
	@Transactional
	public Integer getCount() {
		return getBaseObjectDAO().getCount();
	}

	@Override
	@Transactional
	public T getNext(String id) throws InstantiationException, IllegalAccessException {
		return getBaseObjectDAO().getNext(id).orElse(classType.newInstance());
	}

	@Override
	@Transactional
	public T getPrevious(String id) throws InstantiationException, IllegalAccessException {
		return getBaseObjectDAO().getPrevious(id).orElse(classType.newInstance());
	}

	@Override
	@Transactional
	public boolean hasNext(String id) {
		return getBaseObjectDAO().getNext(id).isPresent();
	}

	@Override
	@Transactional
	public boolean hasPrevious(String id) {
		return getBaseObjectDAO().getPrevious(id).isPresent();
	}

	protected void disableLogging() {
		this.auditTrailLog = false;
	}

}
