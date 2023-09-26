package com.voice.changer.speechshift;

import java.io.File;

public class FilenameUtils {

    private static final char otherSeparator;

    static {
        if (isSystemWindows()) {
            otherSeparator = '/';
        } else {
            otherSeparator = '\\';
        }
    }

    public static String removeExtension(String strExtension) {
        if (strExtension == null) {
            return null;
        }
        failIfNullByte(strExtension);
        int indexOfExtension = extensionIndex(strExtension);
        if (indexOfExtension == -1) {
            return strExtension;
        }
        return strExtension.substring(0, indexOfExtension);
    }

    public static int extensionIndex(String strIndex) throws IllegalArgumentException {
        if (strIndex == null) {
            return -1;
        }
        if (!isSystemWindows() || strIndex.indexOf(58, getAdsCriticalOffset(strIndex)) == -1) {
            int lastIndexOf = strIndex.lastIndexOf(46);
            if (lastSeparatorIndex(strIndex) > lastIndexOf) {
                return -1;
            }
            return lastIndexOf;
        }
        throw new IllegalArgumentException("NTFS ADS separator (':') in file name is forbidden.");
    }

    static boolean isSystemWindows() {
        return File.separatorChar == '\\';
    }

    public static int lastSeparatorIndex(String strIndex) {
        if (strIndex == null) {
            return -1;
        }
        return Math.max(strIndex.lastIndexOf(47), strIndex.lastIndexOf(92));
    }

    private static int getAdsCriticalOffset(String str) {
        int lastIndexOf = str.lastIndexOf(File.separatorChar);
        int lastIndexOf2 = str.lastIndexOf(otherSeparator);
        if (lastIndexOf == -1) {
            if (lastIndexOf2 == -1) {
                return 0;
            }
            return lastIndexOf2 + 1;
        } else if (lastIndexOf2 == -1) {
            return lastIndexOf + 1;
        } else {
            return Math.max(lastIndexOf, lastIndexOf2) + 1;
        }
    }

    private static void failIfNullByte(String strByte) {
        int length = strByte.length();
        int i = 0;
        while (i < length) {
            if (strByte.charAt(i) != 0) {
                i++;
            } else {
                throw new IllegalArgumentException("Null byte present in file/path name. There are no known legitimate use cases for such data, but several injection attacks may use it");
            }
        }
    }

    public static String getExtension(String str) throws IllegalArgumentException {
        if (str == null) {
            return null;
        }
        int indexOfExtension = extensionIndex(str);
        if (indexOfExtension == -1) {
            return "";
        }
        return str.substring(indexOfExtension + 1);
    }

    public static String getBaseName(String str) {
        return removeExtension(getName(str));
    }

    public static String getName(String str) {
        if (str == null) {
            return null;
        }
        failIfNullByte(str);
        return str.substring(lastSeparatorIndex(str) + 1);
    }

}
