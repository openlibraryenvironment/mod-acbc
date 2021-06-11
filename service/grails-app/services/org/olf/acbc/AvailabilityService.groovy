package org.olf.acbc

import static grails.async.Promises.*
import groovyx.net.http.HttpBuilder
import groovyx.net.http.FromServer
import static groovyx.net.http.util.SslUtils.ignoreSslIssues
import org.springframework.cache.annotation.Cacheable

import org.olf.acbc.SymbolLookupService

class AvailabilityService {

  SymbolLookupService symbolLookupService

  @Cacheable(cacheNames='availability', sync=true)
  def getAvailability(String symbol, String id) {
    log.debug("getAvailability(${symbol}, ${id})")
    def srv = symbolLookupService.getAvailabilityService(symbol)
    // log.debug("getAvailability(${symbol}, ${id}) has url ${srv.url}")
    def response = HttpBuilder.configure {
      ignoreSslIssues execution
      request.uri = srv.url
    }.get {
      request.uri.path = '/'
      response.success { FromServer fs, Object body ->
        log.debug("${srv.url} ok...")
        return "${srv.url} ok..."
      }
      response.failure { FromServer fs, Object body ->
        log.debug("${srv.url} not ok...")
        return "${srv.url} not ok..."
      }
    }
    // log.debug(res)
  }

}
