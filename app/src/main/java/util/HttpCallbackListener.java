package util;

/**
 * Created by Administrator on 2016/7/24.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
