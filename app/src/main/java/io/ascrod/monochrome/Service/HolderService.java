package io.ascrod.monochrome.Service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

import io.ascrod.monochrome.Activity.BrowserActivity;
import io.ascrod.monochrome.Browser.AlbumController;
import io.ascrod.monochrome.Browser.BrowserContainer;
import io.ascrod.monochrome.Browser.BrowserController;
import io.ascrod.monochrome.Ninja.R;
import io.ascrod.monochrome.Unit.*;

public class HolderService extends Service implements BrowserController {
    @Override
    public void updateAutoComplete() {}

    @Override
    public void updateBookmarks() {}

    @Override
    public void updateInputBox(String query) {}

    @Override
    public void updateProgress(int progress) {}

    @Override
    public void showAlbum(AlbumController albumController, boolean expand) {}

    @Override
    public void removeAlbum(AlbumController albumController) {}


    @Override
    public void showFileChooser(ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {}

    @Override
    public void onCreateView(WebView view, Message resultMsg) {}

    @Override
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback callback) {}

    @Override
    public boolean onHideCustomView() {
        return true;
    }

    @Override
    public void onLongPress(String url) {}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Intent toActivity = new Intent(HolderService.this, BrowserActivity.class);
        toActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        toActivity.setAction(Intent.ACTION_SEND);
        toActivity.putExtra(Intent.EXTRA_TEXT, RecordUnit.getHolder().getURL());
        startActivity(toActivity);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        if (IntentUnit.isClear()) {
            BrowserContainer.clear();
        }
        stopForeground(true);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void updateNotification() {
        Notification notification = NotificationUnit.getHBuilder(this).build();
        startForeground(NotificationUnit.HOLDER_ID, notification);
        Toast.makeText(this, R.string.toast_load_in_background, Toast.LENGTH_LONG).show();
    }
}
