package com.helix.atyourservice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.widget.Toast

class SplashActivity : AppCompatActivity() {

var permissionString = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.INTERNET)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if(hasPermissions(this@SplashActivity,*permissionString)){
            ActivityCompat.requestPermissions(this@SplashActivity,permissionString,131)

        }
        else{
            Handler().postDelayed({
                val startAct = Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(startAct)
                this.finish()
            },1000)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            131->{
                if(grantResults.isNotEmpty()&& grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    Handler().postDelayed({
                        val startAct = Intent(this@SplashActivity,MainActivity::class.java)
                        startActivity(startAct)
                        this.finish()
                    },1000)
                }
                else{
                    Toast.makeText(this@SplashActivity,"Grant All Permissions",Toast.LENGTH_SHORT).show()
                    this.finish()
                }

            }
            else->{
                Toast.makeText(this@SplashActivity,"Something went Wrong",Toast.LENGTH_SHORT).show()
                this.finish()
            }
        }
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean{
        var hasAllPermissions = true
        for(permission in permissions){
            var res = context.checkCallingOrSelfPermission(permission)
            if(res!=PackageManager.PERMISSION_GRANTED){
                hasAllPermissions=false
            }
        }
        return hasAllPermissions
    }
}
