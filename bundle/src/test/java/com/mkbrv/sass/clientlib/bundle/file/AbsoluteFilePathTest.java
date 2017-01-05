package com.mkbrv.sass.clientlib.bundle.file;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by mkbrv on 02/01/2017.
 */
public class AbsoluteFilePathTest {


    /**
     * For the moment we only support scss discovery;
     */
    @Test
    public void canAddExtension() {
        final String path = "/etc/designs/sass/test";
        AbsoluteFilePath filePath = new AbsoluteFilePath(path);
        assertTrue(filePath.pathWithExtension().endsWith(Extensions.SCSS.dotPrefixExtension()));
    }

    /**
     * For the moment we only support scss discovery;
     */
    @Test
    public void willNotAddExtensionWherePresent() {
        final String path = "/etc/designs/sass/test.scss";
        AbsoluteFilePath filePath = new AbsoluteFilePath(path);
        assertTrue(filePath.pathWithExtension().endsWith(Extensions.SCSS.dotPrefixExtension()));
    }


    @Test
    public void canFindAbsolutePath() {
        final String path = "/etc/designs/sass/test.scss";
        assertEquals(AbsoluteFilePath.class, FilePath.newInstance(path, path).getClass());
    }


}
