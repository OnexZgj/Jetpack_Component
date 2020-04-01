package com.onexzgj.inspur.pageingsample.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import java.lang.Boolean
import java.util.concurrent.Executors


/**
 * des：Database
 * author：onexzgj
 * time：2020/3/18
 */
@Database(entities = [Cheese::class, User::class], version = 2, exportSchema = true)
abstract class CheeseDb : RoomDatabase() {

    abstract fun cheeseDao(): CheeseDao
    abstract fun userDao(): UserDao
    abstract fun cheeseAndUserDao(): CheeseAndUserDao
    abstract fun cheeseAndUsersDao(): CheeseAndUsersDao

    companion object {
        private var instance: CheeseDb? = null

        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN birthday INTEGER NOT NULL DEFAULT 23 ")
//                database.execSQL(
//                    "CREATE TABLE IF NOT EXISTS `XXX` (`uid` INTEGER PRIMARY KEY autoincrement, `name` TEXT , `userId` INTEGER, 'time' INTEGER)"
//                )
            }
        }
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS `book` (`uid` INTEGER PRIMARY KEY autoincrement, `name` TEXT , `userId` INTEGER, 'time' INTEGER)"
                )
            }
        }

        val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                val sql =
                    "insert into  book (uid,name,userId,time) values (?,?,?,?)"
                database.execSQL(
                    sql,
                    arrayOf(10, "onexzgj", 3, 30)
                )
            }
        }

        fun get(context: Context): CheeseDb {
            if (instance == null) {
                instance = Room.databaseBuilder(context, CheeseDb::class.java, "onexzgj")
                    .allowMainThreadQueries()
//                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4)
                    .addMigrations(MIGRATION_1_2)
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Log.d("paging", "数据库创建了")

                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            initData(
                                context
                            )

                        }
                    })
                    .build()

            }
            return instance!!
        }

        /**
         * 填充基本数据的使用
         */
        private fun initData(context: Context) {
            Executors.newSingleThreadExecutor().execute {

//                get(context).userDao().insert(USER_DATA.map {
//                    User(
//                        user_id = 0,
//                        name = it,
//                        temp_id = 1,
//                        age = 25,
//                        sex = 0
//                    )
//                })

                get(context).cheeseDao().insert(CHEESE_DATA.map {
                    Cheese(
                        id = 0,
                        name = it
                    )
                })

            }
        }
    }
}


private val USER_DATA = arrayListOf(
    "Waterloo", "Weichkaese", "Wellington",
    "Wensleydale", "White Stilton", "Whitestone Farmhouse"
)


private val CHEESE_DATA = arrayListOf(
    "Abbaye de Belloc", "Abbaye du Mont des Cats",
    "Abertam", "Abondance", "Ackawi", "Berkswell"

)

