package org.olf.acbc

import org.springframework.cache.annotation.Cacheable

class AvailabilityService {

  @Cacheable('availability')
  def getAvailability() {
    log.debug('uncached')
  }
}
