package me.fabiooliveira.androidjetpacksample.feature.person.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import me.fabiooliveira.androidjetpacksample.R
import me.fabiooliveira.androidjetpacksample.feature.person.ui.fragment.PersonFragment

class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.person_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, PersonFragment.newInstance())
                    .commitNow()
        }
    }

}
