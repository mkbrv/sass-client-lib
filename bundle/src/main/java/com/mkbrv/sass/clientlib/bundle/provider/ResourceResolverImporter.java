package com.mkbrv.sass.clientlib.bundle.provider;

import com.mkbrv.sass.clientlib.bundle.file.FilePath;
import com.mkbrv.sass.clientlib.bundle.file.JCRResourceFile;
import com.mkbrv.sass.clientlib.bundle.file.SassFile;
import io.bit3.jsass.importer.Import;
import io.bit3.jsass.importer.Importer;
import org.apache.commons.io.IOUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by mkbrv on 02/01/2017.
 */
public class ResourceResolverImporter implements Importer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceResolverImporter.class);
    final ResourceResolver resolver;


    public ResourceResolverImporter(final ResourceResolver resolver) {
        this.resolver = resolver;
    }


    @Override
    public Collection<Import> apply(final String url,
                                    final Import origin) {

        try {
            SassFile file = new JCRResourceFile(
                    FilePath.newInstance(url, origin.getAbsoluteUri().getPath()),
                    resolver);
            String contents =
                    IOUtils.toString(file.getReader());
            Import importedFile = new Import(url, url, contents);
            return Collections.singleton(importedFile);
        } catch (IOException | URISyntaxException e) {
            LOGGER.warn("Unable to load sass file {} due to {}", url, e.getMessage(), e);
        }

        return Collections.emptyList();
    }
}
