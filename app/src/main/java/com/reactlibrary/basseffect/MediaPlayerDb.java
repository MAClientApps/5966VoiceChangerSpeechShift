package com.reactlibrary.basseffect;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.reactlibrary.constants.InterfaceVoiceChangerListener;
import com.reactlibrary.utils.StringUtils;
import com.un4seen.bass.BASS;
import com.un4seen.bass.BASS_FX;
import com.un4seen.bass.BASSenc;
import com.un4seen.bass.BASSmix;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class MediaPlayerDb implements DBMediaTypeConstant {
    private static final String logTag = "DBMediaPlayer";
    private boolean isMixNeed;
    private boolean isThisReverse;
    private ArrayList<Integer> integerArrayList;
    private int channelPlay;
    private int tempChanel;
    private DBMediaListener mediaListener;
    private int amplifyFx;
    private int autoEffectFx;
    private int bigQuedEffects;
    private int chorosEffectFx;
    private int compressorEffects;
    private int effectDisort;
    private int effextEQ1;
    private int effectEQ2;
    private int effectEQ3;
    private int effextEQ4;
    private int effectEcho;
    private int effectFlenger;
    private int effectPhaser;
    private int effectReverb;
    private int effectRotate;
    private final String strMediaPath;
    private String strMixPath;
    private int intCurrPosition = 0;
    private int intDuration = 0;
    private boolean isPlaying = false;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            MediaPlayerDb dBMediaPlayerDb = MediaPlayerDb.this;
            dBMediaPlayerDb.intCurrPosition = dBMediaPlayerDb.getChannelPos();
            MediaPlayerDb dBMediaPlayerDb2 = MediaPlayerDb.this;
            dBMediaPlayerDb2.intDuration = dBMediaPlayerDb2.getChanLength();
            if (!MediaPlayerDb.this.isThisReverse) {
                if (MediaPlayerDb.this.intCurrPosition >= MediaPlayerDb.this.intDuration) {
                    removeMessages(0);
                    if (MediaPlayerDb.this.mediaListener != null) {
                        MediaPlayerDb.this.mediaListener.onMediaCompleteListener();
                        return;
                    }
                    return;
                }
                sendEmptyMessageDelayed(0, 50L);
            } else if (MediaPlayerDb.this.intCurrPosition <= 0) {
                removeMessages(0);
                if (MediaPlayerDb.this.mediaListener != null) {
                    MediaPlayerDb.this.mediaListener.onMediaCompleteListener();
                }
            } else {
                sendEmptyMessageDelayed(0, 50L);
            }
        }
    };

    public MediaPlayerDb(String mBeatPath) {
        this.strMediaPath = mBeatPath;
    }

    public void setMixNeed(boolean mixNeed) {
        this.isMixNeed = mixNeed;
    }

    public void setPathMix(String mPathMix) {
        this.strMixPath = mPathMix;
    }

    public boolean audioPrepare() {
        if (strMediaPath == null || strMediaPath.equals("")) {
            return false;
        }
        if (this.strMediaPath.toLowerCase(Locale.getDefault()).endsWith(DBMediaTypeConstant.mp3Type) || this.strMediaPath.toLowerCase(Locale.getDefault()).endsWith(DBMediaTypeConstant.wavType) || this.strMediaPath.toLowerCase(Locale.getDefault()).endsWith(DBMediaTypeConstant.oggType) || this.strMediaPath.toLowerCase(Locale.getDefault()).endsWith(DBMediaTypeConstant.flacType) || this.strMediaPath.toLowerCase(Locale.getDefault()).endsWith(DBMediaTypeConstant.aacType) || this.strMediaPath.toLowerCase(Locale.getDefault()).endsWith(DBMediaTypeConstant.midType) || this.strMediaPath.toLowerCase(Locale.getDefault()).endsWith(DBMediaTypeConstant.wmaType)) {
            mediaInit();
            return true;
        }
        new Exception("DBMidiPlayer:can not support file format").printStackTrace();
        return false;
    }

    public void audioStart() {
        this.isPlaying = true;
        int i = this.channelPlay;
        if (i != 0) {
            BASS.BASS_ChannelPlay(i, false);
        }
        this.mHandler.sendEmptyMessage(0);
    }

    public void setPitchAudio(int i1) {
        int i = this.channelPlay;
        if (i != 0) {
            BASS.BASS_ChannelSetAttribute(i, 65537, i1);
        }
    }

    public void setRateAudio(float v) {
        int i = this.channelPlay;
        if (i != 0) {
            BASS.BASS_ChannelSetAttribute(i, 65536, v);
        }
    }

    public void setReverbAudio(float[] floats) {
        int i = this.channelPlay;
        if (i != 0) {
            if (floats != null) {
                if (this.effectReverb == 0) {
                    this.effectReverb = BASS.BASS_ChannelSetFX(i, 8, 0);
                }
                if (this.effectReverb != 0) {
                    BASS.BASS_DX8_REVERB bass_dx8_reverb = new BASS.BASS_DX8_REVERB();
                    BASS.BASS_FXGetParameters(this.effectReverb, bass_dx8_reverb);
                    bass_dx8_reverb.fReverbMix = floats[0];
                    bass_dx8_reverb.fReverbTime = floats[1];
                    bass_dx8_reverb.fHighFreqRTRatio = floats[2];
                    boolean BASS_FXSetParameters = BASS.BASS_FXSetParameters(this.effectReverb, bass_dx8_reverb);
                    String str = logTag;
                    return;
                }
                return;
            }
            int i2 = this.effectReverb;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.effectReverb = 0;
            }
        }
    }

    public void setEchoAudio(float[] floats) {
        int i = this.channelPlay;
        if (i != 0) {
            if (floats != null) {
                if (this.effectEcho == 0) {
                    this.effectEcho = BASS.BASS_ChannelSetFX(i, 3, 0);
                }
                if (this.effectEcho != 0) {
                    BASS.BASS_DX8_ECHO bass_dx8_echo = new BASS.BASS_DX8_ECHO();
                    BASS.BASS_FXGetParameters(this.effectEcho, bass_dx8_echo);
                    bass_dx8_echo.fLeftDelay = floats[0];
                    bass_dx8_echo.fRightDelay = floats[1];
                    bass_dx8_echo.fFeedback = floats[2];
                    if (floats.length == 4) {
                        bass_dx8_echo.fWetDryMix = floats[3];
                    }
                    boolean BASS_FXSetParameters = BASS.BASS_FXSetParameters(this.effectEcho, bass_dx8_echo);
                    return;
                }
                return;
            }
            int i2 = this.effectEcho;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.effectEcho = 0;
            }
        }
    }

    public void setAmpli(float v) {
        int i = this.channelPlay;
        if (i != 0) {
            if (v != 0.0f) {
                if (this.amplifyFx == 0) {
                    this.amplifyFx = BASS.BASS_ChannelSetFX(i, 65544, 0);
                }
                if (this.amplifyFx != 0) {
                    BASS_FX.BASS_BFX_DAMP bass_bfx_damp = new BASS_FX.BASS_BFX_DAMP();
                    BASS.BASS_FXGetParameters(this.amplifyFx, bass_bfx_damp);
                    bass_bfx_damp.fGain = v;
                    BASS.BASS_FXSetParameters(this.amplifyFx, bass_bfx_damp);
                    return;
                }
                return;
            }
            int i2 = this.amplifyFx;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.amplifyFx = 0;
            }
        }
    }

    public void disortSet(float[] floats) {
        int i = this.channelPlay;
        if (i != 0) {
            if (floats != null) {
                if (this.effectDisort == 0) {
                    this.effectDisort = BASS.BASS_ChannelSetFX(i, 2, 0);
                }
                if (this.effectDisort != 0) {
                    BASS.BASS_DX8_DISTORTION bassDx8Distortion = new BASS.BASS_DX8_DISTORTION();
                    BASS.BASS_FXGetParameters(this.effectDisort, bassDx8Distortion);
                    bassDx8Distortion.fEdge = floats[0];
                    bassDx8Distortion.fGain = floats[1];
                    bassDx8Distortion.fPostEQBandwidth = floats[2];
                    bassDx8Distortion.fPostEQCenterFrequency = floats[3];
                    bassDx8Distortion.fPreLowpassCutoff = floats[4];
                    BASS.BASS_FXSetParameters(this.effectDisort, bassDx8Distortion);
                    return;
                }
                return;
            }
            int i2 = this.effectDisort;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.effectDisort = 0;
            }
        }
    }

    public void chorusSet(float[] floats) {
        int i = this.channelPlay;
        if (i != 0) {
            if (floats != null) {
                if (this.chorosEffectFx == 0) {
                    this.chorosEffectFx = BASS.BASS_ChannelSetFX(i, BASS_FX.BASS_FX_BFX_CHORUS, 0);
                }
                if (this.chorosEffectFx != 0) {
                    BASS_FX.BASS_BFX_CHORUS bassBfxChorus = new BASS_FX.BASS_BFX_CHORUS();
                    BASS.BASS_FXGetParameters(this.chorosEffectFx, bassBfxChorus);
                    bassBfxChorus.fDryMix = floats[0];
                    bassBfxChorus.fWetMix = floats[1];
                    bassBfxChorus.fFeedback = floats[2];
                    bassBfxChorus.fMinSweep = floats[3];
                    bassBfxChorus.fMaxSweep = floats[4];
                    bassBfxChorus.fRate = floats[5];
                    BASS.BASS_FXSetParameters(this.chorosEffectFx, bassBfxChorus);
                    return;
                }
                return;
            }
            int i2 = this.chorosEffectFx;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.chorosEffectFx = 0;
            }
        }
    }

    public void setFilterQuad(float[] floats) {
        int i = this.channelPlay;
        if (i != 0) {
            if (floats != null) {
                if (this.bigQuedEffects == 0) {
                    this.bigQuedEffects = BASS.BASS_ChannelSetFX(i, 65555, 0);
                }
                if (this.bigQuedEffects != 0) {
                    BASS_FX.BASS_BFX_BQF bassBfxBqf = new BASS_FX.BASS_BFX_BQF();
                    BASS.BASS_FXGetParameters(this.bigQuedEffects, bassBfxBqf);
                    bassBfxBqf.lFilter = (int) floats[0];
                    bassBfxBqf.fCenter = floats[1];
                    bassBfxBqf.fBandwidth = floats[2];
                    BASS.BASS_FXSetParameters(this.bigQuedEffects, bassBfxBqf);
                    return;
                }
                return;
            }
            int i2 = this.bigQuedEffects;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.bigQuedEffects = 0;
            }
        }
    }

    public void setEffectEcho4(float[] effectEcho4) {
        int i = this.channelPlay;
        if (i != 0) {
            if (effectEcho4 != null) {
                if (this.effextEQ4 == 0) {
                    this.effextEQ4 = BASS.BASS_ChannelSetFX(i, 65556, 0);
                }
                if (this.effextEQ4 != 0) {
                    BASS_FX.BASS_BFX_ECHO4 bassBfxEcho4 = new BASS_FX.BASS_BFX_ECHO4();
                    bassBfxEcho4.fDryMix = (int) effectEcho4[0];
                    bassBfxEcho4.fWetMix = effectEcho4[1];
                    bassBfxEcho4.fFeedback = effectEcho4[2];
                    bassBfxEcho4.fDelay = effectEcho4[3];
                    bassBfxEcho4.bStereo = false;
                    BASS.BASS_FXSetParameters(this.effextEQ4, bassBfxEcho4);
                    return;
                }
                return;
            }
            int i2 = this.effextEQ4;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.effextEQ4 = 0;
            }
        }
    }

    public void setEQ1Audio(float[] EQ1Audio) {
        int i = this.channelPlay;
        if (i != 0) {
            if (EQ1Audio != null) {
                if (this.effextEQ1 == 0) {
                    this.effextEQ1 = BASS.BASS_ChannelSetFX(i, 7, 0);
                }
                if (this.effextEQ1 != 0) {
                    BASS.BASS_DX8_PARAMEQ bassDx8Parameq = new BASS.BASS_DX8_PARAMEQ();
                    bassDx8Parameq.fCenter = EQ1Audio[0];
                    bassDx8Parameq.fBandwidth = EQ1Audio[1];
                    bassDx8Parameq.fGain = EQ1Audio[2];
                    BASS.BASS_FXSetParameters(this.effextEQ1, bassDx8Parameq);
                    return;
                }
                return;
            }
            int i2 = this.effextEQ1;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.effextEQ1 = 0;
            }
        }
    }

    public void setEQ2Audio(float[] values) {
        int i = this.channelPlay;
        if (i != 0) {
            if (values != null) {
                if (this.effectEQ2 == 0) {
                    this.effectEQ2 = BASS.BASS_ChannelSetFX(i, 7, 0);
                }
                if (this.effectEQ2 != 0) {
                    BASS.BASS_DX8_PARAMEQ bassDx8Parameq = new BASS.BASS_DX8_PARAMEQ();
                    bassDx8Parameq.fCenter = values[0];
                    bassDx8Parameq.fBandwidth = values[1];
                    bassDx8Parameq.fGain = values[2];
                    BASS.BASS_FXSetParameters(this.effectEQ2, bassDx8Parameq);
                    return;
                }
                return;
            }
            int i2 = this.effectEQ2;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.effectEQ2 = 0;
            }
        }
    }

    public void setEQ3Audio(float[] EQ3Audio) {
        int i = this.channelPlay;
        if (i != 0) {
            if (EQ3Audio != null) {
                if (this.effectEQ3 == 0) {
                    this.effectEQ3 = BASS.BASS_ChannelSetFX(i, 7, 0);
                }
                if (this.effectEQ3 != 0) {
                    BASS.BASS_DX8_PARAMEQ bassDx8Parameq = new BASS.BASS_DX8_PARAMEQ();
                    bassDx8Parameq.fCenter = EQ3Audio[0];
                    bassDx8Parameq.fBandwidth = EQ3Audio[1];
                    bassDx8Parameq.fGain = EQ3Audio[2];
                    BASS.BASS_FXSetParameters(this.effectEQ3, bassDx8Parameq);
                    return;
                }
                return;
            }
            int i2 = this.effectEQ3;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.effectEQ3 = 0;
            }
        }
    }

    public void rotateSet(float values) {
        int i = this.channelPlay;
        if (i != 0) {
            if (values != 0.0f) {
                if (this.effectRotate == 0) {
                    this.effectRotate = BASS.BASS_ChannelSetFX(i, 65536, 0);
                }
                if (this.effectRotate != 0) {
                    BASS_FX.BASS_BFX_ROTATE bassBfxRotate = new BASS_FX.BASS_BFX_ROTATE();
                    BASS.BASS_FXGetParameters(this.effectRotate, bassBfxRotate);
                    bassBfxRotate.fRate = values;
                    BASS.BASS_FXSetParameters(this.effectRotate, bassBfxRotate);
                    return;
                }
                return;
            }
            int i2 = this.effectRotate;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.effectRotate = 0;
            }
        }
    }

    public void phrserSet(float[] values) {
        int i = this.channelPlay;
        if (i != 0) {
            if (values != null) {
                if (this.effectPhaser == 0) {
                    this.effectPhaser = BASS.BASS_ChannelSetFX(i, 65547, 0);
                }
                if (this.effectPhaser != 0) {
                    BASS_FX.BASS_BFX_PHASER bassBfxPhaser = new BASS_FX.BASS_BFX_PHASER();
                    BASS.BASS_FXGetParameters(this.effectPhaser, bassBfxPhaser);
                    bassBfxPhaser.fDryMix = values[0];
                    bassBfxPhaser.fWetMix = values[1];
                    bassBfxPhaser.fFeedback = values[2];
                    bassBfxPhaser.fRate = values[3];
                    bassBfxPhaser.fRange = values[4];
                    bassBfxPhaser.fFreq = values[5];
                    boolean BASS_FXSetParameters = BASS.BASS_FXSetParameters(this.effectPhaser, bassBfxPhaser);
                    return;
                }
                return;
            }
            int i2 = this.effectPhaser;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.effectPhaser = 0;
            }
        }
    }

    public void compressorSet(float[] values) {
        int i = this.channelPlay;
        if (i != 0) {
            if (values != null) {
                if (this.compressorEffects == 0) {
                    this.compressorEffects = BASS.BASS_ChannelSetFX(i, 65553, 0);
                }
                if (this.effectPhaser != 0) {
                    BASS_FX.BASS_BFX_COMPRESSOR2 bassBfxCompressor2 = new BASS_FX.BASS_BFX_COMPRESSOR2();
                    BASS.BASS_FXGetParameters(this.compressorEffects, bassBfxCompressor2);
                    bassBfxCompressor2.fGain = values[0];
                    bassBfxCompressor2.fThreshold = values[1];
                    bassBfxCompressor2.fRatio = values[2];
                    bassBfxCompressor2.fAttack = values[3];
                    bassBfxCompressor2.fRelease = values[4];
                    BASS.BASS_FXSetParameters(this.compressorEffects, bassBfxCompressor2);
                    return;
                }
                return;
            }
            int i2 = this.compressorEffects;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.compressorEffects = 0;
            }
        }
    }

    public void setWahAuto(float[] values) {
        int i = this.channelPlay;
        if (i != 0) {
            if (values != null) {
                if (this.autoEffectFx == 0) {
                    this.autoEffectFx = BASS.BASS_ChannelSetFX(i, 65547, 0);
                }
                if (this.autoEffectFx != 0) {
                    BASS_FX.BASS_BFX_PHASER bassBfxPhaser = new BASS_FX.BASS_BFX_PHASER();
                    BASS.BASS_FXGetParameters(this.autoEffectFx, bassBfxPhaser);
                    bassBfxPhaser.fDryMix = values[0];
                    bassBfxPhaser.fWetMix = values[1];
                    bassBfxPhaser.fFeedback = values[2];
                    bassBfxPhaser.fRate = values[3];
                    bassBfxPhaser.fRange = values[4];
                    bassBfxPhaser.fFreq = values[5];
                    BASS.BASS_FXSetParameters(this.autoEffectFx, bassBfxPhaser);
                    return;
                }
                return;
            }
            int i2 = this.autoEffectFx;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.autoEffectFx = 0;
            }
        }
    }

    public void setEffectFlang(float[] values) {
        int i = this.channelPlay;
        if (i != 0) {
            if (values != null) {
                if (this.effectFlenger == 0) {
                    this.effectFlenger = BASS.BASS_ChannelSetFX(i, 4, 0);
                }
                if (this.effectFlenger != 0) {
                    try {
                        BASS.BASS_DX8_FLANGER bassDx8Flanger = new BASS.BASS_DX8_FLANGER();
                        BASS.BASS_FXGetParameters(this.effectFlenger, bassDx8Flanger);
                        bassDx8Flanger.fWetDryMix = values[0];
                        bassDx8Flanger.fDepth = values[1];
                        bassDx8Flanger.fFeedback = values[2];
                        bassDx8Flanger.fDelay = values[3];
                        bassDx8Flanger.lPhase = (int) values[4];
                        if (values.length == 6) {
                            bassDx8Flanger.fFrequency = values[5];
                        }
                        BASS.BASS_FXSetParameters(this.effectFlenger, bassDx8Flanger);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                return;
            }
            int i2 = this.effectFlenger;
            if (i2 != 0) {
                BASS.BASS_ChannelRemoveFX(i, i2);
                this.effectFlenger = 0;
            }
        }
    }

    public void audioPause() {
        if (!this.isPlaying) {
            new Exception(logTag + " pauseAudio:HanetMediaPlayer not init").printStackTrace();
            return;
        }
        int i = this.channelPlay;
        if (i != 0) {
            BASS.BASS_ChannelPause(i);
        }
    }

    public void audioRelease() {
        this.mHandler.removeMessages(0);
        int i = this.effectReverb;
        if (i != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i);
            this.effectReverb = 0;
        }
        int i2 = this.effectFlenger;
        if (i2 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i2);
            this.effectReverb = 0;
        }
        int i3 = this.effectEcho;
        if (i3 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i3);
            this.effectEcho = 0;
        }
        int i4 = this.bigQuedEffects;
        if (i4 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i4);
            this.bigQuedEffects = 0;
        }
        int i5 = this.amplifyFx;
        if (i5 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i5);
            this.amplifyFx = 0;
        }
        int i6 = this.effectDisort;
        if (i6 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i6);
            this.effectDisort = 0;
        }
        int i7 = this.chorosEffectFx;
        if (i7 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i7);
            this.chorosEffectFx = 0;
        }
        int i8 = this.effextEQ4;
        if (i8 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i8);
            this.effextEQ4 = 0;
        }
        int i9 = this.effextEQ1;
        if (i9 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i9);
            this.effextEQ1 = 0;
        }
        int i10 = this.effectEQ2;
        if (i10 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i10);
            this.effectEQ2 = 0;
        }
        int i11 = this.effectEQ3;
        if (i11 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i11);
            this.effectEQ3 = 0;
        }
        int i12 = this.effectRotate;
        if (i12 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i12);
            this.effectRotate = 0;
        }
        int i13 = this.effectPhaser;
        if (i13 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i13);
            this.effectPhaser = 0;
        }
        int i14 = this.autoEffectFx;
        if (i14 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i14);
            this.autoEffectFx = 0;
        }
        int i15 = this.compressorEffects;
        if (i15 != 0) {
            BASS.BASS_ChannelRemoveFX(this.channelPlay, i15);
            this.compressorEffects = 0;
        }
        this.isPlaying = false;
        BASS.BASS_StreamFree(this.channelPlay);
        BASS.BASS_StreamFree(this.tempChanel);
        ArrayList<Integer> arrayList = this.integerArrayList;
        if (arrayList != null && arrayList.size() > 0) {
            Iterator<Integer> it = this.integerArrayList.iterator();
            while (it.hasNext()) {
                BASS.BASS_StreamFree(it.next().intValue());
            }
            this.integerArrayList.clear();
            this.integerArrayList = null;
        }
        this.tempChanel = 0;
        this.channelPlay = 0;
    }

    public void setOnDBMediaListener(DBMediaListener DBMediaListener) {
        this.mediaListener = DBMediaListener;
    }

    public int getIntDuration() {
        if (this.channelPlay != 0) {
            this.intDuration = getChanLength();
        }
        return this.intDuration;
    }

    public int getIntCurrPosition() {
        return this.intCurrPosition;
    }

    public void toSeek(int i) {
        if (!this.isPlaying) {
            new Exception(logTag + " seekTo:HanetMediaPlayer is not playing").printStackTrace();
            return;
        }
        this.intCurrPosition = i;
        seekToChannel(i);
    }

    private void mediaInit() {
        audioRelease();
        if (strMediaPath != null || !strMediaPath.equals("")) {
            if (!this.isMixNeed) {
                this.channelPlay = BASS.BASS_StreamCreateFile(this.strMediaPath, 0L, 0L, 2097152);
            } else {
                String logTag1 = logTag;
                Log.d(logTag1, "========>BASS_Error=" + BASS.BASS_ErrorGetCode());
                initMixToMedia(false);
            }
        }
        String logTag1 = logTag;
        int i = this.channelPlay;
        if (i != 0) {
            if (!this.isMixNeed) {
                this.channelPlay = BASS_FX.BASS_FX_ReverseCreate(i, 2.0f, 2162688);
            }
            if (this.channelPlay != 0) {
                BASS.BASS_ChannelGetInfo(this.channelPlay, new BASS.BASS_CHANNELINFO());
                int i1 = BASS_FX.BASS_FX_TempoCreate(this.channelPlay, 65536);
                this.channelPlay = i1;
                if (i1 == 0) {
                    Log.d(logTag1, "========>BASS_Error=" + BASS.BASS_ErrorGetCode());
                    new Exception(logTag1 + " Couldnt create a resampled stream!").printStackTrace();
                    BASS.BASS_StreamFree(this.channelPlay);
                    return;
                }
                return;
            }
            Log.d(logTag1, "========>BASS_Error=" + BASS.BASS_ErrorGetCode());
            new Exception(logTag1 + " Couldnt create a resampled stream!").printStackTrace();
            BASS.BASS_StreamFree(this.channelPlay);
            return;
        }
        Log.d(logTag1, "========>BASS_Error=" + BASS.BASS_ErrorGetCode());
        new Exception(logTag1 + " Couldnt create a resampled stream!").printStackTrace();
        BASS.BASS_StreamFree(this.channelPlay);
    }


    private void initMixToMedia(boolean b) {
        File tempFile;
        int BASS_Mixer_StreamCreate;
        int i;
        int i2;
        long j;
        if (!this.isMixNeed || (tempFile = getTempDirectory()) == null || !tempFile.isDirectory() || strMixPath == null || strMixPath.equals("")) {
            return;
        }
        File file11 = new File(tempFile, this.strMixPath);
        if (file11.exists() && file11.isFile() && (BASS_Mixer_StreamCreate = BASSmix.BASS_Mixer_StreamCreate(44100, 2, 2097152)) != 0) {
            int i1 = BASS.BASS_StreamCreateFile(this.strMediaPath, 0L, 0L, 2097152);
            if (i1 != 0) {
                boolean streamAddChannel = BASSmix.BASS_Mixer_StreamAddChannel(BASS_Mixer_StreamCreate, i1, 8388608);
                String str = logTag;
                if (!streamAddChannel) {
                    BASS.BASS_StreamFree(BASS_Mixer_StreamCreate);
                    this.channelPlay = i1;
                    return;
                }
                this.channelPlay = BASS_Mixer_StreamCreate;
                this.tempChanel = i1;
                long l = BASS.BASS_ChannelSeconds2Bytes(BASS_Mixer_StreamCreate, 3.0d);
                long l1 = BASS.BASS_ChannelSeconds2Bytes(BASS_Mixer_StreamCreate, 1.0d);
                int length = getChanLength();
                if (length > 0) {
                    this.integerArrayList = new ArrayList<>();
                    long l2 = BASS.BASS_ChannelSeconds2Bytes(BASS_Mixer_StreamCreate, length);
                    int i3 = 0;
                    while (i3 < length) {
                        if (i3 % 3 == 0) {
                            long j2 = i3 * l1;
                            String str2 = logTag;
                            StringBuilder builder = new StringBuilder();
                            i = length;
                            builder.append("=====>start=");
                            builder.append(j2);
                            builder.append("==>totalDuration=");
                            builder.append(l2);
                            builder.append("==>start+lenght=");
                            boolean z = streamAddChannel;
                            long j3 = j2 + l;
                            builder.append(j3);
                            int i4 = BASS.BASS_StreamCreateFile(file11.getAbsolutePath(), 0L, 0L, 2097152);
                            if (i4 != 0) {
                                if (j3 < l2) {
                                    i2 = i3;
                                    j = l2;
                                    streamAddChannel = BASSmix.BASS_Mixer_StreamAddChannelEx(BASS_Mixer_StreamCreate, i4, 8388608, j2, l);
                                } else {
                                    i2 = i3;
                                    j = l2;
                                    long j4 = j - j2;
                                    if (j4 > 0) {
                                        streamAddChannel = BASSmix.BASS_Mixer_StreamAddChannelEx(BASS_Mixer_StreamCreate, i4, 8388608, j2, j4);
                                    }
                                }
                                if (streamAddChannel) {
                                    BASS.BASS_StreamFree(i4);
                                } else {
                                    this.integerArrayList.add(Integer.valueOf(i4));
                                }
                            } else {
                                i2 = i3;
                                j = l2;
                            }
                            streamAddChannel = z;
                            if (streamAddChannel) {
                            }
                        } else {
                            i = length;
                            i2 = i3;
                            j = l2;
                        }
                        i3 = i2 + 1;
                        length = i;
                        l2 = j;
                    }
                    return;
                }
                return;
            }
            BASS.BASS_StreamFree(BASS_Mixer_StreamCreate);
        }
    }

    public boolean initSolveToMedia() {
        BASS.BASS_StreamFree(this.channelPlay);
        int i1 = BASS.BASS_StreamCreateFile(this.strMediaPath, 0L, 0L, 2097152);
        this.channelPlay = i1;
        if (i1 != 0) {
            if (!this.isMixNeed) {
                this.channelPlay = BASS_FX.BASS_FX_ReverseCreate(i1, 2.0f, 2097152);
            } else {
                initMixToMedia(true);
            }
            String logTag1 = logTag;
            int i = this.channelPlay;
            if (i != 0) {
                int i2 = BASS_FX.BASS_FX_TempoCreate(i, 2097152);
                this.channelPlay = i2;
                if (i2 == 0) {
                    new Exception(logTag1 + " Couldnt create a resampled stream!").printStackTrace();
                    BASS.BASS_StreamFree(this.channelPlay);
                    return false;
                }
                return true;
            }
            new Exception(logTag1 + " Couldnt create a resampled stream!").printStackTrace();
            BASS.BASS_StreamFree(this.channelPlay);
        }
        return false;
    }

    public void seekToChannel(int pos) {
        int i = this.channelPlay;
        if (i != 0) {
            BASS.BASS_ChannelSetPosition(i, BASS.BASS_ChannelSeconds2Bytes(i, pos), 0);
        }
    }


    public int getChannelPos() {
        double v;
        int i = this.tempChanel;
        if (i != 0) {
            v = BASS.BASS_ChannelBytes2Seconds(i, BASS.BASS_ChannelGetPosition(i, 0));
        } else {
            int i2 = this.channelPlay;
            if (i2 == 0) {
                return -1;
            }
            v = BASS.BASS_ChannelBytes2Seconds(i2, BASS.BASS_ChannelGetPosition(i2, 0));
        }
        return (int) v;
    }


    public int getChanLength() {
        double v;
        int i = this.tempChanel;
        if (i != 0) {
            v = BASS.BASS_ChannelBytes2Seconds(i, BASS.BASS_ChannelGetLength(i, 0));
        } else {
            int play = this.channelPlay;
            if (play == 0) {
                return 0;
            }
            v = BASS.BASS_ChannelBytes2Seconds(play, BASS.BASS_ChannelGetLength(play, 0));
        }
        return (int) v;
    }

    public void setThisReverse(boolean b) {
        this.isThisReverse = b;
        int i = this.channelPlay;
        if (i != 0) {
            int i1 = BASS_FX.BASS_FX_TempoGetSource(i);
            BASS.FloatValue value = new BASS.FloatValue();
            value.value = 0.0f;
            BASS.BASS_ChannelGetAttribute(i1, BASS_FX.BASS_ATTRIB_REVERSE_DIR, value);
            if (b) {
                BASS.BASS_ChannelSetAttribute(i1, BASS_FX.BASS_ATTRIB_REVERSE_DIR, -1.0f);
            } else {
                BASS.BASS_ChannelSetAttribute(i1, BASS_FX.BASS_ATTRIB_REVERSE_DIR, 1.0f);
            }
        }
    }

    public void saveAsFile(String strFilePath) {
        int pos;
        int i;

        if (strFilePath == null || strFilePath.equals("") || (pos = this.channelPlay) == 0 || BASSenc.BASS_Encode_Start(pos, strFilePath, 262208, null, 0) == 0) {
            return;
        }
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(20000);
            do {
                i = BASS.BASS_ChannelGetData(this.channelPlay, byteBuffer, byteBuffer.capacity());
                if (i == -1) {
                    return;
                }
            } while (i != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getTempDirectory() {
        try {
            if (!Environment.getExternalStorageState().equals("mounted")) {
                return null;
            }
            File file = new File(Environment.getExternalStorageDirectory() , InterfaceVoiceChangerListener.recordedFolderName);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, InterfaceVoiceChangerListener.folderTemp);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            return file2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
