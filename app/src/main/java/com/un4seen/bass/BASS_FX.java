package com.un4seen.bass;


public class BASS_FX {
    public static final int BASS_ATTRIB_REVERSE_DIR = 69632;
    public static final int BASS_ATTRIB_TEMPO = 65536;
    public static final int BASS_ATTRIB_TEMPO_FREQ = 65538;
    public static final int BASS_ATTRIB_TEMPO_OPTION_AA_FILTER_LENGTH = 65553;
    public static final int BASS_ATTRIB_TEMPO_OPTION_OVERLAP_MS = 65557;
    public static final int BASS_ATTRIB_TEMPO_OPTION_PREVENT_CLICK = 65558;
    public static final int BASS_ATTRIB_TEMPO_OPTION_SEEKWINDOW_MS = 65556;
    public static final int BASS_ATTRIB_TEMPO_OPTION_SEQUENCE_MS = 65555;
    public static final int BASS_ATTRIB_TEMPO_OPTION_USE_AA_FILTER = 65552;
    public static final int BASS_ATTRIB_TEMPO_OPTION_USE_QUICKALGO = 65554;
    public static final int BASS_ATTRIB_TEMPO_PITCH = 65537;
    public static final int BASS_BFX_BQF_ALLPASS = 5;
    public static final int BASS_BFX_BQF_BANDPASS = 2;
    public static final int BASS_BFX_BQF_BANDPASS_Q = 3;
    public static final int BASS_BFX_BQF_HIGHPASS = 1;
    public static final int BASS_BFX_BQF_HIGHSHELF = 8;
    public static final int BASS_BFX_BQF_LOWPASS = 0;
    public static final int BASS_BFX_BQF_LOWSHELF = 7;
    public static final int BASS_BFX_BQF_NOTCH = 4;
    public static final int BASS_BFX_BQF_PEAKINGEQ = 6;
    public static final int BASS_BFX_CHAN1 = 1;
    public static final int BASS_BFX_CHAN2 = 2;
    public static final int BASS_BFX_CHAN3 = 4;
    public static final int BASS_BFX_CHAN4 = 8;
    public static final int BASS_BFX_CHAN5 = 16;
    public static final int BASS_BFX_CHAN6 = 32;
    public static final int BASS_BFX_CHAN7 = 64;
    public static final int BASS_BFX_CHAN8 = 128;
    public static final int BASS_BFX_CHANALL = -1;
    public static final int BASS_BFX_CHANNONE = 0;
    public static final int BASS_BFX_FREEVERB_MODE_FREEZE = 1;
    public static final int BASS_CTYPE_STREAM_REVERSE = 127489;
    public static final int BASS_CTYPE_STREAM_TEMPO = 127488;
    public static final int BASS_FX_BFX_APF = 65550;
    public static final int BASS_FX_BFX_AUTOWAH = 65545;
    public static final int BASS_FX_BFX_BQF = 65555;
    public static final int BASS_FX_BFX_CHORUS = 65549;
    public static final int BASS_FX_BFX_COMPRESSOR = 65551;
    public static final int BASS_FX_BFX_COMPRESSOR2 = 65553;
    public static final int BASS_FX_BFX_DAMP = 65544;
    public static final int BASS_FX_BFX_DISTORTION = 65552;
    public static final int BASS_FX_BFX_ECHO = 65537;
    public static final int BASS_FX_BFX_ECHO2 = 65546;
    public static final int BASS_FX_BFX_ECHO3 = 65548;
    public static final int BASS_FX_BFX_ECHO4 = 65556;
    public static final int BASS_FX_BFX_FLANGER = 65538;
    public static final int BASS_FX_BFX_FREEVERB = 65558;
    public static final int BASS_FX_BFX_LPF = 65542;
    public static final int BASS_FX_BFX_MIX = 65543;
    public static final int BASS_FX_BFX_PEAKEQ = 65540;
    public static final int BASS_FX_BFX_PHASER = 65547;
    public static final int BASS_FX_BFX_PITCHSHIFT = 65557;
    public static final int BASS_FX_BFX_REVERB = 65541;
    public static final int BASS_FX_BFX_ROTATE = 65536;
    public static final int BASS_FX_BFX_VOLUME = 65539;
    public static final int BASS_FX_BFX_VOLUME_ENV = 65554;
    public static final int BASS_FX_BPM_BKGRND = 1;
    public static final int BASS_FX_BPM_MULT2 = 2;
    public static final int BASS_FX_BPM_TRAN_2FREQ = 1;
    public static final int BASS_FX_BPM_TRAN_2PERCENT = 3;
    public static final int BASS_FX_BPM_TRAN_FREQ2 = 2;
    public static final int BASS_FX_BPM_TRAN_PERCENT2 = 4;
    public static final int BASS_FX_BPM_TRAN_X2 = 0;
    public static final int BASS_FX_FREESOURCE = 65536;
    public static final int BASS_FX_RVS_FORWARD = 1;
    public static final int BASS_FX_RVS_REVERSE = -1;
    public static final int BASS_FX_TEMPO_ALGO_CUBIC = 1024;
    public static final int BASS_FX_TEMPO_ALGO_LINEAR = 512;
    public static final int BASS_FX_TEMPO_ALGO_SHANNON = 2048;


