package br.com.messore.tech.pokedex.domain.model

@Suppress("MagicNumber")
enum class PokemonType(val id: Int) {
    NORMAL(1),
    FIGHTING(2),
    FLYING(3),
    POISON(4),
    GROUND(5),
    ROCK(6),
    BUG(7),
    GHOST(8),
    STEEL(9),
    FIRE(10),
    WATER(11),
    GRASS(12),
    ELECTRIC(13),
    PSYCHIC(14),
    ICE(15),
    DRAGON(16),
    DARK(17),
    FAIRY(18);

    companion object {
        fun byId(id: Int) = values().first { it.id == id }
    }
}
