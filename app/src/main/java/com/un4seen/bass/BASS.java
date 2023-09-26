package com.un4seen.bass;

import android.content.res.AssetManager;
import android.os.ParcelFileDescriptor;

import java.nio.ByteBuffer;


public class BASS {
    public static final int BASSVERSION = 516;
    public static final String BASSVERSIONTEXT = "2.4";
    public static final int BASS_3DALG_DEFAULT = 0;
    public static final int BASS_3DALG_FULL = 2;
    public static final int BASS_3DALG_LIGHT = 3;
    public static final int BASS_3DALG_OFF = 1;
    public static final int BASS_3DMODE_NORMAL = 0;
    public static final int BASS_3DMODE_OFF = 2;
    public static final int BASS_3DMODE_RELATIVE = 1;
    public static final int BASS_ACTIVE_PAUSED = 3;
    public static final int BASS_ACTIVE_PAUSED_DEVICE = 4;
    public static final int BASS_ACTIVE_PLAYING = 1;
    public static final int BASS_ACTIVE_STALLED = 2;
    public static final int BASS_ACTIVE_STOPPED = 0;
    public static final int BASS_ASYNCFILE = 1073741824;
    public static final int BASS_ATTRIB_BITRATE = 12;
    public static final int BASS_ATTRIB_BUFFER = 13;
    public static final int BASS_ATTRIB_CPU = 7;
    public static final int BASS_ATTRIB_EAXMIX = 4;
    public static final int BASS_ATTRIB_FREQ = 1;
    public static final int BASS_ATTRIB_GRANULE = 14;
    public static final int BASS_ATTRIB_MUSIC_AMPLIFY = 256;
    public static final int BASS_ATTRIB_MUSIC_BPM = 259;
    public static final int BASS_ATTRIB_MUSIC_PANSEP = 257;
    public static final int BASS_ATTRIB_MUSIC_PSCALER = 258;
    public static final int BASS_ATTRIB_MUSIC_SPEED = 260;
    public static final int BASS_ATTRIB_MUSIC_VOL_CHAN = 512;
    public static final int BASS_ATTRIB_MUSIC_VOL_GLOBAL = 261;
    public static final int BASS_ATTRIB_MUSIC_VOL_INST = 768;
    public static final int BASS_ATTRIB_NET_RESUME = 9;
    public static final int BASS_ATTRIB_NOBUFFER = 5;
    public static final int BASS_ATTRIB_NORAMP = 11;
    public static final int BASS_ATTRIB_PAN = 3;
    public static final int BASS_ATTRIB_SCANINFO = 10;
    public static final int BASS_ATTRIB_SRC = 8;
    public static final int BASS_ATTRIB_VBR = 6;
    public static final int BASS_ATTRIB_VOL = 2;
    public static final int BASS_CONFIG_3DALGORITHM = 10;
    public static final int BASS_CONFIG_AM_DISABLE = 58;
    public static final int BASS_CONFIG_ANDROID_AAUDIO = 67;
    public static final int BASS_CONFIG_ANDROID_SESSIONID = 62;
    public static final int BASS_CONFIG_ASYNCFILE_BUFFER = 45;
    public static final int BASS_CONFIG_BUFFER = 0;
    public static final int BASS_CONFIG_CURVE_PAN = 8;
    public static final int BASS_CONFIG_CURVE_VOL = 7;
    public static final int BASS_CONFIG_DEV_BUFFER = 27;
    public static final int BASS_CONFIG_DEV_DEFAULT = 36;
    public static final int BASS_CONFIG_DEV_NONSTOP = 50;
    public static final int BASS_CONFIG_DEV_PERIOD = 53;
    public static final int BASS_CONFIG_FLOAT = 54;
    public static final int BASS_CONFIG_FLOATDSP = 9;
    public static final int BASS_CONFIG_GVOL_MUSIC = 6;
    public static final int BASS_CONFIG_GVOL_SAMPLE = 4;
    public static final int BASS_CONFIG_GVOL_STREAM = 5;
    public static final int BASS_CONFIG_HANDLES = 41;
    public static final int BASS_CONFIG_LIBSSL = 64;
    public static final int BASS_CONFIG_MUSIC_VIRTUAL = 22;
    public static final int BASS_CONFIG_NET_AGENT = 16;
    public static final int BASS_CONFIG_NET_BUFFER = 12;
    public static final int BASS_CONFIG_NET_PASSIVE = 18;
    public static final int BASS_CONFIG_NET_PLAYLIST = 21;
    public static final int BASS_CONFIG_NET_PLAYLIST_DEPTH = 59;
    public static final int BASS_CONFIG_NET_PREBUF = 15;
    public static final int BASS_CONFIG_NET_PREBUF_WAIT = 60;
    public static final int BASS_CONFIG_NET_PROXY = 17;
    public static final int BASS_CONFIG_NET_READTIMEOUT = 37;
    public static final int BASS_CONFIG_NET_SEEK = 56;
    public static final int BASS_CONFIG_NET_TIMEOUT = 11;
    public static final int BASS_CONFIG_OGG_PRESCAN = 47;
    public static final int BASS_CONFIG_PAUSE_NOPLAY = 13;
    public static final int BASS_CONFIG_REC_BUFFER = 19;
    public static final int BASS_CONFIG_SRC = 43;
    public static final int BASS_CONFIG_SRC_SAMPLE = 44;
    public static final int BASS_CONFIG_UPDATEPERIOD = 1;
    public static final int BASS_CONFIG_UPDATETHREADS = 24;
    public static final int BASS_CONFIG_VERIFY = 23;
    public static final int BASS_CONFIG_VERIFY_NET = 52;
    public static final int BASS_CTYPE_MUSIC_IT = 131076;
    public static final int BASS_CTYPE_MUSIC_MO3 = 256;
    public static final int BASS_CTYPE_MUSIC_MOD = 131072;
    public static final int BASS_CTYPE_MUSIC_MTM = 131073;
    public static final int BASS_CTYPE_MUSIC_S3M = 131074;
    public static final int BASS_CTYPE_MUSIC_XM = 131075;
    public static final int BASS_CTYPE_RECORD = 2;
    public static final int BASS_CTYPE_SAMPLE = 1;
    public static final int BASS_CTYPE_STREAM = 65536;
    public static final int BASS_CTYPE_STREAM_AIFF = 65542;
    public static final int BASS_CTYPE_STREAM_AM = 65545;
    public static final int BASS_CTYPE_STREAM_CA = 65543;
    public static final int BASS_CTYPE_STREAM_DEVICE = 98305;
    public static final int BASS_CTYPE_STREAM_DUMMY = 98304;
    public static final int BASS_CTYPE_STREAM_MF = 65544;
    public static final int BASS_CTYPE_STREAM_MP1 = 65539;
    public static final int BASS_CTYPE_STREAM_MP2 = 65540;
    public static final int BASS_CTYPE_STREAM_MP3 = 65541;
    public static final int BASS_CTYPE_STREAM_OGG = 65538;
    public static final int BASS_CTYPE_STREAM_VORBIS = 65538;
    public static final int BASS_CTYPE_STREAM_WAV = 262144;
    public static final int BASS_CTYPE_STREAM_WAV_FLOAT = 327683;
    public static final int BASS_CTYPE_STREAM_WAV_PCM = 327681;
    public static final int BASS_DATA_AVAILABLE = 0;
    public static final int BASS_DATA_FFT1024 = -2147483646;
    public static final int BASS_DATA_FFT16384 = -2147483642;
    public static final int BASS_DATA_FFT2048 = -2147483645;
    public static final int BASS_DATA_FFT256 = Integer.MIN_VALUE;
    public static final int BASS_DATA_FFT32768 = -2147483641;
    public static final int BASS_DATA_FFT4096 = -2147483644;
    public static final int BASS_DATA_FFT512 = -2147483647;
    public static final int BASS_DATA_FFT8192 = -2147483643;
    public static final int BASS_DATA_FFT_COMPLEX = 128;
    public static final int BASS_DATA_FFT_INDIVIDUAL = 16;
    public static final int BASS_DATA_FFT_NOWINDOW = 32;
    public static final int BASS_DATA_FFT_NYQUIST = 256;
    public static final int BASS_DATA_FFT_REMOVEDC = 64;
    public static final int BASS_DATA_FIXED = 536870912;
    public static final int BASS_DATA_FLOAT = 1073741824;
    public static final int BASS_DEVICE_16BITS = 8;
    public static final int BASS_DEVICE_3D = 4;
    public static final int BASS_DEVICE_8BITS = 1;
    public static final int BASS_DEVICE_AUDIOTRACK = 131072;
    public static final int BASS_DEVICE_DEFAULT = 2;
    public static final int BASS_DEVICE_ENABLED = 1;
    public static final int BASS_DEVICE_FREQ = 16384;
    public static final int BASS_DEVICE_INIT = 4;
    public static final int BASS_DEVICE_LATENCY = 256;
    public static final int BASS_DEVICE_MONO = 2;
    public static final int BASS_DEVICE_NOSPEAKER = 4096;
    public static final int BASS_DEVICE_SPEAKERS = 2048;
    public static final int BASS_DX8_PHASE_180 = 4;
    public static final int BASS_DX8_PHASE_90 = 3;
    public static final int BASS_DX8_PHASE_NEG_180 = 0;
    public static final int BASS_DX8_PHASE_NEG_90 = 1;
    public static final int BASS_DX8_PHASE_ZERO = 2;
    public static final int BASS_ERROR_ALREADY = 14;
    public static final int BASS_ERROR_BUFLOST = 4;
    public static final int BASS_ERROR_BUSY = 46;
    public static final int BASS_ERROR_CODEC = 44;
    public static final int BASS_ERROR_CREATE = 33;
    public static final int BASS_ERROR_DECODE = 38;
    public static final int BASS_ERROR_DEVICE = 23;
    public static final int BASS_ERROR_DRIVER = 3;
    public static final int BASS_ERROR_DX = 39;
    public static final int BASS_ERROR_EMPTY = 31;
    public static final int BASS_ERROR_ENDED = 45;
    public static final int BASS_ERROR_FILEFORM = 41;
    public static final int BASS_ERROR_FILEOPEN = 2;
    public static final int BASS_ERROR_FORMAT = 6;
    public static final int BASS_ERROR_FREQ = 25;
    public static final int BASS_ERROR_HANDLE = 5;
    public static final int BASS_ERROR_ILLPARAM = 20;
    public static final int BASS_ERROR_ILLTYPE = 19;
    public static final int BASS_ERROR_INIT = 8;
    public static final int BASS_ERROR_JAVA_CLASS = 500;
    public static final int BASS_ERROR_MEM = 1;
    public static final int BASS_ERROR_NO3D = 21;
    public static final int BASS_ERROR_NOCHAN = 18;
    public static final int BASS_ERROR_NOEAX = 22;
    public static final int BASS_ERROR_NOFX = 34;
    public static final int BASS_ERROR_NOHW = 29;
    public static final int BASS_ERROR_NONET = 32;
    public static final int BASS_ERROR_NOPLAY = 24;
    public static final int BASS_ERROR_NOTAUDIO = 17;
    public static final int BASS_ERROR_NOTAVAIL = 37;
    public static final int BASS_ERROR_NOTFILE = 27;
    public static final int BASS_ERROR_POSITION = 7;
    public static final int BASS_ERROR_SPEAKER = 42;
    public static final int BASS_ERROR_SSL = 10;
    public static final int BASS_ERROR_START = 9;
    public static final int BASS_ERROR_TIMEOUT = 40;
    public static final int BASS_ERROR_UNKNOWN = -1;
    public static final int BASS_ERROR_UNSTREAMABLE = 47;
    public static final int BASS_ERROR_VERSION = 43;
    public static final int BASS_FILEDATA_END = 0;
    public static final int BASS_FILEPOS_ASYNCBUF = 7;
    public static final int BASS_FILEPOS_BUFFER = 5;
    public static final int BASS_FILEPOS_BUFFERING = 9;
    public static final int BASS_FILEPOS_CONNECTED = 4;
    public static final int BASS_FILEPOS_CURRENT = 0;
    public static final int BASS_FILEPOS_DECODE = 0;
    public static final int BASS_FILEPOS_DOWNLOAD = 1;
    public static final int BASS_FILEPOS_END = 2;
    public static final int BASS_FILEPOS_SIZE = 8;
    public static final int BASS_FILEPOS_SOCKET = 6;
    public static final int BASS_FILEPOS_START = 3;
    public static final int BASS_FX_DX8_CHORUS = 0;
    public static final int BASS_FX_DX8_COMPRESSOR = 1;
    public static final int BASS_FX_DX8_DISTORTION = 2;
    public static final int BASS_FX_DX8_ECHO = 3;
    public static final int BASS_FX_DX8_FLANGER = 4;
    public static final int BASS_FX_DX8_GARGLE = 5;
    public static final int BASS_FX_DX8_I3DL2REVERB = 6;
    public static final int BASS_FX_DX8_PARAMEQ = 7;
    public static final int BASS_FX_DX8_REVERB = 8;
    public static final int BASS_FX_VOLUME = 9;
    public static final int BASS_LEVEL_MONO = 1;
    public static final int BASS_LEVEL_RMS = 4;
    public static final int BASS_LEVEL_STEREO = 2;
    public static final int BASS_LEVEL_VOLPAN = 8;
    public static final int BASS_MP3_SETPOS = 131072;
    public static final int BASS_MUSIC_3D = 8;
    public static final int BASS_MUSIC_AUTOFREE = 262144;
    public static final int BASS_MUSIC_CALCLEN = 131072;
    public static final int BASS_MUSIC_DECODE = 2097152;
    public static final int BASS_MUSIC_FLOAT = 256;
    public static final int BASS_MUSIC_FT2MOD = 8192;
    public static final int BASS_MUSIC_FT2PAN = 8192;
    public static final int BASS_MUSIC_FX = 128;
    public static final int BASS_MUSIC_LOOP = 4;
    public static final int BASS_MUSIC_MONO = 2;
    public static final int BASS_MUSIC_NONINTER = 65536;
    public static final int BASS_MUSIC_NOSAMPLE = 1048576;
    public static final int BASS_MUSIC_POSRESET = 32768;
    public static final int BASS_MUSIC_POSRESETEX = 4194304;
    public static final int BASS_MUSIC_PRESCAN = 131072;
    public static final int BASS_MUSIC_PT1MOD = 16384;
    public static final int BASS_MUSIC_RAMP = 512;
    public static final int BASS_MUSIC_RAMPS = 1024;
    public static final int BASS_MUSIC_SINCINTER = 8388608;
    public static final int BASS_MUSIC_STOPBACK = 524288;
    public static final int BASS_MUSIC_SURROUND = 2048;
    public static final int BASS_MUSIC_SURROUND2 = 4096;
    public static final int BASS_NODEVICE = 131072;
    public static final int BASS_OK = 0;
    public static final int BASS_ORIGRES_FLOAT = 65536;
    public static final int BASS_POS_BYTE = 0;
    public static final int BASS_POS_DECODE = 268435456;
    public static final int BASS_POS_DECODETO = 536870912;
    public static final int BASS_POS_INEXACT = 134217728;
    public static final int BASS_POS_MUSIC_ORDER = 1;
    public static final int BASS_POS_OGG = 3;
    public static final int BASS_POS_RELATIVE = 67108864;
    public static final int BASS_POS_RESET = 33554432;
    public static final int BASS_POS_SCAN = 1073741824;
    public static final int BASS_RECORD_PAUSE = 32768;
    public static final int BASS_SAMPLE_3D = 8;
    public static final int BASS_SAMPLE_8BITS = 1;
    public static final int BASS_SAMPLE_FLOAT = 256;
    public static final int BASS_SAMPLE_FX = 128;
    public static final int BASS_SAMPLE_LOOP = 4;
    public static final int BASS_SAMPLE_MONO = 2;
    public static final int BASS_SAMPLE_MUTEMAX = 32;
    public static final int BASS_SAMPLE_OVER_DIST = 196608;
    public static final int BASS_SAMPLE_OVER_POS = 131072;
    public static final int BASS_SAMPLE_OVER_VOL = 65536;
    public static final int BASS_SAMPLE_SOFTWARE = 16;
    public static final int BASS_SAMPLE_VAM = 64;
    public static final int BASS_SLIDE_LOG = 16777216;
    public static final int BASS_SPEAKER_CENLFE = 50331648;
    public static final int BASS_SPEAKER_CENTER = 318767104;
    public static final int BASS_SPEAKER_FRONT = 16777216;
    public static final int BASS_SPEAKER_FRONTLEFT = 285212672;
    public static final int BASS_SPEAKER_FRONTRIGHT = 553648128;
    public static final int BASS_SPEAKER_LEFT = 268435456;
    public static final int BASS_SPEAKER_LFE = 587202560;
    public static final int BASS_SPEAKER_REAR = 33554432;
    public static final int BASS_SPEAKER_REAR2 = 67108864;
    public static final int BASS_SPEAKER_REAR2LEFT = 335544320;
    public static final int BASS_SPEAKER_REAR2RIGHT = 603979776;
    public static final int BASS_SPEAKER_REARLEFT = 301989888;
    public static final int BASS_SPEAKER_REARRIGHT = 570425344;
    public static final int BASS_SPEAKER_RIGHT = 536870912;
    public static final int BASS_STREAMPROC_END = Integer.MIN_VALUE;
    public static final int BASS_STREAM_AUTOFREE = 262144;
    public static final int BASS_STREAM_BLOCK = 1048576;
    public static final int BASS_STREAM_DECODE = 2097152;
    public static final int BASS_STREAM_PRESCAN = 131072;
    public static final int BASS_STREAM_RESTRATE = 524288;
    public static final int BASS_STREAM_STATUS = 8388608;
    public static final int BASS_SYNC_DEV_FAIL = 14;
    public static final int BASS_SYNC_DEV_FORMAT = 15;
    public static final int BASS_SYNC_DOWNLOAD = 7;
    public static final int BASS_SYNC_END = 2;
    public static final int BASS_SYNC_FREE = 8;
    public static final int BASS_SYNC_META = 4;
    public static final int BASS_SYNC_MIXTIME = 1073741824;
    public static final int BASS_SYNC_MUSICFX = 3;
    public static final int BASS_SYNC_MUSICINST = 1;
    public static final int BASS_SYNC_MUSICPOS = 10;
    public static final int BASS_SYNC_OGG_CHANGE = 12;
    public static final int BASS_SYNC_ONETIME = Integer.MIN_VALUE;
    public static final int BASS_SYNC_POS = 0;
    public static final int BASS_SYNC_SETPOS = 11;
    public static final int BASS_SYNC_SLIDE = 5;
    public static final int BASS_SYNC_STALL = 6;
    public static final int BASS_SYNC_THREAD = 536870912;
    public static final int BASS_TAG_AM_MIME = 15;
    public static final int BASS_TAG_AM_NAME = 16;
    public static final int BASS_TAG_APE = 6;
    public static final int BASS_TAG_APE_BINARY = 4096;
    public static final int BASS_TAG_BYTEBUFFER = 268435456;
    public static final int BASS_TAG_HTTP = 3;
    public static final int BASS_TAG_ICY = 4;
    public static final int BASS_TAG_ID3 = 0;
    public static final int BASS_TAG_ID3V2 = 1;
    public static final int BASS_TAG_LYRICS3 = 10;
    public static final int BASS_TAG_META = 5;
    public static final int BASS_TAG_MP4 = 7;
    public static final int BASS_TAG_MUSIC_AUTH = 65539;
    public static final int BASS_TAG_MUSIC_INST = 65792;
    public static final int BASS_TAG_MUSIC_MESSAGE = 65537;
    public static final int BASS_TAG_MUSIC_NAME = 65536;
    public static final int BASS_TAG_MUSIC_ORDERS = 65538;
    public static final int BASS_TAG_MUSIC_SAMPLE = 66304;
    public static final int BASS_TAG_OGG = 2;
    public static final int BASS_TAG_RIFF_BEXT = 257;
    public static final int BASS_TAG_RIFF_CART = 258;
    public static final int BASS_TAG_RIFF_CUE = 260;
    public static final int BASS_TAG_RIFF_DISP = 259;
    public static final int BASS_TAG_RIFF_INFO = 256;
    public static final int BASS_TAG_RIFF_SMPL = 261;
    public static final int BASS_TAG_VENDOR = 9;
    public static final int BASS_TAG_WAVEFORMAT = 14;
    public static final int STREAMFILE_BUFFER = 1;
    public static final int STREAMFILE_BUFFERPUSH = 2;
    public static final int STREAMFILE_NOBUFFER = 0;
    public static final int STREAMPROC_DEVICE = -2;
    public static final int STREAMPROC_DEVICE_3D = -3;
    public static final int STREAMPROC_DUMMY = 0;
    public static final int STREAMPROC_PUSH = -1;


