package com.barryirvine.flickr.network;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class IOUtils {

    public static String convertUTF8StreamToString(final InputStream is) {
        try {
            final Scanner s = new Scanner(is, "UTF-8").useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        } finally {
            closeQuietly(is);
        }
    }

    private static void closeQuietly(final Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IncompatibleClassChangeError | IOException ignored) {

        }
    }

}
