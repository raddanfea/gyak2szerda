package characters;

import java.util.List;
import javax.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name","level","race","age","gender","rpgclass","skills"})
@Data
public class CharacterBase {

    @XmlAttribute
    private String name;

    private Integer level;

    private Race race;

    private Integer age;

    private Gender gender;

    private Rpgclass rpgclass;

    public Rpgclass getRpgclass() {
        return rpgclass;
    }

    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private List<Skills> skills;

    @XmlEnum
    public static enum  Gender {
        Female, Male;
    }

    @XmlEnum
    public static enum  Race {
        Human, Elf, Dwarf, Thiefling;
    }

    @XmlEnum
    public static enum  Rpgclass {
        Warrior, Mage;
    }


}

