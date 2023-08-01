package org.openelisglobal.program.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.openelisglobal.common.daoimpl.BaseDAOImpl;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistrySample;
import org.openelisglobal.program.valueholder.immunohistochemistry.ImmunohistochemistrySample.ImmunohistochemistryStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ImmunohistochemistrySampleDAOImpl extends BaseDAOImpl<ImmunohistochemistrySample, Integer>
        implements ImmunohistochemistrySampleDAO {
    ImmunohistochemistrySampleDAOImpl() {
        super(ImmunohistochemistrySample.class);
    }

    @Override
    public List<ImmunohistochemistrySample> getWithStatus(List<ImmunohistochemistryStatus> statuses) {
        String sql = "from ImmunohistochemistrySample is where is.status in (:statuses)";
        Query<ImmunohistochemistrySample> query = entityManager.unwrap(Session.class).createQuery(sql,
                ImmunohistochemistrySample.class);
        query.setParameterList("statuses", statuses.stream().map(e -> e.toString()).collect(Collectors.toList()));
        List<ImmunohistochemistrySample> list = query.list();

        return list;
    }

    @Override
    public Long getCountWithStatus(List<ImmunohistochemistryStatus> statuses) {
        String sql = "select count(*) from ImmunohistochemistrySample is where is.status in (:statuses)";
        Query<Long> query = entityManager.unwrap(Session.class).createQuery(sql, Long.class);
        query.setParameterList("statuses", statuses.stream().map(e -> e.toString()).collect(Collectors.toList()));
        Long count = query.uniqueResult();

        return count;
    }
}
