package com.mkbrv.sass.clientlib.bundle.file;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by mkbrv on 02/01/2017.
 */
public class RelativeFilePathTest {

    @Test
    public void canFindRelativePath() {
        final String path = "etc/designs/sass/test.scss";
        assertEquals(RelativeFilePath.class, FilePath.newInstance(path, path).getClass());
    }

    @Test
    public void canFindRelativePathForRoot() {
        final String path = "sample";

        assertEquals("/", new RelativeFilePath(path, "test").findOriginLevel());
        assertEquals("/", new RelativeFilePath(path, "").findOriginLevel());
        assertEquals("/", new RelativeFilePath(path, "/").findOriginLevel());
        assertEquals("/", new RelativeFilePath(path, "/test").findOriginLevel());
    }

    @Test
    public void canBuildLevelPath() {
        final String path = "imported";
        final String origin = "/etc/designs/sass/origin";

        assertEquals("/etc/designs/sass/imported", FilePath.newInstance(path, origin).getPath());
    }


    @Test
    public void canSetExtension() {
        final String path = "imported";
        final String origin = "/etc/designs/sass/origin";

        assertEquals("/etc/designs/sass/imported.scss", FilePath.newInstance(path, origin).pathWithExtension());
    }


}
