/**
* The contents of this file are subject to the Mozilla Public License
* Version 1.1 (the "License"); you may not use this file except in
* compliance with the License. You may obtain a copy of the License at
* http://www.mozilla.org/MPL/
*
* Software distributed under the License is distributed on an "AS IS"
* basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
* License for the specific language governing rights and limitations under
* the License.
*
* The Original Code is OpenELIS code.
*
* Copyright (C) The Minnesota Department of Health.  All Rights Reserved.
*/
package us.mn.state.health.lims.organization.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.validator.GenericValidator;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import us.mn.state.health.lims.audittrail.dao.AuditTrailDAO;
import us.mn.state.health.lims.audittrail.daoimpl.AuditTrailDAOImpl;
import us.mn.state.health.lims.common.action.IActionConstants;
import us.mn.state.health.lims.common.daoimpl.GenericDAOImpl;
import us.mn.state.health.lims.common.exception.LIMSDuplicateRecordException;
import us.mn.state.health.lims.common.exception.LIMSRuntimeException;
import us.mn.state.health.lims.common.log.LogEvent;
import us.mn.state.health.lims.common.util.StringUtil;
import us.mn.state.health.lims.common.util.SystemConfiguration;
import us.mn.state.health.lims.hibernate.HibernateUtil;
import us.mn.state.health.lims.organization.dao.OrganizationDAO;
import us.mn.state.health.lims.organization.valueholder.Organization;
import us.mn.state.health.lims.project.dao.ProjectDAO;
import us.mn.state.health.lims.project.daoimpl.ProjectDAOImpl;
import us.mn.state.health.lims.project.valueholder.Project;

/**
 * @author diane benz
 */
public class OrganizationDAOImpl extends GenericDAOImpl<String, Organization> implements OrganizationDAO {

	public OrganizationDAOImpl() {
        super(Organization.class, "organization");
    }

    public void deleteData(List organizations) throws LIMSRuntimeException {
		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			for (int i = 0; i < organizations.size(); i++) {
				Organization data = (Organization)organizations.get(i);

				Organization oldData = (Organization)readOrganization(data.getId());
				Organization newData = new Organization();

				String sysUserId = data.getSysUserId();
				String event = IActionConstants.AUDIT_TRAIL_DELETE;
				String tableName = "ORGANIZATION";
				auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
			}
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","AuditTrail deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in Organization AuditTrail deleteData()", e);
		}

		try {
			for (int i = 0; i < organizations.size(); i++) {
				Organization data = (Organization) organizations.get(i);
				Organization cloneData = (Organization)readOrganization(data.getId());

				//Make the change to the object.
				cloneData.setIsActive(IActionConstants.NO);
				HibernateUtil.getSession().merge(cloneData);
				HibernateUtil.getSession().flush();
				HibernateUtil.getSession().clear();
				HibernateUtil.getSession().evict(cloneData);
				HibernateUtil.getSession().refresh(cloneData);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","deleteData()",e.toString());
			throw new LIMSRuntimeException("Error in Organization deleteData()", e);
		}
	}

	public boolean insertData(Organization organization) throws LIMSRuntimeException {

		try {
			if (organization.getIsActive().equals(IActionConstants.YES) && duplicateOrganizationExists(organization)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for " + organization.getOrganizationName());
	    	}

			String id = (String)HibernateUtil.getSession().save(organization);
			organization.setId(id);

			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			auditDAO.saveNewHistory(organization,organization.getSysUserId(),"ORGANIZATION");

			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

		} catch (Exception e) {
			LogEvent.logError("OrganizationDAOImpl","insertData()",e.toString());
			throw new LIMSRuntimeException("Error in Organization insertData()", e);
		}

		return true;
	}

	public void updateData(Organization organization) throws LIMSRuntimeException {
		// bugzilla 1482 throw Exception if active record already exists
		try {
			if (organization.getIsActive().equals(IActionConstants.YES) && duplicateOrganizationExists(organization)) {
				throw new LIMSDuplicateRecordException(
						"Duplicate record exists for " + organization.getOrganizationName());
			}
		} catch (Exception e) {
    		//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Organization updateData()", e);
		}

		Organization oldData = (Organization)readOrganization(organization.getId());
		Organization newData = organization;

		//add to audit trail
		try {
			AuditTrailDAO auditDAO = new AuditTrailDAOImpl();
			String sysUserId = organization.getSysUserId();
			String event = IActionConstants.AUDIT_TRAIL_UPDATE;
			String tableName = "ORGANIZATION";
			auditDAO.saveHistory(newData,oldData,sysUserId,event,tableName);
		}  catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","AuditTrail updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Organization AuditTrail updateData()", e);
		}

