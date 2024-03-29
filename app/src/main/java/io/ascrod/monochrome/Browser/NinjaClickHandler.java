package io.ascrod.monochrome.Browser;

import android.os.Handler;
import android.os.Message;
import io.ascrod.monochrome.View.NinjaWebView;

public class NinjaClickHandler extends Handler {
    private final NinjaWebView webView;

    public NinjaClickHandler(NinjaWebView webView) {
        super();
        this.webView = webView;
    }

    @Override
    public void handleMessage(Message message) {
        super.handleMessage(message);
        webView.getBrowserController().onLongPress(message.getData().getString("url"));
    }
}
