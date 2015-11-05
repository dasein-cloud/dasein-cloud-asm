package org.dasein.cloud.dell.asm.utils;

import org.apache.log4j.Logger;

import javax.annotation.Nonnull;

public class LoggerUtils {
    static private @Nonnull
    String getLastItem(@Nonnull String name) {
        int idx = name.lastIndexOf('.');

        if( idx < 0 ) {
            return name;
        }
        else if( idx == (name.length()-1) ) {
            return "";
        }
        return name.substring(idx + 1);
    }

    static public @Nonnull
    Logger getLogger(@Nonnull Class<?> cls) {
        String pkg = getLastItem(cls.getPackage().getName());

        if( pkg.equals("azure") ) {
            pkg = "";
        }
        else {
            pkg = pkg + ".";
        }
        return Logger.getLogger("dasein.cloud.dell.asm.std." + pkg + getLastItem(cls.getName()));
    }

    static public @Nonnull Logger getWireLogger(@Nonnull Class<?> cls) {
        return Logger.getLogger("dasein.cloud.dell.asm.wire." + getLastItem(cls.getPackage().getName()) + "." + getLastItem(cls.getName()));
    }
}