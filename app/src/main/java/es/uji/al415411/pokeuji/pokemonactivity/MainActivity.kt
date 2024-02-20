package es.uji.al415411.pokeuji.pokemonactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.uji.al415411.pokeuji.R
import es.uji.al415411.pokeuji.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.activity.viewModels
import es.uji.al415411.pokeuji.networkclasses.Pokemon

class MainActivity : AppCompatActivity(), PokemonInterface {
    lateinit var binding: ActivityMainBinding
    val viewModel: PokemonViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SearchButton.setOnClickListener {
            viewModel.onPokemonSearchRequested(binding.SearchBar.text.toString())
        }

    }

    override fun onResume(){
        super.onResume()
        viewModel.view = this
    }

    override fun onPause(){
        super.onPause()
        viewModel.view = null
    }

    override fun showPokemonData(pokemon: Pokemon) {
        pokemon.let {
            with (binding) {
                PokeName.text = it.name
                WeightData.text = (it.weight / 10f).toString()+" kg"
                HeightData.text = (it.height / 10f).toString()+" m"
            }
        }
    }
    override fun showSearchError(error: Throwable) {
        Toast.makeText(this, error.message ?: "Unknown error", Toast.LENGTH_LONG).show()
    }
    /*
    override fun showImage(pokemon: Pokemon) {

    }
    */
}