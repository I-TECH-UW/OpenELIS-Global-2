package org.openelisglobal.hibernate.search.massindexer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.openelisglobal.common.exception.LIMSRuntimeException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class MassIndexerRestController {

    @PersistenceContext
    EntityManager entityManager;

    @GetMapping("/reindex")
    @Transactional
    public void reindex() {
        SearchSession searchSession = Search.session(entityManager);
        try {
            searchSession.massIndexer().startAndWait();
        } catch (InterruptedException e) {
            throw new LIMSRuntimeException("Error reindexing entities", e);
        }
    }
}
