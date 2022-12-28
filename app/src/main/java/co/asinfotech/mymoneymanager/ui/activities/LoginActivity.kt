package co.asinfotech.mymoneymanager.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import co.asinfotech.mymoneymanager.config.Config
import co.asinfotech.mymoneymanager.databinding.ActivityLoginBinding
import co.asinfotech.mymoneymanager.extensions.showToast
import co.asinfotech.mymoneymanager.utils.Tracer
import co.asinfotech.mymoneymanager.utils.utils
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private val TAG = LoginActivity::class.simpleName + Config.LOGGER_TAG
    private val REQ_ONE_TAP = 2
    private var showOneTapUI = true
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpLogin()
        setupUI()
    }

    private fun setUpLogin() {
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Config.CLIENT_ID_2)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun setupUI() {
        binding.LAGoogleSignIn.setOnClickListener {
            signIn()
        }
        binding.LAbtnSignIn.setOnClickListener {
            validateCredentials()
        }
    }

    private fun validateCredentials() {
        utils.hideKeyboard(this)
        Tracer.debug(TAG, " validateCredentials ")
        val email = binding.LAUsername.editText?.text
        val password = binding.LApassword.editText?.text
        Tracer.debug(TAG, " validateCredentials $email")
        Tracer.debug(TAG, " validateCredentials $password")
        if (utils.isEmpty(email)) {
            showToast("Please enter email")
            return
        }
        if (utils.isEmpty(password)) {
            showToast("Please enter password")
            return
        }
        signInWithUsernamePassword(email.toString(), password.toString())
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToNextActivity()
        }

    }

    private fun navigateToNextActivity() {
        startActivity(Intent(this@LoginActivity, LoginActivity::class.java))

    }

    override fun onResume() {
        super.onResume()
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, REQ_ONE_TAP)

    }

    private fun checkCurrentUser() {
        val user = Firebase.auth.currentUser
        if (user != null) {
            // User is signed in
        } else {
            // No user is signed in
        }
    }

    private fun getUserProfile() {
        val user = Firebase.auth.currentUser
        user?.let {
            // Name, email address, and profile photo Url
            val name = user.displayName
            val email = user.email
            val photoUrl = user.photoUrl
            val emailVerified = user.isEmailVerified

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            val uid = user.uid
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_ONE_TAP) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Tracer.debug(TAG, "onActivityResult firebaseAuthWithGoogle: ${account.id}")
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Tracer.debug(TAG, "onActivityResult : Google sign in failed $e")
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Tracer.debug(TAG, "firebaseAuthWithGoogle:success")
                    val user = auth.currentUser
                    showToast("User signed in ${user?.displayName}")
                } else {
                    Tracer.debug(TAG, "firebaseAuthWithGoogle:failure ${task.exception}")
                }
            }
    }

    private fun toggleButtonVisibility(visible : Boolean){
        binding.LApb.visibility = if(!visible) View.VISIBLE else View.GONE
        binding.LAbtnSignIn.visibility =  if(visible) View.VISIBLE else View.GONE
    }

    private fun signInWithUsernamePassword(email: String, password: String) {
        toggleButtonVisibility(false)
        Tracer.debug(TAG, " signInWithUsernamePassword ")
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                toggleButtonVisibility(true)
                if (task.isSuccessful) {
                    Tracer.debug(TAG, " signInWithUsernamePassword:success ")
                    val user = auth.currentUser
                    navigateToNextActivity()
                } else {
                    Tracer.debug(TAG, " signInWithUsernamePassword:failure ${task.exception}")
                    showToast("Authentication failed!")
                }
            }
    }


}