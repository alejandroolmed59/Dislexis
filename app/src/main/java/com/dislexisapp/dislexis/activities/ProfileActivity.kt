package com.dislexisapp.dislexis.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dislexisapp.dislexis.AppConstants
import com.dislexisapp.dislexis.R
import com.dislexisapp.dislexis.fragments.DesafioFragment
import com.dislexisapp.dislexis.fragments.MedicoFragment
import com.dislexisapp.dislexis.fragments.PacienteFragment
import com.dislexisapp.dislexis.network.UserRetro
import com.dislexisapp.dislexis.viewModels.UserViewModel

class ProfileActivity : AppCompatActivity(), PacienteFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var user = AppConstants.user
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.getUser(user!!.username!!)
        userViewModel.userLD.observe(this, Observer {
            initMainFragment(it)
        })

    }

    fun initMainFragment(user: UserRetro) {
        if(user!=null) {
            val mainFragment = if (user.isPaciente == "true") PacienteFragment.newInstance(user) else{ MedicoFragment.newInstance("lol","xd")}
            val resource = R.id.main_fragmentProfile
            changeFragment(resource, mainFragment)
        }
    }

    private fun changeFragment(id: Int, frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }
}
