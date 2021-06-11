package org.olf.acbc

import java.util.concurrent.TimeoutException
import java.util.concurrent.TimeUnit
import static grails.async.Promises.*
import grails.async.PromiseMap
import groovyx.net.http.HttpBuilder
import groovyx.net.http.FromServer
import org.springframework.cache.annotation.Cacheable

import org.olf.acbc.AvailabilityService
import org.olf.acbc.SharedIndexService

// This is separate from AvailabilityService so we can take advantage of the
// @Cacheable decorator which is only in effect for calls from outside the
// class: https://www.javaer101.com/en/article/3745070.html
class AvailabilityBroadcastService {

  AvailabilityService availabilityService
  SharedIndexService sharedIndexService

  def broadcast(String siId) {
    log.debug("invoked broadcast")
    def instances = sharedIndexService.getInstances(siId)
    log.debug("got ${instances.size()} instances")

    // Alas, while PromiseMap provides a get() method that takes a timeout, it leaves
    // no way to access a partial result.
    // def pending = new PromiseMap (instances.collectEntries {inst -> [(inst.symbol): task {
    def pending = instances.collectEntries {inst -> [(inst.symbol): task {
        // log.debug(inst.symbol)
        availabilityService.getAvailability(inst.symbol, inst.id)
      }]
    }
    return pending
  }

  // Alas, while PromiseMap provides a get() method that takes a timeout, it leaves
  // no way to access a partial result.
  def broadcast(String siId, long delay) {
    def pending = this.broadcast(siId)
    // log.debug("why tho ${pending.toString()}")
    try {
      def result = new PromiseMap (pending.collectEntries{ent -> [(ent.key): task {
          // log.debug("wtf ${ent.toSting()}")
          // ent.value.get(delay, TimeUnit.MILLISECONDS)
          try {
            ent.value.get(delay, TimeUnit.MILLISECONDS)
          } catch(TimeoutException te) {
            log.debug("sometimeout ${te}")
          }
        // }.onError{err ->
        //   log.debug("swallowing error ${err.toString()}")
        //   null
        }
      ]})
    } catch (e) {}
  }

}
