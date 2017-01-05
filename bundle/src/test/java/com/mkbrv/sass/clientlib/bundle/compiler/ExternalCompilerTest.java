package com.mkbrv.sass.clientlib.bundle.compiler;

import io.bit3.jsass.CompilationException;
import io.bit3.jsass.Compiler;
import io.bit3.jsass.Options;
import io.bit3.jsass.Output;
import io.bit3.jsass.context.StringContext;
import io.bit3.jsass.importer.Import;
import io.bit3.jsass.importer.Importer;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Discovery of the sass lib test;
 * Created by mkbrv on 30/12/2016.
 */
public class ExternalCompilerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalCompilerTest.class);

    protected Options options;


    @BeforeEach
    public void setUp() {
        this.options = new Options();
        options.setImporters(Collections.singleton(new Importer() {
            /**
             * Imports an SCSS file based on the URL from the maven resources folder;
             *
             * @param url      of file to be imported
             * @param previous where the import is called from
             * @return current import;
             */
            @Override
            public Collection<Import> apply(String url, Import previous) {
                URI resource = null;
                try {
                    if (url.startsWith("/")) {
                        resource = getClass().getClassLoader().getResource(url).toURI();
                    } else {
                        final String absoluteUri = previous.getAbsoluteUri().getPath();
                        final String relativeUrl = absoluteUri.substring(0, absoluteUri.lastIndexOf("/") + 1) + url + ".scss";
                        resource = getClass().getClassLoader().getResource(relativeUrl).toURI();
                    }
                    Import current = new Import(url, url, IOUtils.toString(resource));
                    return Collections.singleton(current);

                } catch (NullPointerException | URISyntaxException | IOException e) {
                    LOGGER.error(e.getMessage(), e);
                }
                return Collections.emptyList();
            }
        }));
    }


    @Test
    @DisplayName("is able to import a multiple scss files")
    public void canCompile() throws IOException, CompilationException, URISyntaxException {
        final String initialFile = "scss/compiler/jsass-compiler-test.scss";
        final String scssCode = IOUtils.toString(getClass().getClassLoader().getResourceAsStream(initialFile),
                StandardCharsets.UTF_8);

        final StringContext context = new StringContext(scssCode, new URI(initialFile), null, options);

        final Compiler compiler = new Compiler();
        final Output output = compiler.compile(context);

        assertTrue(output.getCss().contains("Netcentricimported"));
        LOGGER.info(output.getCss());
    }

}