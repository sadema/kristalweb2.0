package nl.kristalsoftware.beanreflection.data;

import nl.kristalsoftware.beanreflection.annotation.TestJCRProperty;
import nl.kristalsoftware.beanreflection.annotation.TestParent;

/**
 * Created by sjoerdadema on 15-06-15.
 */
public class TestProductData {

    @TestParent
    private String parent = "myParent";

    @TestJCRProperty
    private Integer productid;

    @TestJCRProperty
    private String description;

    public TestProductData(Integer productid, String description) {
        this.productid = productid;
        this.description = description;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
