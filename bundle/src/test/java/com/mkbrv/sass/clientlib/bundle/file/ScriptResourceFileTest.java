package com.mkbrv.sass.clientlib.bundle.file;

import com.adobe.granite.ui.clientlibs.script.ScriptResource;
import com.adobe.granite.ui.clientlibs.script.ScriptResourceProvider;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Created by mkbrv on 02/01/2017.
 */
public class ScriptResourceFileTest {

    @Mock
    ScriptResourceProvider resourceProvider;


    @BeforeEach
    public void setUp() throws IOException, URISyntaxException {
        MockitoAnnotations.initMocks(this);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                File file = FileUtils.toFile(getClass().getClassLoader()
                        .getResource(invocationOnMock.getArgument(0)));
                if (file == null) {
                    return null;
                }
                Reader reader = new FileReader(file);
                ScriptResource mockedResource = Mockito.mock(ScriptResource.class);
                when(mockedResource.getReader()).thenReturn(reader);
                return mockedResource;
            }
        }).when(resourceProvider).getResource(Mockito.anyString());

    }

    @Test
    public void canLoadFile() throws IOException {
        ScriptResourceFile resourceFile = new ScriptResourceFile(
                new AbsoluteFilePath("scss/compiler/jsass-compiler-test.scss"), resourceProvider);
        assertNotNull(resourceFile.load().getReader());
        assertTrue(IOUtils.toString(resourceFile.getReader()).length() > 0);
    }

    @Test
    public void throwsFileNotFoundException() {
        final String path = "scss/compiler/giberish.scss";
        ScriptResourceFile resourceFile = new ScriptResourceFile(
                new AbsoluteFilePath(path), resourceProvider);
        Throwable exception = assertThrows(FileNotFoundException.class, resourceFile::getReader);
        assertTrue(exception.getMessage().contains(path));
    }


}
