package org.openelisglobal.common.valueholder;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.openelisglobal.common.provider.validation.AccessionNumberValidatorFactory.AccessionFormat;

@Entity(name = "accession_number_info")
public class AccessionNumberInfo {

    @EmbeddedId
    private AccessionIdentity accessionIdentity;

    @Column(name = "cur_val")
    private Long curVal;

    public AccessionIdentity getAccessionIdentity() {
        return accessionIdentity;
    }

    public void setAccessionIdentity(AccessionIdentity accessionIdentity) {
        this.accessionIdentity = accessionIdentity;
    }

    public Long getCurVal() {
        return curVal;
    }

    public void setCurVal(Long curVal) {
        this.curVal = curVal;
    }

    @Embeddable
    public static class AccessionIdentity implements Serializable {
        private static final long serialVersionUID = -2283769400174514682L;

        @NotNull
        @Size(max = 255)
        private String prefix;

        @NotNull
        @Enumerated(EnumType.STRING)
        private AccessionFormat type;

        AccessionIdentity() {
        }

        public AccessionIdentity(String prefix, AccessionFormat type) {
            this.setPrefix(prefix);
            this.setType(type);
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public AccessionFormat getType() {
            return type;
        }

        public void setType(AccessionFormat type) {
            this.type = type;
        }

        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            AccessionIdentity that = (AccessionIdentity) o;

            return Objects.equals(this.prefix, that.prefix) && Objects.equals(this.type, that.type);
        }

        public int hashCode() {
            return Objects.hash(prefix, type);
        }
    }
}
