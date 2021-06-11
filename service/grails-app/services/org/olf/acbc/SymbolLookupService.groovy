package org.olf.acbc

class SymbolLookupService {

  def getAvailabilityService(String symbol) {
    def serviceMap = [
      id: [type: 'sru', url:'https://www.indexdata.com'],
      // kint: [type: 'sru', url:'https://www.indexdata.com'],
      kint: [type: 'sru', url:'https://www.k-int.com'],
      kware: [type: 'sru', url:'http://kwareict.com']
    ]
    return serviceMap[symbol]
  }

}
