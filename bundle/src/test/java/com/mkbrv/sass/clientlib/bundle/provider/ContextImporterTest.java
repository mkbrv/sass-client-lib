package com.mkbrv.sass.clientlib.bundle.provider;

import com.adobe.granite.ui.clientlibs.script.CompilerContext;
import com.adobe.granite.ui.clientlibs.script.ScriptResource;
import com.adobe.granite.ui.clientlibs.script.ScriptResourceProvider;
import com.mkbrv.sass.clientlib.bundle.JcrIntegrationTest;
import io.bit3.jsass.importer.Import;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by mkbrv on 02/01/2017.
 */
public class ContextImporterTest extends JcrIntegrationTest {

    @Mock
    CompilerContext context;

    @Mock
    ScriptResourceProvider resourceProvider;

    @BeforeEach
    public void setUpMocks() throws IOException {
        MockitoAnnotations.initMocks(this);
        when(context.getResourceProvider()).thenReturn(resourceProvider);

        /**
         * Mocks the ScriptResourceProvider so it can deliver script resources from the fake JCR based on path;
         */
        Mockito.doAnswer(invocationOnMock -> {
            final String path = invocationOnMock.getArgument(0);
            Resource resource = slingContext.resourceResolver().getResource(path);
            if (resource == null) {
                throw new IOException("Resource not found");
            }
            ScriptResource scriptResource = Mockito.mock(ScriptResource.class);
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.adaptTo(InputStream.class)));
            when(scriptResource.getReader()).thenReturn(reader);
            when(scriptResource.getName()).thenReturn(resource.getPath());
            return scriptResource;
        }).when(resourceProvider).getResource(Mockito.anyString());
    }

    @Test
    public void canImportAbsolutePath() throws URISyntaxException {
        ContextImporter importer = new ContextImporter(context);
        Collection<Import> imported =
                importer.apply("/etc/designs/sass-demo/scss/imported",
                        new Import("/etc/designs/sass-demo/scss/sample",
                                "/etc/designs/sass-demo/scss/sample"));

        assertTrue(imported.size() > 0);
    }
}
