package es.uji.al415411.pokeuji.evolutionactivity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.uji.al415411.pokeuji.databinding.ActivityEvolutionBinding
import es.uji.al415411.pokeuji.databinding.ActivitySpeciesBinding
import es.uji.al415411.pokeuji.networkclasses.Evolution
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

    override fun cambiaTitulo(evolution: Evolution) {
        evolution.let{
            with (binding) {
                textView.text = it.chain.species.name
            }
        }
    }
}