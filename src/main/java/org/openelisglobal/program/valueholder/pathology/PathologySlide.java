package org.openelisglobal.program.valueholder.pathology;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "pathology_slide")
public class PathologySlide extends BaseObject<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pathology_slide_generator")
    @SequenceGenerator(name = "pathology_slide_generator", sequenceName = "pathology_slide_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "slide_number")
    private Integer slideNumber;
    @Lob
    private byte[] image;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
