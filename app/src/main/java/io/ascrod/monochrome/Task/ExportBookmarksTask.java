package io.ascrod.monochrome.Task;

import android.annotation.SuppressLint;
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
public class ExportBookmarksTask extends AsyncTask<Void, Void, Boolean> {
    private final Context context;
    private BottomSheetDialog dialog;
    private String path;

    public ExportBookmarksTask(Context context) {
        this.context = context;
        this.dialog = null;
        this.path = null;
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
        path = BrowserUnit.exportBookmarks(context);
        return !isCancelled() && path != null && !path.isEmpty();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        dialog.hide();
        dialog.dismiss();

        if (result) {
            NinjaToast.show(context, context.getString(R.string.toast_export_successful) + path);
        } else {
            NinjaToast.show(context, R.string.toast_export_failed);
        }
    }
}
