package sk.kosickaakademia.corporation.entity;




import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "users")
public class XmlUser {     //vysvetlenie na stranke: https://stackoverflow.com/questions/1603404/using-jaxb-to-unmarshal-marshal-a-liststring
    private List<User> list;

    public List<User> getList() {
        return list;
    }

    @XmlElement(name = "user")
    public void setList(List<User> list) {
        this.list = list;
    }
}
