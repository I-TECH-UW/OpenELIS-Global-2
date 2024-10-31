package org.openelisglobal.program.valueholder.pathology;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.openelisglobal.common.valueholder.BaseObject;

@Entity
@Table(name = "pathology_report")
public class PathologyReport extends BaseObject<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pathology_report_generator")
    @SequenceGenerator(name = "pathology_report_generator", sequenceName = "pathology_report_seq", allocationSize = 1)
    private Integer id;

    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] image;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "report_type")
    @Enumerated(EnumType.STRING)
    @NotNull
    private PathologyReportType reportType;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
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

    public PathologyReportType getReportType() {
        return reportType;
    }

    public void setReportType(PathologyReportType reportType) {
        this.reportType = reportType;
    }

    public enum PathologyReportType {
        PATHOLOGY("Pathology Report");

        private String display;

        PathologyReportType(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return display;
        }
    }
}
