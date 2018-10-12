package com.fearaujo.junofq.oauth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fearaujo.junofq.AppApplication
import com.fearaujo.junofq.BuildConfig
import com.fearaujo.junofq.dashboard.DashboardActivity
import com.foursquare.android.nativeoauth.FoursquareOAuth
import org.jetbrains.anko.intentFor

const val REQUEST_CODE_FSQ_CONNECT = 921
const val REQUEST_CODE_FSQ_TOKEN_EXCHANGE = 922
const val CLIENT_ID = BuildConfig.API_KEY
const val CLIENT_SECRET = BuildConfig.SECRET_KEY

class OAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = FoursquareOAuth.getConnectIntent(this, CLIENT_ID)
        startActivityForResult(intent, REQUEST_CODE_FSQ_CONNECT)

        // Just to test, not necessary open foresquore auth
        //openDirectWithOauthToken("")
    }

    private fun openDirectWithOauthToken(token: String) {
        (application as AppApplication).initKoinInjection(token)

        startActivity(intentFor<DashboardActivity>())
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_CODE_FSQ_CONNECT -> {
                val codeResponse = FoursquareOAuth.getAuthCodeFromResult(resultCode, data)
                val intent = FoursquareOAuth.getTokenExchangeIntent(this, CLIENT_ID, CLIENT_SECRET, codeResponse.code)
                startActivityForResult(intent, REQUEST_CODE_FSQ_TOKEN_EXCHANGE)
            }

            REQUEST_CODE_FSQ_TOKEN_EXCHANGE -> {
                val tokenResponse = FoursquareOAuth.getTokenFromResult(resultCode, data)

                var token = ""
                tokenResponse.accessToken?.let { token = it }
                (application as AppApplication).initKoinInjection(token)

                startActivity(intentFor<DashboardActivity>())
                finish()
            }

        }
    }

}