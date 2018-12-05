package us.mn.state.health.lims.barcode.dao;

import us.mn.state.health.lims.barcode.valueholder.BarcodeLabelInfo;
import us.mn.state.health.lims.common.dao.BaseDAO;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;

public interface BarcodeLabelInfoDAO extends BaseDAO {

	/**
	 * Persists the object in the database. updates object with PK from insert
	 * @param barcodeLabelInfo       The object to persist
	 * @return                       If insertion was success
	 * @throws LIMSRuntimeException
	 */
	public boolean insertData(BarcodeLabelInfo barcodeLabelInfo) throws LIMSRuntimeException;
	
	/**
	 * Persists changes on the object based on PK 
	 * @param barcodeLabelInfo       The object to update
	 * @throws LIMSRuntimeException
	 */
	public void updateData(BarcodeLabelInfo barcodeLabelInfo) throws LIMSRuntimeException;
	
	/**
	 * Get object by code instead of PK
	 * @param code                   Code of the object
	 * @return                       the corresponding object
	 * @throws LIMSRuntimeException
	 */
	public BarcodeLabelInfo getDataByCode(String code) throws LIMSRuntimeException;
}