    public static class BASS_CHANNELINFO {
        public int chans;
        public int ctype;
        public String filename;
        public int flags;
        public int freq;
        public int origres;
        public int plugin;
        public int sample;
    }


    public static class BASS_DEVICEINFO {
        public String driver;
        public int flags;
        public String name;
    }


    public static class BASS_DX8_CHORUS {
        public float fDelay;
        public float fDepth;
        public float fFeedback;
        public float fFrequency;
        public float fWetDryMix;
        public int lPhase;
        public int lWaveform;
    }


    public static class BASS_DX8_DISTORTION {
        public float fEdge;
        public float fGain;
        public float fPostEQBandwidth;
        public float fPostEQCenterFrequency;
        public float fPreLowpassCutoff;
    }


    public static class BASS_DX8_ECHO {
        public float fFeedback;
        public float fLeftDelay;
        public float fRightDelay;
        public float fWetDryMix;
        public boolean lPanDelay;
    }


    public static class BASS_DX8_FLANGER {
        public float fDelay;
        public float fDepth;
        public float fFeedback;
        public float fFrequency;
        public float fWetDryMix;
        public int lPhase;
        public int lWaveform;
    }


    public static class BASS_DX8_PARAMEQ {
        public float fBandwidth;
        public float fCenter;
        public float fGain;
    }


