package nl.vyjy.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebsocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	//Volgens mij zet dit /topic voor de adressen waarnaar berichten vanaf de
    	//server worden gestuurd.
    	config.enableSimpleBroker("/topic");
        //@MessageMapping methodes worden nu bereikt via een omweg via app: dus
        //"/bla" wordt "/app/bla". Als je hierheen bericht, ontvangt de server het
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	//dit gebruik je blijkbaar als websocket het niet doet. Maar ik neem aan
    	//dat dat niet nodig is?
        registry.addEndpoint("/vyjy").withSockJS();
    }

}