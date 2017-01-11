package com.mkbrv.sass.clientlib.bundle;

import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.junit.jupiter.api.BeforeAll;

/**
 * Different AEM version will need updated versions;
 * Created by mkbrv on 02/01/2017.
 */
public class JcrIntegrationTest {


    public static SlingContext slingContext;


    @BeforeAll
    public static void setUp() {
        if (slingContext != null) {
            return;
        }
        slingContext = new SlingContext(ResourceResolverType.JCR_MOCK);
        slingContext.setUp();

        slingContext.load().json(JcrIntegrationTest.class.getClassLoader().getResourceAsStream("etc/designs/sass-demo.json"),
                "/etc/designs/sass-demo");


        slingContext.load().binaryFile(JcrIntegrationTest.class.getClassLoader().getResourceAsStream("etc/designs/scss/sample.scss"),
                "/etc/designs/sass-demo/scss/sample.scss");
        slingContext.load().binaryFile(JcrIntegrationTest.class.getClassLoader().getResourceAsStream("etc/designs/scss/imported.scss"),
                "/etc/designs/sass-demo/scss/imported.scss");
        slingContext.load().binaryFile(JcrIntegrationTest.class.getClassLoader().getResourceAsStream("etc/designs/scss/absolute-import.scss"),
                "/etc/designs/sass-demo/scss/absolute-import.scss");
        slingContext.load().binaryFile(JcrIntegrationTest.class.getClassLoader().getResourceAsStream("etc/designs/scss/mixins/_clearfix.scss"),
                "/etc/designs/sass-demo/scss/mixins/_clearfix.scss");
    }


}
