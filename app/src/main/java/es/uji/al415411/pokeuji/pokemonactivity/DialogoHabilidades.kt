package es.uji.al415411.pokeuji.pokemonactivity

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels

class DialogoHabilidades: DialogFragment() {
    private val viewModel: PokemonViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val pokemon = viewModel.pokemon

        return AlertDialog.Builder(requireContext()).run{
            setTitle("Habilidades")
            setItems(pokemon?.abilities?.toTypedArray(), null)
            setPositiveButton(android.R.string.ok, null)
            create()
        }
    }

}