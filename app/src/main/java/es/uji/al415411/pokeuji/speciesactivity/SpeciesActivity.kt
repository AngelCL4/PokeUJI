package es.uji.al415411.pokeuji.speciesactivity

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import es.uji.al415411.pokeuji.R
import es.uji.al415411.pokeuji.databinding.ActivitySpeciesBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.Glide
import es.uji.al415411.pokeuji.networkclasses.Pokemon
import android.app.FragmentManager
import android.content.Intent
import es.uji.al415411.pokeuji.networkclasses.Specie
import es.uji.al415411.pokeuji.networkclasses.Sprites
import es.uji.al415411.pokeuji.pokemonactivity.PokemonViewModel
import es.uji.al415411.pokeuji.speciesactivity.SpeciesActivity

class SpeciesActivity : AppCompatActivity(), SpeciesInterface {
    lateinit var binding: ActivitySpeciesBinding
    val viewModel: SpeciesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpeciesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        val dato = bundle?.getString("Specie")
        with(binding){
            PokeNameS.text = dato
        }
        viewModel.onBeginning(dato.toString())

    }

    override fun showVarietiesData(specie: Specie) {
        println("Hello World")
        specie.let {
            with(binding){
                DescriptionTextS.text = it.varieties[0].variety.name
            }
        }
    }
    override fun showSearchError(error: Throwable) {
        Toast.makeText(this, error.message ?: "Unknown error", Toast.LENGTH_LONG).show()
    }
}