package com.setia.myfootballmatch.playerdetail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.setia.myfootballmatch.R
import com.setia.myfootballmatch.model.Player
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : AppCompatActivity() {

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        setSupportActionBar(htab_toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        player = intent.extras?.get("player") as Player

        title = player.strPlayer ?: ""

        Picasso.get().load(player.strThumb).into(player_iv)
        player_weight_tv.text = player.strWeight ?: ""
        player_height_tv.text = player.strHeight ?: ""
        player_position_tv.text = player.strPosition ?: ""
        player_detail_tv.text = player.strDescriptionEN ?: ""
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
