package com.mkbrv.sass.clientlib.bundle;

import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.context.SlingContextImpl;

/**
 * Sling context without the junit rule;
 * Created by mkbrv on 02/01/2017.
 */
public class SlingContext extends SlingContextImpl {


    public SlingContext(final ResourceResolverType resourceResolverType) {
        this.setResourceResolverType(resourceResolverType);
    }

    public void setUp() {
        super.setUp();
    }

}
