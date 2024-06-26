package org.openelisglobal.program.valueholder.cytology;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "cytology_slide")
public class CytologySlide extends BaseObject<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cytology_slide_generator")
    @SequenceGenerator(name = "cytology_slide_generator", sequenceName = "cytology_slide_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "slide_number")
    private Integer slideNumber;

    @Column(name = "image")
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "location")
    private String location;

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

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
