package com.mkbrv.sass.clientlib.bundle.provider;

import com.adobe.granite.ui.clientlibs.script.CompilerContext;
import com.mkbrv.sass.clientlib.bundle.file.FilePath;
import com.mkbrv.sass.clientlib.bundle.file.SassFile;
import com.mkbrv.sass.clientlib.bundle.file.ScriptResourceFile;
import io.bit3.jsass.importer.Import;
import io.bit3.jsass.importer.Importer;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by mkbrv on 02/01/2017.
 */
public class ContextImporter implements Importer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContextImporter.class);

    private final CompilerContext compilerContext;

    public ContextImporter(final CompilerContext compilerContext) {
        this.compilerContext = compilerContext;
    }


    @Override
    public Collection<Import> apply(String url, Import origin) {
        try {
            SassFile file = new ScriptResourceFile(
                    FilePath.newInstance(url, origin.getAbsoluteUri().getPath()),
                    compilerContext.getResourceProvider());
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
