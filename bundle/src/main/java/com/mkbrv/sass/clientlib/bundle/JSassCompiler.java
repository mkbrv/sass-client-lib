package com.mkbrv.sass.clientlib.bundle;

import com.mkbrv.sass.clientlib.api.SassCompiler;
import io.bit3.jsass.CompilationException;
import io.bit3.jsass.Compiler;
import io.bit3.jsass.Options;
import io.bit3.jsass.Output;
import io.bit3.jsass.OutputStyle;
import io.bit3.jsass.context.StringContext;
import io.bit3.jsass.importer.Importer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

/**
 * Created by mkbrv on 30/12/2016.
 */
public class JSassCompiler implements SassCompiler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JSassCompiler.class);


    private final Options options;
    private final String sass;
    private final String path;


    /**
     * @param sass     code
     * @param path     path to it - absolute would be better;
     * @param importer importer used based on context;
     */
    public JSassCompiler(final String sass,
                         final String path,
                         final Importer importer) {
        options = new Options();
        options.setImporters(Collections.singleton(importer));
        options.setSourceComments(true);
        options.setOutputStyle(OutputStyle.EXPANDED);
        this.sass = sass;
        this.path = path;
    }


    public String compile() {
        final StringContext context;
        String error = "/** Compilation of sass: %s failed due to %s*/";
        try {
            context = new StringContext(sass,
                    new URI(path), null, options);
            final Compiler compilers = new Compiler();
            final Output output = compilers.compile(context);
            return output.getCss();
        } catch (URISyntaxException | CompilationException e) {
            error = String.format(error, path, e.getMessage());
            LOGGER.warn(error, e);
        }

        return error;
    }
}
