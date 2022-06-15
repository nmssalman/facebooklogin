package com.nmssalman.elegantmedia.facebook

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.facebook.*

import com.facebook.appevents.AppEventsLogger
import com.facebook.login.widget.LoginButton

import com.facebook.login.LoginResult

import com.facebook.login.LoginManager
import com.google.gson.Gson
import org.json.JSONObject
import java.util.*


class FacebookAuth(private val context: Context, private val activity: Activity, private val fragment: Fragment, private  val loginButton: LoginButton, private val callbackManager: CallbackManager, private val mListener: FacebookLoginAuth) {

    init {
        FacebookSdk.setApplicationId("592805835414366")
        FacebookSdk.setClientToken("b24151e442c5e9d1ec7c9c7f169e2541")
        FacebookSdk.sdkInitialize(context);
        AppEventsLogger.activateApp(activity.application);
        checkAlreadyLogin()
    }
    private fun facebookAPIInit(){
        loginButton.setOnClickListener {
            loginButton.setFragment(fragment)
            LoginManager.getInstance().logInWithReadPermissions(
                fragment,
                Arrays.asList("email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {

                    override fun onCancel() {
                        mListener.onCancel()
                    }

                    override fun onError(exception: FacebookException) {
                        mListener.onError(exception.message.toString())
                    }

                    override fun onSuccess(result: LoginResult) {
                        checkAlreadyLogin()
                    }
                })


        }
        loginButton.visibility = View.VISIBLE
    }
    private fun checkAlreadyLogin(){
        val accessToken = AccessToken.getCurrentAccessToken()
        Log.i("Execution", "${accessToken != null}")
        if(accessToken != null)
            getFacebookData(accessToken)
        else
            facebookAPIInit()
    }
    private fun getFacebookData(accessToken: AccessToken){
        val request = GraphRequest.newMeRequest(accessToken ,object:  GraphRequest.GraphJSONObjectCallback {
            override fun onCompleted(obj: JSONObject?, response: GraphResponse?) {

                Log.i("GraphResponse", obj.toString() + "SSS")
                val user = Gson().fromJson(obj.toString(), FacebookUser::class.java)
                mListener.onSuccess(user)
            }

        })
        val paramerters = Bundle()
        paramerters.putString("fields", "email,name,id")
        request.parameters = paramerters
        request.executeAsync()
    }

    companion object{
        fun Logout(mListeer: FacebookLogoutAuth){
            LoginManager.getInstance().logOut()
            mListeer.onLogout()
        }
    }

    interface FacebookLoginAuth{
        fun onSuccess(user: FacebookUser)
        fun onError(message: String)
        fun onCancel()
    }
    interface FacebookLogoutAuth{
        fun onLogout()
    }
}