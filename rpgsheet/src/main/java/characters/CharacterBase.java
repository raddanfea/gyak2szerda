package characters;

import java.util.List;
import javax.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name","level","age","gender","rpgclass","skills"})
@Data
public class CharacterBase {

    @XmlAttribute
    private String name;

    private Integer level;

    private Integer age;

    private Gender gender;

    private Rpgclass rpgclass;

    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private List<Skills> skills;

    @XmlEnum
    public static enum  Gender {
        FEMALE, MALE;
    }

    @XmlEnum
    public static enum  Rpgclass {
        Warrior, Mage;
    }


}

