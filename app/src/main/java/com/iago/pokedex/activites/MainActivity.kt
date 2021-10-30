package com.iago.pokedex.activites

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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
import kotlin.collections.filter as filter1


class MainActivity : AppCompatActivity(){

    private val remote = RetrofitClient.createService(PokemonService::class.java)
    private val mAdapter: PokemonAdapter = PokemonAdapter()
    private lateinit var mListener: PokemonListener
    private  var mListPokemons: List<PokemonModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configList()
        configSearch()
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

    fun configSearch(){
        search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var searchPokemons : List<PokemonModel> = mListPokemons.filter1 {
                        s2 -> if(s2.name.isNullOrBlank()) false else s2.name!!.contains(s, true)
                }
               if(searchPokemons.isEmpty()) notFound.text=getString(R.string.not_found) else notFound.text=""
                upadteList(searchPokemons)
            }
        })
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
                    if(resp.body()!=null){
                        mListPokemons= resp.body()!!
                        showPokemons()
                    }
                }

                override fun onFailure(call: Call<List<PokemonModel>>, t: Throwable) {
                    alert.text = getString(R.string.erro_request)
                    load.visibility = View.GONE
                }
            })
    }

    fun showPokemons(){
            upadteList(mListPokemons!!)
            load.visibility = View.GONE
    }

    private fun upadteList(pokemons: List<PokemonModel>) {
        mAdapter.updateList(pokemons)
        mAdapter.notifyDataSetChanged()
    }
}

