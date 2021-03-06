package uz.texnopos.nogironlikhuquqlari.ui.settings

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import dev.b3nedikt.app_locale.AppLocale
import org.koin.android.ext.android.getKoin
import uz.texnopos.nogironlikhuquqlari.R
import uz.texnopos.nogironlikhuquqlari.core.Constants.CYRIL
import uz.texnopos.nogironlikhuquqlari.core.onClick
import uz.texnopos.nogironlikhuquqlari.databinding.FragmentSettingsBinding
import uz.texnopos.nogironlikhuquqlari.di.dataModule

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var bind: FragmentSettingsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind = FragmentSettingsBinding.bind(view)
        bind.toolbar.toolbarTitle.text = getString(R.string.settings)
        bind.text.movementMethod = LinkMovementMethod.getInstance()
        bind.autoComplete.setText(if (AppLocale.desiredLocale == CYRIL) "Кирилл" else "Lotin")

        val adapter =
            ArrayAdapter(requireContext(), R.layout.item_spinner_locale, listOf("Lotin", "Кирилл"))
        bind.autoComplete.setAdapter(adapter)
        bind.autoComplete.setOnItemClickListener { _, _, position, _ ->
            getKoin().unloadModules(listOf(dataModule))
            getKoin().loadModules(listOf(dataModule))
            AppLocale.desiredLocale = AppLocale.supportedLocales[position]
            bind.toolbar.toolbarTitle.text = getString(R.string.settings)
            bind.text.text = resources.getText(R.string.about)
        }

        bind.text.textSize = Settings.textSize
        bind.currentSize.text = Settings.textSize.toInt().toString()
        bind.increment.onClick {
            if (Settings.textSize < 40) {
                Settings.textSize += 2f
                bind.text.textSize = Settings.textSize
                bind.currentSize.text = Settings.textSize.toInt().toString()
            }
        }
        bind.decrement.onClick {
            if (Settings.textSize > 16) {
                Settings.textSize -= 2f
                bind.text.textSize = Settings.textSize
                bind.currentSize.text = Settings.textSize.toInt().toString()
            }
        }
        bind.toolbar.back.onClick {
            requireActivity().onBackPressed()
        }
    }


}