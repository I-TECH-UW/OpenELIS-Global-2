package org.openelisglobal.common.dao;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.openelisglobal.common.exception.LIMSInvalidConfigurationException;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.openelisglobal.common.provider.validation.AccessionNumberValidatorFactory.AccessionFormat;
import org.openelisglobal.common.util.ConfigurationProperties;
import org.openelisglobal.common.util.ConfigurationProperties.Property;
import org.springframework.stereotype.Component;

@Component
public class AccessionDAOImpl implements AccessionDAO {

    @PersistenceContext
    protected EntityManager entityManager;

    @Override
    public long getNextNumberForAccessionFormat(AccessionFormat accessionFormat)
            throws LIMSInvalidConfigurationException {
        if (accessionFormat == AccessionFormat.MAIN) {
            accessionFormat = getMainFormat();
    }
        switch (accessionFormat) {
//        case MAIN:
//            return getConfiguredMainGenerator();
        case SITE_YEAR:
            return getNextNumberForSiteYearFormatIncrement();
        case ALT_YEAR:
            return getNextNumberForAltYearFormatIncrement();
        case PROGRAM:
        case YEAR_NUM_SIX:
        case YEAR_NUM_DASH:
        case YEAR_NUM_SEVEN:
        case GENERAL:
        default:
            throw new LIMSInvalidConfigurationException(
                    "AccessionDAOImpl: Unable to find configuration for " + accessionFormat);
        }
    }

    @Override
    public long getNextNumberForSiteYearFormatIncrement() {
        try {
            String sql = "UPDATE site_year_accession_number_generator SET id = id + 1 RETURNING id";
            Query query = entityManager.createNativeQuery(sql);
            return ((BigInteger) query.getSingleResult()).longValue();
        } catch (HibernateException e) {
            throw new LIMSRuntimeException(
                    "Error in " + this.getClass().getSimpleName() + " " + "getNextNumberForSiteYearFormat", e);
        }
    }

    @Override
    public long getNextNumberForSiteYearFormatNoIncrement() {
        try {
            String sql = "SELECT id FROM site_year_accession_number_generator";
            Query query = entityManager.createNativeQuery(sql);
            return ((BigInteger) query.getSingleResult()).longValue();
        } catch (HibernateException e) {
            throw new LIMSRuntimeException(
                    "Error in " + this.getClass().getSimpleName() + " " + "getNextNumberForSiteYearFormat", e);
        }
    }

    @Override
    public long setNextValNumberForSiteYearFormat(long value) {
        try {
            String sql = "UPDATE site_year_accession_number_generator SET id = :value RETURNING id";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("value", value);

            return ((BigInteger) query.getSingleResult()).longValue();
        } catch (HibernateException e) {
            throw new LIMSRuntimeException(
                    "Error in " + this.getClass().getSimpleName() + " " + "setNextValNumberForSiteYearFormat", e);
        }
    }

    @Override
    public long getNextNumberForAltYearFormatIncrement() {
        try {
            String sql = "UPDATE alt_accession_number_generator SET id = id + 1 RETURNING id";
            Query query = entityManager.createNativeQuery(sql);

            return ((BigInteger) query.getSingleResult()).longValue();
        } catch (HibernateException e) {
            throw new LIMSRuntimeException(
                    "Error in " + this.getClass().getSimpleName() + " " + "getNextNumberForAltYearFormat", e);
        }
    }

    @Override
    public long getNextNumberForAltYearFormatNoIncrement() {
        try {
            String sql = "SELECT id FROM alt_accession_number_generator";
            Query query = entityManager.createNativeQuery(sql);

            return ((BigInteger) query.getSingleResult()).longValue();
        } catch (HibernateException e) {
            throw new LIMSRuntimeException(
                    "Error in " + this.getClass().getSimpleName() + " " + "getNextNumberForAltYearFormat", e);
        }
    }

    @Override
    public long setNextValNumberForAltYearFormat(long value) {
        try {
            String sql = "UPDATE alt_accession_number_generator SET id = :value RETURNING id";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("value", value);

            return ((BigInteger) query.getSingleResult()).longValue();
        } catch (HibernateException e) {
            throw new LIMSRuntimeException(
                    "Error in " + this.getClass().getSimpleName() + " " + "setNextValNumberForAltYearFormat", e);
        }
    }

    private AccessionFormat getMainFormat() throws LIMSInvalidConfigurationException {

            String accessionFormat = ConfigurationProperties.getInstance()
                    .getPropertyValueUpperCase(Property.AccessionFormat);
            synchronized (this) {
                if (accessionFormat.equals("SITEYEARNUM")) {
                    return AccessionFormat.SITE_YEAR;
                } else if (accessionFormat.equals("PROGRAMNUM")) {
                    return AccessionFormat.PROGRAM;
                } else if (accessionFormat.equals("YEARNUM_SIX")) {
                    return  AccessionFormat.YEAR_NUM_SIX;
                } else if (accessionFormat.equals("YEARNUM_DASH_SEVEN")) {
                   return AccessionFormat.YEAR_NUM_DASH;
                } else if (accessionFormat.equals("YEARNUM_SEVEN")) {
                return AccessionFormat.YEAR_NUM_SEVEN;
            } else {
                    throw new LIMSInvalidConfigurationException(
                        "AccessionDAOImpl: Unable to find format for " + accessionFormat);
                }

            }
        }
}
