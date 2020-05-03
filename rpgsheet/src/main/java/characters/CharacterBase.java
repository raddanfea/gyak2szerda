package characters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name","level","race","age","gender","rpgclass","skills","items","stats"})
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

    public List<Skills> getSkillsList(){
        return skills;
    }


    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private Set<String> items;
    public Set<String> getItems() {
        return items;
    }

    @XmlElementWrapper(name = "stats")
    @XmlElement(name = "stat")
    private ArrayList<Stats> stats;
    public ArrayList<Stats> getStats() {
        return stats;
    }



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


    public String getName() { return name;  }

    public Integer getLevel() { return level; }

    public Race getRace() { return race;   }

    public Integer getAge() { return age; }

    public Gender getGender() { return gender;   }

}
