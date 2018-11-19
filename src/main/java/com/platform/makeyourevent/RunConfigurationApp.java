package com.platform.makeyourevent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
@EnableCaching
/*@EnableWebSecurity
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)*/
public class RunConfigurationApp 
//extends WebSecurityConfigurerAdapter
{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RunConfigurationApp.class, args);
	}
	
	@Bean
	public CacheManager cacheManager()
	{
		GuavaCacheManager cacheManager = new GuavaCacheManager("Mongo-cache");
		return cacheManager;
	}
	
	/*protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
		.antMatchers("/oauth/token").permitAll().and().httpBasic();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().withUser("devesh").password("password").roles("USER");
	}*/

	
	
	
	

	/*@Bean
	public SessionFactory<FTPFile> ftpSessionFactory() {
		DefaultFtpSessionFactory sf = new DefaultFtpSessionFactory();
		sf.setHost("localhost");
		sf.setPort(9000);
		sf.setUsername("foo");
		sf.setPassword("foo");
		return new CachingSessionFactory<FTPFile>(sf);
	}

	@Bean
	public FtpInboundFileSynchronizer ftpInboundFileSynchronizer() {
		FtpInboundFileSynchronizer fileSynchronizer = new FtpInboundFileSynchronizer(
				ftpSessionFactory());
		fileSynchronizer.setDeleteRemoteFiles(false);
		fileSynchronizer.setRemoteDirectory("foo");
		fileSynchronizer.setFilter(new FtpSimplePatternFileListFilter("*.xml"));
		return fileSynchronizer;
	}

	@Beasn
	@InboundChannelAdapter(value = "ftpChannel", poller = @Poller(maxMessagesPerPoll="1000"))
	public MessageSource<File> ftpMessageSource() {
		FtpInboundFileSynchronizingMessageSource source = new FtpInboundFileSynchronizingMessageSource(
				ftpInboundFileSynchronizer());
		source.setLocalDirectory(new File("ftp-inbound"));
		source.setAutoCreateLocalDirectory(true);
		source.setLocalFilter(new AcceptOnceFileListFilter<File>());
		return source;
	}*/

	/*@Bean
	@ServiceActivator(inputChannel = "ftpChannel")
	public MessageHandler handler() {
		return new MessageHandler() {

			@sOverride
			public void handleMessage(Message<?> message)
					throws MessagingException {
				System.out.println(message.getPayload());
			}

		};
	}*/

}
