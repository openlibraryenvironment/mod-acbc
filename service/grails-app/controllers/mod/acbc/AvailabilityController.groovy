package mod.acbc

import grails.async.PromiseList
import grails.rest.*
import grails.converters.*
import org.olf.acbc.AvailabilityBroadcastService

class AvailabilityController {

  AvailabilityBroadcastService availabilityBroadcastService

	static responseFormats = ['json']

    def getAvailability() {
      log.debug("invoked controller")
      def pending = availabilityBroadcastService.broadcast('someid', 300)
      log.debug("broadcast promise: ${pending.get().toString()}")
      def result = [key: "value"]
      log.debug("controller responding")
      render result as JSON
    }
}
