package com.mkbrv.sass.clientlib.bundle.provider;

import io.bit3.jsass.importer.Importer;

/**
 * Tests are in the parent class;
 * Created by mkbrv on 02/01/2017.
 */
public class ResourceResolverImporterTest extends AbstractImporterTests {


    @Override
    Importer getImporter() {
        return new ResourceResolverImporter(slingContext.resourceResolver());
    }
}
