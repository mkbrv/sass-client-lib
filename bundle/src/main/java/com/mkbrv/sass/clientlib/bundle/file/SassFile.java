package com.mkbrv.sass.clientlib.bundle.file;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by mkbrv on 02/01/2017.
 */
public interface SassFile {


    /**
     * Path in repository;
     *
     * @return path of the file;
     */
    FilePath getPath();

    /**
     * The nt:file transformed into a reader;
     *
     * @return Reader of the file;
     * @throws IOException
     */
    Reader getReader() throws IOException;

    /**
     * Loads in memory the file;
     *
     * @return current object;
     * @throws IOException
     */
    SassFile load() throws IOException;


}
