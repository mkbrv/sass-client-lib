package com.mkbrv.sass.clientlib.bundle.file;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Wrapper for a JCR Resource;
 * Created by mkbrv on 11/01/2017.
 */
public class JCRResourceFile implements SassFile {


    private final FilePath path;
    private final ResourceResolver resolver;

    private Resource resource;

    /**
     * @param path
     * @param resourceResolver
     */
    public JCRResourceFile(final FilePath path, final ResourceResolver resourceResolver) {
        this.path = path;
        this.resolver = resourceResolver;
    }


    @Override
    public FilePath getPath() {
        return path;
    }

    @Override
    public Reader getReader() throws IOException {
        if (this.resource == null) {
            return load().getReader();
        }

        return new BufferedReader(new InputStreamReader(resource.adaptTo(InputStream.class)));
    }

    private int attemptedLoads = 0;

    @Override
    public SassFile load() throws IOException {
        final Integer MAX_ATTEMPTS = 3;
        if (this.resource == null && attemptedLoads < MAX_ATTEMPTS) {
            attemptedLoads++;
            this.resource = resolver.getResource(this.path.pathWithExtension());
        } else {
            throw new FileNotFoundException("Failed to load the script resource: " + this.path.pathWithExtension());
        }
        return this;
    }
}