    public static class BASS_DX8_REVERB {
        public float fHighFreqRTRatio;
        public float fInGain;
        public float fReverbMix;
        public float fReverbTime;
    }


    public interface BASS_FILEPROCS {
        void FILECLOSEPROC(Object user);

        long FILELENPROC(Object user);

        int FILEREADPROC(ByteBuffer buffer, int length, Object user);

        boolean FILESEEKPROC(long offset, Object user);
    }


    public static class BASS_FX_VOLUME_PARAM {
        public float fCurrent;
        public float fTarget;
        public float fTime;
        public int lCurve;
    }


    public static class BASS_INFO {
        public int dsver;
        public int eax;
        public int flags;
        public int free3d;
        public int freesam;
        public int freq;
        public int hwfree;
        public int hwsize;
        public int initflags;
        public int latency;
        public int maxrate;
        public int minbuf;
        public int minrate;
        public int speakers;
    }


    public static class BASS_PLUGINFORM {
        public int ctype;
        public String exts;
        public String name;
    }


    public static class BASS_PLUGININFO {
        public int formatc;
        public BASS_PLUGINFORM[] formats;
        public int version;
    }


    public static class BASS_RECORDINFO {
        public int flags;
        public int formats;
        public int freq;
        public int inputs;
        public boolean singlein;
    }


