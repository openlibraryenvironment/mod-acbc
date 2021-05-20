package mod.acbc

import grails.rest.*
import grails.converters.*
import org.yaz4j.Connection
import org.yaz4j.PrefixQuery
import org.yaz4j.Record
import org.yaz4j.ResultSet
import org.yaz4j.exception.ZoomException
import org.olf.acbc.AvailabilityService

class AvailabilityController {
  AvailabilityService availabilityService
    
	static responseFormats = ['json', 'xml']
	
    def getAvailability() {
      log.debug("invoked controller")
      availabilityService.getAvailability()
      availabilityService.getAvailability()
      // // Connection con = new Connection("lx2.loc.gov/LCDB", 0)
      // Connection con = new Connection("http://z3950.indexdata.com:210/gils", 0)
      // // con.setSyntax("usmarc")
      // con.connect()
      // // ResultSet set = con.search(new PrefixQuery("@attr 1=7 0253333490"))
      // ResultSet set = con.search(new PrefixQuery("mineral"))
      // Record rec = set.getRecord(0)
      // log.debug(rec.render())
      // con.close()
      def result = [key: "value"]
      render result as JSON
    }
}
