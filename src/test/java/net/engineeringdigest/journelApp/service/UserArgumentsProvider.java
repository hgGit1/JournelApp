package net.engineeringdigest.journelApp.service;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import net.engineeringdigest.journelApp.entity.User;

public class UserArgumentsProvider implements ArgumentsProvider {
	
	public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception{
		
		return Stream.of(
				Arguments.of( new User.Builder().userName("shyam").password("shyam").build()),
				Arguments.of( new User.Builder().userName("suraj").password("").build())
				);
		
	}
	


}
