package characters;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlEnum;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Stats {

    @Getter @Setter private String name;
    @Getter @Setter private Integer value;

    public Stats() {}

    public Stats(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @XmlEnum
    public static enum  Stattypes {
        Strength, Dexterity, Constitution, Intelligence, Wisdom, Charisma;
    }
}
