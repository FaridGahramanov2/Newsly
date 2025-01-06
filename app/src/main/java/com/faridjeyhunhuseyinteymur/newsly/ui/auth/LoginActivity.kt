package com.faridjeyhunhuseyinteymur.newsly.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.faridjeyhunhuseyinteymur.newsly.databinding.ActivityLoginBinding
import com.faridjeyhunhuseyinteymur.newsly.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Add forgot password TextView click listener
        binding.forgotPasswordTextView.setOnClickListener {
            showForgotPasswordDialog()
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                // Show error message with forgot password suggestion
                showLoginErrorDialog(task.exception?.message ?: "Login failed")
            }
        }
    }

    private fun showLoginErrorDialog(errorMessage: String) {
        AlertDialog.Builder(this)
            .setTitle("Login Failed")
            .setMessage("$errorMessage\n\nDid you forget your password?")
            .setPositiveButton("Reset Password") { _, _ ->
                showForgotPasswordDialog()
            }
            .setNegativeButton("Try Again", null)
            .show()
    }

    private fun showForgotPasswordDialog() {
        val email = binding.emailEditText.text.toString()

        AlertDialog.Builder(this)
            .setTitle("Reset Password")
            .setMessage("Enter your email address to receive a password reset link")
            .setView(layoutInflater.inflate(com.faridjeyhunhuseyinteymur.newsly.R.layout.dialog_forgot_password, null))
            .setPositiveButton("Send") { dialog, _ ->
                val emailInput = (dialog as AlertDialog)
                    .findViewById<android.widget.EditText>(com.faridjeyhunhuseyinteymur.newsly.R.id.emailEditText)
                val emailAddress = emailInput?.text?.toString() ?: email

                if (emailAddress.isNotEmpty()) {
                    sendPasswordResetEmail(emailAddress)
                } else {
                    Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun sendPasswordResetEmail(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Password reset link sent to your email",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "Failed to send reset link: ${task.exception?.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
    }
}