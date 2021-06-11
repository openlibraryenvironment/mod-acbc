package mod.acbc

import com.github.benmanes.caffeine.cache.Caffeine
import java.util.concurrent.TimeUnit
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableCaching
public class CachingConfig {

  @Bean
  public CacheManager cacheManager() {
    CaffeineCacheManager cacheManager = new CaffeineCacheManager('availability')
    cacheManager.setCaffeine(caffeineCacheBuilder())
    return cacheManager
  }

  def caffeineCacheBuilder() {
    return Caffeine.newBuilder()
      .expireAfterWrite(5, TimeUnit.SECONDS)
  }
}