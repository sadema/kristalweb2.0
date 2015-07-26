package nl.kristalsoftware.kristalcms.resourcedata;

import org.jboss.resteasy.annotations.providers.NoJackson;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sjoerdadema on 11-06-15.
 */
@NoJackson
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "customer")
public class CustomerData {

    @XmlAttribute(name="id")
    private String nodename = "";

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }
}
