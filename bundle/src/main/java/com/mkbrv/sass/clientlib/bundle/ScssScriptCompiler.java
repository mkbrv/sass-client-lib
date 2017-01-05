package com.mkbrv.sass.clientlib.bundle;


import com.adobe.granite.ui.clientlibs.script.CompilerContext;
import com.adobe.granite.ui.clientlibs.script.ScriptCompiler;
import com.adobe.granite.ui.clientlibs.script.ScriptResource;
import com.mkbrv.sass.clientlib.api.SassCompiler;
import com.mkbrv.sass.clientlib.bundle.file.Extensions;
import com.mkbrv.sass.clientlib.bundle.provider.ContextImporter;
import io.bit3.jsass.Options;
import org.apache.commons.io.IOUtils;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Collections;


/**
 * Created by mkbrv on 30/12/2016.
 */
@Component
@Service(ScriptCompiler.class)
public class ScssScriptCompiler implements ScriptCompiler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScssScriptCompiler.class);


    @Override
    public void compile(final Collection<ScriptResource> scriptResources,
                        final Writer writer,
                        final CompilerContext compilerContext) throws IOException {

        scriptResources.forEach(scriptResource -> {
            Options options = new Options();
            options.setImporters(Collections.singleton((s, anImport) -> Collections.emptyList()));

            try {
                final String scssCode = IOUtils.toString(scriptResource.getReader());
                SassCompiler compiler = new JSassCompiler(scssCode, scriptResource.getName(), new ContextImporter(compilerContext));
                writer.write(compiler.compile());
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }

        });

    }

    @Override
    public String getName() {
        return Extensions.SCSS.extension();
    }

    @Override
    public boolean handles(final String extension) {
        return Extensions.SCSS.hasExtension(extension);
    }

    @Override
    public String getMimeType() {
        return Extensions.CSS_MIME_TYPE;
    }

    @Override
    public String getOutputExtension() {
        return Extensions.CSS.extension();
    }


}
