import static ratpack.groovy.Groovy.ratpack

String environmentName = System.getenv('ENVIRONMENT_NAME') ?: 'dev'

Properties applicationProperties = new Properties()
applicationProperties.load(this.class.getResourceAsStream("application-${environmentName}.properties"))

String callbackUrl = applicationProperties.get('callbackUrl')

String renewTokenHtml = this.class.getResourceAsStream('renew-token.html').text
renewTokenHtml = renewTokenHtml.replace('__CALLBACK_URL__', callbackUrl)

ratpack {
    serverConfig {
        port (environmentName == 'dev' ? 40407 : 5050)
    }

    handlers {
        get {
            response.contentType 'text/html'
            render renewTokenHtml
        }
    }
}
