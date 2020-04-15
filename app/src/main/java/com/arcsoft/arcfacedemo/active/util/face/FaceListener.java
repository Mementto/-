package com.arcsoft.arcfacedemo.active.util.face;


import android.hardware.Camera;

import com.arcsoft.face.LivenessInfo;

import androidx.annotation.Nullable;

/**
 * 人脸处理回调
 */
public interface FaceListener {
    /**
     * 当出现异常时执行
     *
     * @param e 异常信息
     */
    void onFail(Exception e);

    /**
     * 请求活体检测后的回调
     *
     * @param livenessInfo 活体检测结果
     * @param requestId    请求码
     * @param errorCode    错误码
     */
    void onFaceLivenessInfoGet(@Nullable LivenessInfo livenessInfo, Integer requestId, Integer errorCode, byte[] nv21, Camera camera);
}
