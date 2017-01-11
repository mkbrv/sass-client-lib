package com.mkbrv.sass.clientlib.bundle.provider;

import com.adobe.granite.ui.clientlibs.script.CompilerContext;
import com.adobe.granite.ui.clientlibs.script.ScriptResource;
import com.adobe.granite.ui.clientlibs.script.ScriptResourceProvider;
import io.bit3.jsass.importer.Importer;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.mockito.Mockito.when;

/**
 * Tests are in the parent class;
 * Created by mkbrv on 02/01/2017.
 */
public class ContextImporterTest extends AbstractImporterTests {

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

    @Override
    Importer getImporter() {
        return new ContextImporter(context);
    }
}
