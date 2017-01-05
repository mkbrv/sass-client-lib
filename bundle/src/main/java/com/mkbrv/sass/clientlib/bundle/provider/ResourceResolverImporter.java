package com.mkbrv.sass.clientlib.bundle.provider;

import io.bit3.jsass.importer.Import;
import io.bit3.jsass.importer.Importer;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by mkbrv on 02/01/2017.
 */
public class ResourceResolverImporter implements Importer {

    final ResourceResolver resolver;

    public ResourceResolverImporter(final ResourceResolver resolver) {
        this.resolver = resolver;
    }


    @Override
    public Collection<Import> apply(String url, Import origin) {


        return Collections.emptyList();
    }
}
