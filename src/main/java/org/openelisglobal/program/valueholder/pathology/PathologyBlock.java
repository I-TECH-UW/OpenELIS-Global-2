package org.openelisglobal.program.valueholder.pathology;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "pathology_block")
public class PathologyBlock extends BaseObject<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pathology_block_generator")
    @SequenceGenerator(name = "pathology_block_generator", sequenceName = "pathology_block_seq", allocationSize = 1)
    private Integer id;

    private PathologySample pathologySample;

    private Integer slideNumber;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSlideNumber() {
        return slideNumber;
    }

    public void setSlideNumber(Integer slideNumber) {
        this.slideNumber = slideNumber;
    }

    public PathologySample getPathologySample() {
        return pathologySample;
    }

    public void setPathologySample(PathologySample pathologySample) {
        this.pathologySample = pathologySample;
    }

}
