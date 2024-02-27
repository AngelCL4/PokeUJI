package es.uji.al415411.pokeuji.pokemonactivity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import es.uji.al415411.pokeuji.R
import es.uji.al415411.pokeuji.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import es.uji.al415411.pokeuji.networkclasses.Pokemon
import android.app.FragmentManager

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

        binding.AbilityButton.setOnClickListener {
            
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
                SpeciesData.text = it.species.name
            }
        }
    }
    override fun showSearchError(error: Throwable) {
        Toast.makeText(this, error.message ?: "Unknown error", Toast.LENGTH_LONG).show()
    }

    override fun showImage(pokemon: Pokemon) {
        pokemon.let {
            with(binding) {
                Glide.with(this@MainActivity)
                    .load(it.sprites.front_default)
                    .fitCenter()
                    .into(imageView)
            }
        }
    }

    class MostrarDialogoHabilidades: DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return AlertDialog.Builder(requireContext())
                .setTitle("Abilities")
                .setMessage("Mondongo")
                .setPositiveButton("Ok", null)
                .create()
        }


    }
}