    public static class BASS_SAMPLE {
        public int chans;
        public int flags;
        public int freq;
        public int iangle;
        public int length;
        public int max;
        public float maxdist;
        public float mindist;
        public int mingap;
        public int mode3d;
        public int oangle;
        public int origres;
        public float outvol;
        public float pan;
        public int priority;
        public int vam;
        public float volume;
    }


    public interface DOWNLOADPROC {
        void DOWNLOADPROC(ByteBuffer buffer, int length, Object user);
    }


    public interface DSPPROC {
        void DSPPROC(int handle, int channel, ByteBuffer buffer, int length, Object user);
    }


    public static class FloatValue {
        public float value;
    }


    public interface RECORDPROC {
        boolean RECORDPROC(int handle, ByteBuffer buffer, int length, Object user);
    }


    public interface STREAMPROC {
        int STREAMPROC(int handle, ByteBuffer buffer, int length, Object user);
    }


    public interface SYNCPROC {
        void SYNCPROC(int handle, int channel, int data, Object user);
    }


    public static class TAG_APE_BINARY {
        public ByteBuffer data;
        public String key;
        public int length;
    }


    public static class TAG_ID3 {
        public String album;
        public String artist;
        public String comment;
        public byte genre;

        public String id;
        public String title;
        public byte track;
        public String year;
    }


