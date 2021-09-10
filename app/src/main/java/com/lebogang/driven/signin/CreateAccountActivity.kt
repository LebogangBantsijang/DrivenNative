package com.lebogang.driven.signin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lebogang.driven.HomeActivity
import com.lebogang.driven.R
import com.lebogang.driven.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {
    private val bind:ActivityCreateAccountBinding by lazy {ActivityCreateAccountBinding.inflate(layoutInflater)}
    private val auth: FirebaseAuth by lazy { Firebase.auth }
    private val inputValidator = InputValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        initViews()
    }

    /**
     * Initialize views
     * */
    private fun initViews(){
        bind.loginButton.setOnClickListener {
            val email = bind.emailEditText.text?.toString()
            val password = bind.passwordEditText.text?.toString()
            val passwordOther = bind.passwordOtherEditText.text?.toString()
            if (!inputValidator.isEmailValid(email))
                bind.emailInputLayout.error = getString(R.string.invalid_input)
            else if (inputValidator.isPasswordsValid(password,passwordOther)){
                bind.passwordInputLayout.error = getString(R.string.invalid_input)
                bind.passwordOtherInputLayout.error = getString(R.string.invalid_input)
                showInvalidPasswordDialog()
            }else
                createAccount(email!!,password!!)
        }
    }

    /**
     * Create a new user
     * @param email
     * @param password
     * */
    private fun createAccount(email:String,password:String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful)
                nextActivity()
            else
                Snackbar.make(bind.root, getString(R.string.failed_to_authenticate)
                    , Snackbar.LENGTH_SHORT).show()
        }
    }

    /**
     * Display to the user the password criteria
     * */
    private fun showInvalidPasswordDialog() = MaterialAlertDialogBuilder(this)
        .setTitle(getString(R.string.invalid_input))
        .setMessage(getString(R.string.password_criteria))
        .setNegativeButton(getString(R.string.close), null)
        .setPositiveButton(getString(R.string.okay),null)
        .create().show()

    /**
     * Navigate to the next activity
     * */
    private fun nextActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}