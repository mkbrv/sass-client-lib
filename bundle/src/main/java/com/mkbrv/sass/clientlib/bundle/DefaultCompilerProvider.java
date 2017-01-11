package com.mkbrv.sass.clientlib.bundle;

import com.mkbrv.sass.clientlib.api.SassCompiler;
import com.mkbrv.sass.clientlib.api.SassCompilerProvider;
import com.mkbrv.sass.clientlib.bundle.provider.ResourceResolverImporter;
import org.apache.commons.io.IOUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by mkbrv on 30/12/2016.
 */
@Service
@Component(immediate = true, metatype = false)
public class DefaultCompilerProvider implements SassCompilerProvider {


    @Override
    public SassCompiler getCompiler(final SassCompilerBuilder builder) {
        try {
            return new JSassCompiler(
                    IOUtils.toString(builder.getResource().adaptTo(InputStream.class)),
                    builder.getResource().getPath(),
                    new ResourceResolverImporter(builder.getResolver()));
        } catch (IOException e) {
            return null;
        }
    }

}
