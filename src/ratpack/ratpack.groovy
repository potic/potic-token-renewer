import static ratpack.groovy.Groovy.ratpack

String environmentName = System.getenv('ENVIRONMENT_NAME') ?: 'dev'

Properties applicationProperties = new Properties()
applicationProperties.load(this.class.getResourceAsStream("application-${environmentName}.properties"))

String auth0Domain = applicationProperties.get('auth0Domain')
String auth0ClientId = applicationProperties.get('auth0ClientId')
String auth0Scope = applicationProperties.get('auth0Scope')
String auth0Response = applicationProperties.get('auth0Response')
String callbackUrl = applicationProperties.get('callbackUrl')

String renewTokenHtml = this.class.getResourceAsStream('renew-token.html').text
renewTokenHtml = renewTokenHtml.replace('__AUTH0_DOMAIN__', auth0Domain)
renewTokenHtml = renewTokenHtml.replace('__AUTH0_CLIENT_ID__', auth0ClientId)
renewTokenHtml = renewTokenHtml.replace('__AUTH0_SCOPE__', auth0Scope)
renewTokenHtml = renewTokenHtml.replace('__AUTH0_RESPONSE__', auth0Response)
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
