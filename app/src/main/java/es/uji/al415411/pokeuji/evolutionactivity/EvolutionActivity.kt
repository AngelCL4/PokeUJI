package es.uji.al415411.pokeuji.evolutionactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import es.uji.al415411.pokeuji.databinding.ActivityEvolutionBinding
import es.uji.al415411.pokeuji.networkclasses.Chain
import es.uji.al415411.pokeuji.networkclasses.Evolution
import es.uji.al415411.pokeuji.pokemonactivity.MainActivity
import es.uji.al415411.pokeuji.speciesactivity.SpeciesActivity



class EvolutionActivity : AppCompatActivity(), EvolutionInterface {
    lateinit var binding: ActivityEvolutionBinding
    val viewModel: EvolutionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvolutionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getStringExtra(SpeciesActivity.SPECIES_ID)
        if (bundle != null) {
            viewModel.onBeginning(bundle)
        }



    }

    override fun showSearchError(error: Throwable) {
        Toast.makeText(this, error.message ?: "Unknown error", Toast.LENGTH_LONG).show()
    }


    override fun onResume() {
        super.onResume()
        viewModel.view = this
    }

    override fun showEvolution(evol: Evolution){
        binding.recyclerView.let {
            it.adapter = EvolutionAdapter(evol.chain.evolves_to)
            it.layoutManager = LinearLayoutManager(this)
        }

    }

    override fun setListener(pokemonS: Evolution) {
        val recycler = EvolutionAdapter(pokemonS.chain.evolves_to)
        binding?.recyclerView?.adapter = recycler
        recycler.setOnClickListener(object : EvolutionAdapter.OnClickListener {
            override fun onClick(pos: Int, model: Chain?) {
                val intent = Intent(this@EvolutionActivity, MainActivity::class.java)
                intent.putExtra(MainActivity.POKEMON_VAR, model!!.species.name)
                startActivity(intent)
            }
        }
        )
    }
}