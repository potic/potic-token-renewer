import static ratpack.groovy.Groovy.ratpack

String callbackUrl = 'test'

String renewTokenHtml = this.class.getResourceAsStream('renew-token.html').text
renewTokenHtml = renewTokenHtml.replace('__CALLBACK_URL__', callbackUrl)

ratpack {
    serverConfig {
        port (System.getenv('ENVIRONMENT_NAME') in [ 'test', 'prod' ] ? 5050 : 40407)
    }

    handlers {
        get {
            render renewTokenHtml
        }
    }
}
