package com.mkbrv.sass.clientlib.bundle.file;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by mkbrv on 02/01/2017.
 */
public class ExtensionsTest {


    @Test
    public void hasDotInFront() {
        assertTrue(Extensions.CSS.dotPrefixExtension().startsWith("."));
        assertTrue(Extensions.SASS.dotPrefixExtension().startsWith("."));
        assertTrue(Extensions.SCSS.dotPrefixExtension().startsWith("."));
    }

    @Test
    public void isRecognizedWithPath() {
        assertTrue(Extensions.CSS.hasExtension("test.css"));
        assertTrue(Extensions.SASS.hasExtension("test.sass"));
        assertTrue(Extensions.SCSS.hasExtension("test.scss"));
    }

    @Test
    public void isRecognizedWithoutPath() {
        assertTrue(Extensions.CSS.hasExtension("css"));
        assertTrue(Extensions.SASS.hasExtension("sass"));
        assertTrue(Extensions.SCSS.hasExtension("scss"));
    }


}