    public static class Utils {
        public static int HIBYTE(int n) {
            return (n >> 8) & 255;
        }

        public static int HIWORD(int n) {
            return (n >> 16) & 65535;
        }

        public static int LOBYTE(int n) {
            return n & 255;
        }

        public static int LOWORD(int n) {
            return n & 65535;
        }

        public static int MAKELONG(int a, int b) {
            return (a & 65535) | (b << 16);
        }

        public static int MAKEWORD(int a, int b) {
            return (a & 255) | ((b & 255) << 8);
        }
    }

    public static native void BASS_Apply3D();

    public static native double BASS_ChannelBytes2Seconds(int handle, long pos);

    public static native long BASS_ChannelFlags(int handle, int flags, int mask);

    public static native boolean BASS_ChannelGet3DAttributes(int handle, Integer mode, FloatValue min, FloatValue max, Integer iangle, Integer oangle, FloatValue outvol);

    public static native boolean BASS_ChannelGet3DPosition(int handle, BASS_3DVECTOR pos, BASS_3DVECTOR orient, BASS_3DVECTOR vel);

    public static native boolean BASS_ChannelGetAttribute(int handle, int attrib, FloatValue value);

    public static native int BASS_ChannelGetAttributeEx(int handle, int attrib, ByteBuffer value, int size);

