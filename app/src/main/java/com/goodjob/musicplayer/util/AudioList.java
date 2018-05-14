package com.goodjob.musicplayer.util;

import android.content.Context;
import android.util.Log;

import com.goodjob.musicplayer.entity.Audio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AudioList {
    private static Object mLock = new Object();
    private static List<Audio> mAudioList = null;
    private AudioList() {}

    public static List<Audio> getAudioList(Context context) {
        if (mAudioList == null) {
            synchronized (mLock) {
                if (mAudioList == null) {
                    mAudioList = new ArrayList<>();
                    for (Audio audio : MediaUtils.getAudioList(context)) {
                        if (audio.isMusic() && audio.getDuration() > 40 * 1000) {
                            mAudioList.add(audio);
                        }
                    }
                }
            }
        }
        return Collections.unmodifiableList(mAudioList);
    }

}
