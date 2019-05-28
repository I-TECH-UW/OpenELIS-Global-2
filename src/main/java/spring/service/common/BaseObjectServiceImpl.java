package spring.service.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.validator.GenericValidator;
import us.mn.state.health.lims.common.valueholder.BaseObject;
import us.mn.state.health.lims.panelitem.valueholder.PanelItem;

public abstract class BaseObjectServiceImpl<T extends BaseObject> implements BaseObjectService<T> {

	@Autowired
	protected AuditTrailDAO auditTrailDAO;

	private final Class<T> classType;

	protected boolean auditTrailLog = true;

	public BaseObjectServiceImpl(Class<T> clazz) {
		classType = clazz;
	}

	protected abstract BaseDAO<T> getBaseObjectDAO();

	@Override
	@Transactional(readOnly = true)
	public T get(String id) {
		return getBaseObjectDAO().get(id).orElseThrow(() -> new ObjectNotFoundException(id, classType.getName()));

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<T> getMatch(String propertyName, Object propertyValue) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);
		return getMatch(propertyValues);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<T> getMatch(Map<String, Object> propertyValues) {
		List<T> matches = getAllMatching(propertyValues);

		if (matches.isEmpty() || matches.size() > 1) {
			return Optional.empty();
		} else {
			return Optional.of(matches.get(0));
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAll() {
		return getBaseObjectDAO().getAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAllMatching(String propertyName, Object propertyValue) {
		return getBaseObjectDAO().getAllMatching(propertyName, propertyValue);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAllMatching(Map<String, Object> propertyValues) {
		return getBaseObjectDAO().getAllMatching(propertyValues);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAllOrdered(String orderProperty, boolean descending) {
		return getBaseObjectDAO().getAllOrdered(orderProperty, descending);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAllOrdered(List<String> orderProperties, boolean descending) {
		return getBaseObjectDAO().getAllOrdered(orderProperties, descending);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAllMatchingOrdered(String propertyName, Object propertyValue, String orderProperty,
			boolean descending) {
		return getBaseObjectDAO().getAllMatchingOrdered(propertyName, propertyValue, orderProperty, descending);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAllMatchingOrdered(String propertyName, Object propertyValue, List<String> orderProperties,
			boolean descending) {
		return getBaseObjectDAO().getAllMatchingOrdered(propertyName, propertyValue, orderProperties, descending);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAllMatchingOrdered(Map<String, Object> propertyValues, String orderProperty, boolean descending) {
		return getBaseObjectDAO().getAllMatchingOrdered(propertyValues, orderProperty, descending);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getAllMatchingOrdered(Map<String, Object> propertyValues, List<String> orderProperties,
			boolean descending) {
		return getBaseObjectDAO().getAllMatchingOrdered(propertyValues, orderProperties, descending);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getPage(int pageNumber) {
		return getBaseObjectDAO().getPage(pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getMatchingPage(String propertyName, Object propertyValue, int pageNumber) {
		return getBaseObjectDAO().getMatchingPage(propertyName, propertyValue, pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getMatchingPage(Map<String, Object> propertyValues, int pageNumber) {
		return getBaseObjectDAO().getMatchingPage(propertyValues, pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getOrderedPage(String orderProperty, boolean descending, int pageNumber) {
		return getBaseObjectDAO().getOrderedPage(orderProperty, descending, pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getOrderedPage(List<String> orderProperties, boolean descending, int pageNumber) {
		return getBaseObjectDAO().getOrderedPage(orderProperties, descending, pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getMatchingOrderedPage(String propertyName, Object propertyValue, String orderProperty,
			boolean descending, int pageNumber) {
		return getBaseObjectDAO().getMatchingOrderedPage(propertyName, propertyValue, orderProperty, descending,
				pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getMatchingOrderedPage(String propertyName, Object propertyValue, List<String> orderProperties,
			boolean descending, int pageNumber) {
		return getBaseObjectDAO().getMatchingOrderedPage(propertyName, propertyValue, orderProperties, descending,
				pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getMatchingOrderedPage(Map<String, Object> propertyValues, String orderProperty, boolean descending,
			int pageNumber) {
		return getBaseObjectDAO().getMatchingOrderedPage(propertyValues, orderProperty, descending, pageNumber);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> getMatchingOrderedPage(Map<String, Object> propertyValues, List<String> orderProperties,
			boolean descending, int pageNumber) {
		return getBaseObjectDAO().getMatchingOrderedPage(propertyValues, orderProperties, descending, pageNumber);
	}

	@Override
	@Transactional
	public Serializable insert(T baseObject) {
		if (auditTrailLog) {
			auditTrailDAO.saveNewHistory(baseObject, baseObject.getSysUserId(), getBaseObjectDAO().getTableName());
		}
		return getBaseObjectDAO().insert(baseObject);
	}

	@Override
	@Transactional
	public List<Serializable> insertAll(List<T> baseObjects) {
		List<Serializable> ids = new ArrayList<>();
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
	public void delete(String id, String sysUserId) {
		T oldObject = getBaseObjectDAO().get(id)
				.orElseThrow(() -> new ObjectNotFoundException(id, classType.getName()));
		if (auditTrailLog) {
			auditTrailDAO.saveHistory(null, oldObject, sysUserId, IActionConstants.AUDIT_TRAIL_DELETE,
					getBaseObjectDAO().getTableName());
		}
		getBaseObjectDAO().delete(oldObject);
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
	public void deleteAll(List<String> ids, String sysUserId) {
		for (String id : ids) {
			delete(id, sysUserId);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Integer getCount() {
		return getBaseObjectDAO().getCount();
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getCountMatching(String propertyName, Object propertyValue) {
		Map<String, Object> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);
		return getCountMatching(propertyValues);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getCountMatching(Map<String, Object> propertyValues) {
		return getBaseObjectDAO().getAllMatching(propertyValues).size();
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getCountLike(String propertyName, String propertyValue) {
		Map<String, String> propertyValues = new HashMap<>();
		propertyValues.put(propertyName, propertyValue);
		return getCountLike(propertyValues);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getCountLike(Map<String, String> propertyValues) {
		return getBaseObjectDAO().getAllLike(propertyValues).size();
	}

	@Override
	@Transactional(readOnly = true)
	public T getNext(String id) {
		try {
			return getBaseObjectDAO().getNext(id).orElse(classType.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			LogEvent.logError(this.getClass().getSimpleName(), "get()",
					"Could not create new Instance for " + classType.getName());
			throw new LIMSRuntimeException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public T getPrevious(String id) {
		try {
			return getBaseObjectDAO().getPrevious(id).orElse(classType.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			LogEvent.logError(this.getClass().getSimpleName(), "get()",
					"Could not create new Instance for " + classType.getName());
			throw new LIMSRuntimeException(e);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public boolean hasNext(String id) {
		return getBaseObjectDAO().getNext(id).isPresent();
	}

	@Override
	@Transactional(readOnly = true)
	public boolean hasPrevious(String id) {
		return getBaseObjectDAO().getPrevious(id).isPresent();
	}

	protected void disableLogging() {
		this.auditTrailLog = false;
	}
}
