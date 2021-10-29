package com.iago.pokedex.activites

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.iago.pokedex.R
import com.iago.pokedex.adapters.PokemonAdapter
import com.iago.pokedex.constants.PokemonConstants
import com.iago.pokedex.models.PokemonModel
import com.iago.pokedex.services.PokemonListener
import com.iago.pokedex.services.PokemonService
import com.iago.pokedex.services.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(){

    private val remote = RetrofitClient.createService(PokemonService::class.java)
    private val mAdapter: PokemonAdapter = PokemonAdapter()
    private lateinit var mListener: PokemonListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configList()
        getAllPokemons()

    }

    fun configList(){
        listPokemons.layoutManager = LinearLayoutManager(this)
        listPokemons.adapter = mAdapter
        listPokemons.layoutManager = GridLayoutManager(this, 2)

        mListener = object: PokemonListener{
            override fun onClick(pokemon: PokemonModel?, color: String) {
                openCard(pokemon, color)
            }
        }

        mAdapter.attachListener(mListener)
    }

    fun openCard(pokemon: PokemonModel?, color: String){
        if(pokemon!=null){
            val intent = Intent(applicationContext, PokemonActivity::class.java)
            intent.putExtra(PokemonConstants.KEY_POKEMON, pokemon)
            intent.putExtra(PokemonConstants.KEY_COLOR_POKEMON, color)
            startActivity(intent)
        }
    }

    fun getAllPokemons(){
            val call: Call<List<PokemonModel>> = remote.getPokemons()

            call.enqueue(object : Callback<List<PokemonModel>> {
                override fun onResponse(
                    call: Call<List<PokemonModel>>,
                    resp: Response<List<PokemonModel>>
                ) {
                    showPokemons(resp.body())
                }

                override fun onFailure(call: Call<List<PokemonModel>>, t: Throwable) {
                    Toast.makeText(applicationContext, "Can't get all pokemons", Toast.LENGTH_LONG).show()
                }
            })
    }

    fun showPokemons(pokemons: List<PokemonModel>?){
        if(pokemons!=null){
            mAdapter.updateList(pokemons)
            mAdapter.notifyDataSetChanged()
            load.visibility = View.GONE
        }
    }
}