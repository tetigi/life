import io.dropwizard.Configuration

class LifeConfig : Configuration() {
    lateinit var clientId: String
    lateinit var clientSecret: String
    lateinit var incoming: List<Int>
    lateinit var outgoing: List<Int>
    lateinit var redirectUrl: String
}