/**
"Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
"Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
"Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
"Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)",
"Boeren Leidenkaas", "Bonchester", "Bosworth", "Bougon", "Boule Du Roves",
"Boulette d'Avesnes", "Boursault", "Boursin", "Bouyssou", "Bra", "Braudostur",
"Breakfast Cheese", "Brebis du Lavort", "Brebis du Lochois", "Brebis du Puyfaucon",
"Bresse Bleu", "Brick", "Brie", "Brie de Meaux", "Brie de Melun", "Brillat-Savarin",
"Brin", "Brin d' Amour", "Brin d'Amour", "Brinza (Burduf Brinza)",
"Briquette de Brebis", "Briquette du Forez", "Broccio", "Broccio Demi-Affine",
"Brousse du Rove", "Bruder Basil", "Brusselae Kaas (Fromage de Bruxelles)", "Bryndza",
"Buchette d'Anjou", "Buffalo", "Burgos", "Butte", "Butterkase", "Button (Innes)",
"Buxton Blue", "Cabecou", "Caboc", "Cabrales", "Cachaille", "Caciocavallo", "Caciotta",
"Caerphilly", "Cairnsmore", "Calenzana", "Cambazola", "Camembert de Normandie",
"Canadian Cheddar", "Canestrato", "Cantal", "Caprice des Dieux", "Capricorn Goat",
"Capriole Banon", "Carre de l'Est", "Casciotta di Urbino", "Cashel Blue", "Castellano",
"Castelleno", "Castelmagno", "Castelo Branco", "Castigliano", "Cathelain",
"Celtic Promise", "Cendre d'Olivet", "Cerney", "Chabichou", "Chabichou du Poitou",
"Chabis de Gatine", "Chaource", "Charolais", "Chaumes", "Cheddar",
"Cheddar Clothbound", "Cheshire", "Chevres", "Chevrotin des Aravis", "Chontaleno",
"Civray", "Coeur de Camembert au Calvados", "Coeur de Chevre", "Colby", "Cold Pack",
"Comte", "Coolea", "Cooleney", "Coquetdale", "Corleggy", "Cornish Pepper",
"Cotherstone", "Cotija", "Cottage Cheese", "Cottage Cheese (Australian)",
"Cougar Gold", "Coulommiers", "Coverdale", "Crayeux de Roncq", "Cream Cheese",
"Cream Havarti", "Crema Agria", "Crema Mexicana", "Creme Fraiche", "Crescenza",
"Croghan", "Crottin de Chavignol", "Crottin du Chavignol", "Crowdie", "Crowley",
"Cuajada", "Curd", "Cure Nantais", "Curworthy", "Cwmtawe Pecorino",
"Cypress Grove Chevre", "Danablu (Danish Blue)", "Danbo", "Danish Fontina",
"Daralagjazsky", "Dauphin", "Delice des Fiouves", "Denhany Dorset Drum", "Derby",
"Dessertnyj Belyj", "Devon Blue", "Devon Garland", "Dolcelatte", "Doolin",
"Doppelrhamstufel", "Dorset Blue Vinney", "Double Gloucester", "Double Worcester",
"Dreux a la Feuille", "Dry Jack", "Duddleswell", "Dunbarra", "Dunlop", "Dunsyre Blue",
"Duroblando", "Durrus", "Dutch Mimolette (Commissiekaas)", "Edam", "Edelpilz",
"Emental Grand Cru", "Emlett", "Emmental", "Epoisses de Bourgogne", "Esbareich",
"Esrom", "Etorki", "Evansdale Farmhouse Brie", "Evora De L'Alentejo", "Exmoor Blue",
"Explorateur", "Feta", "Feta (Australian)", "Figue", "Filetta", "Fin-de-Siecle",
"Finlandia Swiss", "Finn", "Fiore Sardo", "Fleur du Maquis", "Flor de Guia",
"Flower Marie", "Folded", "Folded cheese with mint", "Fondant de Brebis",
"Fontainebleau", "Fontal", "Fontina Val d'Aosta", "Formaggio di capra", "Fougerus",
"Four Herb Gouda", "Fourme d' Ambert", "Fourme de Haute Loire", "Fourme de Montbrison",
"Fresh Jack", "Fresh Mozzarella", "Fresh Ricotta", "Fresh Truffles", "Fribourgeois",
"Friesekaas", "Friesian", "Friesla", "Frinault", "Fromage a Raclette", "Fromage Corse",
"Fromage de Montagne de Savoie", "Fromage Frais", "Fruit Cream Cheese",
"Frying Cheese", "Fynbo", "Gabriel", "Galette du Paludier", "Galette Lyonnaise",
"Galloway Goat's Milk Gems", "Gammelost", "Gaperon a l'Ail", "Garrotxa", "Gastanberra",
"Geitost", "Gippsland Blue", "Gjetost", "Gloucester", "Golden Cross", "Gorgonzola",
"Gornyaltajski", "Gospel Green", "Gouda", "Goutu", "Gowrie", "Grabetto", "Graddost",
"Grafton Village Cheddar", "Grana", "Grana Padano", "Grand Vatel",
"Grataron d' Areches", "Gratte-Paille", "Graviera", "Greuilh", "Greve",
"Gris de Lille", "Gruyere", "Gubbeen", "Guerbigny", "Halloumi",
"Halloumy (Australian)", "Haloumi-Style Cheese", "Harbourne Blue", "Havarti",
"Heidi Gruyere", "Hereford Hop", "Herrgardsost", "Herriot Farmhouse", "Herve",
"Hipi Iti", "Hubbardston Blue Cow", "Hushallsost", "Iberico", "Idaho Goatster",
"Idiazabal", "Il Boschetto al Tartufo", "Ile d'Yeu", "Isle of Mull", "Jarlsberg",
"Jermi Tortes", "Jibneh Arabieh", "Jindi Brie", "Jubilee Blue", "Juustoleipa",
"Kadchgall", "Kaseri", "Kashta", "Kefalotyri", "Kenafa", "Kernhem", "Kervella Affine",
"Kikorangi", "King Island Cape Wickham Brie", "King River Gold", "Klosterkaese",
"Knockalara", "Kugelkase", "L'Aveyronnais", "L'Ecir de l'Aubrac", "La Taupiniere",
"La Vache Qui Rit", "Laguiole", "Lairobell", "Lajta", "Lanark Blue", "Lancashire",
"Langres", "Lappi", "Laruns", "Lavistown", "Le Brin", "Le Fium Orbo", "Le Lacandou",
"Le Roule", "Leafield", "Lebbene", "Leerdammer", "Leicester", "Leyden", "Limburger",
"Lincolnshire Poacher", "Lingot Saint Bousquet d'Orb", "Liptauer", "Little Rydings",
"Livarot", "Llanboidy", "Llanglofan Farmhouse", "Loch Arthur Farmhouse",
"Loddiswell Avondale", "Longhorn", "Lou Palou", "Lou Pevre", "Lyonnais", "Maasdam",
"Macconais", "Mahoe Aged Gouda", "Mahon", "Malvern", "Mamirolle", "Manchego",
"Manouri", "Manur", "Marble Cheddar", "Marbled Cheeses", "Maredsous", "Margotin",
"Maribo", "Maroilles", "Mascares", "Mascarpone", "Mascarpone (Australian)",
"Mascarpone Torta", "Matocq", "Maytag Blue", "Meira", "Menallack Farmhouse",
"Menonita", "Meredith Blue", "Mesost", "Metton (Cancoillotte)", "Meyer Vintage Gouda",
"Mihalic Peynir", "Milleens", "Mimolette", "Mine-Gabhar", "Mini Baby Bells", "Mixte",
"Molbo", "Monastery Cheeses", "Mondseer", "Mont D'or Lyonnais", "Montasio",
"Monterey Jack", "Monterey Jack Dry", "Morbier", "Morbier Cru de Montagne",
"Mothais a la Feuille", "Mozzarella", "Mozzarella (Australian)",
"Mozzarella di Bufala", "Mozzarella Fresh, in water", "Mozzarella Rolls", "Munster",
"Murol", "Mycella", "Myzithra", "Naboulsi", "Nantais", "Neufchatel",
"Neufchatel (Australian)", "Niolo", "Nokkelost", "Northumberland", "Oaxaca",
"Olde York", "Olivet au Foin", "Olivet Bleu", "Olivet Cendre",
"Orkney Extra Mature Cheddar", "Orla", "Oschtjepka", "Ossau Fermier", "Ossau-Iraty",
"Oszczypek", "Oxford Blue", "P'tit Berrichon", "Palet de Babligny", "Paneer", "Panela",
"Pannerone", "Pant ys Gawn", "Parmesan (Parmigiano)", "Parmigiano Reggiano",
"Pas de l'Escalette", "Passendale", "Pasteurized Processed", "Pate de Fromage",
"Patefine Fort", "Pave d'Affinois", "Pave d'Auge", "Pave de Chirac", "Pave du Berry",
"Pecorino", "Pecorino in Walnut Leaves", "Pecorino Romano", "Peekskill Pyramid",
"Pelardon des Cevennes", "Pelardon des Corbieres", "Penamellera", "Penbryn",
"Pencarreg", "Perail de Brebis", "Petit Morin", "Petit Pardou", "Petit-Suisse",
"Picodon de Chevre", "Picos de Europa", "Piora", "Pithtviers au Foin",
"Plateau de Herve", "Plymouth Cheese", "Podhalanski", "Poivre d'Ane", "Polkolbin",
"Pont l'Eveque", "Port Nicholson", "Port-Salut", "Postel", "Pouligny-Saint-Pierre",
"Pourly", "Prastost", "Pressato", "Prince-Jean", "Processed Cheddar", "Provolone",
"Provolone (Australian)", "Pyengana Cheddar", "Pyramide", "Quark",
"Quark (Australian)", "Quartirolo Lombardo", "Quatre-Vents", "Quercy Petit",
"Queso Blanco", "Queso Blanco con Frutas --Pina y Mango", "Queso de Murcia",
"Queso del Montsec", "Queso del Tietar", "Queso Fresco", "Queso Fresco (Adobera)",
"Queso Iberico", "Queso Jalapeno", "Queso Majorero", "Queso Media Luna",
"Queso Para Frier", "Queso Quesadilla", "Rabacal", "Raclette", "Ragusano", "Raschera",
"Reblochon", "Red Leicester", "Regal de la Dombes", "Reggianito", "Remedou",
"Requeson", "Richelieu", "Ricotta", "Ricotta (Australian)", "Ricotta Salata", "Ridder",
"Rigotte", "Rocamadour", "Rollot", "Romano", "Romans Part Dieu", "Roncal", "Roquefort",
"Roule", "Rouleau De Beaulieu", "Royalp Tilsit", "Rubens", "Rustinu", "Saaland Pfarr",
"Saanenkaese", "Saga", "Sage Derby", "Sainte Maure", "Saint-Marcellin",
"Saint-Nectaire", "Saint-Paulin", "Salers", "Samso", "San Simon", "Sancerre",
"Sap Sago", "Sardo", "Sardo Egyptian", "Sbrinz", "Scamorza", "Schabzieger", "Schloss",
"Selles sur Cher", "Selva", "Serat", "Seriously Strong Cheddar", "Serra da Estrela",
"Sharpam", "Shelburne Cheddar", "Shropshire Blue", "Siraz", "Sirene", "Smoked Gouda",
"Somerset Brie", "Sonoma Jack", "Sottocenare al Tartufo", "Soumaintrain",
"Sourire Lozerien", "Spenwood", "Sraffordshire Organic", "St. Agur Blue Cheese",
"Stilton", "Stinking Bishop", "String", "Sussex Slipcote", "Sveciaost", "Swaledale",
"Sweet Style Swiss", "Swiss", "Syrian (Armenian String)", "Tala", "Taleggio", "Tamie",
"Tasmania Highland Chevre Log", "Taupiniere", "Teifi", "Telemea", "Testouri",
"Tete de Moine", "Tetilla", "Texas Goat Cheese", "Tibet", "Tillamook Cheddar",
"Tilsit", "Timboon Brie", "Toma", "Tomme Brulee", "Tomme d'Abondance",
"Tomme de Chevre", "Tomme de Romans", "Tomme de Savoie", "Tomme des Chouans", "Tommes",
"Torta del Casar", "Toscanello", "Touree de L'Aubier", "Tourmalet",
"Trappe (Veritable)", "Trois Cornes De Vendee", "Tronchon", "Trou du Cru", "Truffe",
"Tupi", "Turunmaa", "Tymsboro", "Tyn Grug", "Tyning", "Ubriaco", "Ulloa",
"Vacherin-Fribourgeois", "Valencay", "Vasterbottenost", "Venaco", "Vendomois",
"Vieux Corse", "Vignotte", "Vulscombe", "Waimata Farmhouse Blue",
"Washed Rind Cheese (Australian)", "Waterloo", "Weichkaese", "Wellington",
"Wensleydale", "White Stilton", "Whitestone Farmhouse", "Wigmore", "Woodside Cabecou",
"Xanadu", "Xynotyro", "Yarg Cornish", "Yarra Valley Pyramid", "Yorkshire Blue",
"Zamorano", "Zanetti Grana Padano", "Zanetti Parmigiano Reggiano"
 */