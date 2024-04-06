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
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
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

        val bundle = intent.getStringExtra(SPECIES_ID)
        if (bundle != null) {
            viewModel.onBeginning(bundle)
            with(binding) {
                PokeNameS.text = bundle
            }
        }

    }

    override fun showVersion(description: String)
    {
        binding.DescriptionTextS.text = description
    }

    override fun showSpeciesInfo(pokemonS: Specie, numVersion: Int) {
        pokemonS.let {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, pokemonS.version)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            with(binding.spinner){
                this.adapter = adapter
                onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewModel.changeVersion(position)
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
                setSelection(numVersion)
            }
            with(binding) {
                spinner.adapter=adapter
            }
        }
    }

    override fun showVarietiesData(specie: Specie) {
        println("Hello World")
        specie.let {
            with(binding){
                //DescriptionTextS.text = it.varieties[0].variety.name
            }
        }
    }
    override fun showSearchError(error: Throwable) {
        Toast.makeText(this, error.message ?: "Unknown error", Toast.LENGTH_LONG).show()
    }

    companion object{
        const val SPECIES_ID = "SPECIES_ID"
    }

    override fun onResume() {
        super.onResume()
        viewModel.view = this
    }
}