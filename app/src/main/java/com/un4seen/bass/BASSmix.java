package com.un4seen.bass;

import java.nio.ByteBuffer;


public class BASSmix {
    public static final int BASS_ATTRIB_MIXER_LATENCY = 86016;
    public static final int BASS_ATTRIB_SPLIT_ASYNCBUFFER = 86032;
    public static final int BASS_ATTRIB_SPLIT_ASYNCPERIOD = 86033;
    public static final int BASS_CONFIG_MIXER_BUFFER = 67073;
    public static final int BASS_CONFIG_MIXER_POSEX = 67074;
    public static final int BASS_CONFIG_SPLIT_BUFFER = 67088;
    public static final int BASS_CTYPE_STREAM_MIXER = 67584;
    public static final int BASS_CTYPE_STREAM_SPLIT = 67585;
    public static final int BASS_MIXER_BUFFER = 8192;
    public static final int BASS_MIXER_CHAN_ABSOLUTE = 4096;
    public static final int BASS_MIXER_CHAN_BUFFER = 8192;
    public static final int BASS_MIXER_CHAN_DOWNMIX = 4194304;
    public static final int BASS_MIXER_CHAN_LIMIT = 16384;
    public static final int BASS_MIXER_CHAN_MATRIX = 65536;
    public static final int BASS_MIXER_CHAN_NORAMPIN = 8388608;
    public static final int BASS_MIXER_CHAN_PAUSE = 131072;
    public static final int BASS_MIXER_DOWNMIX = 4194304;
    public static final int BASS_MIXER_END = 65536;
    public static final int BASS_MIXER_ENV_FREQ = 1;
    public static final int BASS_MIXER_ENV_LOOP = 65536;
    public static final int BASS_MIXER_ENV_PAN = 3;
    public static final int BASS_MIXER_ENV_REMOVE = 131072;
    public static final int BASS_MIXER_ENV_VOL = 2;
    public static final int BASS_MIXER_LIMIT = 16384;
    public static final int BASS_MIXER_MATRIX = 65536;
    public static final int BASS_MIXER_NONSTOP = 131072;
    public static final int BASS_MIXER_NORAMPIN = 8388608;
    public static final int BASS_MIXER_PAUSE = 131072;
    public static final int BASS_MIXER_POSEX = 8192;
    public static final int BASS_MIXER_RESUME = 4096;
    public static final int BASS_POS_MIXER_RESET = 65536;
    public static final int BASS_SPLIT_POS = 8192;
    public static final int BASS_SPLIT_SLAVE = 4096;
    public static final int BASS_SYNC_MIXER_ENVELOPE = 66048;
    public static final int BASS_SYNC_MIXER_ENVELOPE_NODE = 66049;

    public static native int BASS_Mixer_ChannelFlags(int handle, int flags, int mask);

    public static native int BASS_Mixer_ChannelGetData(int handle, ByteBuffer buffer, int length);

    public static native long BASS_Mixer_ChannelGetEnvelopePos(int handle, int type, Float value);

    public static native int BASS_Mixer_ChannelGetLevel(int handle);

    public static native boolean BASS_Mixer_ChannelGetLevelEx(int handle, float[] levels, float length, int flags);

    public static native boolean BASS_Mixer_ChannelGetMatrix(int handle, float[][] matrix);

    public static native int BASS_Mixer_ChannelGetMixer(int handle);

    public static native long BASS_Mixer_ChannelGetPosition(int handle, int mode);

    public static native long BASS_Mixer_ChannelGetPositionEx(int channel, int mode, int delay);

    public static native boolean BASS_Mixer_ChannelRemove(int handle);

    public static native boolean BASS_Mixer_ChannelRemoveSync(int channel, int sync);

    public static native boolean BASS_Mixer_ChannelSetEnvelope(int handle, int type, BASS_MIXER_NODE[] nodes, int count);

    public static native boolean BASS_Mixer_ChannelSetEnvelopePos(int handle, int type, long pos);

    public static native boolean BASS_Mixer_ChannelSetMatrix(int handle, float[][] matrix);

    public static native boolean BASS_Mixer_ChannelSetMatrixEx(int handle, float[][] matrix, float time);

    public static native boolean BASS_Mixer_ChannelSetPosition(int handle, long pos, int mode);

    public static native int BASS_Mixer_ChannelSetSync(int handle, int type, long param, BASS.SYNCPROC proc, Object user);

    public static native int BASS_Mixer_GetVersion();

    public static native boolean BASS_Mixer_StreamAddChannel(int handle, int channel, int flags);

    public static native boolean BASS_Mixer_StreamAddChannelEx(int handle, int channel, int flags, long start, long length);

    public static native int BASS_Mixer_StreamCreate(int freq, int chans, int flags);

    public static native int BASS_Mixer_StreamGetChannels(int handle, int[] channels, int count);

    public static native int BASS_Split_StreamCreate(int channel, int flags, int[] chanmap);

    public static native int BASS_Split_StreamGetAvailable(int handle);

    public static native int BASS_Split_StreamGetSource(int handle);

    public static native int BASS_Split_StreamGetSplits(int handle, int[] splits, int count);

    public static native boolean BASS_Split_StreamReset(int handle);

    public static native boolean BASS_Split_StreamResetEx(int handle, int offset);


    public static class BASS_MIXER_NODE {
        public long pos;
        public float value;

        public BASS_MIXER_NODE() {
        }

        public BASS_MIXER_NODE(long _pos, float _value) {
            this.pos = _pos;
            this.value = _value;
        }
    }

    static {
        System.loadLibrary("bassmix");
    }
}