    public static native int BASS_ChannelGetData(int handle, ByteBuffer buffer, int length);

    public static native int BASS_ChannelGetDevice(int handle);

    public static native boolean BASS_ChannelGetInfo(int handle, BASS_CHANNELINFO info);

    public static native long BASS_ChannelGetLength(int handle, int mode);

    public static native int BASS_ChannelGetLevel(int handle);

    public static native boolean BASS_ChannelGetLevelEx(int handle, float[] levels, float length, int flags);

    public static native long BASS_ChannelGetPosition(int handle, int mode);

    public static native Object BASS_ChannelGetTags(int handle, int tags);

    public static native int BASS_ChannelIsActive(int handle);

    public static native boolean BASS_ChannelIsSliding(int handle, int attrib);

    public static native boolean BASS_ChannelLock(int handle, boolean lock);

    public static native boolean BASS_ChannelPause(int handle);

    public static native boolean BASS_ChannelPlay(int handle, boolean restart);

    public static native boolean BASS_ChannelRemoveDSP(int handle, int dsp);

    public static native boolean BASS_ChannelRemoveFX(int handle, int fx);

    public static native boolean BASS_ChannelRemoveLink(int handle, int chan);

    public static native boolean BASS_ChannelRemoveSync(int handle, int sync);

    public static native long BASS_ChannelSeconds2Bytes(int handle, double pos);

