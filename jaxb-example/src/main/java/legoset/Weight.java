package legoset;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

import lombok.Data;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Weight {
    @XmlValue
    private double value;

    @XmlAttribute
    private String unit;

    public Weight() {}

    public Weight(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }
}
