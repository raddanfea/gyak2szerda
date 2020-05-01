package characters;

import java.util.List;
import javax.xml.bind.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name","level","age","gender","rpgclass","profession","skills"})
@Data
public class CharacterBase {

    @XmlAttribute
    private String name;

    private Integer level;

    private String rpgclass;

    private Integer age;

    private String gender;

    private String profession;

    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private List<Skills> skills;


}

