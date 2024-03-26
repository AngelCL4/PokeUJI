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
import android.content.Intent
import es.uji.al415411.pokeuji.networkclasses.Sprites
import es.uji.al415411.pokeuji.speciesactivity.SpeciesActivity


class MainActivity : AppCompatActivity(), PokemonInterface {
    lateinit var binding: ActivityMainBinding
    val viewModel: PokemonViewModel by viewModels()
    var listab: MutableList<String> = mutableListOf()
    var listat: MutableList<String> = mutableListOf()
    var listaSprites: Array<String?> = arrayOf()
    var spec: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.SearchButton.setOnClickListener {
            listab.clear()
            listat.clear()
            viewModel.onPokemonSearchRequested(binding.SearchBar.text.toString())
        }

        binding.AbilityButton.setOnClickListener {
            MostrarDialogoHabilidades(listab)
        }
        binding.TypeButton.setOnClickListener {
            MostrarDialogoTypes(listat)
        }
        binding.imageView.setOnClickListener{
            MostrarDialogoSprites(listaSprites)
        }

        binding.SpeciesData.setOnClickListener{
            listab.clear()
            listat.clear()
            val intent = Intent(this,SpeciesActivity::class.java)
                intent.putExtra( "Specie", spec)
                startActivity(intent)
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
                spec = it.species.name
                var i = 0
                var j = 0
                while(i < it.abilities.size){
                    listab.add(it.abilities[i].ability.name)
                    i++
                }
                while(j < it.types.size){
                    listat.add(it.types[j].type.name)
                    j++
                }
                listaSprites = arrayOf(it.sprites.back_default,it.sprites.back_female,it.sprites.back_shiny,it.sprites.back_shiny_female,it.sprites.front_default,it.sprites.front_female,it.sprites.front_shiny,it.sprites.front_shiny_female)
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

    fun MostrarDialogoHabilidades(habilidad: List<String>) {
        val alertaDialogo = AlertDialog.Builder(this)
        alertaDialogo.apply{
            setTitle("Habilidades")
            var i = 0
            var habilidades: String = ""
            while(i < habilidad.size){
                if (i == habilidad.size-1){
                    habilidades += habilidad[i]
                }
                else{
                    habilidades += habilidad[i] + "\n\n"
                }
                ++i
            }
            setMessage(habilidades)
            setPositiveButton(android.R.string.ok, null)
        }.create().show()
    }
    fun MostrarDialogoTypes(tipo: List<String>) {
        val alertaDialogo = AlertDialog.Builder(this)
        alertaDialogo.apply{
            setTitle("Tipos")
            var i = 0
            var tipos: String = ""
            while(i < tipo.size){
                if (i == tipo.size-1){
                    tipos += tipo[i]
                }
                else{
                    tipos += tipo[i] + "\n\n"
                }
                ++i
            }
            setMessage(tipos)
            setPositiveButton(android.R.string.ok, null)
        }.create().show()
    }

    fun MostrarDialogoSprites(imagenes: Array<String?>){
        val alertaDialogo = AlertDialog.Builder(this)
        alertaDialogo.apply{
            setTitle("Select a sprite")
            setItems(arrayOf("back_default", "back_female","back_shiny","back_shiny_female","front_default","front_female","front_shiny","front_shiny_female")){ _, pos ->
                when(pos){
                    0 ->{
                        CambiaSprite(imagenes[0])
                    }
                    1 ->{
                        CambiaSprite(imagenes[1])
                    }
                    2 ->{
                        CambiaSprite(imagenes[2])
                    }
                    3 ->{
                        CambiaSprite(imagenes[3])
                    }
                    4 ->{
                        CambiaSprite(imagenes[4])
                    }
                    5 ->{
                        CambiaSprite(imagenes[5])
                    }
                    6 ->{
                        CambiaSprite(imagenes[6])
                    }
                    7 ->{
                        CambiaSprite(imagenes[7])
                    }
                }
            }
            setPositiveButton(android.R.string.ok, null)
        }.create().show()
    }
    fun CambiaSprite(imagen: String?){
        if(imagen != null) {
            with(binding) {
                Glide.with(this@MainActivity)
                    .load(imagen)
                    .fitCenter()
                    .into(imageView)
            }
        }
    }
}

