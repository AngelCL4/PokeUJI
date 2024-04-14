package es.uji.al415411.pokeuji.pokemonactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.uji.al415411.pokeuji.databinding.ActivityMainBinding
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import es.uji.al415411.pokeuji.models.Pokemon
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import es.uji.al415411.pokeuji.models.Info
import es.uji.al415411.pokeuji.speciesactivity.SpeciesActivity


class MainActivity : AppCompatActivity(), PokemonInterface {
    lateinit var binding: ActivityMainBinding
    val viewModel: PokemonViewModel by viewModels()

    var listaSprites: Array<String?> = arrayOf()
    var spec: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getStringExtra(MainActivity.POKEMON_VAR)
        if (bundle != null) {
            viewModel.onPokemonSearchRequested(bundle)
        }

        binding.SearchButton.setOnClickListener {
            changeSearchColor(binding.SearchBar)
            viewModel.onPokemonSearchRequested(binding.SearchBar.text.toString())
        }

        binding.AbilityButton.setOnClickListener {
            viewModel.onAbilityDialog()
        }
        binding.TypeButton.setOnClickListener {
            viewModel.onTypeDialog()
        }
        binding.imageView.setOnClickListener{
            MostrarDialogoSprites(listaSprites)
        }

        binding.SpeciesData.setOnClickListener{
            viewModel.speciesSwitch()
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
                listaSprites = arrayOf(it.sprites.back_default,it.sprites.back_female,it.sprites.back_shiny,it.sprites.back_shiny_female,it.sprites.front_default,it.sprites.front_female,it.sprites.front_shiny,it.sprites.front_shiny_female,
                    it.sprites.other.home.front_default, it.sprites.other.home.front_female, it.sprites.other.home.front_shiny, it.sprites.other.home.front_shiny_female,
                    it.sprites.other.officialartwork.front_default, it.sprites.other.officialartwork.front_shiny,
                    it.sprites.other.showdown.back_default, it.sprites.other.showdown.back_female, it.sprites.other.showdown.back_shiny, it.sprites.other.showdown.back_shiny_female, it.sprites.other.showdown.front_default, it.sprites.other.showdown.front_female, it.sprites.other.showdown.front_shiny, it.sprites.other.showdown.front_shiny_female)
            }
        }
    }
    override fun showSearchError(error: Throwable) {
        binding.SearchBar.setTextColor(Color.RED)
        Toast.makeText(this, error.message ?: "Unknown error", Toast.LENGTH_LONG).show()
    }

    fun changeSearchColor(editText: EditText){
        editText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val color = Color.BLACK
                editText.setTextColor(color)
            }
        })
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

    override fun showAbilities() {
        DialogoHabilidades().show(supportFragmentManager, "Habilidades")
    }

    override fun showTypes() {
        DialogoTipos().show(supportFragmentManager, "Tipos")
    }

    fun MostrarDialogoSprites(imagenes: Array<String?>){
        val alertaDialogo = AlertDialog.Builder(this)
        alertaDialogo.apply{
            setTitle("Select a sprite")
            setItems(arrayOf("back default", "back female","back shiny","back shiny female","front default","front female","front shiny","front shiny female",
                "home: front default", "home: front female", "home: front shiny", "home: front shiny female",
                "official-artwork: front default", "official-artwork: front shiny",
                "showdown: back default", "showdown: back female", "showdown: back shiny", "showdown: back shiny female", "showdown: front default", "showdown: front female", "showdown: front shiny", "showdown: front shiny female")){ _, pos ->
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
                    8 ->{
                        CambiaSprite(imagenes[8])
                    }
                    9 ->{
                        CambiaSprite(imagenes[9])
                    }
                    10 ->{
                        CambiaSprite(imagenes[10])
                    }
                    11 ->{
                        CambiaSprite(imagenes[11])
                    }
                    12 ->{
                        CambiaSprite(imagenes[12])
                    }
                    13 ->{
                        CambiaSprite(imagenes[13])
                    }
                    14 ->{
                        CambiaSprite(imagenes[14])
                    }
                    15 ->{
                        CambiaSprite(imagenes[15])
                    }
                    16 ->{
                        CambiaSprite(imagenes[16])
                    }
                    17 ->{
                        CambiaSprite(imagenes[17])
                    }
                    18 ->{
                        CambiaSprite(imagenes[18])
                    }
                    19 ->{
                        CambiaSprite(imagenes[19])
                    }
                    20 ->{
                        CambiaSprite(imagenes[20])
                    }
                    21 ->{
                        CambiaSprite(imagenes[21])
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

    companion object{
        const val POKEMON_VAR = "POKEMON_VAR"
    }

    override fun showSpeciesView(species: Info) {
        val intent = Intent(this,SpeciesActivity::class.java)
        intent.putExtra( SpeciesActivity.SPECIES_ID, species.name)
        startActivity(intent)
    }

}