		try {
			HibernateUtil.getSession().merge(organization);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			HibernateUtil.getSession().evict(organization);
			HibernateUtil.getSession().refresh(organization);
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","updateData()",e.toString());
			throw new LIMSRuntimeException("Error in Organization updateData()", e);
		}
	}

	public void getData(Organization organization) throws LIMSRuntimeException {
		try {
			Organization org = (Organization)HibernateUtil.getSession().get(Organization.class, organization.getId());
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			if (org != null) {
				PropertyUtils.copyProperties(organization, org);
			} else {
			//bugzilla 1366
				organization.setId(null);
			}
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","getData()",e.toString());
			throw new LIMSRuntimeException("Error in Organization getData()", e);
		}
	}

	public List getAllOrganizations() throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from Organization";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","getAllOrganizations()",e.toString());
			throw new LIMSRuntimeException("Error in Organization getAllOrganizations()", e);
		}

		return list;
	}

	public List getPageOfOrganizations(int startingRecNo) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			// calculate maxRow to be one more than the page size
			int endingRecNo = startingRecNo + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);

			//bugzilla 1399
			String sql = "from Organization o order by o.organizationName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(startingRecNo-1);
			query.setMaxResults(endingRecNo-1);

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","getPageOfOrganizations()",e.toString());
			throw new LIMSRuntimeException("Error in Organization getPageOfOrganizations()", e);
		}

		return list;
	}