    public static native boolean BASS_ChannelSet3DAttributes(int handle, int mode, float min, float max, int iangle, int oangle, float outvol);

    public static native boolean BASS_ChannelSet3DPosition(int handle, BASS_3DVECTOR pos, BASS_3DVECTOR orient, BASS_3DVECTOR vel);

    public static native boolean BASS_ChannelSetAttribute(int handle, int attrib, float value);

    public static native boolean BASS_ChannelSetAttributeEx(int handle, int attrib, ByteBuffer value, int size);

    public static native int BASS_ChannelSetDSP(int handle, DSPPROC proc, Object user, int priority);

    public static native boolean BASS_ChannelSetDevice(int handle, int device);

    public static native int BASS_ChannelSetFX(int handle, int type, int priority);

    public static native boolean BASS_ChannelSetLink(int handle, int chan);

    public static native boolean BASS_ChannelSetPosition(int handle, long pos, int mode);

    public static native int BASS_ChannelSetSync(int handle, int type, long param, SYNCPROC proc, Object user);

    public static native boolean BASS_ChannelSlideAttribute(int handle, int attrib, float value, int time);

    public static native boolean BASS_ChannelStop(int handle);

    public static native boolean BASS_ChannelUpdate(int handle, int length);

    public static native int BASS_ErrorGetCode();

    public static native boolean BASS_FXGetParameters(int handle, Object params);

    public static native boolean BASS_FXReset(int handle);

    public static native boolean BASS_FXSetParameters(int handle, Object params);

    public static native boolean BASS_FXSetPriority(int handle, int priority);

    public static native boolean BASS_Free();

    public static native boolean BASS_Get3DFactors(FloatValue distf, FloatValue rollf, FloatValue doppf);

    public static native boolean BASS_Get3DPosition(BASS_3DVECTOR pos, BASS_3DVECTOR vel, BASS_3DVECTOR front, BASS_3DVECTOR top);

    public static native float BASS_GetCPU();

    public static native int BASS_GetConfig(int option);

    public static native Object BASS_GetConfigPtr(int option);

    public static native int BASS_GetDevice();

    public static native boolean BASS_GetDeviceInfo(int device, BASS_DEVICEINFO info);

    public static native boolean BASS_GetInfo(BASS_INFO info);

    public static native int BASS_GetVersion();

    public static native float BASS_GetVolume();

    public static native boolean BASS_Init(int device, int freq, int flags);

    public static native boolean BASS_IsStarted();

    public static native boolean BASS_MusicFree(int handle);

    public static native int BASS_MusicLoad(ParcelFileDescriptor asset, long offset, int length, int flags, int freq);

    public static native int BASS_MusicLoad(Asset asset, long offset, int length, int flags, int freq);

    public static native int BASS_MusicLoad(String file, long offset, int length, int flags, int freq);

    public static native int BASS_MusicLoad(ByteBuffer file, long offset, int length, int flags, int freq);

    public static native boolean BASS_Pause();

    public static native boolean BASS_PluginFree(int handle);

    public static native BASS_PLUGININFO BASS_PluginGetInfo(int handle);

