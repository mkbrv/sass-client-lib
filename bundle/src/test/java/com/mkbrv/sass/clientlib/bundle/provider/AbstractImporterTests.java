package com.mkbrv.sass.clientlib.bundle.provider;

import com.mkbrv.sass.clientlib.bundle.JcrIntegrationTest;
import io.bit3.jsass.importer.Import;
import io.bit3.jsass.importer.Importer;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Same tests needed for both importers;
 * Created by mkbrv on 11/01/2017.
 */
public abstract class AbstractImporterTests extends JcrIntegrationTest {


    abstract Importer getImporter();


    @Test
    public void canImportAbsolutePath() throws URISyntaxException {
        Importer importer = getImporter();
        Collection<Import> imported =
                importer.apply("/etc/designs/sass-demo/scss/imported",
                        new Import("/etc/designs/sass-demo/scss/sample",
                                "/etc/designs/sass-demo/scss/sample"));

        assertTrue(imported.size() > 0);
    }

    @Test
    public void canImportRelativePath() throws URISyntaxException {
        Importer importer = getImporter();
        Collection<Import> imported =
                importer.apply("imported.scss",
                        new Import("/etc/designs/sass-demo/scss/sample",
                                "/etc/designs/sass-demo/scss/sample"));

        assertTrue(imported.size() > 0);
    }


}
