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
import androidx.recyclerview.widget.LinearLayoutManager
import es.uji.al415411.pokeuji.evolutionactivity.EvolutionActivity
import es.uji.al415411.pokeuji.networkclasses.Info
import es.uji.al415411.pokeuji.networkclasses.Specie
import es.uji.al415411.pokeuji.networkclasses.Sprites
import es.uji.al415411.pokeuji.networkclasses.Variety
import es.uji.al415411.pokeuji.pokemonactivity.MainActivity
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

        binding.button.setOnClickListener{
            viewModel.evolutionSwitch()
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

            binding.recyclerView.let {
                it.adapter = RecyclerAdapter(pokemonS.varieties)
                it.layoutManager = LinearLayoutManager(this)
            }
        }
    }

    override fun showVarietiesData() {

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

    override fun setListener(pokemonS: Specie) {
        val recycler = RecyclerAdapter(pokemonS.varieties)
        binding?.recyclerView?.adapter = recycler
        recycler.setOnClickListener(object : RecyclerAdapter.OnClickListener {
            override fun onClick(pos: Int, model: Variety) {
                val intent = Intent(this@SpeciesActivity, MainActivity::class.java)
                intent.putExtra(MainActivity.POKEMON_VAR, model.pokemon.name)
                startActivity(intent)
            }
        }
        )
    }

    override fun showEvolutionView(species: String) {
        val intent = Intent(this,EvolutionActivity::class.java)
        intent.putExtra(SPECIES_ID, species)
        startActivity(intent)
    }
}