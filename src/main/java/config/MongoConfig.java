package config;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;


@ApplicationScoped
public class MongoConfig {

	@Inject
	@ConfigProperty(name = "mongo.uri")
	private String mongoUri;
	
	@Inject
	@ConfigProperty(name = "mongo.database")
	private String databaseName;
	
	@Produces
	@ApplicationScoped
	public MongoClient mongoClient() {
		CodecRegistry pojoRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new com.mongodb.ConnectionString(mongoUri))
                .codecRegistry(pojoRegistry)
                .build();

        return MongoClients.create(settings);
	}
	
	@Produces
	@ApplicationScoped
	public MongoDatabase mongoDatabase(MongoClient client) {
		return client.getDatabase(databaseName);
	}
	
	public void close(@Disposes MongoClient client) {
		client.close();
	}
}
