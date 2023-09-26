package com.un4seen.bass;

import java.nio.ByteBuffer;


public class BASSenc {
    public static final int BASS_CONFIG_ENCODE_CAST_BIND = 66322;
    public static final int BASS_CONFIG_ENCODE_CAST_PROXY = 66321;
    public static final int BASS_CONFIG_ENCODE_CAST_TIMEOUT = 66320;
    public static final int BASS_CONFIG_ENCODE_PRIORITY = 66304;
    public static final int BASS_CONFIG_ENCODE_QUEUE = 66305;
    public static final int BASS_CONFIG_ENCODE_SERVER_CERT = 66336;
    public static final int BASS_CONFIG_ENCODE_SERVER_KEY = 66337;
    public static final int BASS_ENCODE_AIFF = 16384;
    public static final int BASS_ENCODE_AUTOFREE = 262144;
    public static final int BASS_ENCODE_BIGEND = 16;
    public static final int BASS_ENCODE_CAST_NOLIMIT = 4096;
    public static final int BASS_ENCODE_COUNT_CAST = 2;
    public static final int BASS_ENCODE_COUNT_IN = 0;
    public static final int BASS_ENCODE_COUNT_IN_FP = 6;
    public static final int BASS_ENCODE_COUNT_OUT = 1;
    public static final int BASS_ENCODE_COUNT_QUEUE = 3;
    public static final int BASS_ENCODE_COUNT_QUEUE_FAIL = 5;
    public static final int BASS_ENCODE_COUNT_QUEUE_LIMIT = 4;
    public static final int BASS_ENCODE_DITHER = 32768;
    public static final int BASS_ENCODE_FP_16BIT = 4;
    public static final int BASS_ENCODE_FP_24BIT = 6;
    public static final int BASS_ENCODE_FP_32BIT = 8;
    public static final int BASS_ENCODE_FP_8BIT = 2;
    public static final int BASS_ENCODE_FP_AUTO = 14;
    public static final int BASS_ENCODE_LIMIT = 8192;
    public static final int BASS_ENCODE_NOHEAD = 1;
    public static final int BASS_ENCODE_NOTIFY_CAST = 2;
    public static final int BASS_ENCODE_NOTIFY_CAST_TIMEOUT = 65536;
    public static final int BASS_ENCODE_NOTIFY_ENCODER = 1;
    public static final int BASS_ENCODE_NOTIFY_FREE = 65538;
    public static final int BASS_ENCODE_NOTIFY_QUEUE_FULL = 65537;
    public static final int BASS_ENCODE_NOTIFY_SERVER = 3;
    public static final int BASS_ENCODE_PAUSE = 32;
    public static final int BASS_ENCODE_PCM = 64;
    public static final int BASS_ENCODE_QUEUE = 512;
    public static final int BASS_ENCODE_RF64 = 128;
    public static final int BASS_ENCODE_SERVER_META = 2;
    public static final int BASS_ENCODE_SERVER_NOHTTP = 1;
    public static final int BASS_ENCODE_SERVER_SSL = 4;
    public static final int BASS_ENCODE_STATS_ICE = 1;
    public static final int BASS_ENCODE_STATS_ICESERV = 2;
    public static final int BASS_ENCODE_STATS_SHOUT = 0;
    public static final String BASS_ENCODE_TYPE_AAC = "audio/aacp";
    public static final String BASS_ENCODE_TYPE_MP3 = "audio/mpeg";
    public static final String BASS_ENCODE_TYPE_OGG = "audio/ogg";
    public static final int BASS_ENCODE_WFEXT = 1024;
    public static final int BASS_ERROR_CAST_DENIED = 2100;
    public static final int BASS_ERROR_SERVER_CERT = 2101;


    public interface ENCODECLIENTPROC {
        boolean ENCODECLIENTPROC(int handle, boolean connect, String client, StringBuffer headers, Object user);
    }


    public interface ENCODENOTIFYPROC {
        void ENCODENOTIFYPROC(int handle, int status, Object user);
    }


    public interface ENCODEPROC {
        void ENCODEPROC(int handle, int channel, ByteBuffer buffer, int length, Object user);
    }


    public interface ENCODEPROCEX {
        void ENCODEPROCEX(int handle, int channel, ByteBuffer buffer, int length, long offset, Object user);
    }


    public interface ENCODERPROC {
        int ENCODERPROC(int handle, int channel, ByteBuffer buffer, int length, int maxout, Object user);
    }

    public static native boolean BASS_Encode_AddChunk(int handle, String id, ByteBuffer buffer, int length);

    public static native String BASS_Encode_CastGetStats(int handle, int type, String pass);

    public static native boolean BASS_Encode_CastInit(int handle, String server, String pass, String content, String name, String url, String genre, String desc, String headers, int bitrate, boolean pub);

    public static native boolean BASS_Encode_CastSendMeta(int handle, int type, ByteBuffer data, int length);

    public static native boolean BASS_Encode_CastSetTitle(int handle, String title, String url);

    public static native int BASS_Encode_GetChannel(int handle);

    public static native long BASS_Encode_GetCount(int handle, int count);

    public static native int BASS_Encode_GetVersion();

    public static native int BASS_Encode_IsActive(int handle);

    public static native int BASS_Encode_ServerInit(int handle, String port, int buffer, int burst, int flags, ENCODECLIENTPROC proc, Object user);

    public static native boolean BASS_Encode_ServerKick(int handle, String client);

    public static native boolean BASS_Encode_SetChannel(int handle, int channel);

    public static native boolean BASS_Encode_SetNotify(int handle, ENCODENOTIFYPROC proc, Object user);

    public static native boolean BASS_Encode_SetPaused(int handle, boolean paused);

    public static native int BASS_Encode_Start(int handle, String cmdline, int flags, ENCODEPROC proc, Object user);

    public static native int BASS_Encode_StartLimit(int handle, String cmdline, int flags, ENCODEPROC proc, Object user, int limit);

    public static native int BASS_Encode_StartUser(int handle, String file, int flags, ENCODERPROC proc, Object user);

    public static native boolean BASS_Encode_Stop(int handle);

    public static native boolean BASS_Encode_StopEx(int handle, boolean queue);

    public static native boolean BASS_Encode_UserOutput(int handle, long offset, ByteBuffer buffer, int length);

    public static native boolean BASS_Encode_Write(int handle, ByteBuffer buffer, int length);

    static {
        System.loadLibrary("bassenc");
    }
}
