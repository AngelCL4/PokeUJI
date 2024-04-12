package es.uji.al415411.pokeuji.evolutionactivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import es.uji.al415411.pokeuji.databinding.ActivityEvolutionBinding
import es.uji.al415411.pokeuji.databinding.ActivitySpeciesBinding
import es.uji.al415411.pokeuji.speciesactivity.SpeciesActivity


class EvolutionActivity : AppCompatActivity(), EvolutionInterface {
    lateinit var binding: ActivityEvolutionBinding
    val viewModel: EvolutionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEvolutionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getStringExtra()
        if (bundle != null) {
            with(binding) {

            }
        }

    }
}