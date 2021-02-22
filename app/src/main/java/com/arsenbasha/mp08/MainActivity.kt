package com.arsenbasha.mp08

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.arsenbasha.mp08.Dao.MarcasDaoDB
import com.arsenbasha.mp08.Dao.UserDB
import com.arsenbasha.mp08.Dao.UserDao
import com.arsenbasha.mp08.Entidad.User
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var userDao: UserDao
    lateinit var user: User
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        userDao = UserDB.getInstance(applicationContext).userDao()
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_user
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        var correo = "leandroparedes0721@gmail.com"
        user = User(
            correo,
            "Leandro", "Paredes", "M08",
            "https://scontent.fbcn4-1.fna.fbcdn.net/v/t1.0-9/124037192_3500826149996552_6475852109853844338_n.jpg?_nc_cat=109&ccb=3&_nc_sid=09cbfe&_nc_ohc=scbbqeWvk_kAX_eVEG9&_nc_ht=scontent.fbcn4-1.fna&oh=2c7b64a86c544c14e6b07da515ab1eea&oe=60552BE2"
        )
        var _usuario = userDao.finUser(correo)
        if (_usuario == null) {
            userDao.insertUser(user)
        } else user = _usuario

        //  nombre.text = user.nombre + " " + user.appelido
        //  correo.text = user.correo

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)


        val perfil = findViewById<ImageView>(R.id.imageViewdrawer)
        var nombre = findViewById<TextView>(R.id.nombresUserdrawer)
        var correo = findViewById<TextView>(R.id.mailUserdrawer)

        correo.text = user.correo
        nombre.text = user.nombre + " " + user.appelido
        Glide.with(this).load(user.logo).placeholder(R.drawable.progressbar)
            .error(R.drawable.error).into(perfil)

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}