package com.lebogang.driven.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.lebogang.driven.ApiKeys
import com.lebogang.driven.HomeActivity
import com.lebogang.driven.R
import com.lebogang.driven.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val bind:ActivityLoginBinding by lazy{ActivityLoginBinding.inflate(layoutInflater)}
    private val auth:FirebaseAuth by lazy { Firebase.auth }
    private lateinit var activityResults:ActivityResultLauncher<Intent>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isUserLoggedIn())
            nextActivity()
        else{
            setContentView(bind.root)
            activityResults = initializeLauncher()
            initView()
        }
    }

    /**
     * Initialize all views
     * */
    private fun initView(){
        //login using google
        bind.googleButton.setOnClickListener {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(ApiKeys.WEB_CLIENT_ID)
                .requestEmail()
                .build()
            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            activityResults.launch(googleSignInClient.signInIntent)
        }
        //login using email
        bind.loginButton.setOnClickListener {
            val email = bind.emailEditText.text?.toString()
            val password = bind.passwordEditText.text?.toString()
            if (!email.isNullOrBlank() || !password.isNullOrBlank())
                loginEmail(email!!,password!!)
            else
                Snackbar.make(bind.root,getString(R.string.invalid_input),Snackbar.LENGTH_SHORT).show()
        }
    }

    /**
     * Initialize ActivityResultLauncher for google sign in
     * */
    private fun initializeLauncher():ActivityResultLauncher<Intent> = registerForActivityResult(
        StartActivityForResult()) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                val account = task.getResult(ApiException::class.java)
                loginGmail(account.idToken!!)
            }catch (e: ApiException){
                Snackbar.make(bind.root,getString(R.string.failed_to_authenticate)
                    ,Snackbar.LENGTH_SHORT).show()
            }
    }

    /**
     * Check if user is logged in
     * @return true if user is logged in
     * */
    private fun isUserLoggedIn():Boolean = auth.currentUser != null

    /**
     * Log in user using their email and password
     * @param email
     * @param password
     * */
    private fun loginEmail(email:String,password:String) = auth
        .signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful)
                nextActivity()
            else
                Snackbar.make(bind.root, getString(R.string.invalid_input)
                    ,Snackbar.LENGTH_SHORT).show()
        }

    /**
     * Log in user using their google account
     * @param token: this is retrieved from the activity callback $registerForActivityResult
     * */
    private fun loginGmail(token:String){
        val credential = GoogleAuthProvider.getCredential(token,null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful)
                nextActivity()
            else
                Snackbar.make(bind.root, getString(R.string.invalid_input)
                    ,Snackbar.LENGTH_SHORT).show()
        }
    }

    /**
     * Navigate to the next activity
     * */
    private fun nextActivity(){
        val intent = Intent(this,HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}
