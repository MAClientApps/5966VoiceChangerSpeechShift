package com.reactlibrary.dataMng;

import com.reactlibrary.constants.InterfaceVoiceChangerListener;
import com.reactlibrary.object.ModelEffects;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParsingJsonObjects implements InterfaceVoiceChangerListener {
    public static final String TAG = "JsonParsingUtils";

    public static ModelEffects jsonToObjectEffects(String strVar0) {
        if (strVar0 != null || !strVar0.equals("")) {
            JSONException var10000;
            label553:
            {
                int var1;
                int var2;
                boolean var3;
                JSONObject var4;
                String var5;
                String var6;
                label517:
                {
                    try {
                        var4 = new JSONObject(strVar0);
                        var6 = var4.getString("id");
                        var5 = var4.getString("name");
                        var1 = var4.getInt("pitch");
                        var2 = var4.getInt("rate");
                        if (var4.opt("reverse") != null) {
                            var3 = var4.getBoolean("reverse");
                            break label517;
                        }
                    } catch (JSONException var62) {
                        var10000 = var62;
                        break label553;
                    }

                    var3 = false;
                }

                ModelEffects var63;
                try {
                    var63 = new ModelEffects(var6, var5, var1, (float) var2);
                    var63.setReverseBool(var3);
                    if (var4.opt("amplify") != null) {
                        var63.setFloatAmplify((float) var4.getDouble("amplify"));
                    }
                } catch (JSONException var61) {
                    var10000 = var61;
                    break label553;
                }

                try {
                    if (var4.opt("isMix") != null) {
                        var63.setMixBool(var4.getBoolean("isMix"));
                        var63.setStrPathMix(var4.getString("path"));
                    }
                } catch (JSONException var60) {
                    var10000 = var60;
                    break label553;
                }

                try {
                    if (var4.opt("rotate") != null) {
                        var63.setFloatRotate((float) var4.getDouble("rotate"));
                    }
                } catch (JSONException var59) {
                    var10000 = var59;
                    break label553;
                }

                float[] var66;
                JSONArray var67;
                label499:
                {
                    try {
                        if (var4.opt("reverb") == null) {
                            break label499;
                        }

                        var67 = var4.getJSONArray("reverb");
                        var2 = var67.length();
                    } catch (JSONException var58) {
                        var10000 = var58;
                        break label553;
                    }

                    if (var2 > 0) {
                        var66 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var66[var1] = (float) var67.getDouble(var1);
                            } catch (JSONException var56) {
                                var10000 = var56;
                                break label553;
                            }
                        }

                        var63.setFloatReverb(var66);
                    }
                }

                label480:
                {
                    try {
                        if (var4.opt("distort") == null) {
                            break label480;
                        }

                        var67 = var4.getJSONArray("distort");
                        var2 = var67.length();
                    } catch (JSONException var54) {
                        var10000 = var54;
                        break label553;
                    }

                    if (var2 > 0) {
                        var66 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var66[var1] = (float) var67.getDouble(var1);
                            } catch (JSONException var52) {
                                var10000 = var52;
                                break label553;
                            }
                        }

                        var63.setFloatDistort(var66);
                    }
                }

                label461:
                {
                    try {
                        if (var4.opt("chorus") == null) {
                            break label461;
                        }

                        var67 = var4.getJSONArray("chorus");
                        var2 = var67.length();
                    } catch (JSONException var50) {
                        var10000 = var50;
                        break label553;
                    }

                    if (var2 > 0) {
                        var66 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var66[var1] = (float) var67.getDouble(var1);
                            } catch (JSONException var48) {
                                var10000 = var48;
                                break label553;
                            }
                        }

                        var63.setFloatChorus(var66);
                    }
                }

                JSONArray var68;
                float[] var69;
                label442:
                {
                    try {
                        if (var4.opt("flanger") == null) {
                            break label442;
                        }

                        var68 = var4.getJSONArray("flanger");
                        var2 = var68.length();
                    } catch (JSONException var46) {
                        var10000 = var46;
                        break label553;
                    }

                    if (var2 > 0) {
                        var69 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var69[var1] = (float) var68.getDouble(var1);
                            } catch (JSONException var44) {
                                var10000 = var44;
                                break label553;
                            }
                        }

                        var63.setFloatFlange(var69);
                    }
                }

                label423:
                {
                    try {
                        if (var4.opt("filter") == null) {
                            break label423;
                        }

                        var67 = var4.getJSONArray("filter");
                        var2 = var67.length();
                    } catch (JSONException var42) {
                        var10000 = var42;
                        break label553;
                    }

                    if (var2 > 0) {
                        var66 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var66[var1] = (float) var67.getDouble(var1);
                            } catch (JSONException var40) {
                                var10000 = var40;
                                break label553;
                            }
                        }

                        var63.setFloatFilter(var66);
                    }
                }

                label404:
                {
                    try {
                        if (var4.opt("echo") == null) {
                            break label404;
                        }

                        var67 = var4.getJSONArray("echo");
                        var2 = var67.length();
                    } catch (JSONException var38) {
                        var10000 = var38;
                        break label553;
                    }

                    if (var2 > 0) {
                        var66 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var66[var1] = (float) var67.getDouble(var1);
                            } catch (JSONException var36) {
                                var10000 = var36;
                                break label553;
                            }
                        }

                        var63.setFloatEcho(var66);
                    }
                }

                label385:
                {
                    try {
                        if (var4.opt("echo4") == null) {
                            break label385;
                        }

                        var68 = var4.getJSONArray("echo4");
                        var2 = var68.length();
                    } catch (JSONException var34) {
                        var10000 = var34;
                        break label553;
                    }

                    if (var2 > 0) {
                        var69 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var69[var1] = (float) var68.getDouble(var1);
                            } catch (JSONException var32) {
                                var10000 = var32;
                                break label553;
                            }
                        }

                        var63.setFloatEcho4(var69);
                    }
                }

                label366:
                {
                    try {
                        if (var4.opt("eq1") == null) {
                            break label366;
                        }

                        var67 = var4.getJSONArray("eq1");
                        var2 = var67.length();
                    } catch (JSONException var30) {
                        var10000 = var30;
                        break label553;
                    }

                    if (var2 > 0) {
                        var66 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var66[var1] = (float) var67.getDouble(var1);
                            } catch (JSONException var28) {
                                var10000 = var28;
                                break label553;
                            }
                        }

                        var63.setFloatyEcho1(var66);
                    }
                }

                label347:
                {
                    try {
                        if (var4.opt("eq2") == null) {
                            break label347;
                        }

                        var68 = var4.getJSONArray("eq2");
                        var2 = var68.length();
                    } catch (JSONException var26) {
                        var10000 = var26;
                        break label553;
                    }

                    if (var2 > 0) {
                        var69 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var69[var1] = (float) var68.getDouble(var1);
                            } catch (JSONException var24) {
                                var10000 = var24;
                                break label553;
                            }
                        }

                        var63.setFloatEq2(var69);
                    }
                }

                label328:
                {
                    try {
                        if (var4.opt("eq3") == null) {
                            break label328;
                        }

                        var67 = var4.getJSONArray("eq3");
                        var2 = var67.length();
                    } catch (JSONException var22) {
                        var10000 = var22;
                        break label553;
                    }

                    if (var2 > 0) {
                        var66 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var66[var1] = (float) var67.getDouble(var1);
                            } catch (JSONException var20) {
                                var10000 = var20;
                                break label553;
                            }
                        }

                        var63.setFloatEq3(var66);
                    }
                }

                label309:
                {
                    try {
                        if (var4.opt("phaser") == null) {
                            break label309;
                        }

                        var68 = var4.getJSONArray("phaser");
                        var2 = var68.length();
                    } catch (JSONException var18) {
                        var10000 = var18;
                        break label553;
                    }

                    if (var2 > 0) {
                        var69 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var69[var1] = (float) var68.getDouble(var1);
                            } catch (JSONException var16) {
                                var10000 = var16;
                                break label553;
                            }
                        }

                        var63.setFloatPhaser(var69);
                    }
                }

                label290:
                {
                    try {
                        if (var4.opt("autowah") == null) {
                            break label290;
                        }

                        var67 = var4.getJSONArray("autowah");
                        var2 = var67.length();
                    } catch (JSONException var14) {
                        var10000 = var14;
                        break label553;
                    }

                    if (var2 > 0) {
                        var66 = new float[var2];

                        for (var1 = 0; var1 < var2; ++var1) {
                            try {
                                var66[var1] = (float) var67.getDouble(var1);
                            } catch (JSONException var12) {
                                var10000 = var12;
                                break label553;
                            }
                        }

                        var63.setFloatAutoWah(var66);
                    }
                }

                try {
                    if (var4.opt("compressor") == null) {
                        return var63;
                    }

                    var68 = var4.getJSONArray("compressor");
                    var2 = var68.length();
                } catch (JSONException var10) {
                    var10000 = var10;
                    break label553;
                }

                if (var2 > 0) {
                    float[] var65;
                    var65 = new float[var2];

                    for (var1 = 0; var1 < var2; ++var1) {
                        try {
                            var65[var1] = (float) var68.getDouble(var1);
                        } catch (JSONException var8) {
                            var10000 = var8;
                            break label553;
                        }
                    }

                    var63.setFloatCompressor(var65);
                }

                return var63;
            }

            JSONException var64 = var10000;
            var64.printStackTrace();
        }

        return null;
    }

}
