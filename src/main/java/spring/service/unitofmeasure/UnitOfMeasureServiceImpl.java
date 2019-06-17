package spring.service.unitofmeasure;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.service.common.BaseObjectServiceImpl;
import spring.util.SpringContext;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.util.ConfigurationProperties;
import us.mn.state.health.lims.common.util.LocaleChangeListener;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.unitofmeasure.dao.UnitOfMeasureDAO;
import us.mn.state.health.lims.unitofmeasure.valueholder.UnitOfMeasure;

@Service
@DependsOn({ "springContext" })
@Scope("prototype")
public class UnitOfMeasureServiceImpl extends BaseObjectServiceImpl<UnitOfMeasure, String>
		implements UnitOfMeasureService, LocaleChangeListener {

	private static UnitOfMeasureServiceImpl INSTANCE;

	private static String LANGUAGE_LOCALE = ConfigurationProperties.getInstance()
			.getPropertyValue(ConfigurationProperties.Property.DEFAULT_LANG_LOCALE);
	private static Map<String, String> unitOfMeasureIdToNameMap = null;

	@Autowired
	protected static UnitOfMeasureDAO unitOfMeasureDAO = SpringContext.getBean(UnitOfMeasureDAO.class);

	private UnitOfMeasure unitOfMeasure;

	@PostConstruct
	private void initilaize() {
		SystemConfiguration.getInstance().addLocalChangeListener(this);
	}

	@PostConstruct
	private void registerInstance() {
		INSTANCE = this;
	}

	public static UnitOfMeasureServiceImpl getInstance() {
		return INSTANCE;
	}

	public synchronized void initializeGlobalVariables() {
		if (unitOfMeasureIdToNameMap == null) {
			createTestIdToNameMap();
		}
	}

	UnitOfMeasureServiceImpl() {
		super(UnitOfMeasure.class);
		initializeGlobalVariables();
	}

	public UnitOfMeasureServiceImpl(UnitOfMeasure unitOfMeasure) {
		this();
		this.unitOfMeasure = unitOfMeasure;
		initializeGlobalVariables();
	}

	public UnitOfMeasureServiceImpl(String unitOfMeasureId) {
		this();
		unitOfMeasure = unitOfMeasureDAO.getUnitOfMeasureById(unitOfMeasureId);
		initializeGlobalVariables();
	}

	@Override
	protected UnitOfMeasureDAO getBaseObjectDAO() {
		return unitOfMeasureDAO;
	}

	@Transactional(readOnly = true)
	public UnitOfMeasure getUnitOfMeasure() {
		return unitOfMeasure;
	}

	@Override
	public void localeChanged(String locale) {
		LANGUAGE_LOCALE = locale;
		testNamesChanged();
	}

	public static void refreshNames() {
		testNamesChanged();
	}

	public static void testNamesChanged() {
		createTestIdToNameMap();
	}

	@Transactional(readOnly = true)
	public String getSortOrder() {
		return unitOfMeasure == null ? "0" : unitOfMeasure.getSortOrder();
	}

	public static String getUserLocalizedUnitOfMeasureName(UnitOfMeasure unitOfMeasure) {
		if (unitOfMeasure == null) {
			return "";
		}

		return getUserLocalizedUnitOfMeasureName(unitOfMeasure.getId());
	}

	public static String getUserLocalizedUnitOfMeasureName(String unitOfMeasureId) {
		String name = unitOfMeasureIdToNameMap.get(unitOfMeasureId);
		return name == null ? "" : name;
	}

	private static void createTestIdToNameMap() {
		unitOfMeasureIdToNameMap = new HashMap<>();

		List<UnitOfMeasure> UnitOfMeasures = unitOfMeasureDAO.getAll();

		for (UnitOfMeasure unitOfMeasure : UnitOfMeasures) {
			unitOfMeasureIdToNameMap.put(unitOfMeasure.getId(),
					buildUnitOfMeasureName(unitOfMeasure).replace("\n", " "));
		}
	}

	private static String buildUnitOfMeasureName(UnitOfMeasure unitOfMeasure) {
//       Localization localization = unitOfMeasure.getLocalization();
//
//        if( LANGUAGE_LOCALE.equals( ConfigurationProperties.LOCALE.FRENCH.getRepresentation() )){
//            return localization.getFrench();
//        }else{
//            return localization.getEnglish();
//        }
//  }

//    public static List<Test> getTestsInSection(String id) {
//        return TestServiceImpl.getTestsInTestSectionById(id);
		return ""; // just for compile
	}

	@Override
	@Transactional(readOnly = true)
	public UnitOfMeasure getUnitOfMeasureById(String uomId) {
		return getBaseObjectDAO().getUnitOfMeasureById(uomId);
	}

	@Override
	@Transactional(readOnly = true)
	public UnitOfMeasure getUnitOfMeasureByName(UnitOfMeasure unitOfMeasure) {
		return getMatch("unitOfMeasureName", unitOfMeasure.getUnitOfMeasureName()).orElse(null);
	}

	@Override
	public String insert(UnitOfMeasure unitOfMeasure) {
		if (getBaseObjectDAO().duplicateUnitOfMeasureExists(unitOfMeasure)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + unitOfMeasure.getUnitOfMeasureName());
		}
		return super.insert(unitOfMeasure);
	}

	@Override
	public UnitOfMeasure save(UnitOfMeasure unitOfMeasure) {
		if (getBaseObjectDAO().duplicateUnitOfMeasureExists(unitOfMeasure)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + unitOfMeasure.getUnitOfMeasureName());
		}
		return super.save(unitOfMeasure);
	}

	@Override
	public UnitOfMeasure update(UnitOfMeasure unitOfMeasure) {
		if (getBaseObjectDAO().duplicateUnitOfMeasureExists(unitOfMeasure)) {
			throw new LIMSDuplicateRecordException(
					"Duplicate record exists for " + unitOfMeasure.getUnitOfMeasureName());
		}
		return super.update(unitOfMeasure);
	}

}
