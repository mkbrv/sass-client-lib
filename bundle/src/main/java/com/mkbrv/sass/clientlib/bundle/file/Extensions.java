package com.mkbrv.sass.clientlib.bundle.file;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by mkbrv on 02/01/2017.
 */
public class Extensions {

    public static final String CSS_MIME_TYPE = "text/css";

    public static final Extension CSS = new FileExtension("css");
    public static final Extension SCSS = new FileExtension("scss");
    public static final Extension SASS = new FileExtension("sass");

    public interface Extension {

        default String dotPrefixExtension() {
            return "." + extension();
        }

        /**
         * returns extension
         *
         * @return
         */
        String extension();

        /**
         * checks if a path has extension
         *
         * @param path to be checked;
         * @return true/false
         */
        default boolean hasExtension(final String path) {
            return StringUtils.isNotEmpty(path)
                    && (path.endsWith(extension()) || path.endsWith(dotPrefixExtension()));
        }
    }

    static class FileExtension implements Extension {

        private final String extension;

        /**
         * @param extension
         */
        FileExtension(String extension) {
            this.extension = extension;
        }

        @Override
        public String extension() {
            return this.extension;
        }

    }


}