    public static native int BASS_PluginLoad(String file, int flags);

    public static native boolean BASS_RecordFree();

    public static native int BASS_RecordGetDevice();

    public static native boolean BASS_RecordGetDeviceInfo(int device, BASS_DEVICEINFO info);

    public static native boolean BASS_RecordGetInfo(BASS_RECORDINFO info);

    public static native int BASS_RecordGetInput(int input, FloatValue volume);

    public static native String BASS_RecordGetInputName(int input);

    public static native boolean BASS_RecordInit(int device);

    public static native boolean BASS_RecordSetDevice(int device);

    public static native boolean BASS_RecordSetInput(int input, int flags, float volume);

    public static native int BASS_RecordStart(int freq, int chans, int flags, RECORDPROC proc, Object user);

    public static int BASS_SPEAKER_N(int n) {
        return n << 24;
    }

    public static native int BASS_SampleCreate(int length, int freq, int chans, int max, int flags);

    public static native boolean BASS_SampleFree(int handle);

    public static native int BASS_SampleGetChannel(int handle, boolean onlynew);

    public static native int BASS_SampleGetChannels(int handle, int[] channels);

    public static native boolean BASS_SampleGetData(int handle, ByteBuffer buffer);

    public static native boolean BASS_SampleGetInfo(int handle, BASS_SAMPLE info);

    public static native int BASS_SampleLoad(ParcelFileDescriptor file, long offset, int length, int max, int flags);

    public static native int BASS_SampleLoad(Asset file, long offset, int length, int max, int flags);

    public static native int BASS_SampleLoad(String file, long offset, int length, int max, int flags);

    public static native int BASS_SampleLoad(ByteBuffer file, long offset, int length, int max, int flags);

    public static native boolean BASS_SampleSetData(int handle, ByteBuffer buffer);

    public static native boolean BASS_SampleSetInfo(int handle, BASS_SAMPLE info);

    public static native boolean BASS_SampleStop(int handle);

    public static native boolean BASS_Set3DFactors(float distf, float rollf, float doppf);

    public static native boolean BASS_Set3DPosition(BASS_3DVECTOR pos, BASS_3DVECTOR vel, BASS_3DVECTOR front, BASS_3DVECTOR top);

    public static native boolean BASS_SetConfig(int option, int value);

    public static native boolean BASS_SetConfigPtr(int option, Object value);

    public static native boolean BASS_SetDevice(int device);

    public static native boolean BASS_SetVolume(float volume);

    public static native boolean BASS_Start();

    public static native boolean BASS_Stop();

    public static native int BASS_StreamCreate(int freq, int chans, int flags, STREAMPROC proc, Object user);

    static native int BASS_StreamCreateConst(int freq, int chans, int flags, int proc, Object user);

    public static native int BASS_StreamCreateFile(ParcelFileDescriptor file, long offset, long length, int flags);

    public static native int BASS_StreamCreateFile(Asset asset, long offset, long length, int flags);

    public static native int BASS_StreamCreateFile(String file, long offset, long length, int flags);

    public static native int BASS_StreamCreateFile(ByteBuffer file, long offset, long length, int flags);

    public static native int BASS_StreamCreateFileUser(int system, int flags, BASS_FILEPROCS procs, Object user);

    public static native int BASS_StreamCreateURL(String url, int offset, int flags, DOWNLOADPROC proc, Object user);

    public static native boolean BASS_StreamFree(int handle);

    public static native long BASS_StreamGetFilePosition(int handle, int mode);

    public static native int BASS_StreamPutData(int handle, ByteBuffer buffer, int length);

    public static native int BASS_StreamPutFileData(int handle, ByteBuffer buffer, int length);

    public static native boolean BASS_Update(int length);

    public static class BASS_3DVECTOR {

        public float x;

        public float y;

        public float z;

        public BASS_3DVECTOR() {
        }

        public BASS_3DVECTOR(float _x, float _y, float _z) {
            this.x = _x;
            this.y = _y;
            this.z = _z;
        }
    }

    public static class Asset {
        public String file;
        public AssetManager manager;

        public Asset() {
        }

        public Asset(AssetManager m, String f) {
            this.manager = m;
            this.file = f;
        }
    }

    public static int BASS_StreamCreate(int freq, int chans, int flags, int proc, Object user) {
        return BASS_StreamCreateConst(freq, chans, flags, proc, user);
    }

    static {
        System.loadLibrary("bass");
    }
}
