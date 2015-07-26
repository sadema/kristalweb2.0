package nl.kristalsoftware.beanreflection.main;

import nl.kristalsoftware.beanreflection.annotation.TestParent;
import nl.kristalsoftware.beanreflection.data.TestProductData;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by sjoerdadema on 15-06-15.
 */
@RunWith(Arquillian.class)
public class BeanReflectionUtilsImplTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BeanReflectionUtilsImpl.class)
                .addClass(LogProducer.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private BeanManager beanManager;

    @Inject
    private Logger log;

    @Inject
    private BeanReflectionUtils utils;

    private TestProductData productData;

    @Before
    public void createProductData() {
        productData = new TestProductData(123, "Wyse T10D");
    }

    @After
    public void removeProductData() {
        productData = null;
    }

    @Test
    public void testCDIBootstrap() throws Exception {
        log.info("Start testCDIBootstrap");
        assertNotNull("beanManager not injected", beanManager);
        assertFalse("no beans from BeanManager class", beanManager.getBeans(BeanManager.class).isEmpty());
    }

    @Test
    public void testGetProductidField() throws Exception {
        Field f = utils.getField(TestProductData.class, "productid");
        assertNotNull("field is null", f);
        assertThat("name is not productid", f.getName(), is("productid"));
    }

    @Test(expected = NoSuchFieldException.class)
    public void testThrowNoSuchFieldException() throws Exception {
        Field f = utils.getField(TestProductData.class, "NonExistentFieldname");
    }

    @Test
    public void testGetAllNonStaticFields() throws Exception {
        List<Field> fldList = utils.getNonStaticFields(TestProductData.class);
        assertNotNull("field is null", fldList);
        assertFalse("empty list", fldList.isEmpty());
    }

    @Test(expected = IllegalAccessException.class)
    public void testGetValueOfProductidField() throws Exception {
        Field f = utils.getField(TestProductData.class, "productid");
        Integer val = utils.getPublicFieldValue(productData, f, Integer.class);
        assertNotNull("value of field is null", val);
        assertThat("value of field productid is not 123", val, is(123));
    }

    @Test
    public void testGetValueOfProductidFieldWithGetter() throws Exception {
        Field f = utils.getField(TestProductData.class, "productid");
        Integer val = utils.getFieldValueWithGetter(productData, f, Integer.class);
        assertNotNull("value of field is null", val);
        assertThat("value of field productid is not 123", val, is(123));
    }

    @Test
    public void testGetValueOfDescriptionFieldWithGetter() throws Exception {
        Field f = utils.getField(TestProductData.class, "description");
        String val = utils.getFieldValueWithGetter(productData, f, String.class);
        assertNotNull("value of field is null", val);
        assertThat("value of field description is not Wyse T10D", val, is("Wyse T10D"));
    }

    @Test
    public void testGetFieldAnnotatedWithTestParent() throws Exception {
        Field f = utils.getAnnotatedField(TestProductData.class, TestParent.class);
        assertNotNull("field is null", f);
        assertThat("name is not parent", f.getName(), is("parent"));
    }

    @Test
    public void testGetTwoFieldsAnnotatedWithTestJCRProperty() throws Exception {
        List<Field> fieldList = utils.getAnnotatedFields(TestProductData.class, TestParent.class);
        assertNotNull("fieldList is null", fieldList);
        assertFalse("fieldList is empty", fieldList.isEmpty());
    }

    @Test
    public void testSetValueOfDescriptionFieldWithSetter() throws Exception {

    }
}