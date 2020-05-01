package characters;

import lombok.Data;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Skills {

    @XmlValue
    private String name;

    @XmlAttribute
    private String effect;


    public Skills() {}

    public Skills(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }
}