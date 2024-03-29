package io.ascrod.monochrome.Task;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import io.ascrod.monochrome.Ninja.R;
import io.ascrod.monochrome.Unit.BrowserUnit;
import io.ascrod.monochrome.View.NinjaToast;

@SuppressLint("StaticFieldLeak")
public class ImportWhitelistJSTask extends AsyncTask<Void, Void, Boolean> {
    private final Context context;
    private BottomSheetDialog dialog;
    private int count;

    public ImportWhitelistJSTask(Activity activity) {
        this.context = activity;
        this.dialog = null;
        this.count = 0;
    }

    @Override
    protected void onPreExecute() {

        dialog = new BottomSheetDialog(context);

        View dialogView = View.inflate(context, R.layout.dialog_progress, null);
        TextView textView = dialogView.findViewById(R.id.dialog_text);
        textView.setText(context.getString(R.string.toast_wait_a_minute));
        dialog.setContentView(dialogView);
        //noinspection ConstantConditions
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        count = BrowserUnit.importWhitelistJS(context);
        return !isCancelled() && count >= 0;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        dialog.hide();
        dialog.dismiss();

        if (result) {
            NinjaToast.show(context, context.getString(R.string.toast_import_successful) + count);
        } else {
            NinjaToast.show(context, R.string.toast_import_failed);
        }
    }
}
