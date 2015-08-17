package nl.kristalsoftware.kristalcms.resourcedata;

import org.jboss.resteasy.annotations.providers.NoJackson;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sjoerdadema on 10-07-15.
 */
@NoJackson
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="pages")
public class PageCollectionData {

    private String nodename = "pages";

    @XmlElement(name="page")
    private Collection<PageData> pageCollection = new ArrayList<PageData>();

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Collection<PageData> getPageCollection() {
        return pageCollection;
    }

    public void setPageCollection(Collection<PageData> pageCollection) {
        this.pageCollection = pageCollection;
    }
}
