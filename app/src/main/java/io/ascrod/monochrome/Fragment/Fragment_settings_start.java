package io.ascrod.monochrome.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;

import io.ascrod.monochrome.Activity.Whitelist_Cookie;
import io.ascrod.monochrome.Activity.Whitelist_Javascript;
import io.ascrod.monochrome.Activity.Whitelist_AdBlock;
import io.ascrod.monochrome.Ninja.R;
import io.ascrod.monochrome.Task.ExportWhitelistCookieTask;
import io.ascrod.monochrome.Task.ExportWhitelistJSTask;
import io.ascrod.monochrome.Task.ExportWhitelistAdBlockTask;
import io.ascrod.monochrome.Task.ImportWhitelistAdBlockTask;
import io.ascrod.monochrome.Task.ImportWhitelistCookieTask;
import io.ascrod.monochrome.Task.ImportWhitelistJSTask;

public class Fragment_settings_start extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    @SuppressWarnings({"FieldCanBeLocal", "unused"})
    private boolean spChange = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_start);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
        sp.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }


    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        switch (preference.getTitleRes()) {
            case R.string.setting_title_whitelist:
                Intent toWhitelist = new Intent(getActivity(), Whitelist_AdBlock.class);
                getActivity().startActivity(toWhitelist);
                break;
            case R.string.setting_title_whitelistJS:
                Intent toJavascript = new Intent(getActivity(), Whitelist_Javascript.class);
                getActivity().startActivity(toJavascript);
                break;
            case R.string.setting_title_whitelistCookie:
                Intent toCookie = new Intent(getActivity(), Whitelist_Cookie.class);
                getActivity().startActivity(toCookie);
                break;
            case R.string.setting_title_export_whitelist:
                new ExportWhitelistAdBlockTask(getActivity()).execute();
                break;
            case R.string.setting_title_import_whitelist:
                new ImportWhitelistAdBlockTask(getActivity()).execute();
                break;

            case R.string.setting_title_export_whitelistJS:
                new ExportWhitelistJSTask(getActivity()).execute();
                break;
            case R.string.setting_title_import_whitelistJS:
                new ImportWhitelistJSTask(getActivity()).execute();
                break;
            case R.string.setting_title_export_whitelistCookie:
                new ExportWhitelistCookieTask(getActivity()).execute();
                break;
            case R.string.setting_title_import_whitelistCookie:
                new ImportWhitelistCookieTask(getActivity()).execute();
                break;

            default:
                break;
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sp, String key) {
        spChange = true;
    }
}
