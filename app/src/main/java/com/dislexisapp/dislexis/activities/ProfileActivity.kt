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
import com.dislexisapp.dislexis.network.Paciente
import com.dislexisapp.dislexis.network.UserRetro
import com.dislexisapp.dislexis.viewModels.UserViewModel

class ProfileActivity : AppCompatActivity(), PacienteFragment.OnFragmentInteractionListener, MedicoFragment.Listener {
    lateinit var userViewModel: UserViewModel
    override fun clickPaciente(paciente: Paciente) {
        userViewModel.getUser(paciente.usernamePaciente)
    }


    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var user = AppConstants.user
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.getUser(user!!.username)
        userViewModel.userLD.observe(this, Observer {
            if (it.isPaciente == "true") initFragmentPaciente(it) else {
                initFragmentMedico(it)
            }
        })

    }

    private fun initFragmentPaciente(user: UserRetro) {

        val mainFragment = PacienteFragment.newInstance(user)
        val resource = R.id.main_fragmentProfile
        changeFragment(resource, mainFragment)
    }

    private fun initFragmentMedico(user: UserRetro) {
        val mainFragment = MedicoFragment.newInstance(user.pacientes)
        val resource = R.id.main_fragmentProfile
        changeFragment(resource, mainFragment)
    }

    private fun changeFragment(id: Int, frag: Fragment) {
        supportFragmentManager.beginTransaction().replace(id, frag).commit()
    }
}