    public static class BASS_BFX_APF {
        public float fDelay;
        public float fGain;
        public int lChannel;
    }


    public static class BASS_BFX_AUTOWAH {
        public float fDryMix;
        public float fFeedback;
        public float fFreq;
        public float fRange;
        public float fRate;
        public float fWetMix;
        public int lChannel;
    }


    public static class BASS_BFX_BQF {
        public float fBandwidth;
        public float fCenter;
        public float fGain;

        public float fQ;

        public float fS;
        public int lChannel;
        public int lFilter;
    }


    public static class BASS_BFX_CHORUS {
        public float fDryMix;
        public float fFeedback;
        public float fMaxSweep;
        public float fMinSweep;
        public float fRate;
        public float fWetMix;
        public int lChannel;
    }


    public static class BASS_BFX_COMPRESSOR {
        public float fAttacktime;
        public float fReleasetime;
        public float fThreshold;
        public int lChannel;
    }


    public static class BASS_BFX_COMPRESSOR2 {
        public float fAttack;
        public float fGain;
        public float fRatio;
        public float fRelease;
        public float fThreshold;
        public int lChannel;
    }


    public static class BASS_BFX_DAMP {
        public float fDelay;
        public float fGain;
        public float fQuiet;
        public float fRate;
        public float fTarget;
        public int lChannel;
    }


    public static class BASS_BFX_DISTORTION {
        public float fDrive;
        public float fDryMix;
        public float fFeedback;
        public float fVolume;
        public float fWetMix;
        public int lChannel;
    }


    public static class BASS_BFX_ECHO {
        public float fLevel;
        public int lDelay;
    }


    public static class BASS_BFX_ECHO2 {
        public float fDelay;
        public float fDryMix;
        public float fFeedback;
        public float fWetMix;
        public int lChannel;
    }


    public static class BASS_BFX_ECHO3 {
        public float fDelay;
        public float fDryMix;
        public float fWetMix;
        public int lChannel;
    }


    public static class BASS_BFX_ECHO4 {
        public boolean bStereo;
        public float fDelay;
        public float fDryMix;
        public float fFeedback;
        public float fWetMix;
        public int lChannel;
    }


    public static class BASS_BFX_ENV_NODE {
        public double pos;
        public float val;
    }


    public static class BASS_BFX_FLANGER {
        public float fSpeed;
        public float fWetDry;
        public int lChannel;
    }


    public static class BASS_BFX_FREEVERB {
        public float fDamp;
        public float fDryMix;
        public float fRoomSize;
        public float fWetMix;
        public float fWidth;
        public int lChannel;
        public int lMode;
    }


