package characters;

import lombok.Data;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Skills {



    private String name;

    private String effect;
    private Integer uses;


    public Skills() {}

    public Skills(String name, Integer uses, String effect) {
        this.name = name;
        this.uses = uses;
        this.effect = effect;
    }
}