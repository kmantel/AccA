package mattecarra.accapp

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.multidex.MultiDexApplication
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.topjohnwu.superuser.Shell
import mattecarra.accapp.utils.LogExt
import org.acra.ACRA.init
import org.acra.config.CoreConfigurationBuilder
import org.acra.config.HttpSenderConfigurationBuilder
import org.acra.config.LimiterConfigurationBuilder
import org.acra.config.ToastConfigurationBuilder
import org.acra.data.StringFormat
import org.acra.security.TLS
import org.acra.sender.HttpSender

class MainApplication: MultiDexApplication()
{
    companion object
    {
        var mDEBUG: Int = 0

        init
        {
            Shell.Config.setFlags(Shell.FLAG_REDIRECT_STDERR)
            Shell.Config.verboseLogging(BuildConfig.DEBUG)
            Shell.Config.setTimeout(10)
        }
    }

    @SuppressLint("LogNotTimber")
    override fun onCreate()
    {
        super.onCreate()
        mDEBUG = (getDefaultSharedPreferences(applicationContext).getString("appdebug", "0") ?: "0").toInt()
        LogExt().s(javaClass.simpleName, "DEBUG=$mDEBUG " +when(mDEBUG) {0->"[NONE]" 1->"[CONSOLE]" 2->"[FILE]" else->"[UNKNOWN]"})
    }
}