    public static class BASS_BFX_LPF {
        public float fCutOffFreq;
        public float fResonance;
        public int lChannel;
    }


    public static class BASS_BFX_MIX {
        public int[] lChannel;
    }


    public static class BASS_BFX_PEAKEQ {
        public float fBandwidth;
        public float fCenter;
        public float fGain;

        public float fQ;
        public int lBand;
        public int lChannel;
    }


    public static class BASS_BFX_PHASER {
        public float fDryMix;
        public float fFeedback;
        public float fFreq;
        public float fRange;
        public float fRate;
        public float fWetMix;
        public int lChannel;
    }


    public static class BASS_BFX_PITCHSHIFT {
        public float fPitchShift;
        public float fSemitones;
        public int lChannel;
        public int lFFTsize;
        public int lOsamp;
    }


    public static class BASS_BFX_REVERB {
        public float fLevel;
        public int lDelay;
    }


    public static class BASS_BFX_ROTATE {
        public float fRate;
        public int lChannel;
    }


    public static class BASS_BFX_VOLUME {
        public float fVolume;
        public int lChannel;
    }


    public static class BASS_BFX_VOLUME_ENV {
        public boolean bFollow;
        public int lChannel;
        public int lNodeCount;
        public BASS_BFX_ENV_NODE[] pNodes;
    }


    public interface BPMBEATPROC {
        void BPMBEATPROC(int chan, double beatpos, Object user);
    }


    public interface BPMPROC {
        void BPMPROC(int chan, float bpm, Object user);
    }


    public interface BPMPROCESSPROC {
        void BPMPROCESSPROC(int chan, float percent, Object user);
    }


    public interface BPMPROGRESSPROC {
        void BPMPROGRESSPROC(int chan, float percent, Object user);
    }

    public static int BASS_BFX_CHANNEL_N(int n) {
        return 1 << (n - 1);
    }

    public static native boolean BASS_FX_BPM_BeatCallbackReset(int handle);

    public static native boolean BASS_FX_BPM_BeatCallbackSet(int handle, BPMBEATPROC proc, Object user);

    public static native boolean BASS_FX_BPM_BeatDecodeGet(int chan, double startSec, double endSec, int flags, BPMBEATPROC proc, Object user);

    public static native boolean BASS_FX_BPM_BeatFree(int handle);

    public static native boolean BASS_FX_BPM_BeatGetParameters(int handle, Float bandwidth, Float centerfreq, Float beat_rtime);

    public static native boolean BASS_FX_BPM_BeatSetParameters(int handle, float bandwidth, float centerfreq, float beat_rtime);

    public static native boolean BASS_FX_BPM_CallbackReset(int handle);

    public static native boolean BASS_FX_BPM_CallbackSet(int handle, BPMPROC proc, double period, int minMaxBPM, int flags, Object user);

    public static native float BASS_FX_BPM_DecodeGet(int chan, double startSec, double endSec, int minMaxBPM, int flags, Object proc, Object user);

    public static native boolean BASS_FX_BPM_Free(int handle);

    public static native float BASS_FX_BPM_Translate(int handle, float val2tran, int trans);

    public static native int BASS_FX_GetVersion();

    public static native int BASS_FX_ReverseCreate(int chan, float dec_block, int flags);

    public static native int BASS_FX_ReverseGetSource(int chan);

    public static native int BASS_FX_TempoCreate(int chan, int flags);

    public static native float BASS_FX_TempoGetRateRatio(int chan);

    public static native int BASS_FX_TempoGetSource(int chan);

    public static double BASS_BFX_Linear2dB(double level) {
        return Math.log10(level) * 20.0d;
    }

    public static double BASS_BFX_dB2Linear(double dB) {
        return Math.pow(10.0d, dB / 20.0d);
    }

    static {
        System.loadLibrary("bass_fx");
    }
}