//	 bugzilla 2372
	public List getPagesOfSearchedOrganizations(int startingRecNo, String searchString)
	throws LIMSRuntimeException {
         List list = new Vector();
         String wildCard = "*";
         String newSearchStr;
         String sql;

         try {
        	  int endingRecNo = startingRecNo
  			                    + (SystemConfiguration.getInstance().getDefaultPageSize() + 1);
        	  int wCdPosition = searchString.indexOf (wildCard);

              if (wCdPosition == -1)  // no wild card looking for exact match
              {
            	  newSearchStr = searchString.toLowerCase().trim();
                  sql = "from Organization o where trim(lower (o.organizationName)) = :param  order by o.organizationName";
              }
	          else
	          {
	             newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
	              sql = "from Organization o where trim(lower (o.organizationName)) like :param  order by o.organizationName";
	          }
	          org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
	          query.setParameter("param", newSearchStr);
	          query.setFirstResult(startingRecNo - 1);
	          query.setMaxResults(endingRecNo - 1);

	          list = query.list();
	          HibernateUtil.getSession().flush();
	          HibernateUtil.getSession().clear();
             }       catch (Exception e) {
	                 e.printStackTrace();
	                 throw new LIMSRuntimeException(
			             "Error in OrganizationDAOImpl getPageOfSearchedOrganizations()", e);
       }

        return list;
    }
	//end bugzilla 2372


	public Organization readOrganization(String idString) {
		Organization org = null;
		try {
			org = (Organization)HibernateUtil.getSession().get(Organization.class, idString);
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","readOrganization()",e.toString());
			throw new LIMSRuntimeException("Error in Organization readOrganization()", e);
		}

		return org;
	}

	// this is for autocomplete
	public List getOrganizations(String filter) throws LIMSRuntimeException {
		List list = new Vector();
		try {
			String sql = "from Organization o where upper(o.organizationName) like upper(:param) and o.isActive='Y' order by upper(o.organizationName)";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setParameter("param", filter+"%");

			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","getOrganizations()",e.toString());
			throw new LIMSRuntimeException("Error in Organization getOrganizations(String filter)", e);
		}

		return list;

	}

	public List getNextOrganizationRecord(String id)
			throws LIMSRuntimeException {

		return getNextRecord(id, "Organization", Organization.class);

	}

	public List getPreviousOrganizationRecord(String id)
			throws LIMSRuntimeException {

		return getPreviousRecord(id, "Organization", Organization.class);
	}

	public Organization getOrganizationByName(Organization organization, boolean ignoreCase) throws LIMSRuntimeException {
		    String sql = null;
		try {
		    if (ignoreCase) {
		    	sql = "from Organization o where trim(lower(o.organizationName)) = :param and o.isActive='Y'";
		    } else {
		    	sql = "from Organization o where o.organizationName = :param and o.isActive='Y'";
		    }

			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			if (ignoreCase) {
				query.setString("param", organization.getOrganizationName().trim().toLowerCase());
			} else {
				query.setString("param", organization.getOrganizationName());
			}

			List list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();
			Organization org = null;
			if ( list.size() > 0 )
				org = (Organization)list.get(0);

			return org;

		} catch (Exception e) {
			//bugzilla 2154
			LogEvent.logError("OrganizationDAOImpl","getOrganizationByName()",e.toString());
			throw new LIMSRuntimeException("Error in Organization getOrganizationByName()", e);
		}
	}

	//bugzilla 2069
	public Organization getOrganizationByLocalAbbreviation(Organization organization, boolean ignoreCase) throws LIMSRuntimeException {
	    String sql = null;
	try {
	    if (ignoreCase) {
	    	sql = "from Organization o where trim(lower(o.organizationLocalAbbreviation)) = :param and o.isActive='Y'";
	    } else {
	    	sql = "from Organization o where o.organizationLocalAbbreviation = :param and o.isActive='Y'";
	    }

		org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
		if (ignoreCase) {
			query.setParameter("param", organization.getOrganizationLocalAbbreviation().trim()
					.toLowerCase());
		} else {
			query.setParameter("param", organization.getOrganizationLocalAbbreviation());
		}

		List list = query.list();
		HibernateUtil.getSession().flush();
		HibernateUtil.getSession().clear();
		Organization org = null;
		if ( list.size() > 0 )
			org = (Organization)list.get(0);

		return org;

	} catch (Exception e) {
		//bugzilla 2154
		LogEvent.logError("OrganizationDAOImpl","getOrganizationByLocalAbbreviation()",e.toString());
		throw new LIMSRuntimeException("Error in Organization getOrganizationByLocalAbbreviation()", e);
	}
}


	//bugzilla 1411
	public Integer getTotalOrganizationCount() throws LIMSRuntimeException {
		return getTotalCount("Organization", Organization.class);
	}

	//overriding BaseDAOImpl bugzilla 1427 pass in name not id
	public List getNextRecord(String id, String table, Class clazz) throws LIMSRuntimeException {

		List list = new Vector();
		try {
			String sql = "from "+table+" t where name >= "+ enquote(id) + " order by t.organizationName";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);

			list = query.list();

		} catch (Exception e) {
			//bugzilla 2154
		    LogEvent.logError("OrganizationDAOImpl","getNextRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getNextRecord() for " + table, e);
		}

		return list;
	}

	//overriding BaseDAOImpl bugzilla 1427 pass in name not id
	public List getPreviousRecord(String id, String table, Class clazz) throws LIMSRuntimeException {

		List list = new Vector();
		try {
			String sql = "from "+table+" t order by t.organizationName desc where name <= "+ enquote(id);
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(sql);
			query.setFirstResult(1);
			query.setMaxResults(2);

			list = query.list();
		} catch (Exception e) {
			//bugzilla 2154
		    LogEvent.logError("OrganizationDAOImpl","getPreviousRecord()",e.toString());
			throw new LIMSRuntimeException("Error in getPreviousRecord() for " + table, e);
		}

		return list;
	}


	private boolean duplicateOrganizationExists(Organization organization) throws LIMSRuntimeException {
		try {

			List list = new ArrayList();

			//only check if the test to be inserted/updated is active
			if (organization.getIsActive().equalsIgnoreCase(IActionConstants.YES)) {
			// not case sensitive hemolysis and Hemolysis are considered
			// duplicates
			String sql = "from Organization o where ((trim(lower(o.organizationName))) = :orgName and o.isActive='Y' and o.id != :orgId)" +
					" or " +
					"((trim(lower(o.organizationLocalAbbreviation))) = :orgAbrv and o.isActive='Y' and o.id != :orgId)";
			org.hibernate.Query query = HibernateUtil.getSession().createQuery(
					sql);
			query.setParameter("orgName", organization.getOrganizationName().toLowerCase().trim());

			//initialize with 0 (for new records where no id has been generated yet
			String orgId = "0";
			if (!StringUtil.isNullorNill(organization.getId())) {
				orgId = organization.getId();
			}

			query.setInteger("orgId", Integer.parseInt(orgId));
			String organizationLocalAbbrev = "0";
			if (!StringUtil.isNullorNill(organization.getOrganizationLocalAbbreviation())) {
				organizationLocalAbbrev = organization.getOrganizationLocalAbbreviation();
			}
			query.setParameter("orgAbrv", organizationLocalAbbrev);


			list = query.list();
			HibernateUtil.getSession().flush();
			HibernateUtil.getSession().clear();

			}

			if (list.size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			//bugzilla 2154
		    LogEvent.logError("OrganizationDAOImpl","duplicateOrganizationExists()",e.toString());
			throw new LIMSRuntimeException("Error in duplicateOrganizationExists()", e);
		}
	}

//	 bugzilla 2372 get total searched results
	public Integer getTotalSearchedOrganizationCount( String searchString )
	  throws LIMSRuntimeException{

        String wildCard = "*";
        String newSearchStr;
        String sql;
        Integer count = null;

        try {

       	  int wCdPosition = searchString.indexOf (wildCard);

             if (wCdPosition == -1)  // no wild card looking for exact match
             {
           	  newSearchStr = searchString.toLowerCase().trim();
                 sql = "select count (*) from Organization o where trim(lower (o.organizationName)) = :param ";
             }
	          else
	          {
		         newSearchStr = searchString.replace(wildCard, "%").toLowerCase().trim();
	             sql = "select count (*) from Organization o where trim(lower (o.organizationName)) like :param ";
	          }
	          Query query = HibernateUtil.getSession().createQuery(sql);
	          query.setParameter("param", newSearchStr);

	          List results  = query.list();
	          HibernateUtil.getSession().flush();
	          HibernateUtil.getSession().clear();

	      	if (results != null && results.get(0) != null) {
				if (results.get(0) != null) {
					count = (Integer)results.get(0);
				}
			 }

            }       catch (Exception e) {
	                 e.printStackTrace();
	                 throw new LIMSRuntimeException(
			             "Error in OrganizationDAOImpl getTotalSearchedOrganizations()", e);
                       }

       return count;
   }
   //end bugzilla 2372

	public Set<Organization> getOrganizationsByProjectName(String projectName) {
        Project p = new Project();
        p.setProjectName(projectName);
        p = ((ProjectDAO)new ProjectDAOImpl()).getProjectByName(p, false, true);
        Set<Organization> orgs = p.getOrganizations();
        return orgs;
	}

    /**
     * @see us.mn.state.health.lims.organization.dao.OrganizationDAO#getOrganizationsByOrgTypeName(java.lang.String, java.lang.String[])
     */

    public List<Organization> getOrganizationsByTypeName(String orderByProperty, String... typeNames) {
        String sql = null;
        try {
            sql = "SELECT o FROM Organization o INNER JOIN o.organizationTypes ot WHERE ot.name IN (:names) ";
            sql += " AND o.isActive = 'Y' ";
            if (null != orderByProperty) {
                sql += " ORDER BY o." + orderByProperty;
            }
            Session session = HibernateUtil.getSession();
            Query query = session.createQuery(sql).setParameterList("names", typeNames);
            @SuppressWarnings("unchecked")
            List<Organization> orgs = (List<Organization>) query.list();

            session.flush();
            session.clear();

            return orgs;
        } catch (Exception e) {
            LogEvent.logError("OrganizationDAOImpl", "getOrganizationsByTypeName()", e.toString());
            throw new LIMSRuntimeException("Error in OrganizationType getOrganizationTypeByName()", e);
        }
    }

	public List<Organization> getOrganizationsByTypeNameAndLeadingChars(String partialName, String typeName) {

	        try {
	            String sql = "SELECT o FROM Organization AS o INNER JOIN o.organizationTypes AS ot WHERE ot.name = :typeName " +
	             " AND o.isActive = 'Y' AND upper(o.organizationName) like upper(:partialName) order by upper(o.organizationName)";
	            Query query = HibernateUtil.getSession().createQuery(sql);
	            query.setParameter("typeName", typeName);
	            query.setParameter("partialName", partialName + "%");
	            @SuppressWarnings("unchecked")
	            List<Organization> orgs = (List<Organization>) query.list();
	            closeSession();

	            return orgs;
	        } catch (Exception e) {
	        	handleException(e, "getOrganizationsByTypeNameAndLeadingChars");
	        }

	        return null;
	}
	public Organization getOrganizationById(String organizationId) throws LIMSRuntimeException {
		if( !GenericValidator.isBlankOrNull(organizationId)){
			String sql = "from Organization o where o.id = :organizationId";

			try{
				Query query = HibernateUtil.getSession().createQuery(sql);
				query.setInteger("organizationId", Integer.parseInt(organizationId));
				Organization organization = (Organization)query.uniqueResult();

				closeSession();

				return organization;

			}catch(HibernateException e){
				handleException(e, "getOrganizationById");
			}
		}

		return null;
	}

    @Override
    public void insertOrUpdateData( Organization organization ) throws LIMSRuntimeException{
        if(organization.getId() == null){
            insertData( organization );
        }else{
            updateData( organization );
        }

    }

    @SuppressWarnings("unchecked")
	@Override
	public List<Organization> getOrganizationsByParentId(String parentId) throws LIMSRuntimeException {
		if( GenericValidator.isBlankOrNull(parentId)){
			return new ArrayList<Organization>();
		}
		
		String sql = "from Organization o where o.organization.id = :parentId order by o.id";
		
		try {
			Query query = HibernateUtil.getSession().createQuery(sql);
			query.setInteger("parentId", Integer.parseInt(parentId));
			List<Organization> orgs = query.list();
			
			closeSession();
			
			return orgs;
		} catch (HibernateException e) {
			handleException(e, "getOrganizationsByParentId");
		}
		
		return null;
	}
}